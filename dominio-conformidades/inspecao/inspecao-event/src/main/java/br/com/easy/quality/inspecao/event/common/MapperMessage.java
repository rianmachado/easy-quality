/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.event.common;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.easy.quality.inspecao.dto.InspecaoDTO;

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

	// TODO: REVER TRATATIVA NESSE MAP(TRATAMENTO DE ERRO)
	public InspecaoDTO mapToInspecaoDTO(String body) {
		InspecaoDTO inspecao = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			inspecao = mapper.readValue(body, InspecaoDTO.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return inspecao;
	}

}
