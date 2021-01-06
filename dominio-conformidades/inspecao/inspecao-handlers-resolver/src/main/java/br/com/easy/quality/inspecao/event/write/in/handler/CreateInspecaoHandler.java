/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.event.write.in.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.easy.quality.event.command.Handler;
import br.com.easy.quality.inspecao.command.CreateInspecaoCommand;
import br.com.easy.quality.inspecao.domain.Inspecao;
import br.com.easy.quality.inspecao.domain.Pergunta;
import br.com.easy.quality.inspecao.domain.Questionario;
import br.com.easy.quality.inspecao.write.in.CriarInspecaoUseCase;
import br.com.easy.quality.inspecao.write.util.DataConverter;

@Service
public class CreateInspecaoHandler implements Handler<CreateInspecaoCommand> {

	@Autowired
	private CriarInspecaoUseCase criarInspecaoUseCase;

	@Autowired
	private DataConverter dataConverter;

	@Override
	public void handle(CreateInspecaoCommand command) {

		List<Pergunta> perguntas = new ArrayList<Pergunta>();
		if (!CollectionUtils.isEmpty(command.getQuestionario().getPerguntas())) {
			command.getQuestionario().getPerguntas().stream().forEach(item -> {
				perguntas.add(Pergunta.builder().descricao(item.getDescricao()).resposta(item.getResposta()).build());
			});
		}

		var inspecao = Inspecao.builder()
				.dataDeExpiracao(dataConverter.toLocalDateTime("yyyy-MM-dd HH:mm:ss", command.getDataDeExpiracao()))
				.nomeColaboradorEntrevistado(command.getNomeColaboradorEntrevistado())
				.nomeColaboradorEntrevistador(command.getNomeColaboradorEntrevistador())
				.status(command.getStatus())
				.questionario(
						Questionario.builder()
						.guid(command.getQuestionario().getGuid())
						.perguntas(perguntas).build())
				
				.build();
		
		criarInspecaoUseCase.saveInspecao(inspecao);
	}

}
