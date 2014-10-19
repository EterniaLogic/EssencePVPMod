package com.EssencePVP.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.omg.CORBA_2_3.portable.InputStream;

import com.EssencePVP.EssencePVP;

// Common handler for sqlite and mysql
public class SQLDatabase {
	Connection conn;
	Statement st = null;
    ResultSet rs = null;
    
	public SQLDatabase(){
		if(EssencePVP.getInstance().isbUseMySQL()){
			try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://"+EssencePVP.getInstance().getbMySQLHostname()+":"+EssencePVP.getInstance().getiMySQLPort()+"/"+EssencePVP.getInstance().getsMySQLDatabase()+""?" +
				                                   "user="+EssencePVP.getInstance().getsMySQLUsername()+"&password="+EssencePVP.getInstance().getsMySQLPassword());
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}else{
			try {
				Class.forName("org.sqlite.JDBC");
				String dbURL = "jdbc:sqlite:mods/EssencePVPMod/data.db";
		        conn = DriverManager.getConnection(dbURL);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// Done with connection, next set up tables if they do not exist
		try{
			st = conn.createStatement();
			rs = st.executeQuery("SHOW TABLES LIKE 'Profession';");
			// determine if a table exists
			if(rs.getFetchSize() == 0){
				// Import information from .sql file!
				InputStream is = this.getClass().getResourceAsStream("SQLInit.sql");
				st.execute(is.read_string());
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
