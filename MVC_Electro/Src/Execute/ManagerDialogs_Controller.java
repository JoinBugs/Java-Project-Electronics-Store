import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;

import javax.swing.JOptionPane;
import java.awt.Toolkit;

public class ManagerDialogs_Controller extends ManagerEvents_Controller
{
	private String titulo;
	private Regs_Vista regs;
	
	JTextField[] txtData;
	String[] row;
	
	//override
	public ManagerDialogs_Controller(Regs_Vista r)
	{
		regs = r;
		titulo = regs.title;
	}
	
	//override
	public void windowClosing(WindowEvent e)
	{
		cerrar();	
	}	
	
	private void cerrar()
	{
		regs.setVisible(false);	
		regs.dispose();
	}
	
	//override
	public void actionPerformed(ActionEvent e)
	{	
		if(e.getSource() == regs.btnAceptar)
		{
			cerrar();
		}
		else if(e.getSource() == regs.btnCancelar)
		{
			cleanBoxes();
		}  
		else 
		{
			if(titulo.equals("Agregar"))
			{
				initRegs();
				
				int i;
				
				for ( i = 0; i < txtData.length; i++) 
				{
					if(txtData[i].getText().equals("")) break;
				}
				
				if(i == txtData.length) addActionPerformed();
				
				else
				{
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(regs, "Porfavor Llene Todos Los Campos", "INFORMACION", JOptionPane.WARNING_MESSAGE);
				} 
			}
			else if(titulo.equals("Eliminar"))
			{
				deleteActionPerformed();
			}
			else
			{
				updateActionPerformed();	
			}		
		}
		
	}
	
	private void addActionPerformed()
	{
		initRegs();
		
		boolean successful = BD_Store_Model.setInsertRegsInTable(regs.tabla, row);
		
		if(successful)
		{
			JOptionPane.showMessageDialog(regs, "Registro Insertado Corectamente En La BD.", "CORRECTO", JOptionPane.INFORMATION_MESSAGE);
		} 
			
		else
		{
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(regs, "Error Durante La Insercion Del Registro", "ERROR", JOptionPane.ERROR_MESSAGE);		
		}
	}
	
	private void initRegs()
	{
		txtData = regs.txtGroup;
		
		int length = txtData.length;
		row = new String[length + 1];
		
		for (int i = 0; i < length; i++) 
		{
			row[i] = "'" + txtData[i].getText() + "'";
		}
		
		if(regs.tabla.equals("Empleados")) row[length] = "" + regs.chbMarried.isSelected();
	}
	
	private void cleanBoxes()
	{
		for (int i = 0; i < regs.txtGroup.length; i++)
	    {
			regs.txtGroup[i].setText("");	    	
		}
	}
	
	private void updateActionPerformed()
	{
		initRegs();
		
		boolean successful = BD_Store_Model.doUpdateRegsInTable(regs.tabla, row);
		
		if(successful)
		{
			JOptionPane.showMessageDialog(regs, "Registro Actualizado Corectamente En La BD.", "CORRECTO", JOptionPane.INFORMATION_MESSAGE);
		} 
			
		else
		{
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(regs, "Error Durante La Actualizacion Del Registro", "ERROR", JOptionPane.ERROR_MESSAGE);		
		}
	}
	
	private void deleteActionPerformed()
	{
		txtData = regs.txtGroup;
		String[] campos = regs.labels;
		
		boolean text = false;
		
		for (int i = 0; i < txtData.length; i++) 
		{
			if(!txtData[i].getText().equals(""))
			{
				text = true;
				break;
			}
		}
		
		if(text)
		{
			String[] where = new String[campos.length];
			
			int j = 0;
			
			for (int i = 0; i < txtData.length; i++) 
			{
				if(!txtData[i].getText().equals("")) where[j++] = campos[i] + " = " + "'" +txtData[i].getText() + "'";	
			}
			
			String conditional = "";
			
			for (int i = 0; i < j - 1; i++)
		    {
		    	conditional += where[i] + " AND ";
			}
			
			conditional += where[j - 1]; 
			
			int count = BD_Store_Model.doCountRegsInTable(regs.tabla, conditional);
			
			if(count > 0 )
			{
				boolean successful = false;
				
				int option = JOptionPane.showConfirmDialog(regs, "Se Encontraron " + count + " Coincidencias\nQuieres ELIMINAR Estos Registros?", "Quieres Continuar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if(option == JOptionPane.YES_OPTION)
				{
					successful = BD_Store_Model.doDeleteRegsInTable(regs.tabla, conditional);
					
					if(successful) JOptionPane.showMessageDialog(regs, "Eliminacion De Los " + count + " Registros En La BD", "CORRECTO", JOptionPane.INFORMATION_MESSAGE);
						
					else JOptionPane.showMessageDialog(regs, "Error Durante La Eliminacion Del Registro", "ERROR", JOptionPane.ERROR_MESSAGE);		
				}
			}
			else if(count == 0)
			{
				JOptionPane.showMessageDialog(regs, "No Hay Coincidencias Con Los Campos Especificados");
			}
			else 
				JOptionPane.showMessageDialog(regs, "A Ocurrido Un Error Mientras Se Procesaba La Consulta", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(regs, "Almenos Debe Llenar La Informacion De Un Campo", "INFORMACION", JOptionPane.WARNING_MESSAGE);
		} 		
	}
	
}

