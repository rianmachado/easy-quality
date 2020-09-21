/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.form.read.in.query;

import br.com.easy.quality.dto.QuestionarioDTO;
import br.com.easy.quality.event.query.Query;
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
