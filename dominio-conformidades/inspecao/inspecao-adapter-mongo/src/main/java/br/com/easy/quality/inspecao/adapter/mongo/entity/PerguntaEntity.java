package br.com.easy.quality.inspecao.adapter.mongo.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PerguntaEntity {
	private String descricao;
	private String resposta;
}
