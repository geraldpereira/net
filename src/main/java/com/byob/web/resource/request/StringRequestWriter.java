package com.byob.web.resource.request;

import java.io.IOException;
import java.io.OutputStream;

import com.byob.web.resource.exception.WebResourceException;

/**
 * Writes a String as the request content
 * 
 * @author Gerald Pereira
 *
 */
public class StringRequestWriter implements RequestWriter {

	
	private static final String CHARSET = "UTF-8";

	private final String data;
	private final String charset;
	
	/**
	 * Default constructor : UTF_8 is the charset
	 * @param data the String data
	 */
	public StringRequestWriter(final String data){
		this(data,CHARSET);
	}
	
	/**
	 * Constructor
	 * @param data the String data
	 * @param charset the data charset
	 */
	public StringRequestWriter(final String data,final String charset){
		this.data = data;
		this.charset = charset;
	}
	
	@Override
	public void writeRequest(OutputStream stream) throws WebResourceException {
		try {
			stream.write(data.getBytes(charset));
			stream.close();
		} catch (IOException e) {
			throw new WebResourceException(e);
		}
	}

}
