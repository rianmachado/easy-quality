/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.event.publish.handler;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import br.com.easy.quality.event.PublishEvent;
import br.com.easy.quality.event.command.Handler;
import br.com.easy.quality.inspecao.command.CreateInspecaoPublishCommand;

@Service
public class CreateInspecaoPublishHandler implements Handler<CreateInspecaoPublishCommand> {

	private ApplicationEventPublisher publisher;

	public CreateInspecaoPublishHandler( ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

	
	@Override
	public void handle(CreateInspecaoPublishCommand createInspecaoPublishCommand) {
		var event = new PublishEvent(createInspecaoPublishCommand);
		publisher.publishEvent(event);
		/*
		 * var inspecao = Inspecao.builder()
		 * .dataDeExpiracao(dataConverter.toLocalDateTime("yyyy-MM-dd",
		 * command.getDataDeExpiracao()))
		 * .nomeColaboradorEntrevistado(command.getNomeColaboradorEntrevistado())
		 * .nomeColaboradorEntrevistador(command.getNomeColaboradorEntrevistador()).
		 * build(); inspecao.criarInspecao(); inspecao.editarInspecao();
		 */

	}

}
