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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.easy.quality.application.service.ServiceBus;
import br.com.easy.quality.inspecao.dto.InspecaoDTO;
import br.com.easy.quality.inspecao.event.read.in.query.IdInspecaoQuery;
import br.com.easy.quality.inspecao.event.read.in.query.ListAllInspecaoQuery;
import br.com.easy.quality.inspecao.event.write.in.commad.CreateInspecaoCommand;
import io.swagger.annotations.ApiParam;

@Controller
public class InspecaoApiController implements InspecaoApi {

	private static final Logger log = LoggerFactory.getLogger(InspecaoApiController.class);

	@Autowired
	private ServiceBus serviceBus;

	public ResponseEntity<Void> criarInspecao(
			@ApiParam(value = "Objeto utilizado para adicionar nova inspecao", required = true) @Valid @RequestBody InspecaoDTO body) {
		var comando = new CreateInspecaoCommand(body);
		serviceBus.execute(comando);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

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

	@Override
	public ResponseEntity<List<InspecaoDTO>> getInspecoes() {
		var query = ListAllInspecaoQuery.builder().build();
		serviceBus.execute(query);
		return ResponseEntity.ok(query.getResult());
	}

}
