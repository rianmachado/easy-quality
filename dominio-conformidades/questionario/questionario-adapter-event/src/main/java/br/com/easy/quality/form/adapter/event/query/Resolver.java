/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.form.adapter.event.query;

public interface Resolver<T extends Query> {

    void resolve(T query);
}
