import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ApartmentInformation extends JFrame implements ActionListener {
	private JPanel panel;
	private JTable table;
	private JLabel labelForm;
	private JScrollPane scrollPane;
	private JButton buttonAdd,buttonExit,buttonDelete,buttonUpdate,buttonLogOut,buttonFamily,buttonBills,buttonMembers;
	private DefaultTableModel tableModel;
	private JMenuBar menuBar;
	private JMenu menuAdmin,menuSearch;
	private JMenuItem menuItemAddAdmin,menuItemExit,menuItemDetailsInformatonOfMembers;
	private JSeparator separator;
	
	public ApartmentInformation()
	{
		this.InitializeComponents();
	}
	
	
	public void actionPerformed(ActionEvent event)
	{
		String command = event.getActionCommand();
		if(command.equals("EXIT"))
		{
			System.exit(0);
		}
		else if(command.equals("ADD FLAT"))
		{
			AddFlatInfo add = new AddFlatInfo(this);
            add.setVisible(true);
	
		}
		
		else if(command.equals("UPDATE FLAT"))
		{
			int[] indices = this.table.getSelectedRows();
            if(indices.length > 1)
            	{
            		JOptionPane.showMessageDialog(rootPane, "Please select only one row.");
            	}
            else if(indices.length == 0)
        	{
        		JOptionPane.showMessageDialog(rootPane, "Please select any row.");
        	}
            else {
                String flatId = this.table.getValueAt(indices[0], 0).toString();
                String ownerName = this.table.getValueAt(indices[0], 1).toString();
                String description = this.table.getValueAt(indices[0], 2).toString();
                String flatLocation = this.table.getValueAt(indices[0], 3).toString();
                String intercomeNumber = this.table.getValueAt(indices[0], 4).toString();
                String rentCost = this.table.getValueAt(indices[0], 5).toString();
                UpdateFlatInfo uu = new UpdateFlatInfo(this, flatId, ownerName, description, flatLocation, intercomeNumber,rentCost);
                uu.setVisible(true);
            }
			
		}
		
		else if(command.equals("DELETE FLAT"))
		{
			int[] indices = this.table.getSelectedRows();
			if(indices.length < 1)
			{
				JOptionPane.showMessageDialog(rootPane, "Please select any row.");
			}
			else
			{
            for(int i = indices.length - 1; i >= 0; i--) {
                int index = indices[i];
                String flatId = this.table.getValueAt(indices[0], 0).toString();
                String ownerName = this.table.getValueAt(indices[0], 1).toString();
                String description = this.table.getValueAt(indices[0], 2).toString();
                String flatLocation = this.table.getValueAt(indices[0], 3).toString();
                String intercomeNumber = this.table.getValueAt(indices[0], 4).toString();
                String rentCost = this.table.getValueAt(indices[0], 5).toString();
                DataContext context = new DataContext();
                context.DeleteFlat(flatId, ownerName, description, flatLocation, intercomeNumber,rentCost);
            }
            Refresh();
			}
		}
		
		else if(command.equals("FAMILIES INFO"))
		{
			FamilyInformation familyinfo = new FamilyInformation();
			familyinfo.setVisible(true);
			this.setVisible(false);
		}
		
		else if(command.equals("BILLS INFO"))
		{
			BillInformation billinfo = new BillInformation(this);
			billinfo.setVisible(true);
			this.setVisible(false);
		}
		
		else if(command.equals("MENBERS INFO"))
		{
			MembersInformation memberinfo = new MembersInformation(this);
			memberinfo.setVisible(true);
			this.setVisible(false);
		}
		
		else if(command.equals("Add Admin"))
		{
			AddAdmin addAdmin = new AddAdmin(this);
			addAdmin.setVisible(true);
		}
		
		else if(command.equals("LOG OUT"))
		{
			Entrance login = new Entrance();
			login.setVisible(true);
			this.setVisible(false);
		}
		else if(command.equals("Details information of members"))
		{
			DetailsMembersInfo login = new DetailsMembersInfo();
			login.setVisible(true);
			this.setVisible(false);
		}
	}
	
	private void InitializeComponents()
	{

		this.panel = new JPanel();
		this.panel.setLayout(null);
		this.setTitle("Admin");
		this.setSize(900, 520);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.labelForm = new JLabel("All Flats Information");
		this.labelForm.setBounds(390, 49, 164, 20);
		this.labelForm.setForeground(Color.blue);
		this.labelForm.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		this.panel.add(this.labelForm);
		
		DataContext context = new DataContext();
		Vector<String> columns = new Vector<String>();
		columns.add("Flat ID");
		columns.add("Owner Name");
		columns.add("Description");
		columns.add("Floor No");
		columns.add("Intercome Number");
		columns.add("Rent Cost");
		this.tableModel = new DefaultTableModel(context.getFlatListAsString(), columns){
			public boolean isCellEditable(int row, int column) {
	            return false;
	         }
		};
		
		this.table = new JTable(this.tableModel);
		this.table.getColumnModel().getColumn(0).setPreferredWidth(10);
		this.table.getColumnModel().getColumn(1).setPreferredWidth(25);
		this.table.getColumnModel().getColumn(2).setPreferredWidth(120);
		this.table.getColumnModel().getColumn(3).setPreferredWidth(20);
		this.table.getColumnModel().getColumn(4).setPreferredWidth(30);
		this.table.getColumnModel().getColumn(5).setPreferredWidth(15);
		
		this.scrollPane = new JScrollPane(this.table);
		this.scrollPane.setBounds(150, 80, 720, 380);
		this.panel.add(scrollPane);
		
		Refresh();
		
		this.buttonAdd = new JButton("ADD FLAT");
		this.buttonAdd.setBounds(20, 80, 120, 30);
		this.buttonAdd.addActionListener(this);
		this.panel.add(this.buttonAdd);
		
		this.buttonUpdate = new JButton("UPDATE FLAT");
		this.buttonUpdate.setBounds(20, 130, 120, 30);
		this.buttonUpdate.addActionListener(this);
		this.panel.add(this.buttonUpdate);
		
		this.buttonDelete = new JButton("DELETE FLAT");
		this.buttonDelete.setBounds(20, 180, 120, 30);
		this.buttonDelete.addActionListener(this);
		this.panel.add(this.buttonDelete);
		
		this.buttonFamily = new JButton("FAMILIES INFO");
		this.buttonFamily.setBounds(20, 230, 120, 30);
		this.buttonFamily.addActionListener(this);
		this.panel.add(buttonFamily);
		
		this.buttonBills = new JButton("BILLS INFO");
		this.buttonBills.setBounds(20, 280, 120, 30);
		this.buttonBills.addActionListener(this);
		this.panel.add(buttonBills);
		
		this.buttonMembers = new JButton("MENBERS INFO");
		this.buttonMembers.setBounds(20, 330, 120, 30);
		this.buttonMembers.addActionListener(this);
		this.panel.add(buttonMembers);
		
		this.buttonLogOut = new JButton("LOG OUT");
		this.buttonLogOut.setBounds(20, 380, 120, 30);
		this.buttonLogOut.addActionListener(this);
		this.panel.add(this.buttonLogOut);
		
		this.buttonExit = new JButton("EXIT");
		this.buttonExit.setBounds(20, 430, 120, 30);
		this.buttonExit.addActionListener(this);
		this.panel.add(this.buttonExit);
		
		this.menuBar = new JMenuBar();
		this.menuBar.setBounds(0, 0, 884, 20);
		this.panel.add(menuBar);
		
		this.menuAdmin = new JMenu("Admin");
		this.menuBar.add(menuAdmin);
		
		this.menuItemAddAdmin = new JMenuItem("Add Admin");
		this.menuItemAddAdmin.addActionListener(this);
		this.menuAdmin.add(menuItemAddAdmin);
		
		separator = new JSeparator();
		menuAdmin.add(separator);
		
		this.menuItemExit = new JMenuItem("EXIT");
		this.menuItemExit.addActionListener(this);
		this.menuAdmin.add(menuItemExit);
		
		menuSearch = new JMenu("Search");
		menuBar.add(menuSearch);
		
		menuItemDetailsInformatonOfMembers = new JMenuItem("Details information of members");
		menuSearch.add(menuItemDetailsInformatonOfMembers);
		this.menuItemDetailsInformatonOfMembers.addActionListener(this);
		
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon("F:\\Eclipse Files\\Registration\\bin\\bg.jpg"));
		label.setBounds(0, 0, 900, 520);
		panel.add(label);

		this.add(this.panel);
	}
	public void Refresh() {
	    for(int i = this.tableModel.getRowCount() - 1; i >= 0; i--)
	        this.tableModel.removeRow(i);
	    DataContext context = new DataContext();
		Vector<Vector<String>> cate = context.getFlatListAsString();
	    for(int i = 0; i < cate.size(); i++) {
	        this.tableModel.addRow(cate.get(i).toArray());
	    }
	}
}
