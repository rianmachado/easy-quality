package br.com.easy.quality.inspecao.adapter.kafka.subscribe;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.easy.quality.bus.service.ServiceBus;
import br.com.easy.quality.inspecao.adapter.kafka.Mapper.MapperMessage;
import br.com.easy.quality.inspecao.command.CreateInspecaoCommand;

@Component
public class ConsummerQuestionarioHandlerEvent {

	@Autowired
	private ServiceBus serviceBus;

	@Autowired
	private MapperMessage mapperMessage;

	public CompletableFuture<Void> onEvent(final String message) {
		return CompletableFuture.runAsync(() -> {
			var inspecaoComQuestionarioModeloPreenchido = mapperMessage.mapToInspecaoDTO(message);
			var createInspecaoCommand = new CreateInspecaoCommand(inspecaoComQuestionarioModeloPreenchido);
			serviceBus.execute(createInspecaoCommand);
		});
	}

}
