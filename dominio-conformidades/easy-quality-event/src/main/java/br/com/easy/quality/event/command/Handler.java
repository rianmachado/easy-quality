/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.event.command;

public interface Handler<T extends Command> {

    void handle(T command);
}
