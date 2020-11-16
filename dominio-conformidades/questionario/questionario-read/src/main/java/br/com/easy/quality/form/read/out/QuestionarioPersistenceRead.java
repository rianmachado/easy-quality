/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.form.read.out;

import java.util.List;
import java.util.Optional;

import br.com.easy.quality.questionario.dto.QuestionarioDTO;

public interface QuestionarioPersistenceRead {

	Optional<List<QuestionarioDTO>> getAll();

	Optional<QuestionarioDTO> getById(String titulo);

}
