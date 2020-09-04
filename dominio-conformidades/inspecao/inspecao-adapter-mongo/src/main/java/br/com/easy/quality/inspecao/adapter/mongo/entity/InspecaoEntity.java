package br.com.easy.quality.inspecao.adapter.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class InspecaoEntity {


	private String descricao;

	private Boolean  status;

}
