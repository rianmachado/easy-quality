/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.form.write.in;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.easy.quality.form.domain.Questionario;
import br.com.easy.quality.form.write.exception.BusinessException;
import br.com.easy.quality.form.write.exception.CodeBusinessMessage;
import br.com.easy.quality.form.write.out.QuestionarioWriteDataBaseAdapter;

@Service
public class CriarQuestionarioUseCase {

	@Autowired
	private QuestionarioWriteDataBaseAdapter questionarioVerificacaoDataBaseAdapter;

	public void saveQuestionario(Questionario questionario) {
		if (questionario.getPerguntas() == null || questionario.getPerguntas().isEmpty()
				|| questionario.getPerguntas().size() > 5) {
			throw new BusinessException(CodeBusinessMessage.ERROR_QUESTIONARIO_INCOMPLETO);
		} else {
			questionarioVerificacaoDataBaseAdapter.saveQuestionario(questionario);
		}
	}

}
