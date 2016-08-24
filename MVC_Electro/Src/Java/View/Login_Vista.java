import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JDialog;

import java.awt.Cursor;

import javax.swing.JTextField;
import javax.swing.JPasswordField;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import javax.swing.JButton;
import javax.swing.JLabel;

public class Login_Vista extends JDialog
{
private Cursor hand;

		JTextField txtUser;
		JPasswordField txtPass;

private JLabel lblUser, lblPass;
		JButton btnAcept, btnCancel;

private boolean cancel; 

private JPanel pnlLogin, pnlEtched;

static  JFrame padre;
		
	public Login_Vista(JFrame parent, boolean modal)
	{
		super(parent, modal);
		//getContentPane().setBackground(new Color(170, 170, 170));
		padre = parent;	
		initComponents();
	}
	
	private void initComponents()
	{
		cancel = false;
		
		if(padre == null) setTitle("Entrar");
		else
			setTitle("Confirmar");
				 
		setSize(220, 150);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		
		lblUser = new JLabel("Usuario:"); //Agregar Fuentes setFont(new Font("family type", size, bold | italic)); 
		lblPass = new JLabel("Password:");
		
		lblUser.setBounds(10, 12, 70, 40);
		lblPass.setBounds(10, 38, 70, 40);
		
		txtUser = new JTextField(15);
		txtPass = new JPasswordField(15);
		
		txtUser.setBounds(80, 22, 120, 20);
		txtPass.setBounds(80, 48, 120, 20);
		
		txtUser.setToolTipText("<html><span bgcolor=\"ADFF2F\"><b><i>Ingresa Nombre De Usuario</i></b></span></html>");
		txtPass.setToolTipText("<html><div bgcolor=\"ADFF2F\"><i><b>Ingresa La Contraseña</i></b></div></html>");
		
		add(lblUser);
		add(txtUser);
		
		add(lblPass);
		add(txtPass);
		
		btnAcept  = new JButton("Aceptar");
		btnCancel = new JButton("Cancelar");
		
		hand = new Cursor(Cursor.HAND_CURSOR);
		
		btnAcept.setCursor(hand);
		btnCancel.setCursor(hand);
		
		btnAcept.setBounds(10, 86, 85, 22);
		btnCancel.setBounds(115, 86, 85, 22);
		
		add(btnAcept);
		add(btnCancel);
		
		pnlLogin 	= new JPanel(null);
		pnlEtched 	= new JPanel(null);
		
		pnlLogin.setBorder(new TitledBorder("Login"));
		pnlEtched.setBorder(new EtchedBorder());
		
		pnlLogin.setBounds(1, 1, 207, 75);
		pnlEtched.setBounds(2, 80, 207, 35);
		
		add(pnlLogin);
		add(pnlEtched);
		
		ManagerLogin_Controller oye = new ManagerLogin_Controller(this);
		
		txtPass.addActionListener(oye);
		btnAcept.addActionListener(oye);
		btnCancel.addActionListener(oye);
		
		addWindowListener(oye);
		setVisible(true);
	}
		public void setCancel(boolean can) { cancel = can; }
		
		public boolean isCancel() { return cancel; }
}