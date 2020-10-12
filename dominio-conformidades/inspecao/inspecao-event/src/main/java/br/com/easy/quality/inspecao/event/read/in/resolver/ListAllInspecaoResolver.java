/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.event.read.in.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.easy.quality.event.query.Resolver;
import br.com.easy.quality.inspecao.dto.InspecaoDTO;
import br.com.easy.quality.inspecao.event.read.in.query.ListAllInspecaoQuery;
import br.com.easy.quality.inspecao.read.in.ReadInspecaoUseCase;

@Component
public class ListAllInspecaoResolver implements Resolver<ListAllInspecaoQuery> {

	@Autowired
	private ReadInspecaoUseCase readInspecaoUseCase;

	public void resolve(ListAllInspecaoQuery query) {
		List<InspecaoDTO> result = readInspecaoUseCase.obterInspecoes();
		query.setResult(result);
	}

}
