/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.form.write.in;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.easy.quality.form.domain.Questionario;

@Component
public class CriarQuestionarioUseCase {

	//private final QuestionarioPersistenceWrite questionarioPersistenceWrite;
	
	private final IDemo iDemo;

	@Autowired
	public CriarQuestionarioUseCase( IDemo iDemo) {
		this.iDemo = iDemo;;

	}
	
//	@Autowired
//	public CriarQuestionarioUseCase( QuestionarioPersistenceWrite questionarioPersistenceWrite) {
//		this.questionarioPersistenceWrite = questionarioPersistenceWrite;
//	}
//
//	public void saveQuestionario(Questionario questionario) {
//		if (questionario.getPerguntas() == null || questionario.getPerguntas().isEmpty()
//				|| questionario.getPerguntas().size() > 5) {
//			throw new BusinessException(CodeBusinessMessage.ERROR_QUESTIONARIO_INCOMPLETO);
//		} else {
//			questionarioPersistenceWrite.saveQuestionario(questionario);
//		}
//	}
	
	public void saveQuestionario(Questionario questionario) {
		System.out.println(" DEMO....");
	}

}
