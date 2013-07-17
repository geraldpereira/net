package com.byob.web.resource;

/**
 * The type of content type
 * 
 * @author Gerald Pereira
 * 
 */
public enum ContentType {
	APPLICATION_XML("application/xml"), APPLICATION_JSON("application/json"), TEXT_PLAIN("text/plain");

	private final String type;

	private ContentType(final String type) {
		this.type = type;
	}

	/**
	 * Returns the String content type
	 * @return
	 */
	public String getType() {
		return this.type;
	}
}
