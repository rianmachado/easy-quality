/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.questionario.evente.read.in.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.easy.quality.event.query.Resolver;
import br.com.easy.quality.form.read.in.ReadQuestionarioUseCase;
import br.com.easy.quality.questionario.dto.QuestionarioDTO;
import br.com.easy.quality.questionario.query.IdQuestionarioQuery;

@Component
public class IdQuestionarioResolver implements Resolver<IdQuestionarioQuery> {

	@Autowired
	private ReadQuestionarioUseCase readQuestionarioUseCase;

	public void resolve(IdQuestionarioQuery query) {
		QuestionarioDTO result = readQuestionarioUseCase.obterQuestionario(query.getId());
		query.setResult(result);
	}
}
