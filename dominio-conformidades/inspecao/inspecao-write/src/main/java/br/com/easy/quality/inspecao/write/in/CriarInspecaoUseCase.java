/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.write.in;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.easy.quality.inspecao.domain.Inspecao;
import br.com.easy.quality.inspecao.write.out.InspecaoPersistenceWrite;

@Service
public class CriarInspecaoUseCase {

	@Autowired
	private InspecaoPersistenceWrite inspecaoPesistence;

	public void saveInspecao(Inspecao inspecao) {
		inspecao.criarInspecao();
		inspecaoPesistence.saveInspecao(inspecao);
	}

}
