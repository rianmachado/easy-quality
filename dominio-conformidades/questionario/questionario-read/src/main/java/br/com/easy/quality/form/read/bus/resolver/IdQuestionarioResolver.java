/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.form.read.bus.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.easy.quality.dto.QuestionarioDTO;
import br.com.easy.quality.form.application.service.Resolver;
import br.com.easy.quality.form.read.bus.query.IdQuestionarioQuery;
import br.com.easy.quality.form.read.out.QuestionarioReadDataBaseAdapter;

@Component
public class IdQuestionarioResolver implements Resolver<IdQuestionarioQuery> {

	@Autowired
	private QuestionarioReadDataBaseAdapter questionarioVerificacaoDataBaseAdapter;

	public void resolve(IdQuestionarioQuery query) {
		QuestionarioDTO result = questionarioVerificacaoDataBaseAdapter.getById(query.getId()).get();
		query.setResult(result);
	}
}
