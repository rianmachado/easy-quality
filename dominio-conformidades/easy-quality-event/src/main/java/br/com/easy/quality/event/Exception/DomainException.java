package br.com.easy.quality.event.Exception;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rianmachado@gmail.com
 */
public class DomainException extends RuntimeException {

	private static final long serialVersionUID = -5521779002961612630L;

	private CodeMsg bundle;

	private final transient Map<String, Object> errors = new HashMap<>();

	public DomainException(CodeMsg bundle, RuntimeException runtimeException) {
		super(bundle.getIdBundle(), runtimeException);
		this.bundle = bundle;
	}
	

	public DomainException(CodeMsg bundle) {
		super(bundle.getIdBundle());
		this.bundle = bundle;
	}

	public CodeMsg getbundle() {
		return bundle;
	}

	public boolean hasError() {
		return !errors.isEmpty();
	}

	public void addError(String key, Object value) {
		errors.put(key, value);
	}

	public Map<String, Object> getErrors() {
		return errors;
	}
	

	public int getCode() {
		return bundle.getCodeError();
	}

}