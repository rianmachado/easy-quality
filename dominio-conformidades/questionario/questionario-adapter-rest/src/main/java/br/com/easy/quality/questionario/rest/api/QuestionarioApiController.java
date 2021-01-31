/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.questionario.rest.api;

import java.util.List;

/**
 * @author rianmachado@gmail.com
 */
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.easy.quality.bus.service.ServiceBus;
import br.com.easy.quality.questionario.command.CreateQuestionarioCommand;
import br.com.easy.quality.questionario.dto.QuestionarioDTO;
import br.com.easy.quality.questionario.query.IdQuestionarioQuery;
import br.com.easy.quality.questionario.query.ListAllQuestionarioQuery;
import io.swagger.annotations.ApiParam;

@Controller
public class QuestionarioApiController implements QuestionarioApi {

	private static final Logger log = LoggerFactory.getLogger(QuestionarioApiController.class);

	@Autowired
	private ServiceBus serviceBus;


	public ResponseEntity<Void> criarQuestionario(
			@ApiParam(value = "Objeto utilizado para adicionar novo questionario", required = true) @Valid @RequestBody QuestionarioDTO body) {
		var comando = new CreateQuestionarioCommand(body);
		serviceBus.execute(comando);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	

	public ResponseEntity<QuestionarioDTO> getQuestionarioPorId(
			@ApiParam(value = "ID da questionario para retorno", required = true) @PathVariable("questionarioId") String questionarioId) {
		var query = IdQuestionarioQuery.builder().id(questionarioId).build();
		serviceBus.execute(query);
		return ResponseEntity.ok(query.getResult());
	}

	@Override
	public ResponseEntity<List<QuestionarioDTO>> getQuestionarios() {
		var query = new ListAllQuestionarioQuery();
		serviceBus.execute(query);
		return ResponseEntity.ok(query.getResult());
	}


	/*
	public ResponseEntity<Void> atualizarQuestionario(
			@ApiParam(value = "Object questionario com seus atributos que serão armazenados", required = true) @Valid @RequestBody QuestionarioDTO body) {
		return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
	}

	public ResponseEntity<Void> deleteQuestionario(
			@ApiParam(value = "Questionario id para ser deletado", required = true) @PathVariable("questionarioId") Long questionarioId) {
		return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
	}
	*/

}
