/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.dto;

import java.util.ArrayList;
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
	public void addPerguntas(PerguntaDTO perguntaDTO) {
		if (perguntas == null) {
			perguntas = new ArrayList<>();
			perguntas.add(perguntaDTO);
		} else {
			perguntas.add(perguntaDTO);
		}

	}

}
