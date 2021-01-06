/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.adapter.mongo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.easy.quality.inspecao.adapter.mongo.entity.InspecaoEntity;
import br.com.easy.quality.inspecao.adapter.mongo.entity.PerguntaEntity;
import br.com.easy.quality.inspecao.adapter.mongo.entity.QuestionarioEntity;
import br.com.easy.quality.inspecao.domain.Inspecao;
import br.com.easy.quality.inspecao.dto.InspecaoDTO;
import br.com.easy.quality.inspecao.dto.PerguntaDTO;
import br.com.easy.quality.inspecao.dto.QuestionarioDTO;

@Component
public class InspecaoMapper {

	public InspecaoEntity mapToEntity(Inspecao inspecao) {
		QuestionarioEntity questionarioEntity = new QuestionarioEntity();
		questionarioEntity.setGuid(inspecao.getQuestionario().getGuid());
		InspecaoEntity inspecaoEntity = InspecaoEntity.builder().questionario(questionarioEntity).build();
		BeanUtils.copyProperties(inspecao, inspecaoEntity);
		inspecao.getQuestionario().getPerguntas().stream().forEach(item -> {
			PerguntaEntity perguntaEntity = PerguntaEntity.builder().descricao(item.getDescricao())
					.resposta(item.getResposta()).build();
			inspecaoEntity.getQuestionario().addPerguntas(perguntaEntity);
		});
		return inspecaoEntity;
	}

	public String mapToStringJson(Inspecao inspecao) throws JsonProcessingException {
		ObjectMapper jsonObject = new ObjectMapper();
		return jsonObject.writeValueAsString(inspecao);
	}

	public List<InspecaoDTO> mapFromEntityToDto(Iterable<InspecaoEntity> inspecaos) {
		List<InspecaoDTO> dto = new ArrayList<InspecaoDTO>();
		inspecaos.forEach(item -> {
			InspecaoDTO inspecaoDTO = new InspecaoDTO();
			QuestionarioDTO questionarioDTO = new QuestionarioDTO();
			inspecaoDTO.setQuestionario(questionarioDTO);

			BeanUtils.copyProperties(item, inspecaoDTO);

			item.getQuestionario().getPerguntas().stream().forEach(pergunta -> {
				PerguntaDTO perguntaDto = new PerguntaDTO();
				perguntaDto.setDescricao(pergunta.getDescricao());
				perguntaDto.setResposta(pergunta.getResposta());
				inspecaoDTO.getQuestionario().addPerguntas(perguntaDto);
			});

			dto.add(inspecaoDTO);
		});

		return dto;
	}

	public InspecaoDTO mapFromEntityToDto(InspecaoEntity inspecao) {
		InspecaoDTO dto = new InspecaoDTO();
		BeanUtils.copyProperties(inspecao, dto);
		return dto;
	}
}
