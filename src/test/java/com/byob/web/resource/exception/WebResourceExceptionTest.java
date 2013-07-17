package com.byob.web.resource.exception;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import com.byob.web.resource.exception.WebResourceException;

public class WebResourceExceptionTest {
	private static final String MESSAGE = "test"; 
	
	@Test
	public void testString() {
		final WebResourceException exception = new WebResourceException(MESSAGE);
		assertEquals(MESSAGE, exception.getMessage());
	}
	
	@Test
	public void testException() {
		final WebResourceException exception = new WebResourceException(new Exception(MESSAGE));
		assertEquals(MESSAGE, exception.getCause().getMessage());
	}
	
	@Test
	public void testExceptionString() {
		final WebResourceException exception = new WebResourceException(MESSAGE, new Exception(MESSAGE));
		assertEquals(MESSAGE, exception.getMessage());
		assertEquals(MESSAGE, exception.getCause().getMessage());
	}
}
