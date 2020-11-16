/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.questionario.write.out;

import br.com.easy.quality.questionario.domain.Questionario;

public interface QuestionarioPersistenceWrite {
	 void saveQuestionario(Questionario questionario);
}
