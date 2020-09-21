/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.domain;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Questionario {

	@NonNull
	private String guid;

	@NonNull
	private List<Pergunta> perguntas;

}
