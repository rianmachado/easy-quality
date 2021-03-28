/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.questionario.adapter.kafka.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import br.com.easy.quality.bus.service.ServiceBus;
import br.com.easy.quality.event.PublishEvent;
import br.com.easy.quality.event.command.PublishCommand;

@Service
public class EventBus extends ServiceBus {

	public EventBus(ApplicationContext context, ApplicationEventPublisher publisher) {
		super(context, publisher);
	}

	public void execute(PublishCommand command) {
		var event = new PublishEvent(command);
		execute(event);
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
	

}