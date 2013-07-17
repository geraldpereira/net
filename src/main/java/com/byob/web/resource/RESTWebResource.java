package com.byob.web.resource;

import com.byob.web.resource.exception.WebResourceException;
import com.byob.web.resource.request.StringRequestWriter;
import com.byob.web.resource.response.StringResponseReader;


/**
 * Web resource to call REST Web Services
 * 
 * Content type is set ton application/json
 * String data can be given for POST operations
 * 
 * @author Gerald Pereira
 *
 */
public class RESTWebResource extends DefaultWebResource<String>{
	
	
	/**
	 * Constructor
	 */
	public RESTWebResource(){
		super();
		this.contentType(ContentType.APPLICATION_JSON);
		this.responseReader(new StringResponseReader());
	}
	
	/**
	 * Post a string
	 * @param data
	 * @return
	 * @throws WebResourceException
	 */
	public String post(String data) throws WebResourceException {
		return super.post(new StringRequestWriter(data));
	}
}
