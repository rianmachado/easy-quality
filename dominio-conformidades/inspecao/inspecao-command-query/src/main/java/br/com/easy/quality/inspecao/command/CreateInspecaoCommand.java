/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.command;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.commons.collections4.CollectionUtils;

import br.com.easy.quality.event.command.Command;
import br.com.easy.quality.inspecao.command.validation.SelfValidating;
import br.com.easy.quality.inspecao.domain.Pergunta;
import br.com.easy.quality.inspecao.domain.Questionario;
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

	@NotNull
	private String nomeColaboradorEntrevistador;

	@NotNull
	private String nomeColaboradorEntrevistado;

	@NotNull
	private String dataDeExpiracao;

	@NotNull
	private Questionario questionario;

	public CreateInspecaoCommand(InspecaoDTO inspecaoDTO) {
		this.titulo = inspecaoDTO.getQuestionarioModelo().getTitulo();
		this.status = inspecaoDTO.getStatus();
		this.nomeColaboradorEntrevistador = inspecaoDTO.getNomeColaboradorEntrevistador();
		this.nomeColaboradorEntrevistado = inspecaoDTO.getNomeColaboradorEntrevistado();
		this.dataDeExpiracao = inspecaoDTO.getDataDeExpiracao();

		List<Pergunta> perguntas = new ArrayList<Pergunta>();
		if (CollectionUtils.isEmpty(inspecaoDTO.getQuestionarioModelo().getPerguntas())) {
			inspecaoDTO.getQuestionarioModelo().getPerguntas().stream().forEach(item -> {
				perguntas.add(Pergunta.builder().descricao(item.getDescricao()).opcaoResposta(item.getOpcaoResposta())
						.build());
			});
			questionario.setPerguntas(perguntas);
		}

		this.questionario = Questionario.builder().guid(inspecaoDTO.getGuid()).perguntas(perguntas).build();

		validateSelf();
	}

}
