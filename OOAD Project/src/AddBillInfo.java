import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.util.Date;
import java.util.Vector;

import javax.swing.*;

public class AddBillInfo extends JFrame implements ActionListener{
	
	private JPanel panel;
	private JRootPane rootPane;
	private JLabel labelForm, labelElectricBill, labelWaterBill,labelPhoneBill,labelOthersBill,labelMonth,labelYear,labelFlatId;
	private JTextField textElectricBill, textWaterBill,textPhoneBill,textOthersBill,textYear;
	private JComboBox comboBoxFlatId,comboBoxMonth;
	private JButton buttonSave,buttonCancel;
	private BillInformation source;
	
	public AddBillInfo(BillInformation source)
	{
		this.source = source;
		this.initializeComponents();
	}
	
	public void actionPerformed(ActionEvent event)
	{
		String command = event.getActionCommand();
		if(command.equals("Save"))
		{
            String electricBill,waterBill,telephoneBill,othersBill,month,year,flatId;
            electricBill = this.textElectricBill.getText().trim();
            waterBill = this.textWaterBill.getText().trim();
            telephoneBill = this.textPhoneBill.getText().trim();
            othersBill = this.textOthersBill.getText().trim();
            month = this.comboBoxMonth.getSelectedItem().toString();
            year = this.textYear.getText().trim();
            flatId = this.comboBoxFlatId.getSelectedItem().toString();
            if(electricBillCheck(electricBill)) {JOptionPane.showMessageDialog(rootPane, "Please enter valid electric bill.");}
            else if(waterBillCheck(waterBill)) {JOptionPane.showMessageDialog(rootPane, "Please enter valid water bill.");}
            else if(telephoneBillCheck(telephoneBill)) {JOptionPane.showMessageDialog(rootPane, "Please enter valid telephone bill.");}
            else if(othersBillCheck(othersBill)) {JOptionPane.showMessageDialog(rootPane, "Please enter valid others bill.");}
            else if(!monthCheck(month)) {JOptionPane.showMessageDialog(rootPane, "Please enter valid month.");}
            else if(yearCheck(year)) {JOptionPane.showMessageDialog(rootPane, "Please enter valid year.");}
            else if(!flatIdCheck(flatId)) {JOptionPane.showMessageDialog(rootPane, "Please enter valid flat Id.");}
            else {
            	DataContext context = new DataContext();
            	Bill bill = new Bill();
            	bill.setElectricBill(electricBill);
            	bill.setWaterBill(waterBill);
            	bill.setTelephoneBill(telephoneBill);
            	bill.setOthersBill(othersBill);
            	bill.setMonth(month);
            	bill.setYear(year);
            	bill.setFlatId(flatId);
                if(context.SaveBillInfo(bill))
                		{
                			source.Refresh();
                			JOptionPane.showMessageDialog(rootPane, "Bill information added successfully.");
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
		this.setTitle("Bill Information");
		
		this.labelForm = new JLabel("Bill Information");
		this.labelForm.setBounds(100, 20, 132, 20);
		this.labelForm.setForeground(Color.blue);
		this.labelForm.setFont(new Font("Serif", Font.BOLD, 15));
		this.panel.add(this.labelForm);
		
		this.labelElectricBill = new JLabel("Electric Bill");
		this.labelElectricBill.setBounds(20, 50, 100, 20);
		this.panel.add(this.labelElectricBill);
		
		this.textElectricBill = new JTextField();
		this.textElectricBill.setBounds(130, 50, 150, 20);
		this.panel.add(this.textElectricBill);
		
		this.labelWaterBill = new JLabel("Water Bill");
		this.labelWaterBill.setBounds(20, 80, 70, 20);
		this.panel.add(this.labelWaterBill);
		
		this.textWaterBill = new JTextField();
		this.textWaterBill.setBounds(130, 80, 150, 20);
		this.panel.add(this.textWaterBill);
		
		this.labelPhoneBill = new JLabel("Phone Bill");
		this.labelPhoneBill.setBounds(20, 110, 70, 20);
		this.panel.add(this.labelPhoneBill);
		
		this.textPhoneBill = new JTextField();
		this.textPhoneBill.setBounds(130, 110, 150, 20);
		this.panel.add(this.textPhoneBill);
		
		this.labelOthersBill = new JLabel("Others Bill");
		this.labelOthersBill.setBounds(20, 140, 100, 20);
		this.panel.add(this.labelOthersBill);
		
		this.textOthersBill = new JTextField();
		this.textOthersBill.setBounds(130, 140, 150, 20);
		this.panel.add(this.textOthersBill);
		
		this.labelMonth = new JLabel("Month");
		this.labelMonth.setBounds(20, 170, 100, 20);
		this.panel.add(this.labelMonth);
		
		String months[] = new String[] {"Select a Month","January", "February ", "March", "April","May","June","July","August","September","October","November","December"};
		
		this.comboBoxMonth = new JComboBox(months);
		this.comboBoxMonth.setBounds(130, 170, 150, 20);
		this.panel.add(this.comboBoxMonth);
		
		this.labelYear = new JLabel("Year");
		this.labelYear.setBounds(20, 200, 100, 20);
		this.panel.add(this.labelYear);
		
		this.textYear = new JTextField();
		this.textYear.setBounds(130, 200, 150, 20);
		this.panel.add(this.textYear);
		
		this.labelFlatId = new JLabel("Flat Id");
		this.labelFlatId.setBounds(20, 230, 100, 20);
		this.panel.add(this.labelFlatId);
		
		DataContext context = new DataContext();
		Vector<String> FlatId = context.getFlatId();
		
		FlatId.add(0, "Select a Flat ID");
		
		this.comboBoxFlatId = new JComboBox(FlatId.toArray());
		this.comboBoxFlatId.setBounds(130, 230, 150, 20);
		this.panel.add(comboBoxFlatId);
		
		this.buttonSave = new JButton("Save");
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
	
	private boolean electricBillCheck(String electricBill) {
        if(electricBill.equals("")) return true;
        boolean flag = true;
        for(int i = 0; i < electricBill.length(); i++) {
            if(electricBill.charAt(i)== '.') { flag = false; break;}
        }
        return flag;
    }
    
	private boolean waterBillCheck(String electricBill) {
        if(electricBill.equals("")) return true;
        boolean flag = true;
        for(int i = 0; i < electricBill.length(); i++) {
            if(electricBill.charAt(i)== '.') { flag = false; break;}
        }
        return flag;
    }
    
	private boolean telephoneBillCheck(String electricBill) {
        if(electricBill.equals("")) return true;
        boolean flag = true;
        for(int i = 0; i < electricBill.length(); i++) {
            if(electricBill.charAt(i)== '.') { flag = false; break;}
        }
        return flag;
    }
	
	private boolean othersBillCheck(String electricBill) {
        if(electricBill.equals("")) return true;
        boolean flag = true;
        for(int i = 0; i < electricBill.length(); i++) {
            if(electricBill.charAt(i)== '.') { flag = false; break;}
        }
        return flag;
    }
	
	private boolean yearCheck(String year) {
        if(year.equals("")) return true;
        else if(year.length() <=3) return true;
        boolean flag = true;
        for(int i = 0; i < year.length(); i++) {
            if(Character.isDigit(year.charAt(i))) {
                flag = false;
                break;
            }
        }
        return flag;
    }
	
	private boolean flatIdCheck(String flatId) {
        if(flatId.equals("Select a Flat ID")) return false;
        return true;
    }
	
	private boolean monthCheck(String month) {
        if(month.equals("Select a Month")) return false;
        return true;
    }
}