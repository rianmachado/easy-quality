/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.read.bus.query;

import br.com.easy.quality.inspecao.adapter.event.query.Query;
import br.com.easy.quality.inspecao.dto.InspecaoDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdInspecaoQuery implements Query {
	private InspecaoDTO result;
	private String id;
}
