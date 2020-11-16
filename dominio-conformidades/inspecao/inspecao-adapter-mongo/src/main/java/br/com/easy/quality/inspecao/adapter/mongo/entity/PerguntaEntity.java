package br.com.easy.quality.inspecao.adapter.mongo.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class PerguntaEntity {
	private String descricao;
	private String resposta;
}
