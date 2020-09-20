/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.form.application.service.exception;

import br.com.easy.quality.event.ObservabilityEvent;

public class ServiceBusInvalidObjectException extends RuntimeException {

    public ServiceBusInvalidObjectException(ObservabilityEvent event) {
        super(String.format("ServiceBus does not recognizes Object of type: %s",
                event.getSource().getClass().getCanonicalName()));
    }
}
