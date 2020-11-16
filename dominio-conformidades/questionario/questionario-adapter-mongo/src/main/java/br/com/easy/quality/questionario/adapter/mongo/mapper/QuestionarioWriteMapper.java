/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.questionario.adapter.mongo.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.easy.quality.questionario.adapter.mongo.entity.QuestionarioEntity;
import br.com.easy.quality.questionario.domain.Questionario;

@Component
public class QuestionarioWriteMapper {

	public QuestionarioEntity mapToEntity(Questionario questionario) {
		QuestionarioEntity questionarioEntity = new QuestionarioEntity();
		BeanUtils.copyProperties(questionario, questionarioEntity);
		return questionarioEntity;
	}

}
