/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.form.read.bus.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.easy.quality.dto.QuestionarioDTO;
import br.com.easy.quality.form.application.service.Resolver;
import br.com.easy.quality.form.read.bus.query.ListAllQuestionarioQuery;
import br.com.easy.quality.form.read.out.QuestionarioReadDataBaseAdapter;

@Component
public class ListAllQuestionarioResolver implements Resolver<ListAllQuestionarioQuery> {

	@Autowired
	private QuestionarioReadDataBaseAdapter questionarioVerificacaoDataBaseAdapter;

	public void resolve(ListAllQuestionarioQuery query) {
		List<QuestionarioDTO> result = questionarioVerificacaoDataBaseAdapter.getAll().get();
		query.setResult(result);
	}

}
