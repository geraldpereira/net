package com.byob.web.resource.response;

import java.io.InputStream;

import com.byob.web.resource.exception.WebResourceException;

/**
 * Read an HTTP response content
 * 
 * @author gpereira
 *
 * @param <O>
 */
public interface ResponseReader<O>{

	/**
	 * Reads a response
	 * @param stream
	 * @return the response
	 * @throws WebResourceException
	 */
	O readResponse (InputStream stream) throws WebResourceException;
}
