import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class AllSearchFlat extends JFrame implements ActionListener {
	private JPanel panel;
	private JTable table;
	private JLabel labelForm;
	private JScrollPane scrollPane;
	private JButton buttonExit,buttonHome,buttonBack;
	private DefaultTableModel tableModel;
	
	public AllSearchFlat()
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
		else if(command.equals("BACK"))
		{
			FlatSearch add = new FlatSearch();
            add.setVisible(true);
            this.setVisible(false);
	
		}
		else if(command.equals("HOME"))
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
		this.setTitle("All Flat Information");
		this.setSize(900, 235);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.labelForm = new JLabel("All Flats Information");
		this.labelForm.setBounds(373, 11, 164, 20);
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
		this.scrollPane.setBounds(150, 42, 720, 142);
		this.panel.add(scrollPane);
		
		Refresh();
		
		this.buttonBack = new JButton("BACK");
		buttonBack.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.buttonBack.setBounds(20, 42, 120, 30);
		this.buttonBack.addActionListener(this);
		this.panel.add(buttonBack);
		
		this.buttonHome = new JButton("HOME");
		buttonHome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.buttonHome.setBounds(20, 89, 120, 30);
		this.buttonHome.addActionListener(this);
		this.panel.add(this.buttonHome);
		
		this.buttonExit = new JButton("EXIT");
		buttonExit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.buttonExit.setBounds(20, 147, 120, 30);
		this.buttonExit.addActionListener(this);
		this.panel.add(this.buttonExit);
		
		
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
