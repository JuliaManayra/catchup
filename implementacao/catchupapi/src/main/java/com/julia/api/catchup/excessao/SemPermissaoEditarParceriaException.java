package com.julia.api.catchup.excessao;


public  class SemPermissaoEditarParceriaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SemPermissaoEditarParceriaException() {
        super();
    }

    public SemPermissaoEditarParceriaException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public SemPermissaoEditarParceriaException(final String message) {
        super(message);
    }

    public SemPermissaoEditarParceriaException(final Throwable cause) {
        super(cause);
    }

}