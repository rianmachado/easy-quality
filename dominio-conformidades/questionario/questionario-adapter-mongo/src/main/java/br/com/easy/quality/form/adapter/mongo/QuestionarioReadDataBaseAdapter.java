/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.form.adapter.mongo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.easy.quality.dto.QuestionarioDTO;
import br.com.easy.quality.form.adapter.mongo.mapper.QuestionarioReadMapper;
import br.com.easy.quality.form.read.out.QuestionarioPersistenceRead;

@Component
public class QuestionarioReadDataBaseAdapter implements QuestionarioPersistenceRead {

//	@Autowired
//	private QuestionarioRepositoryCustom formVerificacaoRepositoryCustom;

	@Autowired
	private QuestionarioReadMapper formVerificacaoMapper;

	@Override
	public Optional<List<QuestionarioDTO>> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<QuestionarioDTO> getById(String titulo) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public Optional<List<QuestionarioDTO>> getAll() {
//		Optional<Iterable<QuestionarioEntity>> resposta = Optional.of(formVerificacaoRepositoryCustom.getAll());
//		if (resposta.isEmpty()) {
//			throw new DomainException(CodeDomainMessage.INFO_LIST_QUESTIONARIO_VAZIO);
//		}
//		List<QuestionarioDTO> ListaConvertida = formVerificacaoMapper.mapFromEntityToDto(resposta.get());
//		return Optional.of(ListaConvertida);
//	}
//
//	@Override
//	public Optional<QuestionarioDTO> getById(String id) {
//		Optional<QuestionarioEntity> resposta = formVerificacaoRepositoryCustom.findById(id);
//		if (!resposta.isPresent()) {
//			throw new DomainException(CodeDomainMessage.INFO_QUESTIONARIO_NAO_LOCALIZADO);
//		}
//		QuestionarioDTO dto = formVerificacaoMapper.mapFromEntityToDto(resposta.get());
//		return Optional.of(dto);
//	}

}
