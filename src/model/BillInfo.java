package model;

import java.sql.*;
//import java.util.Date;
import dbconnection.DBConnection;

public class BillInfo {
			public String insertBillInfo( String BillNo, String BillDate, String BillType, String BillAmmount)
			{
				String output = "";
				
				try
				 {
				 
					DBConnection dbconnection = new DBConnection();
					Connection con = dbconnection.connect();
					
					if (con == null)
					{return "Error while connecting to the database for inserting."; } 
				
					// create a prepared statement
					String query = " insert into Bill (`BillID`,`BillNo`,`BillDate`,`BillType`,`BillAmmount`)"
							+ " values (?, ?, ?, ?, ?)";
				
					PreparedStatement preparedStmt = con.prepareStatement(query);
			
					// binding values
					 preparedStmt.setInt(1, 0);
					 preparedStmt.setString(2,BillNo);
					 preparedStmt.setString(3,BillDate);
					 preparedStmt.setString(4,BillType);
					 preparedStmt.setString(5,BillAmmount);	
					
					 
					 
					// execute the statement
					 preparedStmt.execute();
					 con.close(); 
					 
					 output = "Inserted successfully";
				  
					 
				 } 
					 catch (Exception e)
					 {
					
						 output = "Error while inserting the Bill information.";
						 System.err.println(e.getMessage());
					 }
					 	return output;
					
					 	
					 	
					 	
				 } 
			public String readBillInfo() 	 
			{		 
				
				String output = ""; 	 
					 
				try
				 {
					DBConnection dbconnection = new DBConnection();
					Connection con = dbconnection.connect();
				
				 if (con == null)
				 {return "Error while connecting to the database for reading."; }  
					 
					 
				// Prepare the html table to be displayed 
				 
				 output = "<body class=\"bg-image\"\n" + 
				 		"style=\"background-image: url('https://previews.123rf.com/images/samarttiw/samarttiw1603/samarttiw160300020/55867934-medical-abstract-science-background-illustrations.jpg');\">" 
						+ "<h1 style='text-align:center;margin-top:50px; margin-bottom:50px'>Bill Management</h1>"
				 		+ "<table style='margin-left:25%;font-size:24' border=\"1\"><tr><th>Bill ID</th><th>Bill No</th><th>Bill Date</th><th>Bill Type</th><th>Bill Ammount</th><th>Update</th><th>Remove</th></tr>"; 

				 
				 
				 
				 String query = "select * from Bill";
				 Statement stmt = con.createStatement();
				 ResultSet rs = stmt.executeQuery(query); 
				 
				 
				// iterate through the rows in the result set
				 
				 while (rs.next()) 
				 {

					 String BillID = Integer.toString(rs.getInt("BillID")); 
					 String Billno		=	rs.getString("BillNo"); 
					 String BillDate		= 	rs.getString("BillDate");
					 String BillType		= 	rs.getString("BillType");
					 String BillAmmount    =	rs.getString("BillAmmount");
		
				 
					// Add into the html table

					 
					 output += "<tr><td>" + BillID + "</td>"; 
					 output += "<td>" + Billno + "</td>"; 
					 output += "<td>" + BillDate + "</td>"; 
					 output += "<td>" + BillType+ "</td>"; 
					 output += "<td>" +BillAmmount  + "</td>"; 					
					 
					 
			
						output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
								+ "<td><form method=\"post\" action=\"\">"
								+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
								+ "<input name=\"RegId\" type=\"hidden\" value=\"" + BillID  + "\">" + "</form></td></tr>";
				 }
					 con.close();
					 
					 // Complete the html table
					 output += "</table>";
					 }
			
				 catch (Exception e)
				 {
					 output = "Error while reading the information.";
					 System.err.println(e.getMessage());
				 }
				 	
				 return output;
				 	
			
				 	 
			 } 		
			 
			public String updateBillInfo(String BillID ,String BillNo, String BillDate, String BillType, int BillAmmount)
			{ 
			String output = "";  
				 
				 
			try
			 {
				DBConnection dbconnection = new DBConnection();
				Connection con = dbconnection.connect();
					
					if (con == null)
					{return "Error while connecting to the database for updating."; }
			
					// create a prepared statement
			 String query = "UPDATE Bill SET BillNo=?,BillDate=?,BillType=?,BillAmmount=? WHERE BillID=?";
				 
				 
			 PreparedStatement preparedStmt = con.prepareStatement(query);	 
				 
				 
			// binding values
			 preparedStmt.setString(1, BillNo); 
			 preparedStmt.setString(2, BillDate);  
			 preparedStmt.setString(3,  BillType); 	
			 preparedStmt.setInt(4, BillAmmount);   
			 preparedStmt.setInt(5, Integer.parseInt(BillID));

			 
			// execute the statement
			 preparedStmt.execute();
			 con.close();
			 
			 output = "Updated successfully";
			 
			 } 
			
			catch (Exception e)
			 {
			 
				output = "Error while updating the Bill information.";
				System.err.println(e.getMessage());
			 } 
			
			
			return output;
			} 

			public String deleteBillInfo(String BillID)
			{
				String output = ""; 
				
				try
				 {
				
					DBConnection dbconnection = new DBConnection();
					Connection con = dbconnection.connect();
				 
				 if (con == null)
				 {return "Error while connecting to the database for deleting."; }
				
				// create a prepared statement
				 String query="delete from Bill  where BillID=?";
				 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				
				// binding values
				 preparedStmt.setInt(1, Integer.parseInt(BillID)); 
				 
				// execute the statement
				 preparedStmt.execute();
				 con.close();
				 
				 output = "Deleted successfully";
				
				 }
				 
			
			
			catch (Exception e)
			 {
					output = "Error while deleting the Bill.";
					System.err.println(e.getMessage());
			 }
			 
				return output;
			 } 
			
			
	}
