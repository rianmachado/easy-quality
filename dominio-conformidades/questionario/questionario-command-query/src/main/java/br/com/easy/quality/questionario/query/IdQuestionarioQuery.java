/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.questionario.query;

import br.com.easy.quality.event.query.Query;
import br.com.easy.quality.questionario.dto.QuestionarioDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class IdQuestionarioQuery implements Query {
	private QuestionarioDTO result;
	private String id;
}
