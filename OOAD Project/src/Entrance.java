import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import java.awt.*;
import java.util.Vector;




public class Entrance extends JFrame implements ActionListener{

	private JPanel mainPanel,panelLogin,panelUser;
	private JTextField textUserName;
	private JPasswordField passwordField;
	private JLabel labelUserName,labelPassword,labelSystem,labelAdmin,labelGeneralUser;
	private JButton buttonLogin,buttonBillSearch,buttonFlatSearch,buttonMemberSearch,buttonExit;

	
	public Entrance()
	{
		this.initializeComponents();
	}
	
	public void actionPerformed(ActionEvent event)
	{
		String button = event.getActionCommand();
		if(button.equals("EXIT"))
		{
			System.exit(0);
		}
		else if(button.equals("Login"))
		{
			String username = textUserName.getText();
            String password = passwordField.getText();
			DataContext context = new DataContext();
			Vector<Vector<String>> user = context.getLoginInfo(username,password); 
			if(!user.isEmpty())
			{
				ApartmentInformation flatInfo = new ApartmentInformation();
				flatInfo.setVisible(true);
				this.setVisible(false);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Incorrect username/passowrd.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(button.equals("SEARCH BILL"))
		{
			BillSearch frame = new BillSearch();
			frame.setVisible(true);
			this.setVisible(false);
		}
		else if(button.equals("SEARCH MEMBER"))
		{
			MemberSearch mem = new MemberSearch();
			mem.setVisible(true);
			this.setVisible(false);
		}
		
		else if(button.equals("SEARCH FLAT"))
		{
			FlatSearch mem = new FlatSearch();
			mem.setVisible(true);
			this.setVisible(false);
		}
	}
	
	
	
	private void initializeComponents()
	{
		this.mainPanel = new JPanel();
		this.setTitle("OOAD Society");
		this.mainPanel.setLayout(null);
		
		this.labelSystem = new JLabel("Welcome To OOAD SRY's Society");
		this.labelSystem.setForeground(Color.BLUE);
		this.labelSystem.setFont(new Font("Times New Roman", Font.BOLD, 22));
		this.labelSystem.setBounds(165, 57, 395, 51);
		this.mainPanel.add(labelSystem);
		
		this.panelLogin = new JPanel();
		this.panelLogin.setBackground(new Color(0,0,0,25));
		this.panelLogin.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.panelLogin.setBounds(354, 132, 281, 220);
		this.mainPanel.add(panelLogin);
		this.panelLogin.setLayout(null);
		
		this.labelUserName = new JLabel("User Name");
		this.labelUserName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.labelUserName.setBounds(10, 56, 85, 25);
		this.panelLogin.add(labelUserName);
		
		this.labelPassword = new JLabel("Password");
		this.labelPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.labelPassword.setBounds(10, 108, 85, 25);
		this.panelLogin.add(labelPassword);
		
		this.textUserName = new JTextField();
		this.textUserName.setBounds(123, 58, 144, 25);
		this.panelLogin.add(textUserName);
		
		this.buttonLogin = new JButton("Login");
		this.buttonLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.buttonLogin.setBounds(171, 160, 89, 23);
		this.buttonLogin.addActionListener(this);
		this.panelLogin.add(buttonLogin);
		
		this.passwordField = new JPasswordField();
		this.passwordField.setBounds(123, 110, 144, 25);
		this.panelLogin.add(passwordField);
		
		this.labelAdmin = new JLabel("Admin Login");
		this.labelAdmin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.labelAdmin.setForeground(Color.BLUE);
		this.labelAdmin.setBounds(94, 11, 99, 21);
		this.panelLogin.add(labelAdmin);
		
		this.panelUser = new JPanel();
		this.panelUser.setBackground(new Color(0,0,0,25));
		this.panelUser.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.panelUser.setBounds(24, 132, 292, 220);
		this.panelUser.setLayout(null);
		this.mainPanel.add(panelUser);
		
		
		this.labelGeneralUser = new JLabel("General User");
		this.labelGeneralUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.labelGeneralUser.setForeground(Color.BLUE);
		this.labelGeneralUser.setBounds(97, 11, 91, 20);
		this.panelUser.add(labelGeneralUser);
		
		
		this.buttonBillSearch = new JButton("SEARCH BILL");
		this.buttonBillSearch.setBounds(57, 129, 178, 29);
		this.panelUser.add(buttonBillSearch);
		this.buttonBillSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.buttonBillSearch.addActionListener(this);
		
		this.buttonMemberSearch = new JButton("SEARCH MEMBER");
		this.buttonMemberSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.buttonMemberSearch.setBounds(57, 89, 178, 29);
		this.panelUser.add(buttonMemberSearch);
		this.buttonMemberSearch.addActionListener(this);
		
		this.buttonFlatSearch = new JButton("SEARCH FLAT");
		this.buttonFlatSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.buttonFlatSearch.setBounds(57, 49, 178, 29);
		this.panelUser.add(buttonFlatSearch);
		this.buttonFlatSearch.addActionListener(this);
		
		this.buttonExit = new JButton("EXIT");
		this.buttonExit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.buttonExit.setBounds(57, 167, 178, 29);
		this.panelUser.add(buttonExit);
		this.buttonExit.addActionListener(this);
		
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon("F:\\Eclipse Files\\Registration\\bin\\bg.jpg"));
		label.setBounds(0, 0, 703, 453);
		mainPanel.add(label);
		
		this.add(this.mainPanel);
		
		this.setSize(703, 453);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}