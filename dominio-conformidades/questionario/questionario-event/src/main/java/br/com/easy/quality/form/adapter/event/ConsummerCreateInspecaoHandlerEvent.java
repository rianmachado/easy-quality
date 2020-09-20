package br.com.easy.quality.form.adapter.event;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Component;

import br.com.easy.quality.form.adapter.event.subscribe.MessageCommand;


@Component
public class ConsummerCreateInspecaoHandlerEvent {

	public CompletableFuture<Void> onEvent(final MessageCommand command) {
		return CompletableFuture.runAsync(() -> {
			// TODO chamar a base para consultar se o QUESTIONARIO passado na inspecao é valida
		});
	}

}
