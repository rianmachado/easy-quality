/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.adapter.event.command;

import br.com.easy.quality.inspecao.adapter.event.command.Command;

public interface Handler<T extends Command> {

    void handle(T command);
}
