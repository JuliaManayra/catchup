package com.julia.api.catchup.excessao;


public  class RecursoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RecursoNaoEncontradoException() {
        super();
    }

    public RecursoNaoEncontradoException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public RecursoNaoEncontradoException(final String message) {
        super(message);
    }

    public RecursoNaoEncontradoException(final Throwable cause) {
        super(cause);
    }

}