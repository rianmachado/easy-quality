/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.rest.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.easy.quality.inspecao.dto.InspecaoDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "inspecoes", description = "the inspecoes API")
public interface InspecaoApi {

	@ApiOperation(value = "Criar Inspecao", nickname = "criarInspecao", notes = "", tags = { "inspecao", })
	@ApiResponses(value = { @ApiResponse(code = 405, message = "Invalid input") })
	@RequestMapping(value = "/inspecoes", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	ResponseEntity<Void> criarInspecao(
			@ApiParam(value = "Objeto utilizado para adicionar inspecoes(s)", required = true) @Valid @RequestBody InspecaoDTO body);

	@ApiOperation(value = "Atualiza uma Inspecao existente", nickname = "atualizarInspecao", notes = "", tags = {
			"inspecao", })
	@ApiResponses(value = { @ApiResponse(code = 404, message = "inspecao not found"),
			@ApiResponse(code = 405, message = "Validation exception") })
	@RequestMapping(value = "/inspecoes", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.PUT)
	ResponseEntity<Void> atualizarInspecao(
			@ApiParam(value = "Object inspecao com seus atributos que serão armazenados", required = true) @Valid @RequestBody InspecaoDTO body);

	@ApiOperation(value = "Deleta uma inspecao existente", nickname = "deleteInspecao", notes = "", tags = {
			"inspecao", })
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "inspecao not found") })
	@RequestMapping(value = "/inspecoes/{inspecaoId}", produces = { "application/json" }, method = RequestMethod.DELETE)
	ResponseEntity<Void> deleteInspecao(
			@ApiParam(value = "inspecao id para ser deletado", required = true) @PathVariable("inspecaoId") Long inspecaoId);

	@ApiOperation(value = "Obter inspecao por ID", nickname = "getInspecaoPorId", notes = "Retorna uma inspecao simples", response = InspecaoDTO.class, tags = {
			"inspecao", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = InspecaoDTO.class),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "inspecao not found") })
	@RequestMapping(value = "/inspecoes/{inspecaoId}", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<InspecaoDTO> getInspecaoPorId(
			@ApiParam(value = "ID da inspecao para retorno", required = true) @PathVariable("inspecaoId") String inspecaoId);

	@ApiOperation(value = "Obter todos as inspecoes cadastrados", nickname = "getInspecaos", notes = "Retorna uma inspecao simples", response = InspecaoDTO.class, tags = {
			"inspecao", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = InspecaoDTO.class),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "inspecao not found") })
	@RequestMapping(value = "/inspecoes", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<InspecaoDTO>> getInspecoes();

}
