package com.objectstyle.demo.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class NewsResource {

	@GET
	public String get() {
		return "Hello, world!";
	}
}
