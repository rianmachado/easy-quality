/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.form.adapter.event.handler;

public interface Handler<T extends Command> {

    void handle(T command);
}
