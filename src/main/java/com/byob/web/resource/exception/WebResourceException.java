package com.byob.web.resource.exception;

/**
 * Exception during a Web Resource call
 * 
 * @author Gerald Pereira
 *
 */
public class WebResourceException extends Exception {

	private static final long serialVersionUID = -340366041381437405L;

	/**
	 * Constructor
	 * @param message
	 */
	public WebResourceException(final String message) {
		super(message);
	}

	/**
	 * Constructor
	 * @param t
	 */
	public WebResourceException(final Throwable t) {
		super(t);
	}

	/**
	 * Constructor
	 * @param message
	 * @param exception
	 */
	public WebResourceException(final String message, final Throwable t) {
		super(message, t);
	}
}
