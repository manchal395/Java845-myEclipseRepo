package com.lti.resource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.lti.resource.Passenger.Gender;
import com.lti.resource.Passenger.Status;

/* Scenario: Assume you are working for IRCTC and responsible for developing the PNR API 
 */

@Path("/pnr")
public class PnrResource {
	
	@GET
	//@Produces("application/json") //spelling mistake easily possible
	//Produces used with GET; Consumes used with POST
	@Produces(MediaType.APPLICATION_JSON)
	public Pnr checkPnrStatus(@QueryParam("pnrNo") int pnrNum, @Context HttpServletResponse response) {
		//for the time being we will return some hard coded data
		//n a real app, from here we will hit the DB and fetch the data dynamically
//		try {
//			Thread.sleep(5000);
//		}catch(Exception e) {
//			
//		}
		//enabling CORS
		//response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		response.setHeader("Access-Control-Allow-Origin", "*");
		Pnr pnr = new Pnr();
		pnr.setPnrNo(pnrNum);
		pnr.setTrainNo(12345);
		pnr.setTravelDate(LocalDate.of(2020, 12, 25));
		
		List<Passenger> passengers = new ArrayList<Passenger>();
 		passengers.add(new Passenger("Batman", Gender.MALE, Status.RAC));
		passengers.add(new Passenger("Superman", Gender.MALE, Status.RAC));
		passengers.add(new Passenger("Joker", Gender.MALE, Status.CONFIRMED));
		passengers.add(new Passenger("Wonder Woman", Gender.FEMALE, Status.CONFIRMED));
		
		pnr.setPassengers(passengers);
		
		return pnr;
		
		/* Output JSON
		{
			"passengers": [
				{
					"gender": "MALE",
					"name": "Batman",
					"status": "RAC"
				},
				{
					"gender": "MALE",
					"name": "Superman",
					"status": "RAC"
				},
				{
					"gender": "MALE",
					"name": "Joker",
					"status": "CONFIRMED"
				},
				{
					"gender": "FEMALE",
					"name": "Wonder Woman",
					"status": "CONFIRMED"
				}
			],
			"pnrNo": 1234567890,
			"trainNo": 12345,
			"travelDate": "2020-12-25"
		}
		*/
	}
	
}
