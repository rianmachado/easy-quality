package br.com.easy.quality.event;

public interface Event {

	 Object getSource();

	 String toJson();
	 
	 String getJson();

	 String gerarGUID();
	 
	 String obterGUID();
	 

}
