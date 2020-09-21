package br.com.easy.quality.form.event.subscribe;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.easy.quality.form.event.common.MapperMessage;
import br.com.easy.quality.form.event.message.Message;
import br.com.easy.quality.form.read.in.query.IdQuestionarioQuery;
import br.com.easy.quality.form.read.in.resolver.IdQuestionarioResolver;

@Component
public class ConsummerCreateInspecaoHandlerEvent {

	@Autowired
	private IdQuestionarioResolver idQuestionarioResolver;

	@Autowired
	private MapperMessage mapperMessage;

	public CompletableFuture<Void> onEvent(final Message message) {
		return CompletableFuture.runAsync(() -> {
			var guidQuestionario = mapperMessage.mapToJson(message).get("questionario").get("guid").asText();
			idQuestionarioResolver.resolve(IdQuestionarioQuery.builder().id(guidQuestionario).build());
		});
	}

}
