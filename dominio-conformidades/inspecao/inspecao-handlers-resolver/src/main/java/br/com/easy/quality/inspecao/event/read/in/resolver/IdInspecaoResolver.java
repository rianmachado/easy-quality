/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.event.read.in.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.easy.quality.event.query.Resolver;
import br.com.easy.quality.inspecao.dto.InspecaoDTO;
import br.com.easy.quality.inspecao.query.IdInspecaoQuery;
import br.com.easy.quality.inspecao.read.in.ReadInspecaoUseCase;

@Component
public class IdInspecaoResolver implements Resolver<IdInspecaoQuery> {

	@Autowired
	private ReadInspecaoUseCase readInspecaoUseCase;

	public void resolve(IdInspecaoQuery query) {
		InspecaoDTO result = readInspecaoUseCase.obterInspecao(query.getId());
		query.setResult(result);
	}
}
