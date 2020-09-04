/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.application.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import br.com.easy.quality.inspecao.adapter.event.InternalEvent;
import br.com.easy.quality.inspecao.adapter.event.kafka.TransactionalEventPublisher;
import br.com.easy.quality.inspecao.domain.exception.DomainException;

@Component
public class LogListener {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TransactionalEventPublisher transactionalEventPublisher;

	@Async
	@EventListener
	void onEventOccur(InternalEvent event) {

		if (event.isSuccess()) {
			if (logger.isInfoEnabled()) {
				logger.info(event.toJson());
				transactionalEventPublisher.log(event);
			}

		} else if (event.getException() instanceof DomainException) {
			if (logger.isWarnEnabled())
				logger.warn(event.toJson(), event.getException());
		} else {
			if (logger.isErrorEnabled())
				logger.error(event.toJson(), event.getException());
		}
	}
}
