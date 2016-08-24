import java.awt.FlowLayout;

import javax.swing.border.TitledBorder;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JDialog;

import javax.swing.JButton;

import javax.swing.JLabel;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import javax.swing.JComboBox;

public class Settings_Vista extends JDialog
{
		JComboBox cmbUsers;	
		
		JPasswordField txtNewPass;
		JTextField txtNewUser;

		JButton btnApply, btnAcept;

private JLabel lblUsers, lblNewUser, lblNewPass;

private String[] users;
private String title;

private JPanel pnlSettings, pnlBtns;

		ManagerLogin_Controller log;
	
	public Settings_Vista(JFrame parent, boolean modal, String titulo)
	{
		super(parent, modal);
		
		title = titulo;
		
		setTitle(title);
		setSize(240, 175);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		pnlSettings = new JPanel(null);
		pnlSettings.setBorder(new TitledBorder("Settings"));
		
		pnlBtns = new JPanel(null);
		pnlBtns.setBorder(new TitledBorder(null, "Apply", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
		
		pnlSettings.setBounds(1, 1, 232, 80);
		pnlBtns.setBounds(1, 85, 232, 55);
		
		lblUsers = new JLabel("Usuarios:");
		lblUsers.setBounds(12, 10, 70, 40);
		
		pnlSettings.add(lblUsers);
		
		log = new ManagerLogin_Controller();
		
		users = log.getUsers();
		cmbUsers = new JComboBox(users);
		cmbUsers.setMaximumRowCount(users.length);
		cmbUsers.setBounds(102, 20, 120, 20);
		cmbUsers.setCursor(Root.cur);
		
		pnlSettings.add(cmbUsers);
	
		ManagerSettings_Controller listen = new ManagerSettings_Controller(this);
		
		if(!title.equals("Cambiar Password De Usuario"))
		{
			lblNewUser = new JLabel("Nuevo Usuario: ");
			lblNewUser.setBounds(12, 38, 90, 40);
			pnlSettings.add(lblNewUser);
			txtNewUser = new JTextField();
			txtNewUser.setBounds(102, 48, 122, 20);
			txtNewUser.addActionListener(listen);
			pnlSettings.add(txtNewUser);
		}
		else
		{
			lblNewPass = new JLabel("Nuevo Pass:");
			lblNewPass.setBounds(12, 38, 90, 40);
			pnlSettings.add(lblNewPass);
			txtNewPass = new JPasswordField();
			txtNewPass.setBounds(102, 48, 122, 20);
			txtNewPass.addActionListener(listen);
			pnlSettings.add(txtNewPass);
		}	
		
		btnApply = new JButton("Aplicar");
		btnAcept = new JButton("Aceptar");
		
		add(btnApply);
		add(btnAcept);
		
		btnApply.setBounds(12, 105, 85, 22);
		btnAcept.setBounds(135, 105, 87, 22);
		
		btnApply.setCursor(Root.cur);
		btnAcept.setCursor(Root.cur);
		
		add(pnlSettings);
		add(pnlBtns);
		
		btnApply.addActionListener(listen);
		btnAcept.addActionListener(listen);
		
		addWindowListener(listen);
		setVisible(true);
	}
	public String getTitle() { return title; }
}