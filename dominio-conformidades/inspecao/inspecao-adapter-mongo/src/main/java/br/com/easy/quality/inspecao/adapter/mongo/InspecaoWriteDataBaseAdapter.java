/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.adapter.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.easy.quality.inspecao.adapter.mongo.mapper.InspecaoMapper;
import br.com.easy.quality.inspecao.adapter.mongo.repository.InspecaoRepositoryCustom;
import br.com.easy.quality.inspecao.domain.Inspecao;
import br.com.easy.quality.inspecao.write.out.InspecaoPersistenceWrite;

@Component("inspecaoWriteDataBaseAdapter")
public class InspecaoWriteDataBaseAdapter implements InspecaoPersistenceWrite {

	@Autowired
	private InspecaoRepositoryCustom inspecaoRepositoryCustom;

	@Autowired
	private InspecaoMapper inspecaoMapper;

	@Override
	public void saveInspecao(Inspecao inspecao) {
			var inspecaoEntity = inspecaoMapper.mapToEntity(inspecao);
			inspecaoRepositoryCustom.save(inspecaoEntity);
	}

}
