package br.com.easy.quality.questionario.event.publish.handler;

import br.com.easy.quality.event.PublishEvent;
import br.com.easy.quality.event.command.Handler;
import br.com.easy.quality.questionario.command.PublishQuestionarioCommand;

public class PublishQuestionarioHandler implements Handler<PublishQuestionarioCommand> {

	@Override
	public void handle(PublishQuestionarioCommand createQuestionarioPublishCommand) {
		new PublishEvent(createQuestionarioPublishCommand);
	}

}
