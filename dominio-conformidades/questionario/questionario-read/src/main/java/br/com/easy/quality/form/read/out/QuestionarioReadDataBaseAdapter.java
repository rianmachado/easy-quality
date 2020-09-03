/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.form.read.out;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.easy.quality.dto.QuestionarioDTO;
import br.com.easy.quality.form.adapter.mongo.entity.QuestionarioEntity;
import br.com.easy.quality.form.adapter.mongo.repository.QuestionarioRepositoryCustom;
import br.com.easy.quality.form.domain.exception.CodeDomainMessage;
import br.com.easy.quality.form.domain.exception.DomainException;
import br.com.easy.quality.form.read.common.QuestionarioReadMapper;

@Component
public class QuestionarioReadDataBaseAdapter implements QuestionarioReadDataBase {

	@Autowired
	private QuestionarioRepositoryCustom formVerificacaoRepositoryCustom;

	@Autowired
	private QuestionarioReadMapper formVerificacaoMapper;

	@Override
	public Optional<List<QuestionarioDTO>> getAll() {
		Optional<Iterable<QuestionarioEntity>> resposta = Optional.of(formVerificacaoRepositoryCustom.getAll());
		if (resposta.isEmpty()) {
			throw new DomainException(CodeDomainMessage.INFO_LIST_QUESTIONARIO_VAZIO);
		}
		List<QuestionarioDTO> ListaConvertida = formVerificacaoMapper.mapFromEntityToDto(resposta.get());
		return Optional.of(ListaConvertida);
	}

	@Override
	public Optional<QuestionarioDTO> getById(String id) {
		Optional<QuestionarioEntity> resposta = formVerificacaoRepositoryCustom.findById(id);
		if (!resposta.isPresent()) {
			throw new DomainException(CodeDomainMessage.INFO_QUESTIONARIO_NAO_LOCALIZADO);
		}
		QuestionarioDTO dto = formVerificacaoMapper.mapFromEntityToDto(resposta.get());
		return Optional.of(dto);
	}

}
