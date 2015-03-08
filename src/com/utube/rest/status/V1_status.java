package com.utube.rest.status;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.*;
import com.utube.dao.*;

@Path("/v1/status")
public class V1_status {
	
	private static final String api_version="00.02.00";
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle(){
		return "<p>Java Web Service</p>";
	}
	
	@Path("/version/")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion(){
		return "Version:"+api_version;
	}
	
	@Path("/database")
	@GET
	@Produces("text/html")
	public String returnDatabaseStatus() throws Exception{
		PreparedStatement query = null;
		String myString=null;
		String returnString=null;
		Connection conn = null;
		try{
			conn = Oracle308Tube.Oracle308tubeConn().getConnection();
			query = conn.prepareStatement("select to_char(sysdate, 'YYYY-MM-DD HH24:MI:SS') DATETIME "+"from sys.dual");
			ResultSet rs = query.executeQuery();
			while(rs.next()){
				myString = rs.getString("DATETIME");
			}
			query.close();
			returnString="<p>Database Status:</p>"+"<p>Database Date/Time return: "+myString+"</p>";
		}catch(Exception e){
			
		}finally{
			if(conn!=null)conn.close();
		}
		return returnString;
	}
}
