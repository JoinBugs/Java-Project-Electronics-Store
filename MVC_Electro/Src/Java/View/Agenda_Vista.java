import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import java.awt.Color;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import javax.swing.JPanel;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.JButton;
import javax.swing.JScrollPane;

import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;

import javax.swing.JSeparator;

public class Agenda_Vista extends JPanel
{	
			JTable tblRgs;
	
			JButton btnAdd, btnDelete, btnConsult, btnModify;
	private JPanel pnlBtns;
	
	private JScrollPane scp;
	
	public  String tipo;
	
	private JPopupMenu popMenu;
	
			JMenuItem mniDel, mniMod, mniEdit;
	
	public Agenda_Vista(String title)
	{
		tipo = title;
		initComponents();
	}
	
		private void initComponents()
		{
			setLayout(new BorderLayout());
			
			loadTabla();
			
			btnAdd		= new JButton("Agregar");
			btnDelete	= new JButton("Eliminar");
			btnModify	= new JButton("Modificar");
			btnConsult	= new JButton("Consultar");
			
			//getRootPane().setDefaultButton(btnAdd);
			
			btnAdd.setCursor(Root.cur);
			btnModify.setCursor(Root.cur);
			btnDelete.setCursor(Root.cur);
			btnConsult.setCursor(Root.cur);
		
			pnlBtns = new JPanel();
			
			pnlBtns.setLayout(new FlowLayout());
			pnlBtns.setBackground(Color.WHITE);
			pnlBtns.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, new Color(70, 50, 255)), "Configuraciones"));
			
			pnlBtns.add(btnAdd);
			pnlBtns.add(btnModify);
			pnlBtns.add(btnDelete);
			pnlBtns.add(btnConsult);
			
			add(pnlBtns, BorderLayout.SOUTH);
			
			mniDel	= new JMenuItem("Eliminar");
			mniEdit = new JMenuItem("Editar");
			mniMod	= new JMenuItem("Modificar");
			
			mniDel.setCursor(Root.cur);
			mniEdit.setCursor(Root.cur);
			mniMod.setCursor(Root.cur);
			
			popMenu = new JPopupMenu();
			
			popMenu.add(mniMod);
			popMenu.add(new JSeparator());
			popMenu.add(mniDel);
			popMenu.add(new JSeparator());
			popMenu.add(mniEdit);
			
			ManagerEvents_Controller oye = new ManagerEvents_Controller(this);
			
			btnAdd.addActionListener(oye);
			btnModify.addActionListener(oye);
			btnDelete.addActionListener(oye);
			btnConsult.addActionListener(oye);
			
			mniDel.addActionListener(oye);
			mniMod.addActionListener(oye);
			
			tblRgs.addMouseListener(new MouseManager());			
		}
	
		public JTable getTabla()
		{
			return tblRgs;	
		}
		
		public void loadTabla()
		{
			Agenda_Model agendaModel = new Agenda_Model(tipo);
			agendaModel.setModelTable();
	
			tblRgs = agendaModel.getModelTable();
								
			scp = new JScrollPane(tblRgs);
							
			add(scp, BorderLayout.CENTER);
		}
		
	private class MouseManager extends MouseAdapter
	{
		public void mousePressed(MouseEvent e)
		{
			if(e.getButton() == MouseEvent.BUTTON3)
			
				if(tblRgs.getSelectedRow() != -1)
				{
					popMenu.show(tblRgs, e.getX(), e.getY());
				}
		}
		
	}						
}