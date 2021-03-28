/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.questionario.command;

import javax.validation.constraints.NotNull;

import br.com.easy.quality.event.command.PublishCommand;
import br.com.easy.quality.questionario.questionario.validation.SelfValidating;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublishQuestionarioCommand extends SelfValidating<PublishQuestionarioCommand>
		implements PublishCommand {

	@NotNull
	private final String bodyJson;
	

	public PublishQuestionarioCommand(String body) {
		this.bodyJson = body;
		validateSelf();
	}
}
