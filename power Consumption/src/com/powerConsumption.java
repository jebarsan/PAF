package com;

import model.powerConInfo;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/powerCon")

public class powerConsumption {
	powerConInfo api = new powerConInfo();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)

	public String readItems() {
		return api.readpowerConInfo();

	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertpowerConInfo(

			@FormParam("pName") String Name,
			@FormParam("pDate") String Date,
			@FormParam("pQuantity") int Quantity,
			@FormParam("pUnit") int Unit)

	{

		String output = api.insertpowerConInfo(Name, Date, Quantity, Unit);
		return output;
	}

	// Update API JSON

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatepowerCon(String powerConData) {

		// Convert the input string to a JSON object
		JsonObject powerConObject = new JsonParser().parse(powerConData).getAsJsonObject();

		// Read the values from the JSON object
		String powerConID = powerConObject.get("powerConID").getAsString();
		String powerConName = powerConObject.get("pName").getAsString();
		String powerConDate = powerConObject.get("pDate").getAsString();
		int powerConQuantity = powerConObject.get("pQuantity").getAsInt();
		int powerConUnit = powerConObject.get("pUnit").getAsInt();

		String output = api.updatepowerConInfo(powerConID, powerConName, powerConDate, powerConQuantity, powerConUnit);

		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)

	public String deletepowerConInfo(String powerConData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(powerConData, "", Parser.xmlParser());

		// Read the value from the element
		String powerConID = doc.select("powerConID").text();
		String output = api.deletepowerConInfo(powerConID);
		return output;
	}
}