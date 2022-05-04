import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;

class AddAdmin extends JFrame implements ActionListener {
	private ApartmentInformation source;
	private JPanel panel;
	private JLabel labelForm,labelUserName,labelPassword;
	private JTextField textUserName,textPassword;
	private JButton buttonAdd, buttonCancel;
	
	public AddAdmin (ApartmentInformation source)
	{
		this.source = source;
		this.InitializeComponents();
	}
	
	public void actionPerformed(ActionEvent event)
	{
		String command = event.getActionCommand();
		DataContext context = new DataContext();
		if(command.equals("Add"))
		{
			String UName= this.textUserName.getText().trim();
			String UPassword= this.textPassword.getText().trim();
			if(UName.equals("")) JOptionPane.showMessageDialog(rootPane, "Please enter a valid username.");
            else if(UPassword.equals("")) JOptionPane.showMessageDialog(rootPane, "Please enter a password.");
            else
            {
            	if(context.SaveAdminInfo(UName,UPassword ))
            	{
            		JOptionPane.showMessageDialog(null, "New Admin Added.");
            		this.dispose();
            	}
            	else
            	{
            		this.textUserName.setText(null);
            		this.textPassword.setText(null);
            	}
            }
		}
		else if(command.equals("Cancel"))
		{
			this.dispose();
		}
	}
	
	private void InitializeComponents()
	{
		this.panel = new JPanel();
		this.panel.setLayout(null);
		this.setTitle("Adding Admin");
		this.setSize(420, 220);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.labelForm = new JLabel("Admin Details");
		this.labelForm.setBounds(100, 10, 250, 20);
		this.labelForm.setForeground(Color.blue);
		this.labelForm.setFont(new Font("Serif", Font.BOLD, 15));
		this.panel.add(this.labelForm);
		
		this.labelUserName = new JLabel("User Name");
		this.labelUserName.setBounds(20, 50, 180, 20);
		this.panel.add(this.labelUserName);
		
		this.textUserName = new JTextField();
		this.textUserName.setBounds(200, 50, 180, 20);
		this.panel.add(this.textUserName);
		
		this.labelPassword = new JLabel("Password");
		this.labelPassword.setBounds(20, 80, 180, 20);
		this.panel.add(this.labelPassword);
		
		this.textPassword = new JTextField();
		this.textPassword.setBounds(200, 80, 180, 20);
		this.panel.add(this.textPassword);
		
		this.buttonCancel = new JButton("Cancel");
		this.buttonCancel.setBounds(80, 140, 100, 20);
		this.buttonCancel.addActionListener(this);
		this.panel.add(this.buttonCancel);
		
		this.buttonAdd = new JButton("Add");
		this.buttonAdd.setBounds(220, 140, 100, 20);
		this.buttonAdd.addActionListener(this);
		this.panel.add(this.buttonAdd);
		
		this.add(this.panel);
		
	}
}

