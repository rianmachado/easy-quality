/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.query;

import java.util.List;

import br.com.easy.quality.event.query.Query;
import br.com.easy.quality.inspecao.dto.InspecaoDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ListAllInspecaoQuery implements Query {
	private List<InspecaoDTO> result;

}
