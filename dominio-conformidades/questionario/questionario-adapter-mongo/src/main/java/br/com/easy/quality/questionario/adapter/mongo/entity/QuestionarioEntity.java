/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.questionario.adapter.mongo.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document("Questionario")
public class QuestionarioEntity {

	@Id
	private String guid;

	@Indexed
	private String titulo;

	private boolean status;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime dataCriacao;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime dataEdicao;

	private List<PerguntaEntity> perguntas;

}
