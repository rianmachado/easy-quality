/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.event.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Qualifier;
import br.com.easy.quality.event.PublishEvent;
import br.com.easy.quality.event.PublishMessage;
import br.com.easy.quality.event.Exception.DomainException;

@Component
public class InspecaoPlublishListener {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Qualifier("transactionalEventCreateInspecao")
	private PublishMessage publishMessage;

	@Async
	@EventListener
	void onEventOccur(PublishEvent publishEvent) {
		if (publishEvent.isSuccess()) {
			if (logger.isInfoEnabled()) {
				logger.info(publishEvent.toJson());
				publishMessage.publicar(publishEvent);
			}

		} else if (publishEvent.getException() instanceof DomainException) {
			if (logger.isWarnEnabled())
				logger.warn(publishEvent.toJson(), publishEvent.getException());
		} else {
			if (logger.isErrorEnabled())
				logger.error(publishEvent.toJson(), publishEvent.getException());
		}
	}
}
