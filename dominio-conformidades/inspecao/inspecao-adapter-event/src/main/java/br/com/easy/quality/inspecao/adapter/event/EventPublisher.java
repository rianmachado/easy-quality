/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.adapter.event;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface EventPublisher  {

	void log(InternalEvent event) throws JsonProcessingException;
}
