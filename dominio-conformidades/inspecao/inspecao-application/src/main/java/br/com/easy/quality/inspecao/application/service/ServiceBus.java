/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.application.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import br.com.easy.quality.inspecao.adapter.event.CommandEvent;
import br.com.easy.quality.inspecao.adapter.event.InternalEvent;
import br.com.easy.quality.inspecao.adapter.event.QueryEvent;
import br.com.easy.quality.inspecao.adapter.event.command.Command;
import br.com.easy.quality.inspecao.adapter.event.command.Handler;
import br.com.easy.quality.inspecao.adapter.event.query.Query;
import br.com.easy.quality.inspecao.adapter.event.query.Resolver;
import br.com.easy.quality.inspecao.application.service.exception.ServiceBusInvalidObjectException;
import br.com.easy.quality.inspecao.dto.InspecaoDTO;
import br.com.easy.quality.inspecao.read.in.query.IdInspecaoQuery;
import br.com.easy.quality.inspecao.read.in.query.ListAllInspecaoQuery;
import br.com.easy.quality.inspecao.write.in.commad.CreateInspecaoCommand;

@Component
public class ServiceBus {

	private ApplicationContext context;
	private ApplicationEventPublisher publisher;

	// private TransactionalEventPublisher transactionalEventPublisher;

	public ServiceBus(ApplicationContext context, ApplicationEventPublisher publisher) {
		this.context = context;
		this.publisher = publisher;
	}

	public void execute(Query query) {
		var event = new QueryEvent(query);
		execute(event);
	}

	public void execute(Command command) {
		var event = new CommandEvent(command);
		execute(event);
	}

	private void execute(InternalEvent event) {

		try {
			run(event);
		} catch (Exception exception) {
			event.setException(exception);
			throw exception;
		} finally {
			event.stopTimer();
			publisher.publishEvent(event);
		}
	}

	private void run(InternalEvent event) {

		var beanName = event.getOrigin().substring(0, 1).toLowerCase() + event.getOrigin().substring(1);

		switch (event.getType()) {
		case COMMAND -> {
			var handlerBeanName = beanName.replace("Command", "Handler");
			Handler<Command> handler = (Handler) context.getBean(handlerBeanName);
			handler.handle((Command) event.getSource());
		}
		case QUERY -> {
			var resolverBeanName = beanName.replace("Query", "Resolver");
			Resolver<Query> resolver = (Resolver) context.getBean(resolverBeanName);
			resolver.resolve((Query) event.getSource());
		}
		default -> throw new ServiceBusInvalidObjectException(event);
		}
	}

	public CreateInspecaoCommand obterCreateInspecaoCommand(InspecaoDTO body) {
		return new CreateInspecaoCommand(body);
	}

	public IdInspecaoQuery obterQueryInspecaoPorId() {
		return new IdInspecaoQuery();
	}

	public ListAllInspecaoQuery obterQueryListAllInspecao() {
		return new ListAllInspecaoQuery();
	}

}