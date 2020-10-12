/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.adapter.mongo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.easy.quality.inspecao.adapter.mongo.entity.InspecaoEntity;
import br.com.easy.quality.inspecao.domain.Inspecao;
import br.com.easy.quality.inspecao.dto.InspecaoDTO;

@Component
public class InspecaoMapper {

	public InspecaoEntity mapToEntity(Inspecao inspecao) {
		InspecaoEntity inspecaoEntity = new InspecaoEntity();
		BeanUtils.copyProperties(inspecao, inspecaoEntity);
		return inspecaoEntity;
	}

	public String mapToStringJson(Inspecao inspecao) throws JsonProcessingException {
		ObjectMapper jsonObject = new ObjectMapper();
		return jsonObject.writeValueAsString(inspecao);
	}

	
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
