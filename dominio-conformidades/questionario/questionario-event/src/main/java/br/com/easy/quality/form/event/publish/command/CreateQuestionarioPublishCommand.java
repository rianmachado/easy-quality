/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.form.event.publish.command;

import javax.validation.constraints.NotNull;

import br.com.easy.quality.application.validation.SelfValidating;
import br.com.easy.quality.event.command.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateQuestionarioPublishCommand extends SelfValidating<CreateQuestionarioPublishCommand>
		implements Command {

	@NotNull
	private final String body;

	public CreateQuestionarioPublishCommand(String body) {
		this.body = body;
		validateSelf();
	}

}
