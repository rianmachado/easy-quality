/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.write.common;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.easy.quality.inspecao.adapter.mongo.entity.InspecaoEntity;
import br.com.easy.quality.inspecao.domain.Inspecao;

@Component
public class InspecaoWriteMapper {

	public InspecaoEntity mapToEntity(Inspecao inspecao) {
		InspecaoEntity inspecaoEntity = new InspecaoEntity();
		BeanUtils.copyProperties(inspecao, inspecaoEntity);
		return inspecaoEntity;
	}

	public String mapToStringJson(Inspecao inspecao) throws JsonProcessingException {
		ObjectMapper jsonObject = new ObjectMapper();
		return jsonObject.writeValueAsString(inspecao);
	}

}
