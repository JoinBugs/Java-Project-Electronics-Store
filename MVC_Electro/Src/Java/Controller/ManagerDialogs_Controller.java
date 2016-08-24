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
		row = new String[length];
		
		for (int i = 0; i < length; i++) 
		{
			row[i] = "'" + txtData[i].getText() + "'";
		}
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
	
}

