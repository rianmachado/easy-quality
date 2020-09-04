/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.read.out;

import java.util.List;
import java.util.Optional;

import br.com.easy.quality.inspecao.dto.InspecaoDTO;

public interface InspecaoReadDataBase {

	Optional<List<InspecaoDTO>> getAll();

	Optional<InspecaoDTO> getById(String titulo);

}
