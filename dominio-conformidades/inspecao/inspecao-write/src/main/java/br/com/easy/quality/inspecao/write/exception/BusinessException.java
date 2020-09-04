package br.com.easy.quality.inspecao.write.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rianmachado@gmail.com
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -5521779002961612630L;

	private ICodeBusinessMessage bundle;

	private final transient Map<String, Object> errors = new HashMap<>();

	public BusinessException(ICodeBusinessMessage bundle, RuntimeException runtimeException) {
		super(bundle.getIdBundle(), runtimeException);
		this.bundle = bundle;
	}

	public BusinessException(ICodeBusinessMessage bundle) {
		super(bundle.getIdBundle());
		this.bundle = bundle;
	}

	public ICodeBusinessMessage getbundle() {
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

}