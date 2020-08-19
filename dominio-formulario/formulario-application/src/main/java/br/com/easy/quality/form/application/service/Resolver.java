package br.com.easy.quality.form.application.service;

import br.com.easy.quality.form.application.query.Query;

public interface Resolver<T extends Query> {

    void resolve(T query);
}
