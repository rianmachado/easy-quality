/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionarioDTO {
	private String guid;
	private String titulo;
	private Boolean status;
	private List<PerguntaDTO> perguntas;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataEdicao;
}
