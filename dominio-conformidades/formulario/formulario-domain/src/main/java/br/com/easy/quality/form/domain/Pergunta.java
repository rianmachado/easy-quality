/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.form.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
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

		if (!this.opcaoResposta.isEmpty() && (this.opcaoResposta.equals("NAO") || this.opcaoResposta.equals("SIM")
				|| this.opcaoResposta.equals("NDA"))) {
			return true;
		} else {
			return true;
		}
	}

	public boolean isDescricaoValida() {
		return this.descricao.isEmpty() ? false : true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pergunta other = (Pergunta) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}
	
	
	

}
