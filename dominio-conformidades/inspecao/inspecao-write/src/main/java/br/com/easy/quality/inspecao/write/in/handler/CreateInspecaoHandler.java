/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.write.in.handler;

import org.springframework.stereotype.Service;

import br.com.easy.quality.event.command.Handler;
import br.com.easy.quality.inspecao.write.in.commad.CreateInspecaoCommand;

@Service
public class CreateInspecaoHandler implements Handler<CreateInspecaoCommand> {

	@Override
	public void handle(CreateInspecaoCommand command) {

	}

}
