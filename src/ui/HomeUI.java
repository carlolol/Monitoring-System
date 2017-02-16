package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
	private NumberFormat formatter;
	private Date date;
	private FirebaseDAO fdao;
	private Thread thread;
	private JTextArea textLog;
	private JScrollPane scroll;
	private JLabel lblBg, lbllogo, lblOryzaSativa, lblBlock1, lblBlock2, lblBlock3, lblBlock4, lblBlock5, 
					lblMoistureSensor1, lblMoistureSensor2, lblTemperatureSensor1, lblTemperatureSensor2, 
					lblReportLog;
	private JButton tempB, moistB, homeB, minimizeB, exitB, aboutB;
	private JTextField textMoisture1, textMoisture2, textTemperature1, textTemperature2;
	private int h, w, resH, resW;
	private double tempValue, tempValueBeta, moistValue, moistValueBeta;
	private String log = "";
	
	public HomeUI(SystemUI systemUI, FirebaseDAO fdao) 
	{
		// set the decimal format for the data
		formatter = new DecimalFormat("#0.00");
		
		this.fdao = fdao;
		
		// misc
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
		
		textMoisture1 = new JTextField();
		textMoisture1.setFont(new Font("Tahoma", Font.PLAIN, 48));
		textMoisture1.setForeground(Color.WHITE);
		textMoisture1.setHorizontalAlignment(SwingConstants.CENTER);
		textMoisture1.setText("-");
		textMoisture1.setEditable(false);
		textMoisture1.setOpaque(false);
		textMoisture1.setBounds(w+380, h-170, 200, 90);
		textMoisture1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		centerP.add(textMoisture1);
		
		lblMoistureSensor2 = new JLabel("Moisture Sensor #2");
		lblMoistureSensor2.setForeground(Color.BLACK);
		lblMoistureSensor2.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoistureSensor2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblMoistureSensor2.setBounds(w+180, h-10, 600, 50);
		centerP.add(lblMoistureSensor2);
		
		textMoisture2 = new JTextField();
		textMoisture2.setFont(new Font("Tahoma", Font.PLAIN, 48));
		textMoisture2.setForeground(Color.WHITE);
		textMoisture2.setHorizontalAlignment(SwingConstants.CENTER);
		textMoisture2.setText("-");
		textMoisture2.setEditable(false);
		textMoisture2.setOpaque(false);
		textMoisture2.setBounds(w+380, h+70, 200, 90);
		textMoisture2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		centerP.add(textMoisture2);
		
		lblTemperatureSensor1 = new JLabel("Temperature Sensor #1");
		lblTemperatureSensor1.setForeground(Color.BLACK);
		lblTemperatureSensor1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTemperatureSensor1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTemperatureSensor1.setBounds(w-100, h-250, 600, 50);
		centerP.add(lblTemperatureSensor1);
		
		textTemperature1 = new JTextField();
		textTemperature1.setFont(new Font("Tahoma", Font.PLAIN, 48));
		textTemperature1.setForeground(Color.WHITE);
		textTemperature1.setHorizontalAlignment(SwingConstants.CENTER);
		textTemperature1.setText("-");
		textTemperature1.setEditable(false);
		textTemperature1.setOpaque(false);
		textTemperature1.setBounds(w+100, h-170, 200, 90);
		textTemperature1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		centerP.add(textTemperature1);
		
		lblTemperatureSensor2 = new JLabel("Temperature Sensor #2");
		lblTemperatureSensor2.setForeground(Color.BLACK);
		lblTemperatureSensor2.setHorizontalAlignment(SwingConstants.CENTER);
		lblTemperatureSensor2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTemperatureSensor2.setBounds(w-100, h-10, 600, 50);
		centerP.add(lblTemperatureSensor2);
		
		textTemperature2 = new JTextField();
		textTemperature2.setFont(new Font("Tahoma", Font.PLAIN, 48));
		textTemperature2.setForeground(Color.WHITE);
		textTemperature2.setHorizontalAlignment(SwingConstants.CENTER);
		textTemperature2.setText("-");
		textTemperature2.setEditable(false);
		textTemperature2.setOpaque(false);
		textTemperature2.setBounds(w+100, h+70, 200, 90);
		textTemperature2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		centerP.add(textTemperature2);
		
		lblReportLog = new JLabel("Report Log");
		lblReportLog.setForeground(Color.BLACK);
		lblReportLog.setHorizontalAlignment(SwingConstants.CENTER);
		lblReportLog.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblReportLog.setBounds(w-355, h+32, 100, 50);
		centerP.add(lblReportLog);
		
		textLog = new JTextArea(100, 200);
		textLog.setEditable(false);
        textLog.setBackground(Color.white);
		scroll = new JScrollPane(textLog);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        scroll.setBounds(w-560, h+75, 510, 235);
        centerP.add(scroll);
		
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
		lblBlock5.setIcon(new ImageIcon("../Thesis/Images/blueck.png"));
		lblBlock5.setBounds(w-565, h+30,520,300);
		centerP.add(lblBlock5);
		
		lblBg = new JLabel();
		lblBg.setIcon(new ImageIcon("../Thesis/Images/bg.png"));
		lblBg.setBounds(0,0,resW,resH);
		centerP.add(lblBg);
		
		add(centerP);
		
		// checks internet connectivity
		checkNet();
				
		// starts updating of frame
		startThread();
	}
	
	public void checkNet()
	{
		@SuppressWarnings("unused")
		boolean connectivity;
								
		try 
		{
			URL url = new URL("http://google.com/");
			URLConnection conn = url.openConnection();
			conn.connect();
			connectivity = true;
			log = dateFormat.format(date) + " Internet connection: Connected \n\n" + log;
			textLog.setText(log);
		} 
		catch (Exception e) 
		{
			connectivity = false;
			log = dateFormat.format(date) + " Internet connection: Disconnected \n\n" + log;
			textLog.setText(log);
		}
	}
	
	// get date and time
	public void getDate()
	{
		dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		date = new Date();		
	}
	
	// responsible for updating the frame
	public void startThread()
	{
		thread = new Thread()
		{
			public void run(){
				int x = 0;
				
				for(x = 1; x>0; x++)
				{
					try {
						Thread.sleep(5000); // 1000 milliseconds is equal to 1 sec
						getDate();
						
						// TEMPERATURE CHECKER
						tempValue = fdao.getTemperature().getFirst();
						tempValueBeta = fdao.getTemperature().getFirst() + rand.nextInt(20);
						
						if(tempValue > 35)
						{
							log = dateFormat.format(date) + " Temp Sensor #1: "
									+ "TEMPERATURE LEVEL IS ABOVE NORMAL! \n"
									+ "Temperature reading: " + formatter.format(tempValue) + "\u00b0C\n\n"+ log;
							textTemperature1.setForeground(Color.RED);	
						}
						else
						{
							textTemperature1.setForeground(Color.WHITE);
						}
						
						if(tempValueBeta > 35)
						{
							log = dateFormat.format(date) + " Temp Sensor #2: "
									+ "TEMPERATURE LEVEL IS ABOVE NORMAL! \n"
									+ "\tTemperature reading: " + formatter.format(tempValueBeta) + "\u00b0C\n\n" + log;
							textTemperature2.setForeground(Color.RED);
						}
						else if(tempValueBeta < 35)
						{
							textTemperature2.setForeground(Color.WHITE);
						}
						
						// MOISTURE CHECKER
						moistValue = rand.nextInt(15) + 20;
						moistValueBeta = rand.nextInt(20) + 20;
						
						if(moistValue > 35)
						{
							log = dateFormat.format(date) + " Moisture Sensor #1: "
									+ "MOISTURE CONTENT IS ABOVE NORMAL! \n"
									+ "\tMoisture content reading: " + formatter.format(moistValue) + "%\n\n" + log;
							textMoisture1.setForeground(Color.RED);	
						}
						else
						{
							textMoisture1.setForeground(Color.WHITE);
						}
						
						if(moistValueBeta > 35)
						{
							log = dateFormat.format(date) + " Moisture Sensor #2: "
									+ "MOISTURE CONTENT IS ABOVE NORMAL! \n"
									+ "\tMoisture content reading: " + formatter.format(moistValueBeta) + "%\n\n" + log;
							textMoisture2.setForeground(Color.RED);
						}
						else
						{
							textMoisture2.setForeground(Color.WHITE);
						}
						
						textMoisture1.setText(formatter.format(moistValue) + "%");
						textMoisture2.setText(formatter.format(moistValueBeta) + "%");
						textTemperature1.setText(formatter.format(tempValue) + "\u00b0C");
						textTemperature2.setText(formatter.format(tempValueBeta) + "\u00b0C");
						
						textLog.setText(log);
					}
					catch(Exception e) 
					{
						log = dateFormat.format(date) + " Connecting to Firebase, fetching data... \n\n" + log;
						textLog.setText(log);
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
				JOptionPane.showMessageDialog(null, "Oryza Sativa Grains Monitoring System\nv.20\n\n"
						+ "Thesis by: \nMarc Angelo Martinez\nCarl Louie Aruta\nMelvin Uy\n\n",
						"About", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(action.equals("Temp"))
			{
				systemUI.showTemp();
			}
			else if(action.equals("Moist"))
			{
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