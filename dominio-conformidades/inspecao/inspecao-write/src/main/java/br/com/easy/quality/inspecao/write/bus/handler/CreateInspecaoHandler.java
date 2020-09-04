/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.write.bus.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.easy.quality.inspecao.adapter.event.handler.Handler;
import br.com.easy.quality.inspecao.domain.Inspecao;
import br.com.easy.quality.inspecao.write.commad.CreateInspecaoCommand;
import br.com.easy.quality.inspecao.write.in.CriarInspecaoUseCase;

@Service
public class CreateInspecaoHandler implements Handler<CreateInspecaoCommand> {

	@Autowired
	CriarInspecaoUseCase criarInspecaoUseCase;

	@Override
	public void handle(CreateInspecaoCommand command) {
		var inspecao = new Inspecao(command.getTitulo(), command.getStatus());
		criarInspecaoUseCase.saveInspecao(inspecao);
	}
}
