/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.write.in.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.easy.quality.event.command.Handler;
import br.com.easy.quality.inspecao.event.CreateInspecaoEvent;
import br.com.easy.quality.inspecao.write.in.commad.CreateInspecaoCommand;
import br.com.easy.quality.inspecao.write.out.InspecaoWriteMessageAdapter;

@Service
public class CreateInspecaoHandler implements Handler<CreateInspecaoCommand> {

	@Autowired
	InspecaoWriteMessageAdapter inspecaoWriteMessageAdapter;

	@Override
	public void handle(CreateInspecaoCommand command) {
		var createInspecaoEvent = new CreateInspecaoEvent(command);
		inspecaoWriteMessageAdapter.onEvent(createInspecaoEvent);
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
