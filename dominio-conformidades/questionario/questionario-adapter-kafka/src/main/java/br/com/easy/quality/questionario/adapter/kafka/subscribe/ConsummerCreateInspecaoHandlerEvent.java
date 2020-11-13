package br.com.easy.quality.questionario.adapter.kafka.subscribe;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.easy.quality.application.service.ServiceBus;
import br.com.easy.quality.command.CreateQuestionarioPublishCommand;
import br.com.easy.quality.query.IdQuestionarioQuery;
import br.com.easy.quality.questionario.adapter.kafka.Mapper.MapperMessage;

@Component
public class ConsummerCreateInspecaoHandlerEvent {

	@Autowired
	private ServiceBus serviceBus;

	@Autowired
	private MapperMessage mapperMessage;

	public CompletableFuture<Void> onEvent(final String message) {
		return CompletableFuture.runAsync(() -> {
			var body = mapperMessage.mapToJson(message);
			var guidQuestionario = body.get("questionarioModelo").get("guid").asText();
			var query = IdQuestionarioQuery.builder().id(guidQuestionario).build();
			serviceBus.execute(query);
			var questionario = mapperMessage.mapToJSON(query.getResult());
			mapperMessage.updateJSON(body, questionario);

			var publishQuestionario = new CreateQuestionarioPublishCommand(body.asText());
			serviceBus.execute(publishQuestionario);
		});
	}

}
