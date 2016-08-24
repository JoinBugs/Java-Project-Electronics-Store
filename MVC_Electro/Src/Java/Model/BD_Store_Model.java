import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import java.sql.SQLException;

public class BD_Store_Model {

private Connection con;
private static Statement  senteciaSQL;
private static ResultSet  cdr;

private String URL_bd = "jdbc:mysql://127.0.0.1:3306/bd_Store";	
private String user;
private String pass;

    public BD_Store_Model(String usuario, String contr)
    {
    	user = usuario;
    	pass = contr;
    	
    	try 
    	{
    		String path = "com.mysql.jdbc.Driver"; //Variable Que Guarda La Clase A Instanciar Del Jar Del Driver.
    		Class.forName(path).newInstance();	  // Levantando El Driver Es Decir Poner En Marcha El Driver En Memoria.
		}
		catch (InstantiationException ex) { System.out.println(ex.getMessage()); }
		
		catch (IllegalAccessException ex) { System.out.println(ex.getMessage()); }
		
		catch (ClassNotFoundException ex) { System.out.println(ex.getMessage()); }
    }    	
    
    public void setUser(String usuario) { user = usuario; } //Guarda El Nombre De Usuario.
    
    public void setPassword(String password) { pass = password; } //Guarda El Password Del Usuario.
    
    public boolean conectar()
    {
    	try // Intentar Realizar La Conexion Pormedio Del Driver
    	{
    		con = DriverManager.getConnection(URL_bd, user, pass);
    		senteciaSQL = con.createStatement(cdr.TYPE_SCROLL_INSENSITIVE, cdr.CONCUR_UPDATABLE);	    			
		}
		catch (SQLException ex) 
		{ 
			System.out.println(ex.getMessage()); 
			return false; 
		}
    	
    	return true;
    }
    
	public static ResultSet getPersona(String tabla)
	{
		try 
		{
    		return senteciaSQL.executeQuery("SELECT * FROM " + tabla);
		}
		catch (SQLException ex) 
		{
			System.out.println(ex.getMessage());
			return null;
		}
	}
	
	public static String[] getColumnNames(String tabla)
	{
		try 
		{
    		cdr = senteciaSQL.executeQuery("DESCRIBE " + tabla);
    		cdr.last();
    		
    		String[] labels = new String[cdr.getRow()];
    		cdr.beforeFirst();
    		
    		int j = 0;
    		
    		while (cdr.next()) 
    		{
    			labels[j++] = cdr.getString(1);
			}
			
			return labels;
    	
		}
		catch (SQLException ex)
		{
			System.out.println (ex.getMessage());
			return null;
		}
	}
	
	public static boolean setInsertRegsInTable(String tabla, String[] regs)
	{
		try 
		{
			String values = regs[0];
			
			for(int i = 1; i < regs.length; i++) 
			{
				values += ", "+regs[i];
			}
			
    		senteciaSQL.executeUpdate("INSERT INTO " + tabla + " VALUES( " + values + " )");
		}
		catch (SQLException ex)
		{
			System.out.println(ex.getMessage());
			return false;
		}
		
		return true;
	}
	
	public static boolean doDeleteRegsInTable(String tabla, int id)
	{
		String campo;
		
		try 
		{
			if(tabla.equals("Proveedores"))  campo = tabla.substring(0, tabla.length() - 2) + "ID";
			
			else campo = tabla.substring(0, tabla.length() - 1) + "ID";
			
    		senteciaSQL.executeUpdate("DELETE FROM " + tabla + " WHERE " + campo + " = " + id);
		}
		catch (SQLException ex) 
		{
			System.out.println (ex.getMessage());
			return false;
		}
		
		return true;
	}
	
	public static boolean doUpdateRegsInTable(String tabla, String[] regs)
	{
		String[] campos = getColumnNames(tabla);
		
		String strAsign = campos[1] + "=" + regs[1];
		
		for (int i = 2; i < campos.length; i++)
	    {
			strAsign += ", " + campos[i] + "=" + regs[i];
		}
				System.out.println (strAsign);
		try 
		{
    		senteciaSQL.executeUpdate("UPDATE " + tabla + " SET " + strAsign + " WHERE "+ campos[0] + " = " + regs[0]);
		}
		catch (SQLException ex)
	    {
	    	System.out.println (ex.getMessage());
	    	return false;
		}
		
		return true;
	}
	
}


