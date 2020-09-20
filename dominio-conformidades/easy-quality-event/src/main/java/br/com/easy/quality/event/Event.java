package br.com.easy.quality.event;

public interface Event {

	 Object getSource();

	 String toJson();

	 String gerarGUID();
	 
	 String obterGUID();
	 

}
