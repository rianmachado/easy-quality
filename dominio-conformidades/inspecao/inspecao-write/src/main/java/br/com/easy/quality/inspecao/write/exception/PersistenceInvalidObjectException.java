/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.write.exception;

public class PersistenceInvalidObjectException extends RuntimeException {

	private static final long serialVersionUID = 2302464780017859403L;

	public PersistenceInvalidObjectException(Object persistence) {
		super(String.format("Persistence does not recognizes Object of type: %s",
				persistence.getClass().getCanonicalName()));
	}
}
