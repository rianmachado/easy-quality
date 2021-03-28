/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.rest.api;

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
import org.springframework.web.bind.annotation.RequestBody;

import br.com.easy.quality.inspecao.command.CreateInspecaoPublishCommand;
import br.com.easy.quality.inspecao.dto.InspecaoDTO;
import br.com.easy.quality.inspecao.query.ListAllInspecaoQuery;
import br.com.easy.quality.inspecao.rest.event.EventBus;
import io.swagger.annotations.ApiParam;

@Controller
public class InspecaoApiController implements InspecaoApi {

	private static final Logger log = LoggerFactory.getLogger(InspecaoApiController.class);

	@Autowired
	private EventBus eventBus;

	public ResponseEntity<Void> criarInspecao(
			@ApiParam(value = "Objeto utilizado para adicionar nova inspecao", required = true) @Valid @RequestBody InspecaoDTO body) {
		var comando = new CreateInspecaoPublishCommand(body);
		eventBus.execute(comando);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@Override
	public ResponseEntity<List<InspecaoDTO>> getInspecoes() {
		var query = ListAllInspecaoQuery.builder().build();
		eventBus.execute(query);
		return ResponseEntity.ok(query.getResult());
	}


	/*
	public ResponseEntity<Void> atualizarInspecao(
			@ApiParam(value = "Object inspecao com seus atributos que serão armazenados", required = true) @Valid @RequestBody InspecaoDTO body) {
		return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
	}

	public ResponseEntity<Void> deleteInspecao(
			@ApiParam(value = "inspecao id para ser deletado", required = true) @PathVariable("inspecaoId") Long inspecaoId) {
		return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
	}

	public ResponseEntity<InspecaoDTO> getInspecaoPorId(
			@ApiParam(value = "ID da inspecao para retorno", required = true) @PathVariable("inspecaoId") String inspecaoId) {
		var query = IdInspecaoQuery.builder().id(inspecaoId).build();
		serviceBus.execute(query);
		return ResponseEntity.ok(query.getResult());
	}
	*/


}
