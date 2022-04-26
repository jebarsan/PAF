package Controller;

import model.BillInfo;


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Bill")

public class Bill {
	BillInfo api=new BillInfo();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	
	public String readItems()
	 {
		return api.readBillInfo();
	 
	 }
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertBillInfo (
		
			@FormParam("BillNo") String BillNo,
			@FormParam("BillDate") String Billdate,
			@FormParam("BillType") String Billtype,
			@FormParam("BillAmmount") String Billammount
			)
		
	{
				
	 String output =api.insertBillInfo(BillNo,Billdate,Billtype,Billammount);
	return output;
	}
	
	//Update API JSON
	
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateBill(String BillData)
		{
		
			//Convert the input string to a JSON object
		 JsonObject BillObject = new JsonParser().parse(BillData).getAsJsonObject();
		
		 //Read the values from the JSON object
		 String BillID =  BillObject.get("BillID").getAsString();
		 String BillNo =  BillObject.get("BillNo").getAsString();
		 String BillDate =  BillObject.get("BillDate").getAsString();
		 String BillType = BillObject.get("BillType").getAsString();
		 int BillAmmount = BillObject.get("BillAmmount").getAsInt();
		
		
		 String output =  api.updateBillInfo(BillID, BillNo, BillDate, BillType, BillAmmount);
		
		 return output;
		}
		

		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		
		public String deleteBilltInfo(String Billdata)
		{
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse( Billdata, "", Parser.xmlParser());

		//Read the value from the element 
		 String BillID = doc.select("BillID").text();
		 String output = api.deleteBillInfo(BillID);
		return output;
		}
}