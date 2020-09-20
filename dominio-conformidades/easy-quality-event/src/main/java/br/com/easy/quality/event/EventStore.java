/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.event;

public interface EventStore  {

	void registrar(Event event) ;
	
}
