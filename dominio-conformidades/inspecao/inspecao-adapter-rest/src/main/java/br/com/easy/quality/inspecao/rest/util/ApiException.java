/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.rest.util;

import lombok.Getter;

public class ApiException extends Exception{
	private static final long serialVersionUID = 1842818328340992937L;
	@Getter
    private int code;
    public ApiException (int code, String msg) {
        super(msg);
        this.code = code;
    }
}
