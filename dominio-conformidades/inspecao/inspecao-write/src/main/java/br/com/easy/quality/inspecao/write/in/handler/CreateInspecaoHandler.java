/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.write.in.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.easy.quality.inspecao.adapter.event.CreateInspecaoHandlerEvent;
import br.com.easy.quality.inspecao.adapter.event.command.Handler;
import br.com.easy.quality.inspecao.write.in.commad.CreateInspecaoCommand;
import br.com.easy.quality.inspecao.write.util.DataConverter;

@Service
public class CreateInspecaoHandler implements Handler<CreateInspecaoCommand> {

	@Autowired
	DataConverter dataConverter;

	@Override
	public void handle(CreateInspecaoCommand command) {

		/* PASSAR PARA O CONSUMIDOR DO EVENDO RETORNADO PELA CONSULTA DO QUESTIONARIO
		var inspecao = Inspecao.builder()
				.dataDeExpiracao(dataConverter.toLocalDateTime("yyyy-MM-dd", command.getDataDeExpiracao()))
				.nomeColaboradorEntrevistado(command.getNomeColaboradorEntrevistado())
				.nomeColaboradorEntrevistador(command.getNomeColaboradorEntrevistador()).build();

		inspecao.criarInspecao();
		inspecao.editarInspecao();
		*/
		CreateInspecaoHandlerEvent createInspecaoHandlerEvent = new CreateInspecaoHandlerEvent(command);
		createInspecaoHandlerEvent.setStatusEventoCriacaoInspecao(true);

	}

}
