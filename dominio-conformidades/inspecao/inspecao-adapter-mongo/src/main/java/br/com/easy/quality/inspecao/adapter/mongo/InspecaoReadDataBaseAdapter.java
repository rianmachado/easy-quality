/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.adapter.mongo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.easy.quality.inspecao.adapter.mongo.entity.InspecaoEntity;
import br.com.easy.quality.inspecao.adapter.mongo.mapper.InspecaoMapper;
import br.com.easy.quality.inspecao.adapter.mongo.repository.InspecaoRepositoryCustom;
import br.com.easy.quality.inspecao.domain.exception.CodeDomainMessage;
import br.com.easy.quality.inspecao.domain.exception.DomainException;
import br.com.easy.quality.inspecao.dto.InspecaoDTO;
import br.com.easy.quality.inspecao.read.out.InspecaoPersistenceRead;

@Component
public class InspecaoReadDataBaseAdapter implements InspecaoPersistenceRead {

	@Autowired
	private InspecaoRepositoryCustom inspecaoRepositoryCustom;

	@Autowired
	private InspecaoMapper inspecaoReadMapper;

	@Override
	public Optional<List<InspecaoDTO>> getAll() {
		Optional<Iterable<InspecaoEntity>> resposta = Optional.of(inspecaoRepositoryCustom.getAll());
		if (resposta.isEmpty()) {
			throw new DomainException(CodeDomainMessage.INFO_LIST_INSPECAO_VAZIA);
		}
		List<InspecaoDTO> ListaConvertida = inspecaoReadMapper.mapFromEntityToDto(resposta.get());
		return Optional.of(ListaConvertida);
	}

	@Override
	public Optional<InspecaoDTO> getById(String id) {
		Optional<InspecaoEntity> resposta = inspecaoRepositoryCustom.findById(id);
		if (!resposta.isPresent()) {
			throw new DomainException(CodeDomainMessage.INFO_INSPECAO_NAO_LOCALIZADA);
		}
		InspecaoDTO dto = inspecaoReadMapper.mapFromEntityToDto(resposta.get());
		return Optional.of(dto);
	}

}
