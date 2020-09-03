/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.form.read.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.easy.quality.dto.QuestionarioDTO;
import br.com.easy.quality.form.adapter.mongo.entity.QuestionarioEntity;

@Component
public class QuestionarioReadMapper {
	

	public List<QuestionarioDTO> mapFromEntityToDto(Iterable<QuestionarioEntity> questionarios) {
		List<QuestionarioDTO> dto = new ArrayList<QuestionarioDTO>();
		questionarios.forEach(item -> {
			QuestionarioDTO questionarioDTO = new QuestionarioDTO();
			BeanUtils.copyProperties(item, questionarioDTO);
			dto.add(questionarioDTO);
		});
		return dto;
	}

	public QuestionarioDTO mapFromEntityToDto(QuestionarioEntity questionario) {
		QuestionarioDTO dto = new QuestionarioDTO();
		BeanUtils.copyProperties(questionario, dto);
		return dto;
	}

}
