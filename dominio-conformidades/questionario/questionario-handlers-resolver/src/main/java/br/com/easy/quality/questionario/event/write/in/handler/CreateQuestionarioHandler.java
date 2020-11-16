/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.questionario.event.write.in.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.easy.quality.event.command.Handler;
import br.com.easy.quality.questionario.command.CreateQuestionarioCommand;
import br.com.easy.quality.questionario.domain.Questionario;
import br.com.easy.quality.questionario.write.in.CriarQuestionarioUseCase;

@Service
public class CreateQuestionarioHandler implements Handler<CreateQuestionarioCommand> {

	@Autowired
	CriarQuestionarioUseCase criarQuestionarioUseCase;

	@Override
	public void handle(CreateQuestionarioCommand command) {
		var questionario = new Questionario(command.getTitulo(), command.getStatus());
		questionario.criarQuestionario();
		questionario.criarPerguntas(command.getPerguntas());
		questionario.isQuestionarioValido();
		criarQuestionarioUseCase.saveQuestionario(questionario);
	}
}
