package br.com.easy.quality.form.event.subscribe;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.easy.quality.application.service.ServiceBus;
import br.com.easy.quality.form.event.common.MapperMessage;
import br.com.easy.quality.form.event.publish.command.CreateQuestionarioPublishCommand;
import br.com.easy.quality.form.read.in.query.IdQuestionarioQuery;

@Component
public class ConsummerCreateInspecaoHandlerEvent {

	@Autowired
	private ServiceBus serviceBus;

	@Autowired
	private MapperMessage mapperMessage;

	public CompletableFuture<Void> onEvent(final String message) {
		return CompletableFuture.runAsync(() -> {
			var guidQuestionario = mapperMessage.mapToJson(message).get("questionario").get("guid").asText();
			var query = IdQuestionarioQuery.builder().id(guidQuestionario).build();
			serviceBus.execute(query);
			var publishQuestionario = new CreateQuestionarioPublishCommand(query.getResult());
			serviceBus.execute(publishQuestionario);
		});
	}

}
