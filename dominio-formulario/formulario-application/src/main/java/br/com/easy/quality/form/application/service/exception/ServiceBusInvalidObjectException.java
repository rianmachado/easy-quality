package br.com.easy.quality.form.application.service.exception;

import br.com.easy.quality.form.application.event.InternalEvent;

public class ServiceBusInvalidObjectException extends RuntimeException {

    public ServiceBusInvalidObjectException(InternalEvent event) {
        super(String.format("ServiceBus does not recognizes Object of type: %s",
                event.getSource().getClass().getCanonicalName()));
    }
}
