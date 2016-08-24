import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JTable;

import javax.swing.JFrame;

public class Agenda_Model {

private String tabla;
private DefaultTableModel dtm;

private ResultSet cdr;
private ResultSetMetaData cdm;
private JTable tblPersonas;

    public Agenda_Model(String tipo)
    {
    	tabla = tipo;		
    }
    
    public void setModelTable()
    {
    	DefaultTableModel dtm = new DefaultTableModel();
    	tblPersonas = new JTable(dtm);
    	
    	String[] titles = new String[1];
    	
    	Object[][] registros = new Object[1][1];
    	
    	try
    	{
	    	cdr = BD_Store_Model.getPersona(tabla);
	    	
	    	if(cdr != null)
	    	{
		    	cdm = cdr.getMetaData();
		    	
		    	int numCols = cdm.getColumnCount();
		    	
		    	titles = new String[numCols];
		    	
		    	for (int i = 0; i < numCols; i++)
		    	{
		    		titles[i] = "<html><b>" + cdm.getColumnLabel(i + 1) + "</b></html>";
				}
		    	
		    	dtm.setColumnIdentifiers(titles);
		    	
		    	Object[] regs = new Object[numCols];
		    	
		    	cdr.last();
		    	
		    	registros = new Object[cdr.getRow()][numCols];
		    	
		    	cdr.beforeFirst();
		    	
		    	int j = 0;
		    	
		    	while (cdr.next()) 
		    	{
		    		for (int i = 0; i < numCols; i++)
		    		{
						regs[i] = cdr.getObject(i + 1);
						registros[j][i] = regs[i];
					}
					j++;
					dtm.addRow(regs);
				}
				
				TableColumn columna = null;
				
				for (int i = 0; i < tblPersonas.getColumnCount(); i++) 
				{
					columna = tblPersonas.getColumnModel().getColumn(i);
						
					columna.setPreferredWidth(30);
				}
				
	    	}
	    	
	    	if(tabla.equals("Empleados"))
	    	{
	    		tblPersonas.setModel(new DefaultTableModel(registros, titles)
	    			{
	    				Class[] tipoColumn = {	java.lang.String.class,
	    										java.lang.String.class,
	    										java.lang.String.class,
	    										java.lang.Integer.class,
	    										java.lang.String.class,
	    										java.lang.String.class,
	    										java.lang.String.class,
	    										java.lang.String.class,
	    										java.lang.Boolean.class	
	    									 };

						boolean[] editColum = {false, true, true, true, true, true, true, true, false};
	    									 	
	    				public Class getColumnClass(int indColum)
	    				{
	    					return tipoColumn[indColum];
	    				}
	    				
	    				public boolean isCellEditable(int indFila, int indColum)
	    				{
	    					return editColum[indColum];
	    				}				
	    			}
	    		);
	    	}
	    	
		}
		catch (SQLException ex) { System.out.println(ex.getMessage()); }
    }
    
    public JTable getModelTable()
    {	
    	return tblPersonas;
    }
}