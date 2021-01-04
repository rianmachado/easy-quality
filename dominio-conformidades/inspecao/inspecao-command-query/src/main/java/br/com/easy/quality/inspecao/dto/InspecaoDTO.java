package br.com.easy.quality.inspecao.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InspecaoDTO {

	private String guid;
	private String titulo;
	private Boolean status;
	private String nomeColaboradorEntrevistador;
	private String nomeColaboradorEntrevistado;
	private QuestionarioDTO questionario;
	private String dataDeExpiracao;
	private String dataCriacao;
	private String dataEdicao;

}
