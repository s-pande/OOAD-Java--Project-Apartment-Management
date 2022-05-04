import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UpdateFlatInfo extends JFrame implements ActionListener{
    
    private ApartmentInformation source;
    private JPanel mainPanel;
	private JLabel labelForm,labelFlatId,labelOwnerName,labelFlatLocation,labelIntercomeNumber,labelRentCost,labelDescription;
	private JTextField textFlatId,textOwnerName,textFlatLocation,textDescription,textRentCost,textIntercomeNumber;
	private JButton buttonSave, buttonCancel;
    private String flatId, ownerName, description, flatLocation, intercomeNumber,rentCost;
    
    public UpdateFlatInfo(ApartmentInformation source, String flatId,String ownerName,String description,String flatLocation,String intercomeNumber,String rentCost) {
        this.source = source;
        this.flatId = flatId;
        this.ownerName = ownerName;
        this.description = description;
        this.flatLocation = flatLocation;
        this.intercomeNumber = intercomeNumber;
        this.rentCost = rentCost;
        InitializeComponents();
    }
    public void actionPerformed(ActionEvent event) {
        if(event.getActionCommand().equals("Update")) {
        	String UflatId, UownerName, Udescription, UflatLocation, UintercomeNumber,UrentCost;
        	UflatId = this.textFlatId.getText().trim();
           	UownerName = this.textOwnerName.getText().trim();
           	Udescription = this.textDescription.getText().trim();
           	UflatLocation = this.textFlatLocation.getText().trim();
           	UintercomeNumber = this.textIntercomeNumber.getText().trim();
           	UrentCost = this.textRentCost.getText().trim();
           	if(flatIdCheck(UflatId)) JOptionPane.showMessageDialog(rootPane, "Please enter a valid Id.");
            else if(!ownerNameCheck(UownerName)) {JOptionPane.showMessageDialog(rootPane, "Please enter valid name.");}
            else if(Udescription.isEmpty()) JOptionPane.showMessageDialog(rootPane, "Please enter a description.");
            else if(!intercomeNumberCheck(UintercomeNumber)) {JOptionPane.showMessageDialog(rootPane, "Please enter valid phone number.");}
            else if(!flatLocationCheck(UflatLocation)) JOptionPane.showMessageDialog(rootPane, "Please enter a valid flat floor no.");
            else if(rentCostCheck(UrentCost)) {JOptionPane.showMessageDialog(rootPane, "Please enter valid rent cost.");}
            else {
            	DataContext context = new DataContext();
            	Flat flat = new Flat();
            	flat.setFlatId(this.flatId);
            	flat.setOwnerName(this.ownerName);
            	flat.setDescription(this.description);
            	flat.setFlatLocation(this.flatLocation);
            	flat.setIntercomeNumber(this.intercomeNumber);
            	flat.setRentCost(this.rentCost);
                if(context.Updateflatinfo(flat ,UflatId, UownerName, Udescription, UflatLocation, UintercomeNumber,UrentCost))
                {
                	source.Refresh();
                    JOptionPane.showMessageDialog(null, "Flat information updated successfully.");
                    this.setVisible(false);
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
		this.setTitle("Flat Details");
		this.setSize(420, 320);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.labelForm = new JLabel("Flat Information");
		this.labelForm.setBounds(150, 10, 118, 20);
		this.labelForm.setForeground(Color.blue);
		this.labelForm.setFont(new Font("Serif", Font.BOLD, 15));
		this.mainPanel.add(this.labelForm);
		
		this.labelFlatId = new JLabel("Flat Id");
		this.labelFlatId.setBounds(20, 50, 171, 20);
		this.mainPanel.add(this.labelFlatId);
		
		this.textFlatId = new JTextField(flatId);
		this.textFlatId.setBounds(200, 50, 180, 20);
		this.mainPanel.add(this.textFlatId);
		
		this.labelOwnerName = new JLabel("Owner Name");
		this.labelOwnerName.setBounds(20, 80, 171, 20);
		this.mainPanel.add(this.labelOwnerName);
		
		this.textOwnerName = new JTextField(ownerName);
		this.textOwnerName.setBounds(200, 80, 180, 20);
		this.mainPanel.add(this.textOwnerName);
		
		this.labelDescription = new JLabel("Description");
		this.labelDescription.setBounds(20, 110, 171, 20);
		this.mainPanel.add(this.labelDescription);
		
		this.textDescription = new JTextField(description);
		this.textDescription.setBounds(200, 110, 180, 20);
		this.mainPanel.add(this.textDescription);
		
		this.labelFlatLocation = new JLabel("Floor No");
		this.labelFlatLocation.setBounds(20, 140, 171, 20);
		this.mainPanel.add(this.labelFlatLocation);
		
		this.textFlatLocation = new JTextField(flatLocation);
		this.textFlatLocation.setBounds(200, 140, 180, 20);
		this.mainPanel.add(this.textFlatLocation);
		
		this.labelIntercomeNumber = new JLabel("Intercome Number");
		this.labelIntercomeNumber.setBounds(20, 171, 171, 20);
		this.mainPanel.add(labelIntercomeNumber);
		
		this.textIntercomeNumber = new JTextField(intercomeNumber);
		this.textIntercomeNumber.setBounds(200, 171, 180, 20);
		this.mainPanel.add(textIntercomeNumber);
		
		this.labelRentCost = new JLabel("Rent Cost");
		this.labelRentCost.setBounds(20, 202, 171, 20);
		this.mainPanel.add(labelRentCost);
		
		this.textRentCost = new JTextField(rentCost);
		this.textRentCost.setBounds(200, 202, 180, 20);
		this.mainPanel.add(textRentCost);
		
		this.buttonCancel = new JButton("Cancel");
		this.buttonCancel.setBounds(69, 242, 100, 20);
		this.buttonCancel.addActionListener(this);
		this.mainPanel.add(this.buttonCancel);
		
		this.buttonSave = new JButton("Update");
		this.buttonSave.setBounds(235, 242, 100, 20);
		this.buttonSave.addActionListener(this);
		this.mainPanel.add(this.buttonSave);
		
		this.add(this.mainPanel);
		
	}
    
    private boolean flatIdCheck(String UflatId) {
        if(UflatId.equals("")) return true;
        boolean flag = true;
        for(int i = 0; i < UflatId.length(); i++) {
            if(!Character.isDigit(UflatId.charAt(i)) && UflatId.charAt(i) == '-') {
                flag = false;
                break;
            }
        }
        return flag;
    }
	
	private boolean flatLocationCheck(String UflatLocation) {
        if(UflatLocation.equals("")) return true;
        boolean flag = true;
        for(int i = 0; i < UflatLocation.length(); i++) {
            if(!Character.isDigit(UflatLocation.charAt(i))) {
                flag = false;
                break;
            }
        }
        return flag;
    }
	
	private boolean ownerNameCheck(String UownerName)  {
        if(UownerName.isEmpty()) return false;
        else {
            boolean flag = true;
            for(int i = 0; i < UownerName.length(); i++) {
                if(Character.isDigit(UownerName.charAt(i))) {
                    flag = false;
                    break;
                }
            }
            return flag;
        }
    }
    
    private boolean intercomeNumberCheck(String UintercomeNumber) {
        if(UintercomeNumber.equals("")) return true;
        boolean flag = true;
        for(int i = 0; i < UintercomeNumber.length(); i++) {
            if(!Character.isDigit(UintercomeNumber.charAt(i))) {
                flag = false;
                break;
            }
        }
        return flag;
    }
    
    private boolean rentCostCheck(String UrentCost){
        if(UrentCost.equals("")) return true;
        boolean flag = true;
        for(int i = 0; i < UrentCost.length(); i++) {
            if(UrentCost.charAt(i)== '.') { flag = false; break;}
        }
        return flag;
    }
    
}
