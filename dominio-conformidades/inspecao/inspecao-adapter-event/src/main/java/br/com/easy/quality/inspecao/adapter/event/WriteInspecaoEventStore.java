/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.adapter.event;

public interface WriteInspecaoEventStore  {

	void create(CreateInspecaoHandlerEvent event) ;
	void update(CreateInspecaoHandlerEvent event) ;
}
