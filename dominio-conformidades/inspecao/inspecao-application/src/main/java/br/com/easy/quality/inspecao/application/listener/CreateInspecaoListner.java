package br.com.easy.quality.inspecao.application.listener;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import br.com.easy.quality.inspecao.adapter.event.CreateInspecaoHandlerEvent;
import br.com.easy.quality.inspecao.adapter.event.kafka.TransactionalEventCreateInspecao;

public class CreateInspecaoListner {

	private TransactionalEventCreateInspecao transactionalEventCreateInspecao;

	@Async
	@EventListener
	void onEventOccur(CreateInspecaoHandlerEvent event) {
		if (event.isStatusEventoCriacaoInspecao()) {
			transactionalEventCreateInspecao.create(event);
		}
	}

}
