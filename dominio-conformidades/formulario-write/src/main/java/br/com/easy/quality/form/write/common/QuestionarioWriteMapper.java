/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.form.write.common;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.easy.quality.form.adapter.mongo.entity.QuestionarioEntity;
import br.com.easy.quality.form.domain.Questionario;

@Component
public class QuestionarioWriteMapper {

	public QuestionarioEntity mapToEntity(Questionario questionario) {
		QuestionarioEntity questionarioEntity = new QuestionarioEntity();
		BeanUtils.copyProperties(questionario, questionarioEntity);
		return questionarioEntity;
	}

}
