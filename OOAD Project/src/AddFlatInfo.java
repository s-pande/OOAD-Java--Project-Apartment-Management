import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;


class AddFlatInfo extends JFrame implements ActionListener {

	private static ApartmentInformation source;
	private JPanel mainPanel;
	private JLabel labelForm,labelFlatId,labelOwnerName,labelFlatLocation,labelIntercomeNumber,labelRentCost,labelDescription;
	private JTextField textFlatId,textOwnerName,textFlatLocation,textDescription,textRentCost,textIntercomeNumber;
	private JButton buttonSave, buttonCancel;
	
	public AddFlatInfo(ApartmentInformation source)
	{
		this.source = source;
		this.InitializeComponents();
	}
	
	public void actionPerformed(ActionEvent event)
	{
		String command = event.getActionCommand();
		if(command.equals("Save"))
		{
            String flatId, ownerName, description, flatLocation, intercomeNumber,rentCost;
            flatId = this.textFlatId.getText().trim();
            ownerName = this.textOwnerName.getText().trim();
            description = this.textDescription.getText().trim();
            flatLocation = this.textFlatLocation.getText().trim();
            intercomeNumber = this.textIntercomeNumber.getText().trim();
            rentCost = this.textRentCost.getText().trim();
            if(flatIdCheck(flatId)) JOptionPane.showMessageDialog(rootPane, "Please enter a valid flat Id.");
            else if(!ownerNameCheck(ownerName)) {JOptionPane.showMessageDialog(rootPane, "Please enter valid name.");}
            else if(description.isEmpty()) JOptionPane.showMessageDialog(rootPane, "Please enter valid a description.");
            else if(!intercomeNumberCheck(intercomeNumber)) {JOptionPane.showMessageDialog(rootPane, "Please enter valid phone number.");}
            else if(!flatLocationCheck(flatLocation)) JOptionPane.showMessageDialog(rootPane, "Please enter a valid flat floor no.");
            else if(rentCostCheck(rentCost)) {JOptionPane.showMessageDialog(rootPane, "Please enter valid rent cost.");}
            else {
            	DataContext context = new DataContext();
            	Flat flat = new Flat();
            	flat.setFlatId(flatId);
            	flat.setOwnerName(ownerName);
            	flat.setDescription(description);
            	flat.setFlatLocation(flatLocation);
            	flat.setIntercomeNumber(intercomeNumber);
            	flat.setRentCost(rentCost);
                context.SaveFlatInfo(flat);
                source.Refresh();
                JOptionPane.showMessageDialog(rootPane, "Flat information added successfully.");
                this.dispose();
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
		this.setTitle("Adding Flat Details");
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
		
		this.textFlatId = new JTextField();
		this.textFlatId.setBounds(200, 50, 180, 20);
		this.mainPanel.add(this.textFlatId);
		
		this.labelOwnerName = new JLabel("Owner Name");
		this.labelOwnerName.setBounds(20, 80, 171, 20);
		this.mainPanel.add(this.labelOwnerName);
		
		this.textOwnerName = new JTextField();
		this.textOwnerName.setBounds(200, 80, 180, 20);
		this.mainPanel.add(this.textOwnerName);
		
		this.labelDescription = new JLabel("Description");
		this.labelDescription.setBounds(20, 110, 171, 20);
		this.mainPanel.add(this.labelDescription);
		
		this.textDescription = new JTextField();
		this.textDescription.setBounds(200, 110, 180, 20);
		this.mainPanel.add(this.textDescription);
		
		this.labelFlatLocation = new JLabel("Floor No");
		this.labelFlatLocation.setBounds(20, 140, 171, 20);
		this.mainPanel.add(this.labelFlatLocation);
		
		this.textFlatLocation = new JTextField();
		this.textFlatLocation.setBounds(200, 140, 180, 20);
		this.mainPanel.add(this.textFlatLocation);
		
		this.labelIntercomeNumber = new JLabel("Intercome Number");
		this.labelIntercomeNumber.setBounds(20, 171, 171, 20);
		this.mainPanel.add(labelIntercomeNumber);
		
		this.textIntercomeNumber = new JTextField();
		this.textIntercomeNumber.setBounds(200, 171, 180, 20);
		this.mainPanel.add(textIntercomeNumber);
		
		this.labelRentCost = new JLabel("Rent Cost");
		this.labelRentCost.setBounds(20, 202, 171, 20);
		this.mainPanel.add(labelRentCost);
		
		this.textRentCost = new JTextField();
		this.textRentCost.setBounds(200, 202, 180, 20);
		this.mainPanel.add(textRentCost);
		
		this.buttonCancel = new JButton("Cancel");
		this.buttonCancel.setBounds(69, 242, 100, 20);
		this.buttonCancel.addActionListener(this);
		this.mainPanel.add(this.buttonCancel);
		
		this.buttonSave = new JButton("Save");
		this.buttonSave.setBounds(235, 242, 100, 20);
		this.buttonSave.addActionListener(this);
		this.mainPanel.add(this.buttonSave);
		
		this.add(this.mainPanel);
		
	}
	
	
	private boolean flatIdCheck(String flatId) {
        if(flatId.equals("")) return true;
        boolean flag = true;
        for(int i = 0; i < flatId.length(); i++) {
            if(!Character.isDigit(flatId.charAt(i)) && flatId.charAt(i) == '-') {
                flag = false;
                break;
            }
        }
        return flag;
    }
	
	private boolean flatLocationCheck(String flatLocation) {
        if(flatLocation.equals("")) return true;
        boolean flag = true;
        for(int i = 0; i < flatLocation.length(); i++) {
            if(!Character.isDigit(flatLocation.charAt(i))) {
                flag = false;
                break;
            }
        }
        return flag;
    }
	
	private boolean ownerNameCheck(String ownerName)  {
        if(ownerName.isEmpty()) return false;
        else {
            boolean flag = true;
            for(int i = 0; i < ownerName.length(); i++) {
                if(Character.isDigit(ownerName.charAt(i))) {
                    flag = false;
                    break;
                }
            }
            return flag;
        }
    }
    
    private boolean intercomeNumberCheck(String intercomeNumber) {
        if(intercomeNumber.equals("")) return true;
        boolean flag = true;
        for(int i = 0; i < intercomeNumber.length(); i++) {
            if(!Character.isDigit(intercomeNumber.charAt(i))) {
                flag = false;
                break;
            }
        }
        return flag;
    }
    
    private boolean rentCostCheck(String rentCost){
        if(rentCost.equals("")) return true;
        boolean flag = true;
        for(int i = 0; i < rentCost.length(); i++) {
            if(rentCost.charAt(i)== '.') { flag = false; break;}
        }
        return flag;
    }
    
}
