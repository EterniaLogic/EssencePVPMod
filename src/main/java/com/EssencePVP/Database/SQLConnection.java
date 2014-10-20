// This file is part of EssencePvP.

// EssencePvP is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.

// EssencePvP is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.

// You should have received a copy of the GNU General Public License
// along with EssencePvP.  If not, see <http://www.gnu.org/licenses/>.

package com.EssencePVP.Database;
//import com.EssencePVP.EssencePVP; // To get configuration

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.omg.CORBA_2_3.portable.InputStream;

public class SQLConnection{
	private boolean isSQLConnection;
	private Connection hConnection;

	public SQLConnection(){ // If we receive an empty constructor then we default to SQLite
		isSQLConnection = false;
		//Connection hConnection = null;
		try{
			Class.forName("org.sqlite.JDBC");
			String sDatabaseURL = "jdbc:sqlite:mods/EssencePVPMod/data.db";
			hConnection = DriverManager.getConnection(sDatabaseURL);
		} catch (Exception eError){
				eError.printStackTrace();
		}
	}

	public SQLConnection(int _iMySQLPort, String _sMySQLDatabase, String _sMySQLUsername, String _sMySQLPassword, String _sMySQLHostname){
		//Connection hConnection = null;
		Statement hStatement = null;
		ResultSet hReturn = null;
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			hConnection = DriverManager.getConnection("jdbc:mysql://"+_sMySQLHostname+
														":"+_iMySQLPort+
														"/"+_sMySQLDatabase+"?"+
														"user="+_sMySQLUsername+
														"&password="+_sMySQLPassword);
		} catch (Exception eError){ 
			eError.printStackTrace();
		}
		try{
			hStatement = hConnection.createStatement();
			hReturn = hStatement.executeQuery("SHOW TABLES LIKE 'Profession';");
			if(hReturn.getFetchSize() == 0){
				java.io.InputStream iSQLResource = this.getClass().getResourceAsStream("SQLInit.sql");
				StringBuilder sBuilder = new StringBuilder();
				if(iSQLResource.available() > 0)
					sBuilder.append(Character.forDigit(iSQLResource.read(), 10));
				hStatement.executeQuery(sBuilder.toString());
			}
		} catch (Exception eError){
			eError.printStackTrace();
		}
	}

	public Connection getConnection(){
		return(this.hConnection);
	}
}