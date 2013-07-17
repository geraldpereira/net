package com.byob.web.resource;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.byob.web.resource.exception.WebResourceException;
import com.byob.web.resource.request.RequestWriter;
import com.byob.web.resource.response.ResponseReader;
import com.byob.web.resource.response.StringResponseReader;

/**
 * Default WebResource implementation
 * 
 * @author Gerald Pereira
 * 
 */
public class DefaultWebResource<O> implements WebResource<O> {

	private static final int HTTP_STATUS_MAX = 300;
	private static final int HTTP_STATUS_MIN = 200;
	private static final String CONTENT_TYPE = "Content-type";
	

	private enum Method {
		GET(true, false, false), POST(true, true, true), DELETE(true,
				false, false);

		private final boolean doInput;
		private final boolean doOutput;
		private final boolean allowUserInteraction;

		private Method(final boolean doInput, final boolean doOutput,
				final boolean allowUserInteraction) {
			this.doInput = doInput;
			this.doOutput = doOutput;
			this.allowUserInteraction = allowUserInteraction;
		}
		
		private boolean isDoInput() {
			return doInput;
		}
		
		private boolean isDoOutput() {
			return doOutput;
		}
		
		private boolean isAllowUserInteraction() {
			return allowUserInteraction;
		}
	}

	private String url;
	private final Map<String, String> headers;
	private RequestWriter writer;
	private ResponseReader<O> reader;
	private int readTimeout = 60000;
	
	/**
	 * Constructor
	 */
	public DefaultWebResource() {
		this.headers = new HashMap<String, String>();
	}

	@Override
	public WebResource<O> responseReader(final  ResponseReader<O> reader) {
		this.reader = reader;
		return this;
	}
	
	@Override
	public WebResource<O> url(final String url) {
		this.url = url;
		return this;
	}

	@Override
	public WebResource<O> header(final String key, final String value) {
		this.headers.put(key, value);
		return this;
	}
	
	/**
	 * Sets the content type. You can also call
	 * header("Content-Type","application/json") It is used only if you do a
	 * post() to specify the type of the data sent.
	 * 
	 * @param
	 * @return this WebResource
	 */
	public WebResource<O> contentType(final ContentType type) {
		this.headers.put(CONTENT_TYPE, type.getType());
		return this;
	}
	
	@Override
	public WebResource<O> timeout(int timeout) {
		this.readTimeout = timeout;
		return this;
	}

	@Override
	public O get() throws WebResourceException {
		return call(Method.GET);

	}

	@Override
	public O post(final RequestWriter writer) throws WebResourceException {
		this.writer = writer;
		return call(Method.POST);
	}

	@Override
	public O delete() throws WebResourceException {
		return call(Method.DELETE);
	}
	
	private O call(final Method method) throws WebResourceException {
		HttpURLConnection connection = null;
		try {
			// open url connection
			connection = (HttpURLConnection) new URL(this.url).openConnection();

			// set up url connection to get retrieve information back
			connection.setRequestMethod(method.name());
			connection.setDoInput(method.isDoInput());
			connection.setDoOutput(method.isDoOutput());
			connection.setAllowUserInteraction(method.isAllowUserInteraction());
			connection.setReadTimeout(readTimeout);
			
			for (final Entry<String, String> header : headers
					.entrySet()) {
				connection.setRequestProperty(header.getKey(),
						header.getValue());
			}
			if (method.doOutput) {
				connection.connect();
				if (writer != null) {
					writer.writeRequest(connection.getOutputStream());
				}
			}

			final O result;
			final int responseCode = connection.getResponseCode(); 
			if (responseCode >= HTTP_STATUS_MIN && responseCode < HTTP_STATUS_MAX) {
				result = reader.readResponse(connection.getInputStream());				
			} else {
				final InputStream errorStream = connection.getErrorStream();
				if (errorStream != null){
					String error = new StringResponseReader().readResponse(errorStream);
					throw new WebResourceException(error);
				}
				throw new WebResourceException("Request failed "+responseCode);
			}
			return result;
		} catch (final MalformedURLException e) {
			throw new WebResourceException(e);
		} catch (final IOException e) {
			throw new WebResourceException(e);
		}finally{
			if (connection != null){
				connection.disconnect();
			}
		}
	}


}
