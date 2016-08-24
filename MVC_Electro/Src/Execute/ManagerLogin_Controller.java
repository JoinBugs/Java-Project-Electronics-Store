import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Toolkit;
import javax.swing.JOptionPane;

public class ManagerLogin_Controller extends WindowAdapter implements ActionListener
{

private String[] users = {"Andres", "Hugo", "Marco", "Jose"};
private String[] pass  = {"Cornejo", "Otreum", "Antonio", "Castillo"};

private Login_Vista log;

  public ManagerLogin_Controller(Login_Vista l) { log = l; }

  public ManagerLogin_Controller(){}	
  
  
	public void actionPerformed(ActionEvent e)
	{
		if((e.getSource() == log.txtPass) || (e.getSource() == log.btnAcept))
			createConection();
		else
		{
			log.setCancel(true);
			cerrar();
		}
	}
	
	private void createConection()
	{
		String user = log.txtUser.getText();
		
		char[] codc = log.txtPass.getPassword();
		String code = "".copyValueOf(codc);
		
		BD_Store_Model db = new BD_Store_Model(user, code);
		
		if(db.conectar()) openGUI();
		
		else
		{
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(log, "Nombre De Usuario O Contraseña\nIncorrectos !!!", "Entrada", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void openGUI()
	{
		cerrar();
		if(log.padre == null) new Root();
	}
	
	private void cerrar()
	{
		log.setVisible(false);
		log.dispose();
	}
	
	public void setUser(String newUser, int index)
	{ 
		users[index] = newUser; 
	}
  
	public void setPass(String newPass, int index) 
	{
		pass[index] = newPass; 
	}
	
	private int getIndex(String valor, String[] datos)
	{
		for(int i = 0; i < 4; i++)
		{
			if(datos[i].equals(valor)) return i;	
		}	
		return -1;
	}
	
	public String[] getUsers() { return users; }
	
	public String[] getPass()  { return pass; }
	
	//@override
	public void windowClosing(WindowEvent e)
	{
		log.setCancel(true);
		cerrar(); 
	} 
	
	
	private void validate()
	{
		int indexPass = -1;
		
		String user = log.txtUser.getText();
		char[] codc = log.txtPass.getPassword();
		
		String code = "".copyValueOf(codc);
			
		if((user != "") && (code != ""))
		{
			for(int i = 0; i < users.length; i++)
			{
				if(user.equals(users[i]))
				{
					indexPass = i;
					break;	
				}
			}
			
			if((indexPass > -1) && (code.equals(pass[indexPass]))) openGUI();	
			else
				Toolkit.getDefaultToolkit().beep();
		}
	}
}