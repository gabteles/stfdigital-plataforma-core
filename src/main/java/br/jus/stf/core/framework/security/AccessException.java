package br.jus.stf.core.framework.security;

/**
 * @author lucas.rodrigues
 *
 */
public class AccessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AccessException(String msg) {
		super(msg);
	}

}
