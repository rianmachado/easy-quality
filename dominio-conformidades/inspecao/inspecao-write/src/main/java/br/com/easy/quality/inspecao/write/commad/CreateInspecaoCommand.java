/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.write.commad;

import javax.validation.constraints.NotNull;

import br.com.easy.quality.inspecao.adapter.event.command.Command;
import br.com.easy.quality.inspecao.application.validation.SelfValidating;
import br.com.easy.quality.inspecao.dto.InspecaoDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateInspecaoCommand extends SelfValidating<CreateInspecaoCommand> implements Command {

	@NotNull
	private final String titulo;

	@NotNull
	private Boolean status = true;

	public CreateInspecaoCommand(InspecaoDTO inspecaoDTO) {

		this.titulo = inspecaoDTO.getTitulo();
		this.status = inspecaoDTO.getStatus();
		validateSelf();
	}

}
