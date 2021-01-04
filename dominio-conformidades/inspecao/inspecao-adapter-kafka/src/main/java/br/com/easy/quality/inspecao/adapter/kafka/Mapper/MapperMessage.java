/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.adapter.kafka.Mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.easy.quality.inspecao.dto.InspecaoDTO;

@Component
public class MapperMessage {

	private static final Logger log = LoggerFactory.getLogger(MapperMessage.class);

	public JsonNode mapToJson(String message) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readTree(message);
		} catch (JsonMappingException e) {
			log.error(e.getLocalizedMessage(), e);
		} catch (JsonProcessingException e) {
			log.error(e.getLocalizedMessage(), e);
		}
		// (IMPORTANTE)TODO: REVER ESSE RETORNO
		return null;
	}

	// TODO: REVER TRATATIVA NESSE MAP(TRATAMENTO DE ERRO)
	public InspecaoDTO mapToInspecaoDTO(String body) {
		InspecaoDTO inspecao = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			String content = mapper.readTree(body).get("content").toString();
			inspecao = mapper.readValue(content, InspecaoDTO.class);
		} catch (JsonProcessingException e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return inspecao;
	}

}
