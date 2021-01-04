/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.questionario.command;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import br.com.easy.quality.event.command.Command;
import br.com.easy.quality.questionario.domain.Pergunta;
import br.com.easy.quality.questionario.dto.QuestionarioDTO;
import br.com.easy.quality.questionario.questionario.validation.SelfValidating;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateQuestionarioCommand extends SelfValidating<CreateQuestionarioCommand> implements Command {

	@NotNull
	private final String titulo;

	@NotNull
	private final List<Pergunta> perguntas = new ArrayList<Pergunta>();

	@NotNull
	private Boolean status = true;

	public CreateQuestionarioCommand(QuestionarioDTO questionarioDTO) {
		this.titulo = questionarioDTO.getTitulo();
		this.status = questionarioDTO.getStatus();
		if (questionarioDTO.getPerguntas() != null && !questionarioDTO.getPerguntas().isEmpty()) {
			questionarioDTO.getPerguntas().stream().forEach(item -> {
				perguntas.add(Pergunta.builder().descricao(item.getDescricao()).build());

			});
		}
		validateSelf();
	}

	@Override
	public String getBodyJson() {
		// TODO Auto-generated method stub
		return null;
	}

}
