/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PerguntaDTO {
	
	public enum RespostaValida{
		SIM,NAO
	}
	
	private String descricao;
	private String resposta;
}
