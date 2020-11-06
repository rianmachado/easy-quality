package br.com.easy.quality.form.read.in;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.easy.quality.dto.QuestionarioDTO;
import br.com.easy.quality.form.read.out.QuestionarioPersistenceRead;

@Service
public class ReadQuestionarioUseCase {

	@Autowired
	private QuestionarioPersistenceRead questionarioPersistenceRead;

	public List<QuestionarioDTO> obterQuestionarios() {
		return questionarioPersistenceRead.getAll().get();
	}

	public QuestionarioDTO obterQuestionario(String id) {
		return questionarioPersistenceRead.getById(id).get();
	}

}
