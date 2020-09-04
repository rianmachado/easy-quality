/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.adapter.event;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class EventConverter {

	public String from(final InternalEvent event) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(event);
	}

}
