/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.questionario.adapter.mongo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.easy.quality.questionario.adapter.mongo.entity.QuestionarioEntity;
import br.com.easy.quality.questionario.dto.QuestionarioDTO;

@Component
public class QuestionarioReadMapper {

	public List<QuestionarioDTO> mapFromEntityToDto(Iterable<QuestionarioEntity> questionarios) {
		List<QuestionarioDTO> dto = new ArrayList<QuestionarioDTO>();
		questionarios.forEach(item -> {
			QuestionarioDTO questionarioDTO = new QuestionarioDTO();
			questionarioDTO.setGuid(item.getId());
			BeanUtils.copyProperties(item, questionarioDTO);
			dto.add(questionarioDTO);
		});
		return dto;
	}

	public QuestionarioDTO mapFromEntityToDto(QuestionarioEntity questionario) {
		QuestionarioDTO dto = new QuestionarioDTO();
		dto.setGuid(questionario.getId());
		BeanUtils.copyProperties(questionario, dto);
		return dto;
	}

}
