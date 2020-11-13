package br.com.easy.quality.form.adapter.mongo;

import org.springframework.stereotype.Component;

import br.com.easy.quality.form.write.in.IDemo;

@Component
public class DemoAzul  implements IDemo{

	public void escreve() {
		System.out.println("Azul caneta");
		
	}

}
