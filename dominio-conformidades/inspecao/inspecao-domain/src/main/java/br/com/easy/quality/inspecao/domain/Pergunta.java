/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Pergunta {

	private String descricao;

	private String opcaoResposta;

}
