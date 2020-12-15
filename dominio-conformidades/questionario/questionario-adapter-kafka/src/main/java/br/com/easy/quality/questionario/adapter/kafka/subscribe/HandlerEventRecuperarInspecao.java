package br.com.easy.quality.questionario.adapter.kafka.subscribe;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.easy.quality.bus.service.ServiceBus;
import br.com.easy.quality.questionario.adapter.kafka.Mapper.MapperMessage;
import br.com.easy.quality.questionario.command.PublishQuestionarioCommand;
import br.com.easy.quality.questionario.query.IdQuestionarioQuery;

@Component
public class HandlerEventRecuperarInspecao {

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

			var publishQuestionario = new PublishQuestionarioCommand(body.asText());
			serviceBus.execute(publishQuestionario);
		});
	}

}
