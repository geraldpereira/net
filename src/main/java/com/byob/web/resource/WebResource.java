package com.byob.web.resource;

import com.byob.web.resource.exception.WebResourceException;
import com.byob.web.resource.request.RequestWriter;
import com.byob.web.resource.response.ResponseReader;

/**
 * Performs HTTP requests.
 * 
 * It can be is used to call a REST services.
 * 
 * @author Gerald Pereira
 * 
 */
public interface WebResource<O> {

	/**
	 * Sets the response reader for this WebResource
	 * @param reader
	 * @return this webResource
	 */
	WebResource<O> responseReader(ResponseReader<O> reader);
	
	/**
	 * Sets the http url to call
	 * 
	 * @param url
	 * @return this WebResource
	 */
	WebResource<O> url(String url);
	
	
	/**
	 * Sets the read timeout
	 * 
	 * @param timeout
	 * @return this WebResource
	 */
	WebResource<O> timeout(int timeout);

	/**
	 * Adds a header
	 * 
	 * @param key
	 *            the header name
	 * @param value
	 *            the header value
	 * @return this WebResource
	 */
	WebResource<O> header(String key, String value);

	/**
	 * Performs an HTTP GET request. Warning : you must call the method url()
	 * before doing a get().
	 */
	O get() throws WebResourceException;

	/**
	 * Performs an HTTP POST request.
	 * 
	 * Warning : you must call the methods url(), contentType() and data()
	 * before doing a post().
	 * 
	 * @param writer
	 */
	O post(RequestWriter writer) throws WebResourceException;

	/**
	 * Performs an HTTP DELETE request. Warning : you must call the method url()
	 * before doing a get().
	 */
	O delete() throws WebResourceException;


}
