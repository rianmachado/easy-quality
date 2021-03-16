/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.command;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.commons.collections4.CollectionUtils;

import br.com.easy.quality.event.command.PublishCommand;
import br.com.easy.quality.inspecao.command.validation.SelfValidating;
import br.com.easy.quality.inspecao.domain.Pergunta;
import br.com.easy.quality.inspecao.domain.Questionario;
import br.com.easy.quality.inspecao.dto.InspecaoDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateInspecaoPublishCommand extends SelfValidating<CreateInspecaoPublishCommand> implements PublishCommand {

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

	public CreateInspecaoPublishCommand(InspecaoDTO inspecaoDTO) {
		this.titulo = inspecaoDTO.getTitulo();
		this.status = inspecaoDTO.getStatus();
		this.nomeColaboradorEntrevistador = inspecaoDTO.getNomeColaboradorEntrevistador();
		this.nomeColaboradorEntrevistado = inspecaoDTO.getNomeColaboradorEntrevistado();
		this.dataDeExpiracao = inspecaoDTO.getDataDeExpiracao();

		List<Pergunta> perguntas = new ArrayList<Pergunta>();
		if (!CollectionUtils.isEmpty(inspecaoDTO.getQuestionario().getPerguntas())) {
			inspecaoDTO.getQuestionario().getPerguntas().stream().forEach(item -> {
				perguntas.add(Pergunta.builder().descricao(item.getDescricao()).resposta(item.getResposta())
						.build());
			});
		}

		questionario = Questionario.builder().guid(inspecaoDTO.getQuestionario().getGuid()).perguntas(perguntas)
				.build();

		this.questionario = Questionario.builder().guid(inspecaoDTO.getQuestionario().getGuid())
				.perguntas(perguntas).build();

		validateSelf();
	}

	@Override
	public String getBodyJson() {
		// TODO Auto-generated method stub
		return null;
	}

}
