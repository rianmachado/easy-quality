package br.com.easy.quality.form.event.subscribe;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.easy.quality.form.event.common.MapperMessage;
import br.com.easy.quality.form.event.message.Message;
import br.com.easy.quality.form.event.publish.TransactionalEventQuestionario;
import br.com.easy.quality.form.read.in.query.IdQuestionarioQuery;
import br.com.easy.quality.form.read.in.resolver.IdQuestionarioResolver;

@Component
public class ConsummerCreateInspecaoHandlerEvent {

	@Autowired
	private IdQuestionarioResolver idQuestionarioResolver;

	@Autowired
	TransactionalEventQuestionario transactionalEventQuestionario;

	@Autowired
	private MapperMessage mapperMessage;

	public CompletableFuture<Void> onEvent(final String message) {
		return CompletableFuture.runAsync(() -> {

			// Obiservability
			var messageContent = Message.builder().body(message).build();
			new ConsummerHandlerEvent(messageContent);

			var guidQuestionario = mapperMessage.mapToJson(message).get("questionario").get("guid").asText();
			var query = IdQuestionarioQuery.builder().id(guidQuestionario).build();
			idQuestionarioResolver.resolve(query);
			
			//CreateQuestionarioCommand 

		});
	}

}
