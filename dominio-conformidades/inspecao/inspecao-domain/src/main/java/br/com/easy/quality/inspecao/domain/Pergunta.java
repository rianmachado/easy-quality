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
	private String opcaoResposta;

	public Pergunta(String descricao, String opcaoResposta) {
		this.descricao = descricao;
		this.opcaoResposta = opcaoResposta;
	}

	public boolean isRespostaValida() {

		if (!this.opcaoResposta.isEmpty() && (this.opcaoResposta.equals("NAO") || this.opcaoResposta.equals("SIM"))) {
			return true;
		} else {
			return true;
		}
	}


}
