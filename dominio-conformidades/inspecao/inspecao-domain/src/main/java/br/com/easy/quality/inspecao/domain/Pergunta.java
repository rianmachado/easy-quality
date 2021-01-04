/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Pergunta {

	@NonNull
	private String descricao;

	@NonNull
	private String resposta;

	public Pergunta(String descricao, String resposta) {
		this.descricao = descricao;
		this.resposta = resposta;
	}

	public boolean isRespostaValida() {

		if (!this.resposta.isEmpty() && (this.resposta.equals("NAO") || this.resposta.equals("SIM"))) {
			return true;
		} else {
			return true;
		}
	}


}
