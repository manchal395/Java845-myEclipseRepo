package com.lti.resource;

import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

/* download jax-rs https://eclipse-ee4j.github.io/jersey/download.html
 * JAX-RS 2.1  Jersey JAX-RS 2.1 RI bundle
 * SOA : Service Oriented Architecture (Web Services)
 * ROA : Resource Oriented Architecture
 * We can develop a Web Service using servlets also. Using JAX-RS is not the only way.
*/

/* Run Process
 * Right click on the project to run and not any file.
 * will get 404, http://localhost:8181/RESTapp/
 * in the url then appendApplicationPath in app.java/Path in this file.
 * http://localhost:8181/RESTapp/api/helloapp
 * Add Advanced Rest Client extension on chrome or download app like Postman, Insomnia
*/

@Path("/helloapp")
public class HelloResource {
	
//	@GET
//	public String hello() {
//		return "Hello from JAX-RS";
//	}
	
	
	
	/* QueryParam; url - http://localhost:8181/RESTapp/api/helloapp?name=Anchal
	 * if we don't pass name, program will run and will show 'null' as name
	 */
	
//	@GET
//	public String hello(@QueryParam("name") String myName) {
//		//query string
//		//QueryParam will read the valued passed in 'name' parameter of url
//		//and will pass it to the myName string parameter of this function
//		return "Hello " + myName + ". Welcome to JAX-RS !";
//	}
	
	
	/* PathParam; url - http://localhost:8181/RESTapp/api/helloapp/Batman
	 * look in url how name is being passed.
	 * If we don't pass the name, we'll get 404 error.
	 * this is the difference between above and below ways.
	 */
	
//	@Path("/{name}")
//	@GET
//	public String hello(@PathParam("name") String myName) {
//		return "Hello " + myName + ". Welcome to JAX-RS !";
//	}
	
	
	/* MatrixParam; url - http://localhost:8181/RESTapp/api/helloapp;name=Batman
	 * study
	 */
	
	@GET
	public String hello(@MatrixParam("name") String myName) {
		return "Hello " + myName + ". Welcome to JAX-RS !";
	}
}
