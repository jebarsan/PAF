package com;
import model.Customer;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Customer")

public class CustomerService {
	Customer Obj = new Customer();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	 {
	 return Obj.readItems();
	 }
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertcustomermanagement(@FormParam("user_id") String user_id,
	 @FormParam("mobile") String mobile,
	 @FormParam("com_type") String com_type,
	 @FormParam("com_descript") String com_descript)
	{
	 String output = Obj.insertcustomermanagement(user_id, mobile, com_type, com_descript);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatecustomermanagement(String inquiryData)
	{
	//Convert the input string to a JSON object
	 JsonObject Object = new JsonParser().parse(inquiryData).getAsJsonObject();
	//Read the values from the JSON object
	 String com_id = Object.get("com_id").getAsString();
	 String user_id = Object.get("user_id").getAsString();
	 String mobile = Object.get("mobile").getAsString();
	 String com_type = Object.get("com_type").getAsString();
	 String com_descript = Object.get("com_descript").getAsString();
	 String output = Obj.updatecustomermanagement(com_id, user_id, mobile, com_type, com_descript);
	return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletecustomermanagement(String inquiryData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(inquiryData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String com_id = doc.select("com_id").text();
	 String output = Obj.deletecustomermanagement(com_id);
	return output;
	}

	
}
