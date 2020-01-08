package com.julia.api.catchup.excessao;


public  class SemPermissaoEditarAreaAtuacaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SemPermissaoEditarAreaAtuacaoException() {
        super();
    }

    public SemPermissaoEditarAreaAtuacaoException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public SemPermissaoEditarAreaAtuacaoException(final String message) {
        super(message);
    }

    public SemPermissaoEditarAreaAtuacaoException(final Throwable cause) {
        super(cause);
    }

}