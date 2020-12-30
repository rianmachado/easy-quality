package br.com.easy.quality.questionario.adapter.kafka.subscribe;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import br.com.easy.quality.bus.service.ServiceBus;
import br.com.easy.quality.event.PublishEvent;
import br.com.easy.quality.questionario.adapter.kafka.Mapper.MapperMessage;
import br.com.easy.quality.questionario.command.PublishQuestionarioCommand;
import br.com.easy.quality.questionario.query.IdQuestionarioQuery;

@Component
public class HandlerEventRecuperarInspecao {

	@Autowired
	private ServiceBus serviceBus;

	@Autowired
	private MapperMessage mapperMessage;

	@Autowired
	private ApplicationEventPublisher publisher;

	public CompletableFuture<Void> onEvent(final String message) {
		return CompletableFuture.runAsync(() -> {
			var body = mapperMessage.mapToJson(message);
			var guidQuestionario = body.get("content").get("questionario").get("guid").asText();
			var query = IdQuestionarioQuery.builder().id(guidQuestionario).build();
			serviceBus.execute(query);
			var questionario = mapperMessage.mapToJSON(query.getResult());
			String bodyAtualizado = mapperMessage.updateQuestionario(body, questionario);
		
			var command = new PublishQuestionarioCommand(bodyAtualizado);
			var event = new PublishEvent(command);
			publisher.publishEvent(event);
		});
	}

}
