/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.read.in;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.easy.quality.inspecao.dto.InspecaoDTO;
import br.com.easy.quality.inspecao.read.out.InspecaoPersistenceRead;

@Service
public class ReadInspecaoUseCase {

	@Autowired
	private InspecaoPersistenceRead inspecaoPesistence;

	public List<InspecaoDTO> obterInspecoes() {
		return inspecaoPesistence.getAll().get();
	}

	public InspecaoDTO obterInspecao(String id) {
		return inspecaoPesistence.getById(id).get();
	}

}
