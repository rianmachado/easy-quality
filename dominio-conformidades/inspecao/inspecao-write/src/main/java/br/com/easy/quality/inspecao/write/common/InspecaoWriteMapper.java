/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.write.common;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.easy.quality.inspecao.adapter.mongo.entity.InspecaoEntity;
import br.com.easy.quality.inspecao.domain.Inspecao;

@Component
public class InspecaoWriteMapper {

	public InspecaoEntity mapToEntity(Inspecao inspecao) {
		InspecaoEntity inspecaoEntity = new InspecaoEntity();
		BeanUtils.copyProperties(inspecao, inspecaoEntity);
		return inspecaoEntity;
	}

}
