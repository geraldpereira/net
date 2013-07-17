package com.byob.web.resource.response;

import static junit.framework.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

import com.byob.web.resource.exception.WebResourceException;


public class StringResponseReaderTest {

	private static final String CHARSET = "UTF-8";
	private static final String WRONG_CHARSET = "UTQDQSD";
	private static final String FAKE_STRING = "monpetitponey";

	@Test
	public void successTest() throws WebResourceException, UnsupportedEncodingException{
		final StringResponseReader reader = new StringResponseReader(CHARSET,512);
		final String data = reader.readResponse(new ByteArrayInputStream(FAKE_STRING.getBytes(CHARSET)));
		assertEquals(FAKE_STRING,data);
	}
	
	@Test(expected = WebResourceException.class)
	public void failureTest() throws WebResourceException, UnsupportedEncodingException{
		final StringResponseReader reader = new StringResponseReader(WRONG_CHARSET,512);
		reader.readResponse(new ByteArrayInputStream(FAKE_STRING.getBytes(CHARSET)));
	}
}
