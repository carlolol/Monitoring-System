package ui;


import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import dao.FirebaseDAO;

public class HomeUI extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JPanel centerP;
	private SystemUI systemUI;
	private LoginHandler loginHandler;
	private Random rand;
	private DateFormat dateFormat;
	private Date date;
	
	private FirebaseDAO fdao;
	
	private JTextArea textLog;
	private JLabel lblBg, lbllogo, lblOryzaSativa, lblBlock1, lblBlock2, lblBlock3, lblBlock4, lblBlock5, 
					lblMoistureSensor1, lblMoistureSensor2, lblTemperatureSensor1, lblTemperatureSensor2, 
					lblReportLog;
	private JButton tempB, moistB, homeB, minimizeB, exitB, aboutB, btnNet;
	private JTextField textField1, textField2, textField3, textField4;
	public int h, w, resH, resW;
	public static String log = "";
	
	public static Thread thread;
	
	public HomeUI(SystemUI systemUI, FirebaseDAO fdao) 
	{
		this.fdao = fdao;
		rand = new Random();
	
		getDate();
		
		// GUI components
		resH = SystemUI.getH();
		resW = SystemUI.getW();
		h = resH / 2;
		w = resW / 2;
		
		setLayout(new GridLayout(1, 1));
		this.systemUI = systemUI;
		
		centerP = new JPanel();
		centerP.setLayout(null);
		
		loginHandler = new LoginHandler();
		
		lblOryzaSativa = new JLabel("Oryza Sativa Grains Monitoring System");
		lblOryzaSativa.setForeground(Color.BLACK);
		lblOryzaSativa.setHorizontalAlignment(SwingConstants.CENTER);
		lblOryzaSativa.setFont(new Font("Times New Roman", Font.BOLD, 32));
		lblOryzaSativa.setBounds(w-605, h-10, 600, 50);
		centerP.add(lblOryzaSativa);
		
		lblMoistureSensor1 = new JLabel("Moisture Sensor #1");
		lblMoistureSensor1.setForeground(Color.BLACK);
		lblMoistureSensor1.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoistureSensor1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblMoistureSensor1.setBounds(w+180, h-250, 600, 50);
		centerP.add(lblMoistureSensor1);
		
		textField1 = new JTextField();
		textField1.setFont(new Font("Tahoma", Font.PLAIN, 48));
		textField1.setForeground(Color.WHITE);
		textField1.setHorizontalAlignment(SwingConstants.CENTER);
		textField1.setText("0%");
		textField1.setEditable(false);
		textField1.setOpaque(false);
		textField1.setBounds(w+380, h-170, 200, 90);
		textField1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		centerP.add(textField1);
		textField1.setColumns(10);
		
		lblMoistureSensor2 = new JLabel("Moisture Sensor #2");
		lblMoistureSensor2.setForeground(Color.BLACK);
		lblMoistureSensor2.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoistureSensor2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblMoistureSensor2.setBounds(w+180, h-10, 600, 50);
		centerP.add(lblMoistureSensor2);
		
		textField2 = new JTextField();
		textField2.setFont(new Font("Tahoma", Font.PLAIN, 48));
		textField2.setForeground(Color.WHITE);
		textField2.setHorizontalAlignment(SwingConstants.CENTER);
		textField2.setText("0%");
		textField2.setEditable(false);
		textField2.setOpaque(false);
		textField2.setBounds(w+380, h+70, 200, 90);
		textField2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		centerP.add(textField2);
		textField2.setColumns(10);
		
		lblTemperatureSensor1 = new JLabel("Temperature Sensor #1");
		lblTemperatureSensor1.setForeground(Color.BLACK);
		lblTemperatureSensor1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTemperatureSensor1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTemperatureSensor1.setBounds(w-100, h-250, 600, 50);
		centerP.add(lblTemperatureSensor1);
		
		textField3 = new JTextField();
		textField3.setFont(new Font("Tahoma", Font.PLAIN, 48));
		textField3.setForeground(Color.WHITE);
		textField3.setHorizontalAlignment(SwingConstants.CENTER);
		textField3.setText("-");
		textField3.setEditable(false);
		textField3.setOpaque(false);
		textField3.setBounds(w+100, h-170, 200, 90);
		textField3.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		centerP.add(textField3);
		textField3.setColumns(10);
		
		lblTemperatureSensor2 = new JLabel("Temperature Sensor #2");
		lblTemperatureSensor2.setForeground(Color.BLACK);
		lblTemperatureSensor2.setHorizontalAlignment(SwingConstants.CENTER);
		lblTemperatureSensor2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTemperatureSensor2.setBounds(w-100, h-10, 600, 50);
		centerP.add(lblTemperatureSensor2);
		
		textField4 = new JTextField();
		textField4.setFont(new Font("Tahoma", Font.PLAIN, 48));
		textField4.setForeground(Color.WHITE);
		textField4.setHorizontalAlignment(SwingConstants.CENTER);
		textField4.setText("-");
		textField4.setEditable(false);
		textField4.setOpaque(false);
		textField4.setBounds(w+100, h+70, 200, 90);
		textField4.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		centerP.add(textField4);
		textField4.setColumns(10);
		
		lblReportLog = new JLabel("Report Log");
		lblReportLog.setForeground(Color.BLACK);
		lblReportLog.setHorizontalAlignment(SwingConstants.CENTER);
		lblReportLog.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblReportLog.setBounds(w-350, h+60, 100, 50);
		centerP.add(lblReportLog);
		
		textLog = new JTextArea(100, 200);
		textLog.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textLog.setForeground(Color.WHITE);
        textLog.setEditable(false);
        textLog.setOpaque(false);
        textLog.setBounds(w-555, h+100, 500, 120);
		centerP.add(textLog);
		
		btnNet = new JButton("Check connection");
		btnNet.setBackground(Color.WHITE);
		btnNet.setForeground(Color.BLACK);
		btnNet.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNet.setBounds(w-560, h+72, 135, 23);
		btnNet.setActionCommand("Net");
		btnNet.addActionListener(loginHandler);
		centerP.add(btnNet);
		
		exitB = new JButton("");
		exitB.setToolTipText("Exit");
		exitB.setIcon(new ImageIcon("../Thesis/Images/x.png"));
		exitB.setRolloverIcon(new ImageIcon("../Thesis/Images/xhover.png"));
		exitB.setBounds(resW-60, 15, 40, 40);
		exitB.setOpaque(false);
		exitB.setContentAreaFilled(false);
		exitB.setBorderPainted(false);
		exitB.setActionCommand("Exit");
		exitB.addActionListener(loginHandler);
		centerP.add(exitB);
		
		minimizeB = new JButton("");
		minimizeB.setToolTipText("Minimize");
		minimizeB.setIcon(new ImageIcon("../Thesis/Images/minimize.png"));
		minimizeB.setRolloverIcon(new ImageIcon("../Thesis/Images/minimizehover.png"));
		minimizeB.setBounds(resW-105, 15, 40, 40);
		minimizeB.setOpaque(false);
		minimizeB.setContentAreaFilled(false);
		minimizeB.setBorderPainted(false);
		minimizeB.setActionCommand("Minimize");
		minimizeB.addActionListener(loginHandler);
		centerP.add(minimizeB);
		
		aboutB = new JButton("");
		aboutB.setToolTipText("About");
		aboutB.setIcon(new ImageIcon("../Thesis/Images/help.png"));
		aboutB.setRolloverIcon(new ImageIcon("../Thesis/Images/helphover.png"));
		aboutB.setBounds(resW-150, 15, 40, 40);
		aboutB.setOpaque(false);
		aboutB.setContentAreaFilled(false);
		aboutB.setBorderPainted(false);
		aboutB.setActionCommand("About");
		aboutB.addActionListener(loginHandler);
		centerP.add(aboutB);
		
		tempB = new JButton("");
		tempB.setToolTipText("Temperature");
		tempB.setIcon(new ImageIcon("../Thesis/Images/tempIcon.png"));
		tempB.setRolloverIcon(new ImageIcon("../Thesis/Images/tempIconhover.png"));
		tempB.setOpaque(false);
		tempB.setContentAreaFilled(false);
		tempB.setBorderPainted(false);
		tempB.setBounds(resW-240, resH-85, 60, 60);
		tempB.setActionCommand("Temp");
		tempB.addActionListener(loginHandler);
		centerP.add(tempB);
		
		moistB = new JButton("");
		moistB.setToolTipText("Moisture");
		moistB.setIcon(new ImageIcon("../Thesis/Images/moistureIcon.png"));
		moistB.setRolloverIcon(new ImageIcon("../Thesis/Images/moistureIconhover.png"));
		moistB.setBounds(resW-160, resH-85, 60, 60);
		moistB.setOpaque(false);
		moistB.setContentAreaFilled(false);
		moistB.setBorderPainted(false);
		moistB.setActionCommand("Moist");
		moistB.addActionListener(loginHandler);
		centerP.add(moistB);
		
		homeB = new JButton("");
		homeB.setToolTipText("Home");
		homeB.setIcon(new ImageIcon("../Thesis/Images/homehover.png"));
		homeB.setRolloverIcon(new ImageIcon("../Thesis/Images/homehover.png"));
		homeB.setBounds(resW-80, resH-85, 60, 60);
		homeB.setOpaque(false);
		homeB.setContentAreaFilled(false);
		homeB.setBorderPainted(false);
		homeB.setActionCommand("Home");
		homeB.addActionListener(loginHandler);
		homeB.setEnabled(false);
		centerP.add(homeB);
		
		lbllogo = new JLabel();
		lbllogo.setIcon(new ImageIcon("../Thesis/Images/finallogo.png"));
		lbllogo.setBounds(w-420, h-250,250,250);
		centerP.add(lbllogo);
		
		lblBlock1 = new JLabel();
		lblBlock1.setIcon(new ImageIcon("../Thesis/Images/block.png"));
		lblBlock1.setBounds(w+360, h-270,300,250);
		centerP.add(lblBlock1);
		
		lblBlock2 = new JLabel();
		lblBlock2.setIcon(new ImageIcon("../Thesis/Images/block.png"));
		lblBlock2.setBounds(w+360, h-30,300,250);
		centerP.add(lblBlock2);
		
		lblBlock3 = new JLabel();
		lblBlock3.setIcon(new ImageIcon("../Thesis/Images/block.png"));
		lblBlock3.setBounds(w+80, h-270,300,250);
		centerP.add(lblBlock3);
		
		lblBlock4 = new JLabel();
		lblBlock4.setIcon(new ImageIcon("../Thesis/Images/block.png"));
		lblBlock4.setBounds(w+80, h-30,300,250);
		centerP.add(lblBlock4);
		
		lblBlock5 = new JLabel();
		lblBlock5.setIcon(new ImageIcon("../Thesis/Images/block2.png"));
		lblBlock5.setBounds(w-565, h+70,520,160);
		centerP.add(lblBlock5);
		
		lblBg = new JLabel();
		lblBg.setIcon(new ImageIcon("../Thesis/Images/bg.png"));
		lblBg.setBounds(0,0,resW,resH);
		centerP.add(lblBg);
		
		add(centerP);
		
		// checks internet connectivity
		checkNet();
				
		// starts updating the home UI
		startThread();
	}
	
	public void checkNet()
	{
		boolean connectivity;
								
		try 
		{
			URL url = new URL("http://google.com/");
			URLConnection conn = url.openConnection();
			conn.connect();
			connectivity = true;
			log = dateFormat.format(date) + " Internet connection: Connected \n" + log;
			textLog.setText(log);
		} 
		catch (Exception e) 
		{
			connectivity = false;
			log = dateFormat.format(date) + " Internet connection: Disconnected \n" + log;
			textLog.setText(log);
		}
	}
	
	public void getDate()
	{
		// get date
		dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		date = new Date();		
	}
	
	// responsible for updating the home UI
	public void startThread()
	{
		thread = new Thread()
		{
			public void run(){
				int x = 0;
				
				for(x = 1; x>0; x++) // Don't stop belieben! HAHAHAHA.
				{
					try {
						Thread.sleep(2000); // 1000 milliseconds is equal to 1 sec
						x++;
						
						getDate();
						
						textField1.setText((rand.nextInt(15) + 10) + "%");
						textField2.setText((rand.nextInt(15) + 10) + "%");
						textField3.setText(fdao.getTemperature().getFirst() + "\u00b0C");
						textField4.setText((rand.nextInt(10) + 30) + "\u00b0C");
						log = dateFormat.format(date) + " Reading of sensor is running... \n" + log;
						textLog.setText(log);
					}
					catch(Exception e) 
					{
//						log = dateFormat.format(date) + " Reading of sensor is interrupted. \n" + log;
//						textLog.setText(log);
					}
				}
			}
		};
		thread.start();
	}
	
	// functionality
	private class LoginHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			String action = e.getActionCommand();
			
			if(action.equals("Exit"))
			{
				int result = JOptionPane.showConfirmDialog(null, "Are you sure? All connection will be disconnected.", 
						"Confirmation", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

				if(result == JOptionPane.YES_OPTION)
				{
	              System.exit(0);
	            }
			}
			else if(action.equals("Minimize"))
			{
				systemUI.setState(Frame.ICONIFIED);
			}
			else if(action.equals("About")) 
			{
				JOptionPane.showMessageDialog(null, "Oryza Sativa Grains Monitoring System\nv.09\n\n"
						+ "Thesis by: \nMarc Angelo Martinez\nCarl Louie Aruta\nMelvin Uy",
						"About", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(action.equals("Temp"))
			{
				thread.suspend();
				systemUI.showTemp();
			}
			else if(action.equals("Moist"))
			{
				thread.suspend();
				systemUI.showMoist();
			}
			else if(action.equals("Home"))
			{
				systemUI.showMain();
			}
			else if(action.equals("Net"))
			{
				checkNet();
			}
			repaint();
		}
	}
}