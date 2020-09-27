package br.com.easy.quality.form.event.publish.handler;

import br.com.easy.quality.event.PublishEvent;
import br.com.easy.quality.event.command.Handler;
import br.com.easy.quality.form.event.publish.command.CreateQuestionarioPublishCommand;

public class CreateQuestionarioPublishHandler implements Handler<CreateQuestionarioPublishCommand> {

	@Override
	public void handle(CreateQuestionarioPublishCommand createQuestionarioPublishCommand) {
		new PublishEvent(createQuestionarioPublishCommand);
	}

}
