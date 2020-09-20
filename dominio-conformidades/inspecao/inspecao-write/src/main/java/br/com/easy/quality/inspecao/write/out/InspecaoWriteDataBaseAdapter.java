/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.write.out;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.easy.quality.inspecao.adapter.mongo.repository.InspecaoRepositoryCustom;
import br.com.easy.quality.inspecao.domain.Inspecao;
import br.com.easy.quality.inspecao.write.common.InspecaoWriteMapper;
import br.com.easy.quality.inspecao.write.exception.PersistenceInvalidObjectException;

@Component("inspecaoWriteDataBaseAdapter")
public class InspecaoWriteDataBaseAdapter implements InspecaoPesistence {

	@Autowired
	private InspecaoRepositoryCustom inspecaoRepositoryCustom;

	@Autowired
	private InspecaoWriteMapper inspecaoWriteMapper;

	@Override
	public void saveInspecao(Object obj) {
		if (obj instanceof Inspecao inspecao) {
			var inspecaoEntity = inspecaoWriteMapper.mapToEntity(inspecao);
			inspecaoRepositoryCustom.save(inspecaoEntity);
		} else {
			throw new PersistenceInvalidObjectException(obj);
		}
	}

}
