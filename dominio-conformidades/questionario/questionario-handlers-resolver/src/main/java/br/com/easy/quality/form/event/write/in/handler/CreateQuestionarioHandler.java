/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.form.event.write.in.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.easy.quality.command.CreateQuestionarioCommand;
import br.com.easy.quality.event.command.Handler;
import br.com.easy.quality.form.domain.Questionario;
import br.com.easy.quality.form.write.in.CriarQuestionarioUseCase;

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
