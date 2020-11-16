/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.questionario.adapter.mongo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.easy.quality.form.read.out.QuestionarioPersistenceRead;
import br.com.easy.quality.questionario.adapter.mongo.entity.QuestionarioEntity;
import br.com.easy.quality.questionario.adapter.mongo.mapper.QuestionarioReadMapper;
import br.com.easy.quality.questionario.adapter.mongo.repository.QuestionarioRepositoryCustom;
import br.com.easy.quality.questionario.domain.exception.CodeDomainMessage;
import br.com.easy.quality.questionario.domain.exception.DomainException;
import br.com.easy.quality.questionario.dto.QuestionarioDTO;

@Component
public class QuestionarioReadDataBaseAdapter implements QuestionarioPersistenceRead {

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
