/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.domain;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Inspecao {

	private String titulo;

	private boolean status;

	private LocalDate dataCriacao;

	private LocalDate dataEdicao;

	public Inspecao() {
	}

	public Inspecao(String titulo, Boolean status) {
		this.status = status;
		this.titulo = titulo;
	}


}
