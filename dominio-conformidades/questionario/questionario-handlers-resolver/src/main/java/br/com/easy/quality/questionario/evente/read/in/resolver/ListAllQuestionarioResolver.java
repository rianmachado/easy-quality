/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.questionario.evente.read.in.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.easy.quality.event.query.Resolver;
import br.com.easy.quality.form.read.in.ReadQuestionarioUseCase;
import br.com.easy.quality.questionario.dto.QuestionarioDTO;
import br.com.easy.quality.questionario.query.ListAllQuestionarioQuery;

@Component
public class ListAllQuestionarioResolver implements Resolver<ListAllQuestionarioQuery> {

	@Autowired
	private ReadQuestionarioUseCase readQuestionarioUseCase;

	public void resolve(ListAllQuestionarioQuery query) {
		List<QuestionarioDTO> result = readQuestionarioUseCase.obterQuestionarios();
		query.setResult(result);
	}

}
