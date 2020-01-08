package com.julia.api.catchup.excessao;


public  class SemPermissaoEditarPostException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SemPermissaoEditarPostException() {
        super();
    }

    public SemPermissaoEditarPostException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public SemPermissaoEditarPostException(final String message) {
        super(message);
    }

    public SemPermissaoEditarPostException(final Throwable cause) {
        super(cause);
    }

}