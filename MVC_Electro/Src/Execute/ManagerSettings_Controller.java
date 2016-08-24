import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

import java.awt.Toolkit;

import javax.swing.DefaultComboBoxModel;

import javax.swing.JOptionPane;

public class ManagerSettings_Controller extends ManagerLogin_Controller
{
	private Settings_Vista sett;
	private ManagerLogin_Controller log;
	
	private DefaultComboBoxModel cmbModel;
	
	public ManagerSettings_Controller(Settings_Vista s) { sett = s; }
	
		public void actionPerformed(ActionEvent e)
		{	
			cmbModel = (DefaultComboBoxModel)sett.cmbUsers.getModel();
			
			if(e.getSource() == sett.btnAcept) 
			{
				close();
			}
			else if(sett.getTitle().equals("Cambiar Nombre De Usuario") || e.getSource() == sett.txtNewUser)
			{
				changeUserName();
			} 
			else changePassWord();
		}
	
		private void changeUserName()
		{
			String newUser = sett.txtNewUser.getText();			
			int pos;	
			Object item;
			
			if(!(newUser.equals("")) && !isInList(newUser, 0))
			{
				item = cmbModel.getSelectedItem();	
				pos  = cmbModel.getIndexOf(item);
				cmbModel.removeElement(item);
				cmbModel.insertElementAt(newUser, pos);
				cmbModel.setSelectedItem(newUser);	
				
				sett.log.setUser(newUser, pos);
			}
			else
			{
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(sett, "El Usuario Ya Existe O \nNo Ha Escrito Nada\nPorfavor Despliege La Lista\nPara Ver Los Usuarios Existentes.\n\nXD.", "Informacion", JOptionPane.INFORMATION_MESSAGE);
			} 
		}
		
		private void changePassWord()
		{
			int pos;
			Object item;
			
			String newPass = "".copyValueOf(sett.txtNewPass.getPassword());
			
			if(!newPass.equals(""))
			{
				item = cmbModel.getSelectedItem();
				pos  = cmbModel.getIndexOf(item); 	
				sett.log.setPass(newPass, pos);
			}
			else Toolkit.getDefaultToolkit().beep();			
		}
		
		private boolean isInList(String user, int index)
		{	
			if( 4 <= index) return false;
			if(cmbModel.getElementAt(index).equals(user)) return true;
			
			index++;
			return isInList(user, index);
		}
	
		public void windowClosing(WindowEvent e) { close();}
		
		private void close()
		{
			sett.setVisible(false);
			sett.dispose();
		}
}