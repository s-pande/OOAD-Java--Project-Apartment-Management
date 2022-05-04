import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.Vector;

import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;




public class FlatSearch extends JFrame implements ActionListener{

	private JPanel mainPanel,panelTable,panelUser;
	private JLabel labelTable,labelUser,labelSearch,labelFlatId;
	private JButton buttonSubmit,buttonExit,buttonSearchBill,buttonSearchMember,buttonHome,buttonAllFlat;
	private JSeparator separator;
	private JComboBox comboBoxFlatId;
	private JTable table;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private String sflatId;
	private JLabel lblAllFlatInformation;
	
	public FlatSearch()
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
			this.sflatId = this.comboBoxFlatId.getSelectedItem().toString();
			if(!flatIdCheck(sflatId)) {JOptionPane.showMessageDialog(rootPane, "Please enter valid flat Id.");}
            else {
            	this.panelTable = new JPanel();
            	this.panelTable.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
    			this.panelTable.setBounds(15, 256, 656, 147);
    			this.mainPanel.add(panelTable);
    			this.panelTable.setLayout(null);
 
    			DataContext context = new DataContext();
    			Vector<String> columns = new Vector<String>();
    			columns.add("Flat ID");
    			columns.add("Owner Name");
    			columns.add("Description");
    			columns.add("Floor No");
    			columns.add("Intercome Number");
    			columns.add("Rent Cost");
    			Vector<Vector<String>> SearchFlatList = context.getSearchFlatAsString(sflatId);
    			if(SearchFlatList.isEmpty())
    			{
    				JOptionPane.showMessageDialog(rootPane, "Nothing Found.Please Search Again.");
    			}
    			else
    			{
    				this.tableModel = new DefaultTableModel(SearchFlatList, columns){
        				public boolean isCellEditable(int row, int column) {
        		            return false;
        		         }
        			};
        			
        			labelTable.setVisible(true);
        			
        			this.table = new JTable(this.tableModel);
        			this.table.getColumnModel().getColumn(0).setPreferredWidth(20);
        			this.table.getColumnModel().getColumn(1).setPreferredWidth(60);
        			this.table.getColumnModel().getColumn(2).setPreferredWidth(100);
        			this.table.getColumnModel().getColumn(3).setPreferredWidth(60);
        			this.table.getColumnModel().getColumn(4).setPreferredWidth(60);
        			this.table.getColumnModel().getColumn(5).setPreferredWidth(20);
        			
        			this.scrollPane = new JScrollPane(this.table);
        			this.scrollPane.setBounds(0, 0, 656, 147);
        			this.panelTable.add(scrollPane);
        			this.mainPanel.add(panelTable);
        			
        			Refresh();
    				
    			}
    			MouseListener[] listeners = table.getMouseListeners();
    			for (MouseListener l : listeners)
    			{
    			    table.removeMouseListener(l);
    			}
    			
            }
		}
		
		else if(button.equals("SEARCH MEMBER"))
		{
			MemberSearch frame = new MemberSearch();
			frame.setVisible(true);
			this.setVisible(false);
		}
		else if(button.equals("SEARCH BILL"))
		{
			BillSearch frame = new BillSearch();
			frame.setVisible(true);
			this.setVisible(false);
		}
		else if(button.equals("CLICK HERE"))
		{
			AllSearchFlat frame = new AllSearchFlat();
			frame.setVisible(true);
			this.setVisible(false);
		}
	}
	
	
	
	private void initializeComponents()
	{
		this.mainPanel = new JPanel();
		this.setTitle("Flat Searching");
		this.mainPanel.setLayout(null);
		
		panelUser = new JPanel();
		panelUser.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelUser.setBounds(30, 28, 625, 196);
		mainPanel.add(panelUser);
		panelUser.setLayout(null);
		
		this.labelUser = new JLabel("Search Flat Information");
		labelUser.setBounds(23, 11, 149, 18);
		panelUser.add(labelUser);
		this.labelUser.setForeground(Color.BLUE);
		this.labelUser.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		labelFlatId = new JLabel("Flat ID");
		labelFlatId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelFlatId.setBounds(101, 48, 48, 28);
		panelUser.add(labelFlatId);
		
		DataContext context = new DataContext();
		Vector<String> FlatId = context.getFlatId();
		
		FlatId.add(0, "Select a Flat ID");
		
		this.comboBoxFlatId = new JComboBox(FlatId.toArray());
		this.comboBoxFlatId.setBounds(174, 49, 100, 28);
		panelUser.add(comboBoxFlatId);
		
		this.buttonSubmit = new JButton("SUBMIT");
		buttonSubmit.setBounds(174, 88, 100, 28);
		panelUser.add(buttonSubmit);
		this.buttonSubmit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.buttonSubmit.addActionListener(this);
		
		buttonHome = new JButton("HOME");
		buttonHome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonHome.setBounds(299, 49, 100, 28);
		panelUser.add(buttonHome);
		this.buttonHome.addActionListener(this);
		
		buttonExit = new JButton("EXIT");
		buttonExit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonExit.setBounds(299, 87, 100, 30);
		panelUser.add(buttonExit);
		this.buttonExit.addActionListener(this);
		
		lblAllFlatInformation = new JLabel("All Flat Information");
		lblAllFlatInformation.setForeground(new Color(0, 0, 255));
		lblAllFlatInformation.setBounds(44, 133, 105, 28);
		panelUser.add(lblAllFlatInformation);
		
		buttonAllFlat = new JButton("CLICK HERE");
		buttonAllFlat.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonAllFlat.setBounds(174, 132, 100, 28);
		panelUser.add(buttonAllFlat);
		buttonAllFlat.addActionListener(this);
		
		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(433, 0, 15, 196);
		panelUser.add(separator);
		
		labelSearch = new JLabel("Search");
		labelSearch.setForeground(Color.BLUE);
		labelSearch.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		labelSearch.setBounds(510, 11, 48, 18);
		panelUser.add(labelSearch);
		
		buttonSearchBill = new JButton("SEARCH BILL");
		buttonSearchBill.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonSearchBill.setBounds(454, 60, 149, 28);
		panelUser.add(buttonSearchBill);
		buttonSearchBill.addActionListener(this);
		
		buttonSearchMember = new JButton("SEARCH MEMBER");
		buttonSearchMember.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonSearchMember.setBounds(454, 112, 149, 28);
		panelUser.add(buttonSearchMember);
		buttonSearchMember.addActionListener(this);
		
		labelTable = new JLabel("Searched Flat Information");
		labelTable.setForeground(Color.BLUE);
		labelTable.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelTable.setBounds(244, 224, 179, 25);
		mainPanel.add(labelTable);
		this.labelTable.setVisible(false);
		
		
		this.add(this.mainPanel);
		
		this.setSize(703, 453);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void Refresh() {
	    for(int i = this.tableModel.getRowCount() - 1; i >= 0; i--)
	        this.tableModel.removeRow(i);
	    DataContext context = new DataContext();
		Vector<Vector<String>> cate = context.getSearchFlatAsString(sflatId);
	    for(int i = 0; i < cate.size(); i++) {
	        this.tableModel.addRow(cate.get(i).toArray());
	    }
	}
	
	private boolean flatIdCheck(String sflatId) {
        if(sflatId.equals("Select a Flat ID")) return false;
        return true;
    }
}
