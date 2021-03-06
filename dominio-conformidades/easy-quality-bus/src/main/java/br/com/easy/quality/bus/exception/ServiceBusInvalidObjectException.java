/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.bus.exception;

import br.com.easy.quality.event.ObservabilityEvent;

public class ServiceBusInvalidObjectException extends RuntimeException {

	private static final long serialVersionUID = 7167807442381612960L;

	public ServiceBusInvalidObjectException(ObservabilityEvent event) {
        super(String.format("ServiceBus does not recognizes Object of type: %s",
                event.getSource().getClass().getCanonicalName()));
    }
}
