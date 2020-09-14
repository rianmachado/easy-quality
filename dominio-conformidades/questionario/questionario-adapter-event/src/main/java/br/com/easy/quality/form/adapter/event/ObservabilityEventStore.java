/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.form.adapter.event;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface ObservabilityEventStore  {

	void registrar(InternalEvent event) throws JsonProcessingException;
}
