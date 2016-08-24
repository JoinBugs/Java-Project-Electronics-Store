import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;

import javax.swing.JSeparator;

public class Root extends JFrame
{
private JMenuBar mnBar;
private JMenu  mnArchivo, mnCuenta, mnContent, mnSettings;
		JMenuItem mniSalir, mniOpenF, mniAcerca, mniSave;
	
		JMenuItem mniCerrar, mniUser, mniPass;
 public static Cursor cur;
		
		static Agenda_Vista ageClientes, ageEmpleados, ageProveedores;
		
		static JTabbedPane tabViews, tabAgendas, tabArticles, tabBuy, tabSell;
private String leyend;

private JPanel pnlModify;

private JMenu mnEdit;

private JCheckBoxMenuItem[] cbxMniClientes;
private JCheckBoxMenuItem[] cbxMniProveedo;
private JCheckBoxMenuItem[] cbxMniEmpleado;

	public Root()
	{
		setTitle("Control");
		setSize(1150, 570);
		setLocationRelativeTo(null);
		
		mnBar = new JMenuBar();
		
		mnArchivo 	= new JMenu("Archivo");
		mnCuenta 	= new JMenu("Cuenta");
		mnSettings 	= new JMenu("Configuracion");
		mnContent 	= new JMenu("Contenido");
		
		mniOpenF = new JMenuItem("Abrir Registro");
		mniSave  = new JMenuItem("Guardar Registro"); 
		mniSalir = new JMenuItem("Salir");
		
		mniCerrar 	= new JMenuItem("Cerrar Sesion");
	
		mniUser = new JMenuItem("Cambiar Usuario");
		mniPass = new JMenuItem("Cambiar Contrase\u00f1a");
	
		mniAcerca= new JMenuItem("Acerca De");
	
		cur = new Cursor(Cursor.HAND_CURSOR);
		
		mnEdit = new JMenu("Editar");

		initMenusItem(cbxMniClientes, "Clientes");
		initMenusItem(cbxMniProveedo, "Proveedores");
		initMenusItem(cbxMniEmpleado, "Empleados");
		
		mniSave.setCursor(cur);
		mniOpenF.setCursor(cur);
		mniSalir.setCursor(cur);
		mniUser.setCursor(cur);
		mniPass.setCursor(cur);

		mniCerrar.setCursor(cur);
		mniAcerca.setCursor(cur);	
		
		mnArchivo.setMnemonic('a');
		
		mniSave.setMnemonic('g');
		mniOpenF.setMnemonic('a');
		mniSalir.setMnemonic('s');
		
		mniAcerca.setMnemonic('a');
		
		mnArchivo.add(mniOpenF);
		mnArchivo.add(new JSeparator());
		mnArchivo.add(mniSave);
		mnArchivo.add(new JSeparator());
		mnArchivo.add(mniSalir);
		
		mnSettings.add(mniUser);
		mnSettings.add(new JSeparator());
		mnSettings.add(mniPass);
		
		mnCuenta.add(mniCerrar);
		mnCuenta.add(new JSeparator());
		mnCuenta.add(mnSettings);
		
		mnContent.add(mniAcerca);
	
		ManagerEvents_Controller oye = new ManagerEvents_Controller(this);
	
		mnBar.add(mnArchivo);
		mnBar.add(mnEdit);
		mnBar.add(mnCuenta);
		mnBar.add(mnContent);
		
		setJMenuBar(mnBar);
		
		tabViews 	= new JTabbedPane();
		
		tabAgendas	= new JTabbedPane(); 
		tabArticles	= new JTabbedPane();
		tabBuy		= new JTabbedPane();
		tabSell		= new JTabbedPane();
		
		ageClientes		= new Agenda_Vista("Clientes");
		ageEmpleados	= new Agenda_Vista("Empleados");
		ageProveedores	= new Agenda_Vista("Proveedores");
		
		tabAgendas.addTab("Clientes", ageClientes);
		tabAgendas.addTab("Proveedores", ageProveedores);
		tabAgendas.addTab("Empleados", ageEmpleados);
		
		tabViews.add("Agendas", tabAgendas);
		tabViews.add("Articulos", tabArticles);
		tabViews.add("Compras", tabBuy);
		tabViews.add("Ventas", tabSell);
		
		tabViews.setBackgroundAt(0, Color.yellow);
		tabViews.setBackgroundAt(1, new Color(205, 133, 63));
		tabViews.setBackgroundAt(2, Color.orange);
		tabViews.setBackgroundAt(3, new Color(173, 255, 47));
		
		leyend = "<html><body style=\"background-color: #FFD700\"><strong style=\"color: 000000\">Agenda De:</strong><hr><br><i><ol style=\"list-style: none; \"><i><li>Clientes<li>Empleados<li>Proveedores</ol></i></body></html>";
		tabViews.setToolTipTextAt(0, leyend);
	
		add(tabViews);
		
		mniOpenF.addActionListener(oye);
		mniSave.addActionListener(oye);
		mniAcerca.addActionListener(oye);
		mniSalir.addActionListener(oye);
		mniCerrar.addActionListener(oye);
		mniUser.addActionListener(oye);
		mniPass.addActionListener(oye);
		
		addWindowListener(oye);
		setVisible(true);
	}
	
	private void initMenusItem(JCheckBoxMenuItem[] cbxPersonas, String tabla)
	{
		String[] titles = BD_Store_Model.getColumnNames(tabla);	
		cbxPersonas = new JCheckBoxMenuItem[titles.length];
		
		JMenu tipo = new JMenu(tabla);
		tipo.setCursor(cur);
			
		for (int i = 0; i < titles.length; i++) 
		{
			cbxPersonas[i] = new JCheckBoxMenuItem(titles[i]);
			cbxPersonas[i].setCursor(cur);
			tipo.add(cbxPersonas[i]);
		}
		
		JCheckBoxMenuItem cbxMniAllItems = new JCheckBoxMenuItem("Todos Los Campos");
		cbxMniAllItems.setCursor(cur);
		
		tipo.add(new JSeparator());
		tipo.add(cbxMniAllItems);
		mnEdit.add(tipo);	
	}
}