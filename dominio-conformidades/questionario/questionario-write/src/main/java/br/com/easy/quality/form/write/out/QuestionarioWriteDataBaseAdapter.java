/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.form.write.out;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.easy.quality.form.adapter.mongo.repository.QuestionarioRepositoryCustom;
import br.com.easy.quality.form.domain.Questionario;
import br.com.easy.quality.form.write.common.QuestionarioWriteMapper;

@Component
public class QuestionarioWriteDataBaseAdapter implements QuestionarioWriteDataBase {

	@Autowired
	private QuestionarioRepositoryCustom formVerificacaoRepositoryCustom;

	@Autowired
	private QuestionarioWriteMapper formVerificacaoMapper;

	@Override
	public void saveQuestionario(Questionario questionario) {

		var questionarioEntity = formVerificacaoMapper.mapToEntity(questionario);

		formVerificacaoRepositoryCustom.save(questionarioEntity);
	}

}
