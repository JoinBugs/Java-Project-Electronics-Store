import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import java.awt.Color;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import java.awt.FlowLayout;

import javax.swing.border.TitledBorder;
import javax.swing.BorderFactory;
import javax.swing.border.MatteBorder;

import javax.swing.JPanel;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Regs_Vista extends JDialog
{
			JButton btnAceptar;
			JButton btnCancelar;
			JButton btnSave;

	private String[] labels;
			JTextField[] txtGroup;
	
	private	Root padre;
			String title;
	
	private JPanel pnlTxt, pnlBtn;
	private FlowLayout flujo;
	
	private GridBagConstraints gbc;
	
			String tabla;
	
	private Color colorBorde;
	
	private Object[] values;
	
	public Regs_Vista(Root parent, boolean modal, String titulo, String tipo, Object[] v)
	{		
		super(parent, modal);
		
		padre = parent;
		title = titulo;
		tabla = tipo;
		values = v;
		
		initComponents();					
	}
	
	private void initFieldsModify()
	{
		txtGroup[0].setEditable(false);
		
		for (int i = 0; i < txtGroup.length; i++)
	    {
	    	txtGroup[i].setText("" + values[i]);
		}
	}
	
	public Agenda_Vista getPadre()
	{
		return Root.ageClientes;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	private void loadLabels()
	{
		labels = BD_Store_Model.getColumnNames(tabla);	
	}
	
	private void initComponents()
	{
		setTitle(title);
		setLocationRelativeTo(null);
		
		setLayout(new GridBagLayout());
		
		int x = 0, y = 0;
		
		if(tabla.equals("Clientes"))
		{
			x = 345;
			y = 255;
			colorBorde = new Color(139, 69, 19);
		}
		else if(tabla.equals("Proveedores"))
		{
			x = 346;
			y = 300;
			colorBorde = Color.blue;	
		}
		else
		{
			x = 320;
			y = 350;
			colorBorde = new Color(0, 100, 0);	
		} 
		
		setSize(x, y);	
		setResizable(false);		
		
		flujo = new FlowLayout(FlowLayout.TRAILING);
		
		pnlTxt = new JPanel(flujo);
		pnlBtn = new JPanel(new FlowLayout());
		
		ManagerFocus_Controller hey = new ManagerFocus_Controller();
		
		loadLabels();
		txtGroup = new JTextField[labels.length];
		
		for (int i = 0; i < labels.length; i++) 
		{
			txtGroup[i] = new JTextField(20);
			txtGroup[i].addFocusListener(hey);
			
			pnlTxt.add(new JLabel(labels[i]));
			pnlTxt.add(txtGroup[i]);
		}
		
		if(values != null) 	initFieldsModify();
		
		btnAceptar 	= new JButton("Aceptar");
		btnCancelar = new JButton("Nuevo");
		
		btnAceptar.setCursor(Root.cur);
		btnCancelar.setCursor(Root.cur);
		
		pnlTxt.setBorder(BorderFactory.createTitledBorder(new MatteBorder(1, 1, 1, 1, colorBorde), "Registro"));
		pnlBtn.setBorder(BorderFactory.createTitledBorder(new MatteBorder(1, 1, 1, 1, colorBorde), "Aplicar", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
		
		ManagerDialogs_Controller	oye = new ManagerDialogs_Controller(this);
		
		btnAceptar.addActionListener(oye);
		btnCancelar.addActionListener(oye);
		
		
		addWindowListener(oye);
		
		if(title != "Consultar")
		{
			btnSave	= new JButton("Guardar");
			btnSave.setCursor(Root.cur);
			btnSave.addActionListener(oye);
			pnlBtn.add(btnSave);
			getRootPane().setDefaultButton(btnSave);
		}
		else getRootPane().setDefaultButton(btnAceptar);
		
		pnlBtn.add(btnCancelar);
		pnlBtn.add(btnAceptar);
		
		gbc = new GridBagConstraints();
		
		gbc.gridx = 0;			gbc.gridy = 0;
		gbc.gridwidth = 1;		gbc.gridheight = 3;
		gbc.weightx = 1.0;		gbc.weighty = 1.0;
		gbc.fill = gbc.BOTH;
		
		add(pnlTxt, gbc);
		
		gbc.gridx = 0;			gbc.gridy = 3;
		gbc.gridwidth = 1;		gbc.gridheight = 1;
		gbc.fill = gbc.HORIZONTAL;
		gbc.weighty = 0.0;
		add(pnlBtn, gbc);
		
		setVisible(true);
	}
	
  private class ManagerFocus_Controller extends FocusAdapter
  {
		JTextField txtGenerico;
	
		//@override
		public void focusGained(FocusEvent e)
		{
			txtGenerico = (JTextField)e.getSource();
			if(txtGenerico.getText() != "") txtGenerico.selectAll();
			
			int index = 0;
			
			for(int i = 0; i < txtGroup.length; i++)
			{
				if(e.getSource() == txtGroup[i]) index = i; 
				
				txtGroup[i].setBorder(new MatteBorder(1, 1, 1, 1, new Color(158, 158, 158)));
			}
			
			if(!(title.equals("Modificar") && e.getSource() == txtGroup[0])) txtGroup[index].setBorder(new MatteBorder(2, 2, 2, 2, colorBorde));
		}
  }	
	
}