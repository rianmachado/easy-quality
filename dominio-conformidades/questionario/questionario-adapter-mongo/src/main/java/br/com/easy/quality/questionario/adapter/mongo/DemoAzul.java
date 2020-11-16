package br.com.easy.quality.questionario.adapter.mongo;

import org.springframework.stereotype.Component;

import br.com.easy.quality.questionario.write.in.IDemo;

@Component
public class DemoAzul  implements IDemo{

	public void escreve() {
		System.out.println("Azul caneta");
		
	}

}
