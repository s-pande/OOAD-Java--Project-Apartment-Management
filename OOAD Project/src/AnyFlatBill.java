import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.Vector;

import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;




public class AnyFlatBill extends JFrame implements ActionListener{

	private JPanel mainPanel,panelTable,panelInfo,panelUser;
	private JLabel labelTable,labelUser,labelSearch,labelFlatId,labelYear;
	private JButton buttonSubmit,buttonBack,buttonSearchFlat,buttonSearchMember,buttonHome;
	private JSeparator separator;
	private JComboBox comboBoxFlatId;
	private JTextField textyear;
	private JTable table;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private String sflatId,syear;
	
	public AnyFlatBill()
	{
		this.initializeComponents();
	}
	
	public void actionPerformed(ActionEvent event)
	{
		DataContext context = new DataContext();
		String button = event.getActionCommand();
		if(button.equals("BACK"))
		{
			BillSearch login = new BillSearch();
			login.setVisible(true);
			this.setVisible(false);
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
			this.syear = this.textyear.getText().trim();
			if(!yearCheck(syear)) {JOptionPane.showMessageDialog(rootPane, "Please enter valid year.");}
            else if(!flatIdCheck(sflatId)) {JOptionPane.showMessageDialog(rootPane, "Please enter valid flat Id.");}
            else {
            	context.CreateFlatVeiw(sflatId, syear);
            	
            	labelTable.setVisible(true);
            	
            	this.panelTable = new JPanel();
            	this.panelTable.setLayout(null);
            	this.panelTable.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
    			this.panelTable.setBounds(20, 280, 690, 140);
    			
    			JLabel labelTable1 = new JLabel("All Bill Information of Flat No: " + sflatId + " in " + syear);
    			labelTable1.setBounds(215, 10, 450, 20);
    			labelTable1.setForeground(Color.BLUE);
    			labelTable1.setFont(new Font("Tahoma", Font.PLAIN, 15));
    			panelTable.add(labelTable1);
    			
    			this.mainPanel.add(panelTable);
    			
 
    			Vector<String> columns = new Vector<String>();
    			columns.add("Bill ID");
    			columns.add("Electric Bill");
    			columns.add("Water Bill");
    			columns.add("Telephone Bill");
    			columns.add("Others Bill");
    			columns.add("Month");
    			Vector<Vector<String>> SearchAnyFlatBillList = context.getAnyFlatBillAsString();
    			if(SearchAnyFlatBillList.isEmpty())
    			{
    				JOptionPane.showMessageDialog(rootPane, "Nothing Found.Please Search Again.");
    			}
    			else
    			{
    				this.tableModel = new DefaultTableModel(SearchAnyFlatBillList, columns){
        				public boolean isCellEditable(int row, int column) {
        		            return false;
        		         }
        			};
        			
        			this.table = new JTable(this.tableModel);
        			this.table.getColumnModel().getColumn(0).setPreferredWidth(20);
        			this.table.getColumnModel().getColumn(1).setPreferredWidth(30);
        			this.table.getColumnModel().getColumn(2).setPreferredWidth(30);
        			this.table.getColumnModel().getColumn(3).setPreferredWidth(30);
        			this.table.getColumnModel().getColumn(4).setPreferredWidth(30);
        			this.table.getColumnModel().getColumn(4).setPreferredWidth(30);
        			
        			this.scrollPane = new JScrollPane(this.table);
        			this.scrollPane.setBounds(0, 40, 690, 100);
        			this.panelTable.add(scrollPane);
        			this.mainPanel.add(panelTable);
    				
    			}
    			
            }
		}
		
		else if(button.equals("SEARCH MEMBER"))
		{
			MemberSearch frame = new MemberSearch();
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
		this.setTitle("Bill Searching");
		this.mainPanel.setLayout(null);
		
		panelUser = new JPanel();
		panelUser.setLayout(null);
		panelUser.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelUser.setBounds(33, 30, 658, 195);
		mainPanel.add(panelUser);
		
		this.labelUser = new JLabel("Please select any flat no and also enter year");
		labelUser.setBounds(23, 11, 300, 18);
		panelUser.add(labelUser);
		this.labelUser.setForeground(Color.BLUE);
		this.labelUser.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		labelFlatId = new JLabel("Flat ID");
		labelFlatId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelFlatId.setBounds(23, 49, 84, 28);
		panelUser.add(labelFlatId);
		
		DataContext context = new DataContext();
		Vector<String> FlatId = context.getFlatId();
		
		FlatId.add(0, "Select a Flat ID");
		
		this.comboBoxFlatId = new JComboBox(FlatId.toArray());
		this.comboBoxFlatId.setBounds(117, 49, 157, 28);
		panelUser.add(comboBoxFlatId);
		
		labelYear = new JLabel("Year");
		labelYear.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelYear.setBounds(23, 90, 84, 28);
		panelUser.add(labelYear);
		
		textyear = new JTextField();
		textyear.setBounds(117, 90, 157, 28);
		panelUser.add(textyear);
		
		this.buttonSubmit = new JButton("SUBMIT");
		buttonSubmit.setBounds(318, 48, 91, 29);
		panelUser.add(buttonSubmit);
		this.buttonSubmit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.buttonSubmit.addActionListener(this);
		
		buttonHome = new JButton("HOME");
		buttonHome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonHome.setBounds(458, 128, 179, 28);
		panelUser.add(buttonHome);
		this.buttonHome.addActionListener(this);
		
		buttonBack = new JButton("BACK");
		buttonBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonBack.setBounds(318, 87, 91, 29);
		panelUser.add(buttonBack);
		this.buttonBack.addActionListener(this);
		
		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(433, 0, 15, 194);
		panelUser.add(separator);
		
		labelSearch = new JLabel("Search");
		labelSearch.setForeground(Color.BLUE);
		labelSearch.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		labelSearch.setBounds(526, 11, 48, 18);
		panelUser.add(labelSearch);
		
		buttonSearchFlat = new JButton("SEARCH FLAT");
		buttonSearchFlat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonSearchFlat.setBounds(458, 49, 179, 28);
		panelUser.add(buttonSearchFlat);
		buttonSearchFlat.addActionListener(this);
		
		buttonSearchMember = new JButton("SEARCH MEMBER");
		buttonSearchMember.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonSearchMember.setBounds(458, 89, 179, 28);
		panelUser.add(buttonSearchMember);
		buttonSearchMember.addActionListener(this);
		
		labelTable = new JLabel("");
		labelTable.setBounds(235, 235,50, 20);
		mainPanel.add(labelTable);
		labelTable.setVisible(false);
		
		this.add(this.mainPanel);
		
		this.setSize(740, 470);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private boolean flatIdCheck(String sflatId) {
        if(sflatId.equals("Select a Flat ID")) return false;
        return true;
    }
	
	private boolean yearCheck(String syear) {
        if(syear.equals("")) return false;
        boolean flag = true;
        for(int i = 0; i < syear.length(); i++) {
            if(!Character.isDigit(syear.charAt(i))) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}
