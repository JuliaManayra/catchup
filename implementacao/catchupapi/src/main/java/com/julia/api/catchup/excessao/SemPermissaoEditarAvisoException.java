package com.julia.api.catchup.excessao;


public  class SemPermissaoEditarAvisoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SemPermissaoEditarAvisoException() {
        super();
    }

    public SemPermissaoEditarAvisoException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public SemPermissaoEditarAvisoException(final String message) {
        super(message);
    }

    public SemPermissaoEditarAvisoException(final Throwable cause) {
        super(cause);
    }

}