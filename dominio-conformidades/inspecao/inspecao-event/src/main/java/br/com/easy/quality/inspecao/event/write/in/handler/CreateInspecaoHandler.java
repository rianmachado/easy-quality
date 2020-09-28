/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.event.write.in.handler;

import org.springframework.stereotype.Service;

import br.com.easy.quality.event.command.Handler;
import br.com.easy.quality.inspecao.event.write.in.commad.CreateInspecaoCommand;

@Service
public class CreateInspecaoHandler implements Handler<CreateInspecaoCommand> {

	@Override
	public void handle(CreateInspecaoCommand command) {

	}

}
