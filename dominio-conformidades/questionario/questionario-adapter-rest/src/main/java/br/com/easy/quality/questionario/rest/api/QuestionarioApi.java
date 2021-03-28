/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.questionario.rest.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.easy.quality.questionario.dto.QuestionarioDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "questionarios", description = "the questionarios API")
public interface QuestionarioApi {

	@ApiOperation(value = "Criar questionario", nickname = "criarQuestionario", notes = "", tags = {
			"questionario", })
	@ApiResponses(value = { @ApiResponse(code = 405, message = "Invalid input") })
	@RequestMapping(value = "/questionarios", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	ResponseEntity<Void> criarQuestionario(
			@ApiParam(value = "Objeto utilizado para adicionar questionario(s)", required = true) @Valid @RequestBody QuestionarioDTO body);

	@ApiOperation(value = "Obter questionario por ID", nickname = "getQuestionarioPorId", notes = "Retorna uma questionario simples", response = QuestionarioDTO.class, tags = {
			"questionario", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "successful operation", response = QuestionarioDTO.class),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Questionario not found") })
	@RequestMapping(value = "/questionarios/{questionarioId}", produces = {
			"application/json" }, method = RequestMethod.GET)
	ResponseEntity<QuestionarioDTO> getQuestionarioPorId(
			@ApiParam(value = "ID da questionario para retorno", required = true) @PathVariable("questionarioId") String questionarioId);

	@ApiOperation(value = "Obter todos os questionarios cadastrados", nickname = "getQuestionarios", notes = "Retorna lista de questionarios cadastrados", response = QuestionarioDTO.class, tags = {
			"questionario", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "successful operation", response = QuestionarioDTO.class)})
	@RequestMapping(value = "/questionarios", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<QuestionarioDTO>> getQuestionarios();
	
	
	/*
	@ApiOperation(value = "Atualiza uma questionario existente", nickname = "atualizarQuestionario", notes = "", tags = {
			"questionario", })
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Questionario not found"),
			@ApiResponse(code = 405, message = "Validation exception") })
	@RequestMapping(value = "/questionarios", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.PUT)
	ResponseEntity<Void> atualizarQuestionario(
			@ApiParam(value = "Object questionario com seus atributos que serão armazenados", required = true) @Valid @RequestBody QuestionarioDTO body);

	@ApiOperation(value = "Deleta uma questionario existente", nickname = "deleteQuestionario", notes = "", tags = {
			"questionario", })
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Questionario not found") })
	@RequestMapping(value = "/questionarios/{questionarioId}", produces = {
			"application/json" }, method = RequestMethod.DELETE)
	ResponseEntity<Void> deleteQuestionario(
			@ApiParam(value = "Questionario id para ser deletado", required = true) @PathVariable("questionarioId") Long questionarioId);

	*/

}
