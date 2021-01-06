package br.com.easy.quality.inspecao.adapter.mongo.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Document("Inspecao")
public class InspecaoEntity {

	@Id
	private String id;

	@Indexed
	private String titulo;

	private Boolean status;

	private String nomeColaboradorEntrevistador;

	private String nomeColaboradorEntrevistado;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime dataCriacao;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime dataEdicao;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime dataDeExpiracao;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime dataDeUsoQuestionarioModelo;

	private QuestionarioEntity questionario;

}
