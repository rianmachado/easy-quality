/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.form.event.common;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MapperMessage {
	public JsonNode mapToJson(String message) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readTree(message);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// (IMPORTANTE)TODO: REVER ESSE RETORNO
		return null;
	}

}
