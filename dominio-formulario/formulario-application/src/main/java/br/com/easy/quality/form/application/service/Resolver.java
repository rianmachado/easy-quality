/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.form.application.service;

import br.com.easy.quality.form.adapter.event.query.Query;

public interface Resolver<T extends Query> {

    void resolve(T query);
}
