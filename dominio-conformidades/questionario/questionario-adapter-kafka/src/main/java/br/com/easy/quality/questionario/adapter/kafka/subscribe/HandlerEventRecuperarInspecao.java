package br.com.easy.quality.questionario.adapter.kafka.subscribe;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.easy.quality.questionario.adapter.kafka.Mapper.MapperMessage;
import br.com.easy.quality.questionario.adapter.kafka.event.EventBus;
import br.com.easy.quality.questionario.command.PublishQuestionarioCommand;
import br.com.easy.quality.questionario.query.IdQuestionarioQuery;

@Component
public class HandlerEventRecuperarInspecao {

	@Autowired
	private EventBus eventBus;

	@Autowired
	private MapperMessage mapperMessage;

	public CompletableFuture<Void> onEvent(final String message) {
		return CompletableFuture.runAsync(() -> {
			var body = mapperMessage.mapToJson(message);
			var guidQuestionario = body.get("content").get("questionario").get("guid").asText();
			var query = IdQuestionarioQuery.builder().id(guidQuestionario).build();
			eventBus.execute(query);
			var questionario = mapperMessage.mapToJSON(query.getResult());
			String bodyAtualizado = mapperMessage.updateQuestionario(body.get("content"), questionario);
			var command = new PublishQuestionarioCommand(bodyAtualizado);
			eventBus.execute(command);
		});
	}

}
