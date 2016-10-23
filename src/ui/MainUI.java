package ui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.Font;

public class MainUI extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JPanel centerP;
	private JTextField txtNormal, txtNormal_1, txtNormal_2, txtNormal_3, txtNormal_4, txtNormal_5;
	private JButton btnOn, btnOn_1, btnOn_2, btnOn_3, btnOn_4, btnOn_5, btnOff, btnOff_1, btnOff_2, btnOff_3, btnOff_4, btnOff_5,
					btnSyncData, btnSyncData_1, btnSyncData_2, btnSyncData_3, btnSyncData_4, btnSyncData_5, btnOnAll, btnOffAll,
					btnSyncAllData, btnTerminate;
	private JLabel lblPrototype, lblModule, lblModule2, lblModule3, lblModule4, lblModule5, lblModule6, lblStatus, lblStatus_1,
					lblStatus_2, lblStatus_3, lblStatus_4, lblStatus_5, lblConnectionStatus, lblBg;
	@SuppressWarnings("unused")
	private SystemUI systemUI;
	private String status;
	private LoginHandler loginHandler;
	private JTextField txtConnected;
	
	public MainUI(SystemUI systemUI) 
	{	
		status = "0";
		setLayout(new GridLayout(1, 1));
		this.systemUI = systemUI;
		
		loginHandler = new LoginHandler();
		
		centerP = new JPanel();
		centerP.setLayout(null);
		
		lblPrototype = new JLabel("Oryza Sativa Grains Monitoring System v0.1");
		lblPrototype.setForeground(Color.BLACK);
		lblPrototype.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPrototype.setBounds(74, 22, 295, 19);
		lblPrototype.setHorizontalAlignment(SwingConstants.CENTER);
		centerP.add(lblPrototype);
		
		lblModule = new JLabel("Module 1");
		lblModule.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblModule.setForeground(Color.BLACK);
		lblModule.setBounds(61, 66, 63, 14);
		centerP.add(lblModule);
		
		lblModule2 = new JLabel("Module 2");
		lblModule2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblModule2.setForeground(Color.BLACK);
		lblModule2.setBounds(61, 91, 63, 14);
		centerP.add(lblModule2);
		
		lblModule3 = new JLabel("Module 3");
		lblModule3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblModule3.setForeground(Color.BLACK);
		lblModule3.setBounds(61, 116, 63, 14);
		centerP.add(lblModule3);
		
		lblModule4 = new JLabel("Module 4");
		lblModule4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblModule4.setForeground(Color.BLACK);
		lblModule4.setBounds(61, 141, 63, 14);
		centerP.add(lblModule4);
		
		lblModule5 = new JLabel("Module 5");
		lblModule5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblModule5.setForeground(Color.BLACK);
		lblModule5.setBounds(61, 166, 63, 14);
		centerP.add(lblModule5);
		
		lblModule6 = new JLabel("Module 6");
		lblModule6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblModule6.setForeground(Color.BLACK);
		lblModule6.setBounds(61, 191, 63, 14);
		centerP.add(lblModule6);
		
		lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStatus.setForeground(Color.BLACK);
		lblStatus.setBounds(290, 66, 46, 14);
		centerP.add(lblStatus);
		
		lblStatus_1 = new JLabel("Status:");
		lblStatus_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStatus_1.setForeground(Color.BLACK);
		lblStatus_1.setBounds(290, 91, 46, 14);
		centerP.add(lblStatus_1);
		
		lblStatus_2 = new JLabel("Status:");
		lblStatus_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStatus_2.setForeground(Color.BLACK);
		lblStatus_2.setBounds(290, 116, 46, 14);
		centerP.add(lblStatus_2);
		
		lblStatus_3 = new JLabel("Status:");
		lblStatus_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStatus_3.setForeground(Color.BLACK);
		lblStatus_3.setBounds(290, 141, 46, 14);
		centerP.add(lblStatus_3);
		
		lblStatus_4 = new JLabel("Status:");
		lblStatus_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStatus_4.setForeground(Color.BLACK);
		lblStatus_4.setBounds(290, 166, 46, 14);
		centerP.add(lblStatus_4);
		
		lblStatus_5 = new JLabel("Status:");
		lblStatus_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStatus_5.setForeground(Color.BLACK);
		lblStatus_5.setBounds(290, 191, 46, 14);
		centerP.add(lblStatus_5);
		
		txtNormal = new JTextField();
		txtNormal.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtNormal.setForeground(Color.WHITE);
		txtNormal.setHorizontalAlignment(SwingConstants.CENTER);
		txtNormal.setText(status);
		txtNormal.setBackground(new Color(255, 0, 0));
		txtNormal.setBounds(343, 66, 46, 20);
		centerP.add(txtNormal);
		txtNormal.setColumns(10);
		txtNormal.setEditable(false);
		
		txtNormal_1 = new JTextField();
		txtNormal_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtNormal_1.setForeground(Color.WHITE);
		txtNormal_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtNormal_1.setText(status);
		txtNormal_1.setBackground(new Color(255, 0, 0));
		txtNormal_1.setBounds(343, 89, 46, 20);
		centerP.add(txtNormal_1);
		txtNormal_1.setColumns(10);
		txtNormal_1.setEditable(false);
		
		txtNormal_2 = new JTextField();
		txtNormal_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtNormal_2.setForeground(Color.WHITE);
		txtNormal_2.setHorizontalAlignment(SwingConstants.CENTER);
		txtNormal_2.setText(status);
		txtNormal_2.setBackground(new Color(255, 0, 0));
		txtNormal_2.setBounds(343, 114, 46, 20);
		centerP.add(txtNormal_2);
		txtNormal_2.setColumns(10);
		txtNormal_2.setEditable(false);
		
		txtNormal_3 = new JTextField();
		txtNormal_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtNormal_3.setForeground(Color.WHITE);
		txtNormal_3.setHorizontalAlignment(SwingConstants.CENTER);
		txtNormal_3.setText(status);
		txtNormal_3.setBackground(new Color(255, 0, 0));
		txtNormal_3.setBounds(343, 139, 46, 20);
		centerP.add(txtNormal_3);
		txtNormal_3.setColumns(10);
		txtNormal_3.setEditable(false);
		
		txtNormal_4 = new JTextField();
		txtNormal_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtNormal_4.setForeground(Color.WHITE);
		txtNormal_4.setHorizontalAlignment(SwingConstants.CENTER);
		txtNormal_4.setText(status);
		txtNormal_4.setBackground(new Color(255, 0, 0));
		txtNormal_4.setBounds(343, 164, 46, 20);
		centerP.add(txtNormal_4);
		txtNormal_4.setColumns(10);
		txtNormal_4.setEditable(false);
		
		txtNormal_5 = new JTextField();
		txtNormal_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtNormal_5.setForeground(Color.WHITE);
		txtNormal_5.setHorizontalAlignment(SwingConstants.CENTER);
		txtNormal_5.setText(status);
		txtNormal_5.setBackground(new Color(255, 0, 0));
		txtNormal_5.setBounds(343, 189, 46, 20);
		centerP.add(txtNormal_5);
		txtNormal_5.setColumns(10);
		txtNormal_5.setEditable(false);
		
		btnOn = new JButton("On");
		btnOn.setBackground(Color.WHITE);
		btnOn.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnOn.setToolTipText("Turns on the module.");
		btnOn.setForeground(Color.BLACK);
		btnOn.setBounds(120, 62, 72, 23);
		centerP.add(btnOn);
		
		btnOn_1 = new JButton("On");
		btnOn_1.setBackground(Color.WHITE);
		btnOn_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnOn_1.setToolTipText("Turns on the module.");
		btnOn_1.setForeground(Color.BLACK);
		btnOn_1.setBounds(120, 87, 72, 23);
		centerP.add(btnOn_1);
		
		btnOn_2 = new JButton("On");
		btnOn_2.setBackground(Color.WHITE);
		btnOn_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnOn_2.setToolTipText("Turns on the module.");
		btnOn_2.setForeground(Color.BLACK);
		btnOn_2.setBounds(120, 112, 72, 23);
		centerP.add(btnOn_2);
		
		btnOn_3 = new JButton("On");
		btnOn_3.setBackground(Color.WHITE);
		btnOn_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnOn_3.setToolTipText("Turns on the module.");
		btnOn_3.setForeground(Color.BLACK);
		btnOn_3.setBounds(120, 137, 72, 23);
		centerP.add(btnOn_3);
		
		btnOn_4 = new JButton("On");
		btnOn_4.setBackground(Color.WHITE);
		btnOn_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnOn_4.setToolTipText("Turns on the module.");
		btnOn_4.setForeground(Color.BLACK);
		btnOn_4.setBounds(120, 162, 72, 23);
		centerP.add(btnOn_4);
		
		btnOn_5 = new JButton("On");
		btnOn_5.setBackground(Color.WHITE);
		btnOn_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnOn_5.setToolTipText("Turns on the module.");
		btnOn_5.setForeground(Color.BLACK);
		btnOn_5.setBounds(120, 187, 72, 23);
		centerP.add(btnOn_5);
		
		btnOff = new JButton("Off");
		btnOff.setBackground(Color.WHITE);
		btnOff.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnOff.setToolTipText("Turns off the module.");
		btnOff.setForeground(Color.BLACK);
		btnOff.setBounds(195, 62, 75, 23);
		
		centerP.add(btnOff);
		btnOff.setEnabled(false);
		
		btnOff_1 = new JButton("Off");
		btnOff_1.setBackground(Color.WHITE);
		btnOff_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnOff_1.setToolTipText("Turns off the module.");
		btnOff_1.setForeground(Color.BLACK);
		btnOff_1.setBounds(195, 87, 75, 23);
		centerP.add(btnOff_1);
		btnOff_1.setEnabled(false);
		
		btnOff_2 = new JButton("Off");
		btnOff_2.setBackground(Color.WHITE);
		btnOff_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnOff_2.setToolTipText("Turns off the module.");
		btnOff_2.setForeground(Color.BLACK);
		btnOff_2.setBounds(195, 112, 75, 23);
		centerP.add(btnOff_2);
		btnOff_2.setEnabled(false);
		
		btnOff_3 = new JButton("Off");
		btnOff_3.setBackground(Color.WHITE);
		btnOff_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnOff_3.setToolTipText("Turns off the module.");
		btnOff_3.setForeground(Color.BLACK);
		btnOff_3.setBounds(195, 137, 75, 23);
		centerP.add(btnOff_3);
		btnOff_3.setEnabled(false);
		
		btnOff_4 = new JButton("Off");
		btnOff_4.setBackground(Color.WHITE);
		btnOff_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnOff_4.setToolTipText("Turns off the module.");
		btnOff_4.setForeground(Color.BLACK);
		btnOff_4.setBounds(195, 162, 75, 23);
		centerP.add(btnOff_4);
		btnOff_4.setEnabled(false);
		
		btnOff_5 = new JButton("Off");
		btnOff_5.setBackground(Color.WHITE);
		btnOff_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnOff_5.setToolTipText("Turns off the module.");
		btnOff_5.setForeground(Color.BLACK);
		btnOff_5.setBounds(195, 187, 75, 23);
		centerP.add(btnOff_5);
		btnOff_5.setEnabled(false);
		
		btnOnAll = new JButton("On All");
		btnOnAll.setBackground(Color.WHITE);
		btnOnAll.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnOnAll.setToolTipText("Turns on all the module.");
		btnOnAll.setForeground(Color.BLACK);
		btnOnAll.setBounds(120, 224, 72, 23);
		centerP.add(btnOnAll);
		
		btnOffAll = new JButton("Off All");
		btnOffAll.setBackground(Color.WHITE);
		btnOffAll.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnOffAll.setToolTipText("Turns off all the module.");
		btnOffAll.setForeground(Color.BLACK);
		btnOffAll.setBounds(195, 224, 75, 23);
		centerP.add(btnOffAll);
		
		btnTerminate = new JButton("Exit");
		btnTerminate.setBackground(Color.WHITE);
		btnTerminate.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnTerminate.setToolTipText("Exits the system.");
		btnTerminate.setForeground(Color.RED);
		btnTerminate.setBounds(340, 288, 96, 23);
		centerP.add(btnTerminate);
		
		lblConnectionStatus = new JLabel("Connection Status:");
		lblConnectionStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblConnectionStatus.setForeground(Color.BLACK);
		lblConnectionStatus.setBounds(10, 292, 110, 14);
		centerP.add(lblConnectionStatus);
		
		txtConnected = new JTextField();
		txtConnected.setForeground(Color.BLACK);
		txtConnected.setBackground(new Color(50, 205, 50));
		txtConnected.setHorizontalAlignment(SwingConstants.CENTER);
		txtConnected.setText("Connected");
		txtConnected.setBounds(121, 289, 72, 20);
		txtConnected.setColumns(10);
		txtConnected.setEditable(false);
		
		btnOn.addActionListener(loginHandler);
		btnOn_1.addActionListener(loginHandler);
		btnOn_2.addActionListener(loginHandler);
		btnOn_3.addActionListener(loginHandler);
		btnOn_4.addActionListener(loginHandler);
		btnOn_5.addActionListener(loginHandler);
		btnOff.addActionListener(loginHandler);
		btnOff_1.addActionListener(loginHandler);
		btnOff_2.addActionListener(loginHandler);
		btnOff_3.addActionListener(loginHandler);
		btnOff_4.addActionListener(loginHandler);
		btnOff_5.addActionListener(loginHandler);
		btnOnAll.addActionListener(loginHandler);
		btnOffAll.addActionListener(loginHandler);
		btnTerminate.addActionListener(loginHandler);
		
		btnOn_1.setActionCommand("On2");
		btnOn_2.setActionCommand("On3");
		btnOn_3.setActionCommand("On4");
		btnOn_4.setActionCommand("On5");
		btnOn_5.setActionCommand("On6");
		btnOff_1.setActionCommand("Off2");
		btnOff_2.setActionCommand("Off3");
		btnOff_3.setActionCommand("Off4");
		btnOff_4.setActionCommand("Off5");
		btnOff_5.setActionCommand("Off6");
		btnOnAll.setActionCommand("OnAll");
		btnOffAll.setActionCommand("OffAll");
		
		centerP.add(txtConnected);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setIcon(new ImageIcon("../Thesis/Images/home.png"));
		btnNewButton.setBounds(29, 216, 50, 50);
		centerP.add(btnNewButton);
		
		lblBg = new JLabel();
		lblBg.setIcon(new ImageIcon("../Thesis/Images/IoT.png"));
		lblBg.setBounds(0,0,450,325);
		centerP.add(lblBg);
		
		add(centerP);
	}
	
	private class LoginHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			String action = e.getActionCommand();
			
			if(action.equals("Exit"))
			{
				System.exit(0);
			}
			else if(action.equals("On"))
			{
				btnOn.setEnabled(false);
				btnOff.setEnabled(true);
				txtNormal.setBackground(new Color(50, 205, 50));
			}
			else if(action.equals("On2"))
			{
				btnOn_1.setEnabled(false);
				btnOff_1.setEnabled(true);
				txtNormal_1.setBackground(new Color(50, 205, 50));
			}
			else if(action.equals("On3"))
			{
				btnOn_2.setEnabled(false);
				btnOff_2.setEnabled(true);
				txtNormal_2.setBackground(new Color(50, 205, 50));
			}
			else if(action.equals("On4"))
			{
				btnOn_3.setEnabled(false);
				btnOff_3.setEnabled(true);
				txtNormal_3.setBackground(new Color(50, 205, 50));
			}
			else if(action.equals("On5"))
			{
				btnOn_4.setEnabled(false);
				btnOff_4.setEnabled(true);
				txtNormal_4.setBackground(new Color(50, 205, 50));
			}
			else if(action.equals("On6"))
			{
				btnOn_5.setEnabled(false);
				btnOff_5.setEnabled(true);
				txtNormal_5.setBackground(new Color(50, 205, 50));
			}
			else if(action.equals("Off"))
			{
				btnOn.setEnabled(true);
				btnOff.setEnabled(false);
				txtNormal.setBackground(new Color(255, 0, 0));
			}
			else if(action.equals("Off2"))
			{
				btnOn_1.setEnabled(true);
				btnOff_1.setEnabled(false);
				txtNormal_1.setBackground(new Color(255, 0, 0));
			}
			else if(action.equals("Off3"))
			{
				btnOn_2.setEnabled(true);
				btnOff_2.setEnabled(false);
				txtNormal_2.setBackground(new Color(255, 0, 0));
			}
			else if(action.equals("Off4"))
			{
				btnOn_3.setEnabled(true);
				btnOff_3.setEnabled(false);
				txtNormal_3.setBackground(new Color(255, 0, 0));
			}
			else if(action.equals("Off5"))
			{
				btnOn_4.setEnabled(true);
				btnOff_4.setEnabled(false);
				txtNormal_4.setBackground(new Color(255, 0, 0));
			}
			else if(action.equals("Off6"))
			{
				btnOn_5.setEnabled(true);
				btnOff_5.setEnabled(false);
				txtNormal_5.setBackground(new Color(255, 0, 0));
			}
			else if(action.equals("OnAll"))
			{
				onAllModule();
			}
			else if(action.equals("OffAll"))
			{
				offAllModule();
			}
			repaint();
		}
	}
	
	
	public void onAllModule()
	{
		btnOn.setEnabled(false);
		btnOn_1.setEnabled(false);
		btnOn_2.setEnabled(false);
		btnOn_3.setEnabled(false);
		btnOn_4.setEnabled(false);
		btnOn_5.setEnabled(false);
		
		btnOff.setEnabled(true);
		btnOff_1.setEnabled(true);
		btnOff_2.setEnabled(true);
		btnOff_3.setEnabled(true);
		btnOff_4.setEnabled(true);
		btnOff_5.setEnabled(true);
		
		txtNormal.setBackground(new Color(50, 205, 50));
		txtNormal_1.setBackground(new Color(50, 205, 50));
		txtNormal_2.setBackground(new Color(50, 205, 50));
		txtNormal_3.setBackground(new Color(50, 205, 50));
		txtNormal_4.setBackground(new Color(50, 205, 50));
		txtNormal_5.setBackground(new Color(50, 205, 50));
	}
	
	public void offAllModule()
	{
		btnOn.setEnabled(true);
		btnOn_1.setEnabled(true);
		btnOn_2.setEnabled(true);
		btnOn_3.setEnabled(true);
		btnOn_4.setEnabled(true);
		btnOn_5.setEnabled(true);
		
		btnOff.setEnabled(false);
		btnOff_1.setEnabled(false);
		btnOff_2.setEnabled(false);
		btnOff_3.setEnabled(false);
		btnOff_4.setEnabled(false);
		btnOff_5.setEnabled(false);
		
		txtNormal.setBackground(new Color(255, 0, 0));
		txtNormal_1.setBackground(new Color(255, 0, 0));
		txtNormal_2.setBackground(new Color(255, 0, 0));
		txtNormal_3.setBackground(new Color(255, 0, 0));
		txtNormal_4.setBackground(new Color(255, 0, 0));
		txtNormal_5.setBackground(new Color(255, 0, 0));
	}
}