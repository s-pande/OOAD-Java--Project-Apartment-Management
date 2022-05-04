import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.Vector;

import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;




public class BillSearch extends JFrame implements ActionListener{

	private JPanel mainPanel,panelTable,panelUser;
	private JLabel labelTable,labelUser,labelSearch,labelFlatId,labelMonth,labelYear;
	private JButton buttonSubmit,buttonExit,buttonSearchFlat,buttonSearchMember,buttonOnlyFlatBill;
	private JSeparator separator;
	private JComboBox comboBoxFlatId,comboBoxMonth;
	private JTable table;
	private JTextField textYear;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private String sflatId,smonth,syear;
	private JButton buttonBack;
	
	public BillSearch()
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
			this.smonth = this.comboBoxMonth.getSelectedItem().toString();
			this.syear = this.textYear.getText().trim();
			if(!monthCheck(smonth)) {JOptionPane.showMessageDialog(rootPane, "Please enter valid month.");}
            else if(!yearCheck(syear)) {JOptionPane.showMessageDialog(rootPane, "Please enter valid year.");}
            else if(!flatIdCheck(sflatId)) {JOptionPane.showMessageDialog(rootPane, "Please enter valid flat Id.");}
            else {
            	this.panelTable = new JPanel();
            	this.panelTable.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
    			this.panelTable.setBounds(20, 256, 730, 147);
    			this.mainPanel.add(panelTable);
    			this.panelTable.setLayout(null);
 
    			DataContext context = new DataContext();
    			Vector<String> columns = new Vector<String>();
    			columns.add("Bill ID");
    			columns.add("Electric Bill");
    			columns.add("Water Bill");
    			columns.add("Telephone Bill");
    			columns.add("Others Bill");
    			columns.add("Month");
    			columns.add("Year");
    			columns.add("Flat No");
    			columns.add("Total Bill");
    			Vector<Vector<String>> SearchBillList = context.getSearchBillAsString(sflatId,smonth,syear);
    			if(SearchBillList.isEmpty())
    			{
    				JOptionPane.showMessageDialog(rootPane, "Nothing Found.Please Search Again.");
    			}
    			else
    			{
    				this.tableModel = new DefaultTableModel(SearchBillList, columns){
        				public boolean isCellEditable(int row, int column) {
        		            return false;
        		         }
        			};
        			
        			labelTable.setVisible(true);
        			
        			this.table = new JTable(this.tableModel);
        			this.table.getColumnModel().getColumn(0).setPreferredWidth(20);
        			this.table.getColumnModel().getColumn(1).setPreferredWidth(60);
        			this.table.getColumnModel().getColumn(2).setPreferredWidth(30);
        			this.table.getColumnModel().getColumn(3).setPreferredWidth(60);
        			this.table.getColumnModel().getColumn(4).setPreferredWidth(40);
        			this.table.getColumnModel().getColumn(5).setPreferredWidth(30);
        			this.table.getColumnModel().getColumn(6).setPreferredWidth(20);
        			this.table.getColumnModel().getColumn(7).setPreferredWidth(20);
        			this.table.getColumnModel().getColumn(8).setPreferredWidth(50);
        			
        			this.scrollPane = new JScrollPane(this.table);
        			this.scrollPane.setBounds(0, 0, 690, 147);
        			this.panelTable.add(scrollPane);
        			this.mainPanel.add(panelTable);
        			
        			Refresh();
        			MouseListener[] listeners = table.getMouseListeners();
        			for (MouseListener l : listeners)
        			{
        			    table.removeMouseListener(l);
        			}
    				
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
		else if(button.equals("ANY FLAT's ALL BILL"))
		{
			AnyFlatBill anybill = new AnyFlatBill();
			anybill.setVisible(true);
			this.setVisible(false);
		}
	}
	
	
	
	private void initializeComponents()
	{
		this.mainPanel = new JPanel();
		this.setTitle("Bill Searching");
		this.mainPanel.setLayout(null);
		
		panelUser = new JPanel();
		panelUser.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelUser.setBounds(33, 29, 658, 196);
		mainPanel.add(panelUser);
		panelUser.setLayout(null);
		
		this.labelUser = new JLabel("Search Bill Information");
		labelUser.setBounds(23, 11, 136, 18);
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
		
		labelMonth = new JLabel("Month");
		labelMonth.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelMonth.setBounds(23, 90, 84, 28);
		panelUser.add(labelMonth);
		
		String months[] = new String[] {"Select a Month","January", "February ", "March", "April","May","June","July","August","September","October","November","December"};
		
		comboBoxMonth = new JComboBox(months);
		comboBoxMonth.setBounds(117, 90, 157, 28);
		panelUser.add(comboBoxMonth);
		
		labelYear = new JLabel("Year");
		labelYear.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelYear.setBounds(23, 129, 84, 28);
		panelUser.add(labelYear);
		
		textYear = new JTextField();
		textYear.setBounds(117, 129, 157, 28);
		panelUser.add(textYear);
		
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
		
		buttonOnlyFlatBill = new JButton("ANY FLAT's ALL BILL");
		buttonOnlyFlatBill.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonOnlyFlatBill.setBounds(458, 129, 179, 28);
		panelUser.add(buttonOnlyFlatBill);
		buttonOnlyFlatBill.addActionListener(this);
		
		labelTable = new JLabel("Searched Bill Information");
		labelTable.setForeground(Color.BLUE);
		labelTable.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelTable.setBounds(274, 224, 179, 25);
		mainPanel.add(labelTable);
		this.labelTable.setVisible(false);
		
		
		this.add(this.mainPanel);
		
		this.setSize(740, 453);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void Refresh() {
	    for(int i = this.tableModel.getRowCount() - 1; i >= 0; i--)
	        this.tableModel.removeRow(i);
	    DataContext context = new DataContext();
		Vector<Vector<String>> cate = context.getSearchBillAsString(sflatId,smonth,syear);
	    for(int i = 0; i < cate.size(); i++) {
	        this.tableModel.addRow(cate.get(i).toArray());
	    }
	}
	
	private boolean flatIdCheck(String sflatId) {
        if(sflatId.equals("Select a Flat ID")) return false;
        return true;
    }
	
	private boolean monthCheck(String smonth) {
        if(smonth.equals("Select a Month")) return false;
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
