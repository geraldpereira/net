package com.byob.web.resource.request;

import java.io.OutputStream;

import com.byob.web.resource.exception.WebResourceException;

/**
 * Write an HTTP request content
 * 
 * @author gpereira
 *
 * @param <I>
 */
public interface RequestWriter{

	/**
	 * Writes a request
	 * @param input
	 * @param stream
	 * @throws WebResourceException
	 */
	void writeRequest (OutputStream stream) throws WebResourceException;
}
