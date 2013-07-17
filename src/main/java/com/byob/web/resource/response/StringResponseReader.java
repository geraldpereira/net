package com.byob.web.resource.response;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.byob.web.resource.exception.WebResourceException;

/**
 * Reads a String response
 * 
 * @author Gerald Pereira
 * 
 */
public class StringResponseReader implements ResponseReader<String> {

	private static final String CHARSET = "UTF-8";
	private static final int BUF_SIZE = 4096;

	private final String charset;
	private final int bufferSize;

	/**
	 * Default constructor : UTF_8 charset and 4096 buffer size
	 */
	public StringResponseReader() {
		this(CHARSET, BUF_SIZE);
	}

	/**
	 * Constructor
	 * 
	 * @param charset
	 * @param bufferSize
	 */
	public StringResponseReader(final String charset, final int bufferSize) {
		this.charset = charset;
		this.bufferSize = bufferSize;
	}

	@Override
	public String readResponse(InputStream stream) throws WebResourceException {
		try {
			final InputStreamReader reader = new InputStreamReader(stream,
					charset);
			final StringBuffer result = new StringBuffer();
			final char[] buf = new char[bufferSize];
			int len = 0;
			while (-1 != (len = reader.read(buf))) {
				result.append(buf, 0, len);
			}
			return result.toString();
		} catch (IOException e) {
			throw new WebResourceException(e);
		}
	}

}
