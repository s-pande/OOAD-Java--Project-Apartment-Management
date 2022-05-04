import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.util.Date;
import java.util.Vector;

import javax.swing.*;

public class UpdateMemberInfo extends JFrame implements ActionListener{
	
	private JPanel panel;
	private JRootPane rootPane;
	private JLabel labelForm, labelName, labelEmail,labelPermanentAddress,labelPhone,labelOccupation,labelFamilyId;
	private JTextField textName, textEmail,textPermanentAddress,textPhone,textOccupation;
	private JComboBox comboBoxFamilyId;
	private JButton buttonSave,buttonCancel;
	private MembersInformation source;
	String name,email,permanentAddress,phone,occupation,familyId;
	
	public UpdateMemberInfo(MembersInformation source,String name,String email,String permanentAddress,String phone,String occupation,String familyId)
	{
		this.source = source;
		this.name = name;
		this.email = email;
		this.permanentAddress = permanentAddress;
		this.phone = phone;
		this.occupation = occupation;
		this.familyId = familyId;
		this.initializeComponents();
	}
	
	public void actionPerformed(ActionEvent event)
	{
		String command = event.getActionCommand();
		if(command.equals("Update"))
		{
			String Uname,Uemail,UpermanentAddress,Uphone,Uoccupation,UfamilyId,UflatId;
            Uname = this.textName.getText().trim();
            Uemail = this.textEmail.getText().trim();
            UpermanentAddress = this.textPermanentAddress.getText().trim();
            Uphone = this.textPhone.getText().trim();
            Uoccupation = this.textOccupation.getText().trim();
            UfamilyId = this.comboBoxFamilyId.getSelectedItem().toString();
            if(!nameCheck(Uname)) {JOptionPane.showMessageDialog(rootPane, "Please enter valid name.");}
            else if(!emailCheck(Uemail)) {JOptionPane.showMessageDialog(rootPane, "Please enter valid email.");}
            else if(permanentAddressCheck(UpermanentAddress)) {JOptionPane.showMessageDialog(rootPane, "Please enter valid permanent address.");}
            else if(!phoneNumberCheck(Uphone)) {JOptionPane.showMessageDialog(rootPane, "Please enter valid phone number.");}
            else if(!occupationCheck(Uoccupation)) {JOptionPane.showMessageDialog(rootPane, "Please enter valid occupation.");}
            else if(!familyIdCheck(UfamilyId)) {JOptionPane.showMessageDialog(rootPane, "Please enter valid familyId.");}
            else {
            	DataContext context = new DataContext();
            	Member member = new Member();
            	member.setName(name);
            	member.setEmail(email);
            	member.setPermanentAddress(permanentAddress);
            	member.setPhone(phone);
            	member.setOccupation(occupation);
            	member.setFamilyId(familyId);
            	if(context.UpdateMemberInfo(member,Uname,Uemail,UpermanentAddress,Uphone,Uoccupation,UfamilyId))
            	{
            		source.Refresh();
            		JOptionPane.showMessageDialog(rootPane, "Member information updated successfully.");
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
	
	

	private void initializeComponents() {
		this.panel = new JPanel();
		this.panel.setLayout(null);
		this.setTitle("Member Information");
		
		this.labelForm = new JLabel("Member Information");
		this.labelForm.setBounds(80, 20, 250, 20);
		this.labelForm.setForeground(Color.blue);
		this.labelForm.setFont(new Font("Serif", Font.BOLD, 15));
		this.panel.add(this.labelForm);
		
		this.labelName = new JLabel("Name");
		this.labelName.setBounds(20, 50, 100, 20);
		this.panel.add(this.labelName);
		
		this.textName = new JTextField(name);
		this.textName.setBounds(130, 50, 150, 20);
		this.panel.add(this.textName);
		
		this.labelEmail = new JLabel("Email");
		this.labelEmail.setBounds(20, 80, 70, 20);
		this.panel.add(this.labelEmail);
		
		this.textEmail = new JTextField(email);
		this.textEmail.setBounds(130, 80, 150, 20);
		this.panel.add(this.textEmail);
		
		this.labelPermanentAddress = new JLabel("Permanent Address");
		this.labelPermanentAddress.setBounds(20, 110, 70, 20);
		this.panel.add(this.labelPermanentAddress);
		
		this.textPermanentAddress = new JTextField(permanentAddress);
		this.textPermanentAddress.setBounds(130, 110, 150, 20);
		this.panel.add(this.textPermanentAddress);
		
		this.labelPhone = new JLabel("Phone");
		this.labelPhone.setBounds(20, 140, 100, 20);
		this.panel.add(this.labelPhone);
		
		this.textPhone = new JTextField(phone);
		this.textPhone.setBounds(130, 140, 150, 20);
		this.panel.add(this.textPhone);
		
		this.labelOccupation = new JLabel("Occupation");
		this.labelOccupation.setBounds(20, 170, 100, 20);
		this.panel.add(this.labelOccupation);
		
		this.textOccupation = new JTextField(occupation);
		this.textOccupation.setBounds(130, 170, 150, 20);
		this.panel.add(this.textOccupation);
	
		this.labelFamilyId = new JLabel("Family ID");
		this.labelFamilyId.setBounds(20, 200, 100, 20);
		this.panel.add(this.labelFamilyId);
		
		DataContext context = new DataContext();
		Vector<String> FamilyId = context.getFamilyId();
		
		FamilyId.add(0, "Select a Family ID");
		
		this.comboBoxFamilyId = new JComboBox(FamilyId.toArray());
		this.comboBoxFamilyId.setBounds(130, 200, 150, 20);
		this.panel.add(this.comboBoxFamilyId);
		
		comboBoxFamilyId.setSelectedItem(familyId);
		
		this.buttonSave = new JButton("Update");
		this.buttonSave.setBounds(200, 260, 80, 20);
		this.buttonSave.addActionListener(this);
		this.panel.add(this.buttonSave);
		
		this.buttonCancel = new JButton("Cancel");
		this.buttonCancel.setBounds(100, 260,80, 20);
		this.buttonCancel.addActionListener(this);
		this.panel.add(this.buttonCancel);
		
		this.rootPane = this.getRootPane();
		this.rootPane.setDefaultButton(this.buttonSave);
		
		this.add(this.panel);
		
		
		this.setSize(310, 340);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private boolean nameCheck(String name) {
        if(name.isEmpty()) return false;
        else {
            boolean flag = true;
            for(int i = 0; i < name.length(); i++) {
                if(Character.isDigit(name.charAt(i))) {
                    flag = false;
                    break;
                }
            }
            return flag;
        }
    }

	private boolean emailCheck(String email) {
        int count1 = 0, count2 = 0;
        if(email.equals("")) return true;
        for(int i = 0; i < email.length(); i++) {
            if(email.charAt(i) == ' ') {return false;}
            else if(email.charAt(i) == '.') {return true;}
            else if(email.charAt(i) == '.' && i != email.length() - 1 && email.charAt(i - 1) != '@') count1++;
            else if(email.charAt(i) == '@' && count1 == 0 && i != 0) count2++;
        }
        if(count1 == 1 && count2 == 1)
            return true;
        else
            return false;
    }

	private boolean permanentAddressCheck(String permanentAddress) {
        if(permanentAddress.equals("")) return true;
        boolean flag = true;
        for(int i = 0; i < permanentAddress.length(); i++) {
            if(permanentAddress.charAt(i)== ',' || permanentAddress.charAt(i)== '-') { flag = false; break;}
        }
        return flag;
    }

	private boolean phoneNumberCheck(String phone)  {
        if(phone.isEmpty()) return false;
        else {
            boolean flag = true;
            for(int i = 0; i < phone.length(); i++) {
                if(!Character.isDigit(phone.charAt(i))) {
                    flag = false;
                    break;
                }
            }
            return flag;
        }
    }

	private boolean occupationCheck(String occupation) {
        if(occupation.isEmpty()) return false;
        else {
            boolean flag = true;
            for(int i = 0; i < occupation.length(); i++) {
                if(Character.isDigit(occupation.charAt(i))) {
                    flag = false;
                    break;
                }
            }
            return flag;
        }
    }

	private boolean familyIdCheck(String familyId) {
        if(familyId.equals("Select a Family ID")) return false;
        return true;
    }
	
}