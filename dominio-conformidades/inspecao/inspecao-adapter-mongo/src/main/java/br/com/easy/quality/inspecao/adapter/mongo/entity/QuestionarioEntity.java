/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.adapter.mongo.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionarioEntity {
	private String guid;
	private List<PerguntaEntity> perguntas;

	public void addPerguntas(PerguntaEntity perguntaEntity) {
		if (perguntas == null) {
			perguntas = new ArrayList<>();
			perguntas.add(perguntaEntity);
		} else {
			perguntas.add(perguntaEntity);
		}
	}

}
