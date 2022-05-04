import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.Vector;

import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;




public class MemberSearch extends JFrame implements ActionListener{

	private JPanel mainPanel,panelTable,panelUser;
	private JLabel labelTable,labelUser,labelSearch,labelName,labelOccupation;
	private JTextField textFieldName,textFieldOccupation;
	private JSeparator separator;
	private JButton buttonSubmit,buttonExit,buttonBack,buttonSearchFlat,buttonSearchBill;
	private JTable table;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private String sName,sOccupation;
	
	
	public MemberSearch()
	{
		this.initializeComponents();
	}
	
	public void actionPerformed(ActionEvent event)
	{
		String button = event.getActionCommand();
		if(button.equals("EXIT"))
		{
			System.exit(0);
		}
		else if(button.equals("HOME"))
		{
			Entrance login = new Entrance();
			login.setVisible(true);
			this.setVisible(false);
		}
		else if(button.equals("SUBMIT"))
		{
			DataContext context = new DataContext();
			this.sName = this.textFieldName.getText().trim();
			this.sOccupation = this.textFieldOccupation.getText().trim();
			if(sName.isEmpty() && sOccupation.isEmpty()) 
			{
				JOptionPane.showMessageDialog(rootPane, "Please enter valid name or occupation or both.");
			}
            else
            {
            	if(!sName.isEmpty() && !sOccupation.isEmpty())
            	{
            		this.panelTable = new JPanel();
                	this.panelTable.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        			this.panelTable.setBounds(15, 256, 656, 147);
        			this.mainPanel.add(panelTable);
        			this.panelTable.setLayout(null);
     
        			
        			Vector<String> columns = new Vector<String>();
        			columns.add("Name");
        			columns.add("Email");
        			columns.add("Phone");
        			columns.add("Occupation");
        			columns.add("Flat No");
        			Vector<Vector<String>> SearchMemberList = context.getSearchMemberAsString(sName,sOccupation);
        			if(SearchMemberList.isEmpty())
        			{
        				JOptionPane.showMessageDialog(rootPane, "Nothing Found.Please Search Again.");
        			}
        			else
        			{
        				this.tableModel = new DefaultTableModel(SearchMemberList, columns){
            				public boolean isCellEditable(int row, int column) {
            		            return false;
            		         }
            			};
            			
            			labelTable.setVisible(true);
            			
            			this.table = new JTable(this.tableModel);
            			this.table.getColumnModel().getColumn(0).setPreferredWidth(20);
            			this.table.getColumnModel().getColumn(1).setPreferredWidth(80);
            			this.table.getColumnModel().getColumn(2).setPreferredWidth(40);
            			this.table.getColumnModel().getColumn(3).setPreferredWidth(30);
            			this.table.getColumnModel().getColumn(4).setPreferredWidth(20);
            			
            			
            			
            			this.scrollPane = new JScrollPane(this.table);
            			this.scrollPane.setBounds(0, 0, 656, 147);
            			this.panelTable.add(scrollPane);
            			this.mainPanel.add(panelTable);
            			
            			MouseListener[] listeners = table.getMouseListeners();
            			for (MouseListener l : listeners)
            			{
            			    table.removeMouseListener(l);
            			}
            			Refresh();
        			}
        			
                }
            	else if(sName.isEmpty() || !sOccupation.isEmpty())
            	{
            		this.panelTable = new JPanel();
                	this.panelTable.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        			this.panelTable.setBounds(15, 256, 656, 147);
        			this.mainPanel.add(panelTable);
        			this.panelTable.setLayout(null);
     
        			
        			Vector<String> columns = new Vector<String>();
        			columns.add("Name");
        			columns.add("Email");
        			columns.add("Phone");
        			columns.add("Occupation");
        			columns.add("Flat No");
        			Vector<Vector<String>> SearchMemberList = context.getSearchMemberByOccupation(sOccupation);
        			if(SearchMemberList.isEmpty())
        			{
        				JOptionPane.showMessageDialog(rootPane, "Nothing Found.Please Search Again.");
        			}
        			else
        			{
        				this.tableModel = new DefaultTableModel(SearchMemberList, columns){
            				public boolean isCellEditable(int row, int column) {
            		            return false;
            		         }
            			};
            			
            			labelTable.setVisible(true);
            			
            			this.table = new JTable(this.tableModel);
            			this.table.getColumnModel().getColumn(0).setPreferredWidth(20);
            			this.table.getColumnModel().getColumn(1).setPreferredWidth(80);
            			this.table.getColumnModel().getColumn(2).setPreferredWidth(40);
            			this.table.getColumnModel().getColumn(3).setPreferredWidth(30);
            			this.table.getColumnModel().getColumn(4).setPreferredWidth(20);
            			
            			
            			
            			this.scrollPane = new JScrollPane(this.table);
            			this.scrollPane.setBounds(0, 0, 656, 147);
            			this.panelTable.add(scrollPane);
            			this.mainPanel.add(panelTable);
            			
            			MouseListener[] listeners = table.getMouseListeners();
            			for (MouseListener l : listeners)
            			{
            			    table.removeMouseListener(l);
            			}
            			Refresh1();
        			}
        			
                }
            	else
            	{
            		this.panelTable = new JPanel();
                	this.panelTable.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        			this.panelTable.setBounds(15, 256, 656, 147);
        			this.mainPanel.add(panelTable);
        			this.panelTable.setLayout(null);
     
        			
        			Vector<String> columns = new Vector<String>();
        			columns.add("Name");
        			columns.add("Email");
        			columns.add("Phone");
        			columns.add("Occupation");
        			columns.add("Flat No");
        			Vector<Vector<String>> SearchMemberListByname = context.getSearchMemberByName(sName);
        			if(SearchMemberListByname.isEmpty())
        			{
        				JOptionPane.showMessageDialog(rootPane, "Nothing Found.Please Search Again.");
        			}
        			else
        			{
        				this.tableModel = new DefaultTableModel(SearchMemberListByname, columns){
            				public boolean isCellEditable(int row, int column) {
            		            return false;
            		         }
            			};
            			
            			labelTable.setVisible(true);
            			
            			this.table = new JTable(this.tableModel);
            			this.table.getColumnModel().getColumn(0).setPreferredWidth(20);
            			this.table.getColumnModel().getColumn(1).setPreferredWidth(80);
            			this.table.getColumnModel().getColumn(2).setPreferredWidth(40);
            			this.table.getColumnModel().getColumn(3).setPreferredWidth(30);
            			this.table.getColumnModel().getColumn(4).setPreferredWidth(20);
            			
            			
            			
            			this.scrollPane = new JScrollPane(this.table);
            			this.scrollPane.setBounds(0, 0, 656, 147);
            			this.panelTable.add(scrollPane);
            			this.mainPanel.add(panelTable);
            			
            			MouseListener[] listeners = table.getMouseListeners();
            			for (MouseListener l : listeners)
            			{
            			    table.removeMouseListener(l);
            			}
            			Refresh2();
        			}
            	}
            }
            	
		}
		else if(button.equals("SEARCH BILL"))
		{
			BillSearch frame = new BillSearch();
			frame.setVisible(true);
			this.setVisible(false);
		}
		else if(button.equals("SEARCH FLAT"))
		{
			FlatSearch frame = new FlatSearch();
			frame.setVisible(true);
			this.setVisible(false);
		}
	}
	
	
	
