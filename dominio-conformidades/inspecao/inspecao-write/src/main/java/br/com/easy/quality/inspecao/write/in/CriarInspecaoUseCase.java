/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.write.in;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.easy.quality.inspecao.domain.Inspecao;
import br.com.easy.quality.inspecao.write.out.InspecaoWriteDataBaseAdapter;

@Service
public class CriarInspecaoUseCase {

	@Autowired
	private InspecaoWriteDataBaseAdapter InspecaoWriteDataBaseAdapter;

	public void saveInspecao(Inspecao inspecao) {
		
		//TODO:
		//OBTER O QUESTIONARIO TEMPLATE e SETAR DATA/HORA NO dataDeUsoQuestionarioModelo
		
		
		InspecaoWriteDataBaseAdapter.saveInspecao(inspecao);
	}

}
