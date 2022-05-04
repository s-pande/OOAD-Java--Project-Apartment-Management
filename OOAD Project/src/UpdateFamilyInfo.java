import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class UpdateFamilyInfo extends JFrame implements ActionListener {

	private FamilyInformation source;
	private JPanel mainPanel;
	private JLabel labelForm,labelHeadOfFamily,labelNumberOfMembers,labelFlatId;
	private JTextField textHeadOfFamily,textNumberOfMembers;
	private JButton buttonSave, buttonCancel;
	private JComboBox comboBoxFlatId;
	private String headOfFamily, numberOfmembers, flatId;
	public UpdateFamilyInfo(FamilyInformation source,String headOfFamily,String numberOfmembers,String flatId)
	{
		this.source = source;
		this.headOfFamily = headOfFamily;
		this.numberOfmembers = numberOfmembers;
		this.flatId = flatId;
		this.InitializeComponents();
	}
	
	public void actionPerformed(ActionEvent event)
	{
		String command = event.getActionCommand();
		if(command.equals("Update"))
		{
            String UheadOfFamily,UnumberOfmembers,UflatId;
            UheadOfFamily = this.textHeadOfFamily.getText().trim();
            UnumberOfmembers = this.textNumberOfMembers.getText().trim();
            UflatId = this.comboBoxFlatId.getSelectedItem().toString();
            if(!nameCheck(UheadOfFamily)) {JOptionPane.showMessageDialog(rootPane, "Please enter valid name of family head.");}
            else if(!membersCheck(UnumberOfmembers)) {JOptionPane.showMessageDialog(rootPane, "Please enter valid number of members.");}
            else if(!flatIdCheck(UflatId)) {JOptionPane.showMessageDialog(rootPane, "Please enter valid flat id.");}
            else {
            	DataContext context = new DataContext();
            	Family family = new Family();
            	family.setHeadOfFamily(headOfFamily);
            	family.setNumberOfmembers(Integer.parseInt(numberOfmembers));
            	family.setStayOfTime(new Date());
            	family.setFlatId(flatId);
                if(context.UpdateFamilyInfo(family,UheadOfFamily,UnumberOfmembers,UflatId))
                {
                	source.Refresh();
                	JOptionPane.showMessageDialog(rootPane, "Family information updated successfully.");
                	this.dispose();
                }
                else
                {
                	JOptionPane.showMessageDialog(null, "Something wrong.Please Try Again.");
                }
            }
        }
        else if(event.getActionCommand().equals("Cancel")) {
            this.dispose();
        }
	}
	
	private void InitializeComponents()
	{
		this.mainPanel = new JPanel();
		this.mainPanel.setLayout(null);
		this.setTitle("Family Details");
		this.setSize(430, 306);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.labelForm = new JLabel("Update Family Information");
		this.labelForm.setBounds(125, 10, 180, 20);
		this.labelForm.setForeground(Color.blue);
		this.labelForm.setFont(new Font("Serif", Font.BOLD, 15));
		this.mainPanel.add(this.labelForm);
		
		this.labelHeadOfFamily = new JLabel("Head Of Family");
		this.labelHeadOfFamily.setBounds(20, 50, 171, 20);
		this.mainPanel.add(this.labelHeadOfFamily);
		
		this.textHeadOfFamily = new JTextField(headOfFamily);
		this.textHeadOfFamily.setBounds(200, 50, 180, 20);
		this.mainPanel.add(this.textHeadOfFamily);
		
		this.labelNumberOfMembers = new JLabel("Number Of Members");
		this.labelNumberOfMembers.setBounds(20, 90, 171, 20);
		this.mainPanel.add(this.labelNumberOfMembers);
		
		this.textNumberOfMembers = new JTextField(numberOfmembers);
		this.textNumberOfMembers.setBounds(200, 90, 180, 20);
		this.mainPanel.add(this.textNumberOfMembers);
		
		this.labelFlatId = new JLabel("Flat ID");
		this.labelFlatId.setBounds(20, 130, 171, 20);
		this.mainPanel.add(labelFlatId);
		
		DataContext context = new DataContext();
		Vector<String> FlatId = context.getFlatId();
		
		FlatId.add(0, "Select a Flat ID");
		
		this.comboBoxFlatId = new JComboBox(FlatId.toArray());
		this.comboBoxFlatId.setBounds(200, 130, 180, 20);
		this.mainPanel.add(this.comboBoxFlatId);
		
		comboBoxFlatId.setSelectedItem(flatId);
		
		this.buttonCancel = new JButton("Cancel");
		this.buttonCancel.setBounds(66, 220, 100, 20);
		this.buttonCancel.addActionListener(this);
		this.mainPanel.add(this.buttonCancel);
		
		this.buttonSave = new JButton("Update");
		this.buttonSave.setBounds(242, 220, 100, 20);
		this.buttonSave.addActionListener(this);
		this.mainPanel.add(this.buttonSave);
		
		this.add(this.mainPanel);
		
	}
	
	private boolean nameCheck(String headOfFamily) {
        if(headOfFamily.isEmpty()) return false;
        else {
            boolean flag = true;
            for(int i = 0; i < headOfFamily.length(); i++) {
                if(Character.isDigit(headOfFamily.charAt(i))) {
                    flag = false;
                    break;
                }
            }
            return flag;
        }
    }
    
	private boolean membersCheck(String numberOfMembers) {
        if(numberOfMembers.equals("")) return true;
        boolean flag = true;
        for(int i = 0; i < numberOfMembers.length(); i++) {
            if(!Character.isDigit(numberOfMembers.charAt(i))) {
                flag = false;
                break;
            }
        }
        return flag;
    }
    
    private boolean flatIdCheck(String UflatId) {
        if(UflatId.equals("Select a Flat ID")) return false;
        return true;
    }
}