	private void initializeComponents()
	{
		this.mainPanel = new JPanel();
		this.setTitle("Member Searching");
		this.mainPanel.setLayout(null);
		
		panelUser = new JPanel();
		panelUser.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelUser.setBounds(38, 28, 610, 196);
		mainPanel.add(panelUser);
		panelUser.setLayout(null);
		
		this.labelUser = new JLabel("Search Member Information");
		labelUser.setBounds(20, 11, 180, 18);
		panelUser.add(labelUser);
		this.labelUser.setForeground(Color.BLUE);
		this.labelUser.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		labelName = new JLabel("Name");
		labelName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelName.setBounds(20, 49, 84, 28);
		panelUser.add(labelName);
		
		textFieldName = new JTextField();
		textFieldName.setToolTipText("");
		textFieldName.setBounds(117, 49, 157, 28);
		panelUser.add(textFieldName);
		
		
		labelOccupation = new JLabel("Occupation");
		labelOccupation.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelOccupation.setBounds(20, 90, 84, 28);
		panelUser.add(labelOccupation);
		
		textFieldOccupation = new JTextField();
		textFieldOccupation.setToolTipText("");
		textFieldOccupation.setBounds(117, 90, 157, 28);
		panelUser.add(textFieldOccupation);
		
		this.buttonSubmit = new JButton("SUBMIT");
		buttonSubmit.setBounds(318, 48, 91, 29);
		panelUser.add(buttonSubmit);
		this.buttonSubmit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.buttonSubmit.addActionListener(this);
		
		buttonBack = new JButton("HOME");
		buttonBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonBack.setBounds(320, 90, 89, 28);
		panelUser.add(buttonBack);
		this.buttonBack.addActionListener(this);
		
		buttonExit = new JButton("EXIT");
		buttonExit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonExit.setBounds(318, 128, 91, 29);
		panelUser.add(buttonExit);
		this.buttonExit.addActionListener(this);
		
		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(433, 0, 15, 196);
		panelUser.add(separator);
		
		labelSearch = new JLabel("Search");
		labelSearch.setForeground(Color.BLUE);
		labelSearch.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		labelSearch.setBounds(493, 11, 48, 18);
		panelUser.add(labelSearch);
		
		buttonSearchFlat = new JButton("SEARCH FLAT");
		buttonSearchFlat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonSearchFlat.setBounds(454, 60, 129, 28);
		panelUser.add(buttonSearchFlat);
		buttonSearchFlat.addActionListener(this);
		
		buttonSearchBill = new JButton("SEARCH BILL");
		buttonSearchBill.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonSearchBill.setBounds(454, 112, 129, 28);
		panelUser.add(buttonSearchBill);
		buttonSearchBill.addActionListener(this);
		
		labelTable = new JLabel("Searched Member Information");
		labelTable.setForeground(Color.BLUE);
		labelTable.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelTable.setBounds(233, 229, 227, 25);
		mainPanel.add(labelTable);
		this.labelTable.setVisible(false);
		
		
		getContentPane().add(this.mainPanel);
		
		this.setSize(703, 453);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void Refresh() {
	    for(int i = this.tableModel.getRowCount() - 1; i >= 0; i--)
	        this.tableModel.removeRow(i);
	    DataContext context = new DataContext();
		Vector<Vector<String>> cate = context.getSearchMemberAsString(sName,sOccupation);
	    for(int i = 0; i < cate.size(); i++) {
	        this.tableModel.addRow(cate.get(i).toArray());
	    }
	}
	
	public void Refresh2() {
	    for(int i = this.tableModel.getRowCount() - 1; i >= 0; i--)
	        this.tableModel.removeRow(i);
	    DataContext context = new DataContext();
		Vector<Vector<String>> cate = context.getSearchMemberByName(sName);
	    for(int i = 0; i < cate.size(); i++) {
	        this.tableModel.addRow(cate.get(i).toArray());
	    }
	}
	
	public void Refresh1() {
	    for(int i = this.tableModel.getRowCount() - 1; i >= 0; i--)
	        this.tableModel.removeRow(i);
	    DataContext context = new DataContext();
		Vector<Vector<String>> cate = context.getSearchMemberByOccupation(sOccupation);
	    for(int i = 0; i < cate.size(); i++) {
	        this.tableModel.addRow(cate.get(i).toArray());
	    }
	}
	
	private boolean nameCheck(String sName) {
        if(sName.isEmpty()) return true;
        else {
            boolean flag = true;
            for(int i = 0; i < sName.length(); i++) {
                if(Character.isDigit(sName.charAt(i))) {
                    flag = false;
                    break;
                }
            }
            return flag;
        }
    }
	
	private boolean occupationCheck(String sOccupation) {
        if(sOccupation.isEmpty()) return true;
        else {
            boolean flag = true;
            for(int i = 0; i < sOccupation.length(); i++) {
                if(Character.isDigit(sOccupation.charAt(i))) {
                    flag = false;
                    break;
                }
            }
            return flag;
        }
    }
	
}
