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


public class BillInformation extends JFrame implements ActionListener {
	
	private ApartmentInformation source;
	private JPanel panel;
	private JTable table;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private JLabel labelForm;
	private JButton buttonAdd,buttonExit,buttonDelete,buttonUpdate,buttonLogOut,buttonBack;
	
	
	public BillInformation(ApartmentInformation source)
	{
		this.source = source;
		this.InitializeComponents();
	}
	
	
	public void actionPerformed(ActionEvent event)
	{
		String command = event.getActionCommand();
		if(command.equals("Exit"))
		{
			System.exit(0);
		}
		else if(command.equals("ADD BILL"))
		{
			AddBillInfo add = new AddBillInfo(this);
            add.setVisible(true);
	
		}
		
		else if(command.equals("UPDATE BILL"))
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
                String electricBill = this.table.getValueAt(indices[0], 1).toString();
                String waterBill = this.table.getValueAt(indices[0], 2).toString();
                String telephoneBill = this.table.getValueAt(indices[0], 3).toString();
                String othersBill = this.table.getValueAt(indices[0], 4).toString();
                String month = this.table.getValueAt(indices[0], 5).toString();
                String year = this.table.getValueAt(indices[0], 6).toString();
                String flatId = this.table.getValueAt(indices[0], 7).toString();
                UpdateBillInfo uu = new UpdateBillInfo(this,electricBill, waterBill, telephoneBill, othersBill,month,year,flatId);
                uu.setVisible(true);
            }
			
		}
		
		else if(command.equals("DELETE BILL"))
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
                String billId = this.table.getValueAt(indices[0], 0).toString();
                String electricBill = this.table.getValueAt(indices[0], 1).toString();
                String waterBill = this.table.getValueAt(indices[0], 2).toString();
                String telephoneBill = this.table.getValueAt(indices[0], 3).toString();
                String othersBill = this.table.getValueAt(indices[0], 4).toString();
                String month = this.table.getValueAt(indices[0], 5).toString();
                String year = this.table.getValueAt(indices[0], 6).toString();
                String flatId = this.table.getValueAt(indices[0], 7).toString();
                DataContext context = new DataContext();
                context.DeleteBill(billId, electricBill, waterBill, telephoneBill, othersBill,month,year,flatId);
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
		else if(command.equals("Log Out"))
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
		this.setTitle("Bill Information");
		this.setSize(900, 550);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.labelForm = new JLabel("All Flat's Bill Information");
		this.labelForm.setBounds(356, 26, 198, 20);
		this.labelForm.setForeground(Color.blue);
		this.labelForm.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		this.panel.add(this.labelForm);
		
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
		this.tableModel = new DefaultTableModel(context.getBillListAsString(), columns){
			public boolean isCellEditable(int row, int column) {
	            return false;
	         }
		};
		
		this.table = new JTable(this.tableModel);
		this.table.getColumnModel().getColumn(0).setPreferredWidth(20);
		this.table.getColumnModel().getColumn(1).setPreferredWidth(60);
		this.table.getColumnModel().getColumn(2).setPreferredWidth(30);
		this.table.getColumnModel().getColumn(3).setPreferredWidth(60);
		this.table.getColumnModel().getColumn(4).setPreferredWidth(60);
		this.table.getColumnModel().getColumn(5).setPreferredWidth(20);
		this.table.getColumnModel().getColumn(6).setPreferredWidth(20);
		this.table.getColumnModel().getColumn(7).setPreferredWidth(20);


		
		
		
		this.scrollPane = new JScrollPane(this.table);
		this.scrollPane.setBounds(150, 80, 720, 400);
		this.panel.add(scrollPane);
		
		Refresh();
		
		this.buttonAdd = new JButton("ADD BILL");
		this.buttonAdd.setBounds(20, 80, 120, 30);
		this.buttonAdd.addActionListener(this);
		this.panel.add(this.buttonAdd);
		
		this.buttonUpdate = new JButton("UPDATE BILL");
		this.buttonUpdate.setBounds(20, 130, 120, 30);
		this.buttonUpdate.addActionListener(this);
		this.panel.add(this.buttonUpdate);
		
		this.buttonDelete = new JButton("DELETE BILL");
		this.buttonDelete.setBounds(20, 180, 120, 30);
		this.buttonDelete.addActionListener(this);
		this.panel.add(this.buttonDelete);
		
		this.buttonBack = new JButton("BACK");
		this.buttonBack.setBounds(20, 230, 120, 30);
		this.buttonBack.addActionListener(this);
		this.panel.add(buttonBack);
		
		this.buttonLogOut = new JButton("Log Out");
		this.buttonLogOut.setBounds(20, 280, 120, 30);
		this.buttonLogOut.addActionListener(this);
		this.panel.add(this.buttonLogOut);
		
		this.buttonExit = new JButton("Exit");
		this.buttonExit.setBounds(20, 330, 120, 30);
		this.buttonExit.addActionListener(this);
		this.panel.add(this.buttonExit);
		
		this.add(this.panel);
	}
	public void Refresh() {
	    for(int i = this.tableModel.getRowCount() - 1; i >= 0; i--)
	        this.tableModel.removeRow(i);
	    DataContext context = new DataContext();
		Vector<Vector<String>> cate = context.getBillListAsString();
	    for(int i = 0; i < cate.size(); i++) {
	        this.tableModel.addRow(cate.get(i).toArray());
	    }
	}
}
