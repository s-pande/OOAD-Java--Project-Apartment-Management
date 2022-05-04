import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

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


public class FamilyInformation extends JFrame implements ActionListener {
	private JPanel panel;
	private JTable table;
	private JLabel labelForm;
	private JScrollPane scrollPane;
	private JButton buttonAdd,buttonExit,buttonDelete,buttonUpdate,buttonBack;
	private DefaultTableModel tableModel;
	private JMenuBar menuBar;
	private JMenu menuAdmin;
	private JMenuItem menuItemLogout,menuItemExit;
	private JSeparator separator;
	
	public FamilyInformation()
	{
		this.InitializeComponents();
	}
	
	public void actionPerformed(ActionEvent event)
	{
		String command = event.getActionCommand();
		if(command.equals("Exit"))
		{
			System.exit(0);
		}
		else if(command.equals("ADD FAMILY"))
		{
			AddFamilyInfo add = new AddFamilyInfo(this);
			add.setVisible(true);
	
		}
		
		else if(command.equals("UPDATE FAMILY"))
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
                String headOfFamily = this.table.getValueAt(indices[0], 1).toString();
                String numberOfmembers = this.table.getValueAt(indices[0], 2).toString();
                String flatId = this.table.getValueAt(indices[0], 4).toString();
                UpdateFamilyInfo uu = new UpdateFamilyInfo(this, headOfFamily, numberOfmembers, flatId);
                uu.setVisible(true);
            }
			
		}
		
		else if(command.equals("DELETE FAMILY"))
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
                String familyId = this.table.getValueAt(indices[0], 0).toString();
                String headOfFamily = this.table.getValueAt(indices[0], 1).toString();
                String numberOfmembers = this.table.getValueAt(indices[0], 2).toString();
                String stayOfTime = this.table.getValueAt(indices[0], 3).toString(); 
                String flatId = this.table.getValueAt(indices[0], 4).toString();
                DataContext context = new DataContext();
                context.DeleteFamily(familyId , headOfFamily, numberOfmembers, stayOfTime, flatId);
            }
            Refresh();
			}
		}
		
		else if(command.equals("BACK"))
		{
			ApartmentInformation flatinfo = new ApartmentInformation();
			flatinfo.setVisible(true);
			this.setVisible(false);
		}
		
		else if(command.equals("Logout"))
		{
			Entrance login = new Entrance();
			login.setVisible(true);
			this.setVisible(false);
		}
	}
	
	private void InitializeComponents()
	{

		this.panel = new JPanel();
		this.panel.setLayout(null);
		this.setTitle("Family Information");
		this.setSize(900, 520);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.labelForm = new JLabel("All Families Information");
		this.labelForm.setBounds(438, 49, 180, 20);
		this.labelForm.setForeground(Color.blue);
		this.labelForm.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		this.panel.add(this.labelForm);
		
		DataContext context = new DataContext();
		Vector<String> columns = new Vector<String>();
		columns.add("Family ID");
		columns.add("Head of Family");
		columns.add("Number of Members");
		columns.add("Stay Time");
		columns.add("FLat ID");
		this.tableModel = new DefaultTableModel(context.getFamilyListAsString(), columns){
			public boolean isCellEditable(int row, int column) {
	            return false;
	         }
		};
		
		this.table = new JTable(this.tableModel);
		this.table.getColumnModel().getColumn(0).setPreferredWidth(30);
		this.table.getColumnModel().getColumn(1).setPreferredWidth(80);
		this.table.getColumnModel().getColumn(2).setPreferredWidth(80);
		this.table.getColumnModel().getColumn(3).setPreferredWidth(50);
		this.table.getColumnModel().getColumn(4).setPreferredWidth(10);
		
		this.menuBar = new JMenuBar();
		this.menuBar.setBounds(0, 0, 884, 20);
		this.panel.add(menuBar);
		
		this.menuAdmin = new JMenu("Admin");
		this.menuBar.add(menuAdmin);
		
		this.menuItemLogout = new JMenuItem("Logout");
		this.menuItemLogout.addActionListener(this);
		this.menuAdmin.add(menuItemLogout);
		
		this.separator = new JSeparator();
		this.menuAdmin.add(separator);
		
		this.menuItemExit = new JMenuItem("Exit");
		this.menuItemExit.addActionListener(this);
		this.menuAdmin.add(menuItemExit);
		
		this.scrollPane = new JScrollPane(this.table);
		this.scrollPane.setBounds(175, 80, 695, 380);
		this.panel.add(scrollPane);
		
		Refresh();
		
		this.buttonAdd = new JButton("ADD FAMILY");
		this.buttonAdd.setBounds(20, 80, 135, 30);
		this.buttonAdd.addActionListener(this);
		this.panel.add(this.buttonAdd);
		
		this.buttonUpdate = new JButton("UPDATE FAMILY");
		this.buttonUpdate.setBounds(20, 130, 135, 30);
		this.buttonUpdate.addActionListener(this);
		this.panel.add(this.buttonUpdate);
		
		this.buttonDelete = new JButton("DELETE FAMILY");
		this.buttonDelete.setBounds(20, 180, 135, 30);
		this.buttonDelete.addActionListener(this);
		this.panel.add(this.buttonDelete);
		
		this.buttonBack = new JButton("BACK");
		this.buttonBack.setBounds(20, 230, 135, 30);
		this.buttonBack.addActionListener(this);
		this.panel.add(this.buttonBack);
		
		this.buttonExit = new JButton("Exit");
		this.buttonExit.setBounds(20, 280, 135, 30);
		this.buttonExit.addActionListener(this);
		this.panel.add(this.buttonExit);
		
		
		this.add(this.panel);
	}
	public void Refresh() {
	    for(int i = this.tableModel.getRowCount() - 1; i >= 0; i--)
	        this.tableModel.removeRow(i);
	    DataContext context = new DataContext();
		Vector<Vector<String>> cate = context.getFamilyListAsString();
	    for(int i = 0; i < cate.size(); i++) {
	        this.tableModel.addRow(cate.get(i).toArray());
	    }
	}
}