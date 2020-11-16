/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.questionario.adapter.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.easy.quality.questionario.adapter.mongo.mapper.QuestionarioWriteMapper;
import br.com.easy.quality.questionario.adapter.mongo.repository.QuestionarioRepositoryCustom;
import br.com.easy.quality.questionario.domain.Questionario;
import br.com.easy.quality.questionario.write.out.QuestionarioPersistenceWrite;


@Component
public class QuestionarioWriteDataBaseAdapter implements QuestionarioPersistenceWrite{


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
