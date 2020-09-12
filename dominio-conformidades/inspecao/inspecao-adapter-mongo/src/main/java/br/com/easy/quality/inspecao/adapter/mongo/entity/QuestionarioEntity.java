/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.adapter.mongo.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionarioEntity {


	private List<PerguntaEntity> perguntas;

}
