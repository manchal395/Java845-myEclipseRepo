package com.lti.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.lti.dao.ProductDao;
import com.lti.entity.Product;

/* run URLs
 * http://localhost:8181/RESTapp/api/product/222 -- any id
 * http://localhost:8181/RESTapp/api/product/all
 */

/* Errors
 * 404 - url error
 * 500 - @Path error
 * ClassNotFound - jar missing
 */

@Path("/product")
public class ProductResource {

	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Product get(@PathParam("id") int id) {
		ProductDao dao = new ProductDao();
		return dao.select(id);
	}
	
	@Path("/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getAll() {
		ProductDao dao = new ProductDao();
		return dao.selectAll();
	}
	
	@Path("/add")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN) //can be JSON too
	//whatever json client will get stored, we will dynamically receive it as a Product object
	//here, the json will be parsed as class object autmatically?
	/* Steps to run
	 * in the Advanced Rest Client app, give url
	 * 1. http://localhost:8181/RESTapp/api/product/add
	 * 2. Select method as POST
	 * 3. Inside Body, select body content type as application/json
	 * 4. {"id":555,"name":"Redmi Note 9","price":12000}
	 */
	public String post(Product product, @Context HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		ProductDao dao = new ProductDao();
		dao.insert(product);
		return "Product added successfully!";
		//actually we should reply in json format always, this is not good
	}
}
