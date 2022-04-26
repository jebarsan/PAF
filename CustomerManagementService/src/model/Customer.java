package model;
import java.sql.*;
public class Customer {
	//A common method to connect to the DB
		private Connection connect()
		 {
		 Connection con = null;
		 try
		 {
		 Class.forName("com.mysql.jdbc.Driver");

		
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/inshaf", "root", "");
		 }
		 catch (Exception e)
		 {e.printStackTrace();}
		 return con;
		 }
		public String insertcustomermanagement(String user_id,String mobile, String com_type, String com_descript )
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
			 return "Error while connecting to the database for inserting."; 
		 }
		 
		 // create a prepared statement
		 String query = " insert into complaint (`com_id`,`user_id`,`mobile`,`com_type`,`com_descript`)" + " values (?, ?, ?, ?, ?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setString(2, user_id);
		 preparedStmt.setString(3, mobile);
		 preparedStmt.setString(4, com_type);
		 preparedStmt.setString(5, com_descript);
		// execute the statement
		
		 preparedStmt.execute();
		 con.close();
		 output = "Inserted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while inserting the item.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		public String readItems()
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
			 
		 {return "Error while connecting to the database for reading."; }
		 
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>User ID</th>" +
		 "<th>Mobile Number</th>" +
		 "<th>Complaint Type</th>" +
		 "<th>Complaint Description</th>"+
		 "<th>Update</th><th>Remove</th></tr>";

		 String query = "select * from complaint";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
		 String com_id = Integer.toString(rs.getInt("com_id"));
		 String user_id = rs.getString("user_id");
		 String mobile = rs.getString("mobile");
		 String com_type =rs.getString("com_type");
		 String com_descript = rs.getString("com_descript");
		
		 
		 
		 output += "<tr><td>" + user_id + "</td>";
		 output += "<td>" + mobile + "</td>";
		 output += "<td>" + com_type + "</td>";
		 output += "<td>" + com_descript + "</td>";

		
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
				 + "<td><form method='post' action='customer.jsp'>"
				 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
				 + "<input name='com_id' type='hidden' value='" + com_id
				 + "'>" + "</form></td></tr>";
				 }
				 con.close();
				
				 output += "</table>";
				 } 
		 catch (Exception e)
		 {
		 output = "Error while reading the Datas.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		public String updatecustomermanagement(String com_id, String user_id, String mobile, String com_type, String com_descript)
		
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		 String query = "UPDATE complaint SET user_id=?,mobile=?, com_type=?, com_descript=? WHERE com_id=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 
		 preparedStmt.setString(1, user_id);
		 preparedStmt.setString(2, mobile);
		 preparedStmt.setString(3, com_type);
		 preparedStmt.setString(4, com_descript);

		 preparedStmt.setInt(5, Integer.parseInt(com_id));
		 
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while updating the item.";
		 System.out.println(e.getMessage());
		 }
		 return output;
		 }
		public String deletecustomermanagement(String com_id)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from complaint where com_id=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(com_id));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while deleting the item.";
		 System.out.println(e.getMessage());
		 }
		 return output;
		 }
		} 

