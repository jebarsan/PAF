package model;

import com.google.gson.*;
import java.sql.*;
import java.util.Date;
import dbconnection.DBConnection;

public class powerConInfo {
	public String insertpowerConInfo(String pName, String pDate, int pDistrict, int pUnit) {
		String output = "";

		try {

			DBConnection dbconnection = new DBConnection();
			Connection con = dbconnection.connect();

			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			// create a prepared statement
			String query = " insert into powerCons (`powerConID`,`pName`,`pDate`,`pDistrict`,`pUnit`)"
					+ " values (?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, pName);
			preparedStmt.setString(3, pDate);
			preparedStmt.setInt(4, pDistrict);
			preparedStmt.setInt(5, pUnit);

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Inserted successfully";

		} catch (Exception e) {

			output = "Error while inserting the powerCon information.";
			System.err.println(e.getMessage());
		}
		return output;

	}

	public String readpowerConInfo() {

		String output = "";

		try {
			DBConnection dbconnection = new DBConnection();
			Connection con = dbconnection.connect();

			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed

			output = "<body class=\"bg-image\"\n" +
					"style=\"background-image: url('https://motionarray.imgix.net/preview-41462fuhzQiVTjG_0015.jpg');\">"
					+ "<h1 style='text-align:center;margin-top:50px; margin-bottom:50px'>powerCon Management</h1>"
					+ "<table style='margin-left:25%;font-size:24' bpowerCon=\"1\"><tr><th>powerCon ID</th><th>powerCon Name</th><th>powerCon Date</th><th>Quantity</th><th>Unit</th><th>Update</th><th>Remove</th></tr>";

			String query = "select * from powerCons";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set

			while (rs.next()) {

				String ID = Integer.toString(rs.getInt("powerConID"));
				String Name = rs.getString("pName");
				String Date = rs.getString("pDate");
				String District = rs.getString("pDistrict");
				String Unit = rs.getString("pUnit");

				// Add into the html table

				output += "<tr><td>" + ID + "</td>";
				output += "<td>" + Name + "</td>";
				output += "<td>" + Date + "</td>";
				output += "<td>" + District + "</td>";
				output += "<td>" + Unit + "</td>";

				output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
						+ "<input name=\"RegId\" type=\"hidden\" value=\"" + ID + "\">" + "</form></td></tr>";
			}
			con.close();

			// Complete the html table
			output += "</table>";
		}

		catch (Exception e) {
			output = "Error while reading the information.";
			System.err.println(e.getMessage());
		}

		return output;

	}

	public String updatepowerConInfo(String ID, String Name, String Date, int District,
			int Unit) {
		String output = "";

		try {
			DBConnection dbconnection = new DBConnection();
			Connection con = dbconnection.connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE powerCons SET powerConName=?,powerConDate=?,powerConDistrict=?,powerConUnit=? WHERE powerConID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, Name);
			preparedStmt.setString(2, Date);
			preparedStmt.setInt(3, District);
			preparedStmt.setInt(4, Unit);
			preparedStmt.setInt(5, Integer.parseInt(ID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		}

		catch (Exception e) {

			output = "Error while updating the powerCon information.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deletepowerConInfo(String powerConID) {
		String output = "";

		try {

			DBConnection dbconnection = new DBConnection();
			Connection con = dbconnection.connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from powerCons  where powerConID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(powerConID));
			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";

		}

		catch (Exception e) {
			output = "Error while deleting the powerCon.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
