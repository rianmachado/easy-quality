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
	

	public List<QuestionarioDTO> mapFromEntityToDto(Iterable<QuestionarioEntity> formularios) {
		List<QuestionarioDTO> dto = new ArrayList<QuestionarioDTO>();
		formularios.forEach(item -> {
			QuestionarioDTO formularioDTO = new QuestionarioDTO();
			BeanUtils.copyProperties(item, formularioDTO);
			dto.add(formularioDTO);
		});
		return dto;
	}

	public QuestionarioDTO mapFromEntityToDto(QuestionarioEntity formulario) {
		QuestionarioDTO dto = new QuestionarioDTO();
		BeanUtils.copyProperties(formulario, dto);
		return dto;
	}

}
