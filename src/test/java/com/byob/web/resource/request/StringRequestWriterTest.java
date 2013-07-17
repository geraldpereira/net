package com.byob.web.resource.request;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

import com.byob.web.resource.exception.WebResourceException;

import static junit.framework.Assert.*;


public class StringRequestWriterTest {

	private static final String CHARSET = "UTF-8";
	private static final String WRONG_CHARSET = "UTQDQSD";
	private static final String FAKE_STRING = "monpetitponey";

	@Test
	public void successTest() throws WebResourceException, UnsupportedEncodingException{
		final StringRequestWriter writer = new StringRequestWriter(FAKE_STRING,CHARSET);
		final ByteArrayOutputStream stream = new ByteArrayOutputStream();
		writer.writeRequest(stream);
		assertEquals(FAKE_STRING,stream.toString(CHARSET));
	}
	
	@Test(expected = WebResourceException.class)
	public void failureTest() throws WebResourceException, UnsupportedEncodingException{
		final StringRequestWriter writer = new StringRequestWriter(FAKE_STRING,WRONG_CHARSET);
		writer.writeRequest( new ByteArrayOutputStream());
	}
}
