package br.com.easy.quality.questionario.adapter.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PerguntaEntity {


	private String descricao;

	private String resposta;

}
