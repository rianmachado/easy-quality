/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.form.application.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import br.com.easy.quality.form.adapter.event.InternalEvent;
import br.com.easy.quality.form.domain.exception.DomainException;

@Component
public class LogListener {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Async
    @EventListener
    void onEventOccur(InternalEvent event) {

        if (event.isSuccess()) {
            if (logger.isInfoEnabled()) logger.info(event.toJson());
        } else if (event.getException() instanceof DomainException) {
            if (logger.isWarnEnabled()) logger.warn(event.toJson(), event.getException());
        } else {
            if (logger.isErrorEnabled()) logger.error(event.toJson(), event.getException());
        }
    }
}
