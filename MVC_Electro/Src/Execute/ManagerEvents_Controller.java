import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import javax.swing.JOptionPane;
import java.awt.Toolkit;

public class ManagerEvents_Controller extends WindowAdapter implements ActionListener
{
	private Root comp;
	private Agenda_Vista agen;
	private String our;
	
	private JFileChooser open, save;
	
	private Login_Vista state;
	
	private Object[] values;
	
	//@overload
	public ManagerEvents_Controller(Root com)
	{
		comp = com;
		agen = null;
		our = "Created By\nCopyRight(c)\n\n     Andres Cornejo Cruz";
		open = new JFileChooser();
		save = new JFileChooser();
	}
	
	public ManagerEvents_Controller(Agenda_Vista age)
	{ 
		agen = age;
		comp = null;	
	}
	
	public ManagerEvents_Controller() {}
	
		//override
		public void actionPerformed(ActionEvent e)
		{
			if(agen == null)
			{
				 if(e.getSource() == comp.mniOpenF)
				 {
					open.showOpenDialog(comp);	
				 }	
				 else if(e.getSource() == comp.mniSave)
				 {
					save.showSaveDialog(comp);
				 }
				 else if(e.getSource() == comp.mniAcerca)
				 {
					JOptionPane.showMessageDialog(comp, our, "Acerca De Nosotros", JOptionPane.INFORMATION_MESSAGE);
				 }
				 else if(e.getSource() == comp.mniCerrar)
				 {
					System.out.print("\nln49");
					comp.dispose();
					new Login_Vista(null, false);
				 }
				 else if(e.getSource() == comp.mniUser)
				 {
					state = new Login_Vista(comp, true);
					if(!state.isCancel()) new Settings_Vista(comp, true, "Cambiar Nombre De Usuario");
				 }
				 else if(e.getSource() == comp.mniPass)
				 {
					state = new Login_Vista(comp, true);
					if(!state.isCancel()) new Settings_Vista(comp, true, "Cambiar Password De Usuario");
				 }
				 else
				 {
					System.exit(0);
				 }
			}
			 else
			{
				 if(e.getSource() == agen.btnAdd)
				 {
					new Regs_Vista(comp, true, "Agregar", agen.tipo, null);
				 }
				 else if(e.getSource() == agen.btnModify || e.getSource() == agen.mniMod)
				 {
				 	int indexRow = agen.tblRgs.getSelectedRow();
				 	
				 	System.out.println (indexRow);
				 	
				 	if(indexRow > -1)
				 	{
				 		initValues(indexRow);
				 		new Regs_Vista(comp, true, "Modificar", agen.tipo, values);
				 	} 
				 	else
				 	{
				 		Toolkit.getDefaultToolkit().beep();
				 		JOptionPane.showMessageDialog(agen, "No Haz Seleccionado Alguna Fila De La Tabla", "INFORMACION", JOptionPane.INFORMATION_MESSAGE);
				 	}
				 }
				 else if(e.getSource() == agen.btnDelete)
				 {
				 	int indexRow = agen.tblRgs.getSelectedRow();
				 	
				 	if(indexRow > -1)
				 	{
				 		initValues(indexRow);
				 		new Regs_Vista(comp, true, "Eliminar", agen.tipo, values);
				 	} 
				 	else 
						new Regs_Vista(comp, true, "Eliminar", agen.tipo, null);	
				 }
				 else if(e.getSource() == agen.mniDel)
				 {
				 	int indRow = agen.tblRgs.getSelectedRow();
				 	
				 	String clave = "" +agen.tblRgs.getValueAt(indRow, 0);
				 	
				 	int option = JOptionPane.showConfirmDialog(agen, "Deseas Eliminar El Registro \n '" +clave + "' Con El Nombre De '" + agen.tblRgs.getValueAt(indRow, 1) + "'", "Quieres Continuar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				 	
				 	if(option == JOptionPane.YES_OPTION)
				 	{
				 		String tabla = agen.tipo;
				 		
					 	if		(tabla.equals("Proveedores"))	clave = "ProveedorID = "+ clave;
					 	
					 	else if (tabla.equals("Empleados"))		clave = "EmpleadoID = "	+ clave;
					 	
					 	else 									clave = "ClienteID = "	+ clave;
					 	
					 	boolean successful = BD_Store_Model.doDeleteRegsInTable(agen.tipo, clave);
					 	
					 	if(successful) JOptionPane.showMessageDialog(agen, "Registro Eliminado Exitosamente", "INFORMACION", JOptionPane.INFORMATION_MESSAGE);
					 	
					 	else
					 	{
					 		Toolkit.getDefaultToolkit().beep();
					 		JOptionPane.showMessageDialog(agen, "Registro No Eliminado", "ERROR", JOptionPane.ERROR_MESSAGE);
					 	}	
				 	}
				 }
				 else  
					new Regs_Vista(comp, true, "Consultar", agen.tipo, null);
			} 
		}	
		//@override
		public void windowClosing(WindowEvent e)
		{
			 System.exit(0);
		}	
			
		private void initValues(int row)
		{
			values = new Object[agen.tblRgs.getColumnCount()];
			
			for (int i = 0; i < values.length; i++)
			{
				values[i] = agen.tblRgs.getValueAt(row, i);
			}
		}			
} 