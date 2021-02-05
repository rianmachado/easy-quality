/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.bus.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import br.com.easy.quality.bus.exception.ServiceBusInvalidObjectException;
import br.com.easy.quality.event.CommandEvent;
import br.com.easy.quality.event.ObservabilityEvent;
import br.com.easy.quality.event.PublishEvent;
import br.com.easy.quality.event.QueryEvent;
import br.com.easy.quality.event.command.Command;
import br.com.easy.quality.event.command.Handler;
import br.com.easy.quality.event.query.Query;
import br.com.easy.quality.event.query.Resolver;

@Service
public class ServiceBus {

	private ApplicationContext context;
	private ApplicationEventPublisher publisher;

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
	

	private void execute(ObservabilityEvent event) {
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
	
	public void execute(PublishEvent event) {
		try {
			publisher.publishEvent(event);
		} catch (Exception exception) {
			event.setException(exception);
			throw exception;
		} finally {
			event.stopTimer();
		}
	}
	
	

	private void run(ObservabilityEvent event) {

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


}