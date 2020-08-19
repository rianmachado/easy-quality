package br.com.easy.quality.form.application.handler;

import br.com.easy.quality.form.application.command.Command;

public interface Handler<T extends Command> {

    void handle(T command);
}
