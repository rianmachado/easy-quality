/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.write.out;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.easy.quality.inspecao.event.CreateInspecaoEvent;
import br.com.easy.quality.inspecao.publish.TransactionalEventCreateInspecao;

@Component("inspecaoWriteMessageAdapter")
public class InspecaoWriteMessageAdapter {

	@Autowired
	private TransactionalEventCreateInspecao transactionalEventCreateInspecao;

	public CompletableFuture<Void> onEvent(CreateInspecaoEvent createInspecaoEvent) {
		return CompletableFuture.runAsync(() -> {
			transactionalEventCreateInspecao.registrar(createInspecaoEvent);
		});
	}

}
