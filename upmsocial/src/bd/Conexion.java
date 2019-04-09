package bd;

import java.sql.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.naming.NamingContext;

public class Conexion {
	    
    private DataSource ds;
    private Connection conn;

	public Conexion() {
	      InitialContext ctx;
	        try {
	            ctx = new InitialContext();
	            NamingContext envCtx = (NamingContext) ctx.lookup("java:comp/env");
	            ds = (DataSource) envCtx.lookup("jdbc/upmsocial");
	            conn = ds.getConnection();
	        } catch (NamingException e) {
	            e.printStackTrace();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
			
	public Connection getConn() {
		return this.conn;
	}
	
	public DataSource getDS() {
		return this.ds;
	}
}
