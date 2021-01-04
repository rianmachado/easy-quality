/**
Os * @author rianmachado@gmail.com
 */
package br.com.easy.quality.questionario.adapter.kafka.Mapper;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.easy.quality.questionario.dto.QuestionarioDTO;

@Component
public class MapperMessage {

	// TODO: REVER TRATATIVA NESSE MAP(TRATAMENTO DE ERRO)
	public JsonNode mapToJson(String message) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readTree(message);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	// TODO: REVER TRATATIVA NESSE MAP(TRATAMENTO DE ERRO)
	public String updateQuestionario(final JsonNode body, final JsonNode questionario) {
		JsonNode copia = body.get("questionario").get("perguntas");
		ObjectNode o = (ObjectNode) body;
		o.put("questionario", questionario);
		int indice = 0;
		for(JsonNode node : o.get("questionario").get("perguntas")) {
			ObjectNode pergunta = (ObjectNode) node;
			pergunta.put("resposta", copia.get(indice).get("resposta"));
			indice++;
		}
		
		return o.toString();
	}

	// TODO: REVER TRATATIVA NESSE MAP(TRATAMENTO DE ERRO)
	public JsonNode mapToJSON(final QuestionarioDTO questionarioDTO) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		JsonNode json = mapper.valueToTree(questionarioDTO);
		return json;
	}

}
