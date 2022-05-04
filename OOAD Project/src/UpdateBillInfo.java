import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.util.Date;
import java.util.Vector;

import javax.swing.*;

public class UpdateBillInfo extends JFrame implements ActionListener{
	
	private JPanel panel;
	private JRootPane rootPane;
	private JLabel labelForm, labelElectricBill, labelWaterBill,labelPhoneBill,labelOthersBill,labelMonth,labelYear,labelFlatId;
	private JTextField textElectricBill, textWaterBill,textPhoneBill,textOthersBill,textYear;
	private JComboBox comboBoxFlatId,comboBoxMonth;
	private JButton buttonSave,buttonCancel;
	private BillInformation source;
	private String electricBill, waterBill, telephoneBill, othersBill,month,year,flatId;
	public UpdateBillInfo(BillInformation source,String electricBill,String waterBill,String telephoneBill,String othersBill,String month,String year,String flatId)
	{
		this.source = source;
		this.electricBill = electricBill;
		this.waterBill = waterBill;
		this.telephoneBill = telephoneBill;
		this.othersBill = othersBill;
		this.month = month;
		this.year = year;
		this.flatId = flatId;
		this.initializeComponents();
	}
	
	public void actionPerformed(ActionEvent event)
	{
		String command = event.getActionCommand();
		if(command.equals("Update"))
		{
            String UelectricBill,UwaterBill,UtelephoneBill,UothersBill,Umonth,Uyear,UflatId;
            UelectricBill = this.textElectricBill.getText().trim();
            UwaterBill = this.textWaterBill.getText().trim();
            UtelephoneBill = this.textPhoneBill.getText().trim();
            UothersBill = this.textOthersBill.getText().trim();
            Umonth = this.comboBoxMonth.getSelectedItem().toString();
            Uyear = this.textYear.getText().trim();
            UflatId = this.comboBoxFlatId.getSelectedItem().toString();
            if(electricBillCheck(UelectricBill)) {JOptionPane.showMessageDialog(rootPane, "Please enter valid electric bill.");}
            else if(waterBillCheck(UwaterBill)) {JOptionPane.showMessageDialog(rootPane, "Please enter valid water bill.");}
            else if(telephoneBillCheck(UtelephoneBill)) {JOptionPane.showMessageDialog(rootPane, "Please enter valid telephone bill.");}
            else if(othersBillCheck(UothersBill)) {JOptionPane.showMessageDialog(rootPane, "Please enter valid others bill.");}
            else if(!monthCheck(Umonth)) {JOptionPane.showMessageDialog(rootPane, "Please enter valid month.");}
            else if(yearCheck(Uyear)) {JOptionPane.showMessageDialog(rootPane, "Please enter valid year.");}
            else if(!flatIdCheck(UflatId)) {JOptionPane.showMessageDialog(rootPane, "Please enter valid flat Id.");}
            else {
            	DataContext context = new DataContext();
            	Bill bill = new Bill();
            	bill.setElectricBill(electricBill);
            	bill.setWaterBill(waterBill);
            	bill.setTelephoneBill(telephoneBill);
            	bill.setOthersBill(othersBill);
            	bill.setMonth(this.month);
            	bill.setYear(this.year);
            	bill.setFlatId(this.flatId);
                if(context.UpdateBillInfo(bill,UelectricBill,UwaterBill,UtelephoneBill,UothersBill,Umonth,Uyear,UflatId))
                {
                	source.Refresh();
                    JOptionPane.showMessageDialog(rootPane, "Bill information updated successfully.");
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
		
		this.labelForm = new JLabel("Update Bill Information");
		this.labelForm.setBounds(70, 20, 180, 20);
		this.labelForm.setForeground(Color.blue);
		this.labelForm.setFont(new Font("Serif", Font.BOLD, 15));
		this.panel.add(this.labelForm);
		
		this.labelElectricBill = new JLabel("Electric Bill");
		this.labelElectricBill.setBounds(20, 50, 100, 20);
		this.panel.add(this.labelElectricBill);
		
		this.textElectricBill = new JTextField(electricBill);
		this.textElectricBill.setBounds(130, 50, 150, 20);
		this.panel.add(this.textElectricBill);
		
		this.labelWaterBill = new JLabel("Water Bill");
		this.labelWaterBill.setBounds(20, 80, 70, 20);
		this.panel.add(this.labelWaterBill);
		
		this.textWaterBill = new JTextField(waterBill);
		this.textWaterBill.setBounds(130, 80, 150, 20);
		this.panel.add(this.textWaterBill);
		
		this.labelPhoneBill = new JLabel("Phone Bill");
		this.labelPhoneBill.setBounds(20, 110, 70, 20);
		this.panel.add(this.labelPhoneBill);
		
		this.textPhoneBill = new JTextField(telephoneBill);
		this.textPhoneBill.setBounds(130, 110, 150, 20);
		this.panel.add(this.textPhoneBill);
		
		this.labelOthersBill = new JLabel("Others Bill");
		this.labelOthersBill.setBounds(20, 140, 100, 20);
		this.panel.add(this.labelOthersBill);
		
		this.textOthersBill = new JTextField(othersBill);
		this.textOthersBill.setBounds(130, 140, 150, 20);
		this.panel.add(this.textOthersBill);
		
		this.labelMonth = new JLabel("Month");
		this.labelMonth.setBounds(20, 170, 100, 20);
		this.panel.add(this.labelMonth);
		
		String months[] = new String[] {"Select a Month","January", "February ", "March", "April","May","June","July","August","September","October","November","December"};
		
		this.comboBoxMonth = new JComboBox(months);
		this.comboBoxMonth.setBounds(130, 170, 150, 20);
		this.panel.add(this.comboBoxMonth);
		
		comboBoxMonth.setSelectedItem(month);
		
		this.labelYear = new JLabel("Year");
		this.labelYear.setBounds(20, 200, 100, 20);
		this.panel.add(this.labelYear);
		
		this.textYear = new JTextField(year);
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
		
		comboBoxFlatId.setSelectedItem(flatId);
		
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
	
	private boolean electricBillCheck(String UelectricBill) {
        if(UelectricBill.equals("")) return true;
        boolean flag = true;
        for(int i = 0; i < UelectricBill.length(); i++) {
            if(UelectricBill.charAt(i)== '.') { flag = false; break;}
        }
        return flag;
    }
    
	private boolean waterBillCheck(String UwaterBill) {
        if(UwaterBill.equals("")) return true;
        boolean flag = true;
        for(int i = 0; i < UwaterBill.length(); i++) {
            if(UwaterBill.charAt(i)== '.') { flag = false; break;}
        }
        return flag;
    }
    
	private boolean telephoneBillCheck(String UtelephoneBill) {
        if(UtelephoneBill.equals("")) return true;
        boolean flag = true;
        for(int i = 0; i < UtelephoneBill.length(); i++) {
            if(UtelephoneBill.charAt(i)== '.') { flag = false; break;}
        }
        return flag;
    }
	
	private boolean othersBillCheck(String UothersBill) {
        if(UothersBill.equals("")) return true;
        boolean flag = true;
        for(int i = 0; i < UothersBill.length(); i++) {
            if(UothersBill.charAt(i)== '.') { flag = false; break;}
        }
        return flag;
    }
	
	private boolean yearCheck(String Uyear) {
        if(Uyear.equals("")) return true;
        else if(Uyear.length() <=3 ) return true;
        boolean flag = true;
        for(int i = 0; i < Uyear.length(); i++) {
            if(Character.isDigit(Uyear.charAt(i))) {
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
	
	private boolean monthCheck(String Umonth) {
        if(Umonth.equals("Select a Month")) return false;
        return true;
    }
}