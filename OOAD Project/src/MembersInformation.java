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


 public class MembersInformation extends JFrame implements ActionListener {
 	
 	private ApartmentInformation source;
 	private JPanel panel;
 	private JTable table;
 	private JLabel labelForm;
 	private JScrollPane scrollPane;
 	private JButton buttonAdd,buttonExit,buttonDelete,buttonUpdate,buttonLogOut,buttonBack;
 	private DefaultTableModel tableModel;
 	
 	public MembersInformation(ApartmentInformation source)
 	{
 		this.source = source;
 		this.InitializeComponents();
 	}
 	
 	
 	public void actionPerformed(ActionEvent event)
 	{
 		String command = event.getActionCommand();
 		if(command.equals("EXIT"))
 		{
 			System.exit(0);
 		}
 		else if(command.equals("ADD MEMBER"))
 		{
 			AddMemberInfo add = new AddMemberInfo(this);
            add.setVisible(true);
 	
 		}
 		
 		else if(command.equals("UPDATE MEMBER"))
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
                 String name = this.table.getValueAt(indices[0], 1).toString();
                 String email = this.table.getValueAt(indices[0], 2).toString();
                 String permanentAddress = this.table.getValueAt(indices[0], 3).toString();
                 String phone = this.table.getValueAt(indices[0], 4).toString();
                 String occupation = this.table.getValueAt(indices[0], 5).toString();
                 String familyId = this.table.getValueAt(indices[0], 6).toString();
                 UpdateMemberInfo uu = new UpdateMemberInfo(this,name,email,permanentAddress,phone,occupation,familyId);
                 uu.setVisible(true);
             }
 			
 		}
 		
 		else if(command.equals("DELETE MEMBER"))
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
                 String memberId = this.table.getValueAt(indices[0], 0).toString();
                 String name = this.table.getValueAt(indices[0], 1).toString();
                 String email = this.table.getValueAt(indices[0], 2).toString();
                 String permanentAddress = this.table.getValueAt(indices[0], 3).toString();
                 String phone = this.table.getValueAt(indices[0], 4).toString();
                 String occupation = this.table.getValueAt(indices[0], 5).toString();
                 String familyId = this.table.getValueAt(indices[0], 6).toString();
                 DataContext context = new DataContext();
                 context.DeleteMember(memberId,name,email,permanentAddress,phone,occupation,familyId);
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
 		else if(command.equals("LOG OUT"))
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
 		this.setTitle("Members Information");
 		this.setSize(1200, 380);
 		this.setLocationRelativeTo(null);
 		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		
 		this.labelForm = new JLabel("All Member's Information");
 		this.labelForm.setBounds(570, 26, 198, 20);
 		this.labelForm.setForeground(Color.blue);
 		this.labelForm.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
 		this.panel.add(this.labelForm);
 		
 		DataContext context = new DataContext();
 		Vector<String> columns = new Vector<String>();
 		columns.add("Member ID");
 		columns.add("Name");
 		columns.add("Email");
 		columns.add("Permanent Address");
 		columns.add("Phone");
 		columns.add("Occupation");
 		columns.add("Family No");
 		this.tableModel = new DefaultTableModel(context.getMemberListAsString(), columns){
 			public boolean isCellEditable(int row, int column) {
 	            return false;
 	         }
 		};
 		
 		this.table = new JTable(this.tableModel);
 		this.table.getColumnModel().getColumn(0).setPreferredWidth(10);
 		this.table.getColumnModel().getColumn(1).setPreferredWidth(20);
 		this.table.getColumnModel().getColumn(2).setPreferredWidth(50);
 		this.table.getColumnModel().getColumn(3).setPreferredWidth(100);
 		this.table.getColumnModel().getColumn(4).setPreferredWidth(20);
 		this.table.getColumnModel().getColumn(5).setPreferredWidth(10);
 		this.table.getColumnModel().getColumn(6).setPreferredWidth(10);

 		
 		this.scrollPane = new JScrollPane(this.table);
 		this.scrollPane.setBounds(170, 50, 1000,280);
 		this.panel.add(scrollPane);
 		
 		Refresh();
 		
 		this.buttonAdd = new JButton("ADD MEMBER");
 		this.buttonAdd.setBounds(20, 50, 140, 30);
 		this.buttonAdd.addActionListener(this);
 		this.panel.add(this.buttonAdd);
 		
 		this.buttonUpdate = new JButton("UPDATE MEMBER");
 		this.buttonUpdate.setBounds(20, 100, 140, 30);
 		this.buttonUpdate.addActionListener(this);
 		this.panel.add(this.buttonUpdate);
 		
 		this.buttonDelete = new JButton("DELETE MEMBER");
 		this.buttonDelete.setBounds(20, 150, 140, 30);
 		this.buttonDelete.addActionListener(this);
 		this.panel.add(this.buttonDelete);
 		
 		this.buttonBack = new JButton("BACK");
 		this.buttonBack.setBounds(20, 200, 140, 30);
 		this.buttonBack.addActionListener(this);
 		this.panel.add(buttonBack);
 		
 		this.buttonLogOut = new JButton("LOG OUT");
 		this.buttonLogOut.setBounds(20, 250, 140, 30);
 		this.buttonLogOut.addActionListener(this);
 		this.panel.add(this.buttonLogOut);
 		
 		this.buttonExit = new JButton("EXIT");
 		this.buttonExit.setBounds(20, 300, 140, 30);
 		this.buttonExit.addActionListener(this);
 		this.panel.add(this.buttonExit);
 		
 		this.add(this.panel);
 	}
 	public void Refresh() {
 	    for(int i = this.tableModel.getRowCount() - 1; i >= 0; i--)
 	        this.tableModel.removeRow(i);
 	    DataContext context = new DataContext();
 		Vector<Vector<String>> cate = context.getMemberListAsString();
 	    for(int i = 0; i < cate.size(); i++) {
 	        this.tableModel.addRow(cate.get(i).toArray());
 	    }
 	}
 }

