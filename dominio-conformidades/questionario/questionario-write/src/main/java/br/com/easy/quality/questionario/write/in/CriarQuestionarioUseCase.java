/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.questionario.write.in;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.easy.quality.questionario.domain.Questionario;
import br.com.easy.quality.questionario.write.exception.BusinessException;
import br.com.easy.quality.questionario.write.exception.CodeBusinessMessage;
import br.com.easy.quality.questionario.write.out.QuestionarioPersistenceWrite;

@Component
public class CriarQuestionarioUseCase {

	private final QuestionarioPersistenceWrite questionarioPersistenceWrite;

	@Autowired
	public CriarQuestionarioUseCase(QuestionarioPersistenceWrite questionarioPersistenceWrite) {
		this.questionarioPersistenceWrite = questionarioPersistenceWrite;
	}

	public void saveQuestionario(Questionario questionario) {
		if (questionario.getPerguntas() == null || questionario.getPerguntas().isEmpty()
				|| questionario.getPerguntas().size() > 5) {
			throw new BusinessException(CodeBusinessMessage.ERROR_QUESTIONARIO_INCOMPLETO);
		} else {
			questionarioPersistenceWrite.saveQuestionario(questionario);
		}
	}

}
