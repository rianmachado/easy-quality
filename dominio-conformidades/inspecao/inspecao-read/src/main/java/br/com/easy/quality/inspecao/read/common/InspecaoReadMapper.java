/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.read.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.easy.quality.inspecao.adapter.mongo.entity.InspecaoEntity;
import br.com.easy.quality.inspecao.dto.InspecaoDTO;

@Component
public class InspecaoReadMapper {
	

	public List<InspecaoDTO> mapFromEntityToDto(Iterable<InspecaoEntity> inspecaos) {
		List<InspecaoDTO> dto = new ArrayList<InspecaoDTO>();
		inspecaos.forEach(item -> {
			InspecaoDTO inspecaoDTO = new InspecaoDTO();
			BeanUtils.copyProperties(item, inspecaoDTO);
			dto.add(inspecaoDTO);
		});
		return dto;
	}

	public InspecaoDTO mapFromEntityToDto(InspecaoEntity inspecao) {
		InspecaoDTO dto = new InspecaoDTO();
		BeanUtils.copyProperties(inspecao, dto);
		return dto;
	}

}
