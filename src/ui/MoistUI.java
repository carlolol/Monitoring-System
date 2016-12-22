package ui;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MoistUI extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JPanel centerP, moisture1, moisture2;
	private SystemUI systemUI;
	
	private Random rand;
	private int trigger = 1;
	
	private JButton switchB, btnOn1, btnOff1, btnOn2, btnOff2, tempB, moistB, homeB, minimizeB, exitB;
	private JLabel lblBg, lblMoistureSensor1, lblMoistureSensor2;
	private JTextField textField1, textField2;
	private LoginHandler loginHandler;
	
	private int[] statMoist1, statMoist2;
	
	private JFreeChart moistLine1, moistLine2;
	private ChartPanel chartPanel1, chartPanel2;
	
	private DefaultCategoryDataset dataset1, dataset2;
	
	public MoistUI(SystemUI systemUI)
	{
		rand = new Random();
		
		statMoist1 = new int[24];
		statMoist2 = new int[24];
		
		for(int a = 0; a < statMoist1.length; a++)
			statMoist1[a] = rand.nextInt(10) + 30;
		
		for(int a = 0; a < statMoist2.length; a++)
			statMoist2[a] = rand.nextInt(10) + 30;
		
		setLayout(new GridLayout(1, 1));
		this.systemUI = systemUI;
		
		centerP = new JPanel();
		centerP.setLayout(null);
		
		loginHandler = new LoginHandler();
		
		//------------------------------------------BLOCK 1------------------------------------------
		lblMoistureSensor1 = new JLabel("Moisture Sensor #1");
		lblMoistureSensor1.setForeground(Color.BLACK);
		lblMoistureSensor1.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoistureSensor1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblMoistureSensor1.setBounds(30, 80, 156, 23);
		centerP.add(lblMoistureSensor1);
		
		textField1 = new JTextField();
		textField1.setForeground(Color.WHITE);
		textField1.setHorizontalAlignment(SwingConstants.CENTER);
		textField1.setText("0");
		textField1.setBackground(new Color(255, 0, 0));
		textField1.setEditable(false);
		textField1.setBounds(330, 81, 51, 20);
		centerP.add(textField1);
		textField1.setColumns(10);
		
		btnOn1 = new JButton("On");
		btnOn1.setBackground(Color.WHITE);
		btnOn1.setForeground(Color.BLACK);
		btnOn1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnOn1.setBounds(190, 80, 55, 23);
		btnOn1.setActionCommand("ON");
		btnOn1.addActionListener(loginHandler);
		centerP.add(btnOn1);
		
		btnOff1 = new JButton("Off");
		btnOff1.setEnabled(false);
		btnOff1.setBackground(Color.WHITE);
		btnOff1.setForeground(Color.BLACK);
		btnOff1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnOff1.setBounds(250, 80, 55, 23);
		btnOff1.setActionCommand("OFF");
		btnOff1.addActionListener(loginHandler);
		centerP.add(btnOff1);
		
		//------------------------------------------BLOCK 2------------------------------------------
		lblMoistureSensor2 = new JLabel("Moisture Sensor #2");
		lblMoistureSensor2.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoistureSensor2.setForeground(Color.BLACK);
		lblMoistureSensor2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblMoistureSensor2.setBounds(840, 180, 156, 23);
		centerP.add(lblMoistureSensor2);
		
		btnOn2 = new JButton("On");
		btnOn2.setBackground(Color.WHITE);
		btnOn2.setForeground(Color.BLACK);
		btnOn2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnOn2.setBounds(1000, 180, 55, 23);
		btnOn2.setActionCommand("ON1");
		btnOn2.addActionListener(loginHandler);
		centerP.add(btnOn2);
		
		btnOff2 = new JButton("Off");
		btnOff2.setEnabled(false);
		btnOff2.setBackground(Color.WHITE);
		btnOff2.setForeground(Color.BLACK);
		btnOff2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnOff2.setBounds(1060, 180, 55, 23);
		btnOff2.setActionCommand("OFF1");
		btnOff2.addActionListener(loginHandler);
		centerP.add(btnOff2);
		
		textField2 = new JTextField();
		textField2.setForeground(Color.WHITE);
		textField2.setHorizontalAlignment(SwingConstants.CENTER);
		textField2.setText("0");
		textField2.setBackground(new Color(255, 0, 0));
		textField2.setEditable(false);
		textField2.setColumns(10);
		textField2.setBounds(1140, 181, 51, 20);
		centerP.add(textField2);
		
		switchB = new JButton("");
		switchB.setToolTipText("Switch Sensor");
		switchB.setIcon(new ImageIcon("../Thesis/Images/switch.png"));
		switchB.setRolloverIcon(new ImageIcon("../Thesis/Images/switchhover.png"));
		switchB.setBounds(790, 265, 40, 40);
		switchB.setOpaque(false);
		switchB.setContentAreaFilled(false);
		switchB.setBorderPainted(false);
		switchB.setActionCommand("Switch");
		switchB.addActionListener(loginHandler);
		centerP.add(switchB);
		
		dataset1 = new DefaultCategoryDataset();
		
		for(int a = 0; a < statMoist1.length; a++)
			dataset1.addValue(statMoist1[a], "moisture", "" + a + ":00");
			
		moistLine1 = ChartFactory.createLineChart("Moisture #1", "Time", "Moisture", dataset1);
		chartPanel1 = new ChartPanel(moistLine1);
		chartPanel1.setPreferredSize(new Dimension(750, 400));
		chartPanel1.setMouseZoomable(false);
		//chartPanel1.zoomInRange(0, 40);
		
		dataset2 = new DefaultCategoryDataset();
		
		for(int a = 0; a < statMoist2.length; a++)
			dataset2.addValue(statMoist2[a], "moisture", "" + a + ":00");
		
		moistLine2 = ChartFactory.createLineChart("Moisture #2", "Time", "Moisture", dataset2);
		chartPanel2 = new ChartPanel(moistLine2);
		chartPanel2.setPreferredSize(new Dimension(500, 145));
		chartPanel2.setMouseZoomable(false);

		moisture1 = new JPanel();
		moisture1.setBounds(30, 120, 750, 380);
		moisture1.add(chartPanel1, BorderLayout.CENTER);
		moisture1.validate();
		centerP.add(moisture1);
		
		moisture2 = new JPanel();
		moisture2.setBounds(840, 220, 500, 130);
		moisture2.add(chartPanel2, BorderLayout.CENTER);
		moisture2.validate();
		centerP.add(moisture2);
		
		exitB = new JButton("");
		exitB.setToolTipText("Exit");
		exitB.setIcon(new ImageIcon("../Thesis/Images/x.png"));
		exitB.setRolloverIcon(new ImageIcon("../Thesis/Images/xhover.png"));
		exitB.setBounds(1315, 10, 40, 40);
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
		minimizeB.setBounds(1270, 10, 40, 40);
		minimizeB.setOpaque(false);
		minimizeB.setContentAreaFilled(false);
		minimizeB.setBorderPainted(false);
		minimizeB.setActionCommand("Minimize");
		minimizeB.addActionListener(loginHandler);
		centerP.add(minimizeB);
		
		tempB = new JButton("");
		tempB.setToolTipText("Temperature");
		tempB.setIcon(new ImageIcon("../Thesis/Images/tempIcon.png"));
		tempB.setRolloverIcon(new ImageIcon("../Thesis/Images/tempIconhover.png"));
		tempB.setOpaque(false);
		tempB.setContentAreaFilled(false);
		tempB.setBorderPainted(false);
		tempB.setBounds(1000, 640, 60, 60);
		tempB.setActionCommand("Temp");
		tempB.addActionListener(loginHandler);
		centerP.add(tempB);
		
		moistB = new JButton("");
		moistB.setToolTipText("Moisture");
		moistB.setIcon(new ImageIcon("../Thesis/Images/moistureIconhover.png"));
		moistB.setRolloverIcon(new ImageIcon("../Thesis/Images/moistureIconhover.png"));
		moistB.setBounds(1100, 640, 60, 60);
		moistB.setOpaque(false);
		moistB.setContentAreaFilled(false);
		moistB.setBorderPainted(false);
		moistB.setActionCommand("Moist");
		moistB.addActionListener(loginHandler);
		moistB.setEnabled(false);
		centerP.add(moistB);
		
		homeB = new JButton("");
		homeB.setToolTipText("Home");
		homeB.setIcon(new ImageIcon("../Thesis/Images/home.png"));
		homeB.setRolloverIcon(new ImageIcon("../Thesis/Images/homehover.png"));
		homeB.setBounds(1200, 640, 60, 60);
		homeB.setOpaque(false);
		homeB.setContentAreaFilled(false);
		homeB.setBorderPainted(false);
		homeB.setActionCommand("Home");
		homeB.addActionListener(loginHandler);
		centerP.add(homeB);
		
		lblBg = new JLabel();
		lblBg.setIcon(new ImageIcon("../Thesis/Images/bg.png"));
		lblBg.setBounds(0,0,1366,780);
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
				int result = JOptionPane.showConfirmDialog(null, "Are you sure", "Confirm",
						JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

				if(result == JOptionPane.YES_OPTION)
				{
	              System.exit(0);
	            }
				else
				{
				}
			}
			else if(action.equals("Minimize"))
			{
				systemUI.setState(Frame.ICONIFIED);
			}
			else if(action.equals("ON"))
			{
				btnOn1.setEnabled(false);
				btnOff1.setEnabled(true);
				textField1.setBackground(new Color(75, 190, 0));
				textField1.setText(statMoist1[23] + "");
			}
			else if(action.equals("OFF"))
			{
				btnOn1.setEnabled(true);
				btnOff1.setEnabled(false);
				textField1.setBackground(new Color(255, 0, 0));
				textField1.setText("0");
			}
			else if(action.equals("ON1"))
			{
				btnOn2.setEnabled(false);
				btnOff2.setEnabled(true);
				textField2.setBackground(new Color(75, 190, 0));
				textField2.setText(statMoist2[23] + "");
			}
			else if(action.equals("OFF1"))
			{
				btnOn2.setEnabled(true);
				btnOff2.setEnabled(false);
				textField2.setBackground(new Color(255, 0, 0));
				textField2.setText("0");
			}
			else if(action.equals("Switch"))
			{
				if(trigger==1)
				{
					trigger = 2;
					
					chartPanel2.setPreferredSize(new Dimension(750, 400));
					lblMoistureSensor2.setBounds(30, 80, 156, 23);
					textField2.setBounds(330, 81, 51, 20);
					btnOn2.setBounds(190, 80, 55, 23);
					btnOff2.setBounds(250, 80, 55, 23);
					moisture2.setBounds(30, 120, 750, 380);
					moisture2.validate();
					centerP.validate();
					
					chartPanel1.setPreferredSize(new Dimension(500, 145));
					lblMoistureSensor1.setBounds(840, 180, 156, 23);
					btnOn1.setBounds(1000, 180, 55, 23);
					btnOff1.setBounds(1060, 180, 55, 23);
					textField1.setBounds(1140, 181, 51, 20);
					moisture1.setBounds(840, 220, 500, 130);
					moisture1.validate();
					centerP.validate();
				}
				else if(trigger==2)
				{
					trigger = 1;
					chartPanel1.setPreferredSize(new Dimension(750, 400));
					lblMoistureSensor1.setBounds(30, 80, 156, 23);
					textField1.setBounds(330, 81, 51, 20);
					btnOn1.setBounds(190, 80, 55, 23);
					btnOff1.setBounds(250, 80, 55, 23);
					moisture1.setBounds(30, 120, 750, 380);
					moisture1.validate();
					centerP.validate();
					
					chartPanel2.setPreferredSize(new Dimension(500, 145));
					lblMoistureSensor2.setBounds(840, 180, 156, 23);
					btnOn2.setBounds(1000, 180, 55, 23);
					btnOff2.setBounds(1060, 180, 55, 23);
					textField2.setBounds(1140, 181, 51, 20);
					moisture2.setBounds(840, 220, 500, 130);
					moisture2.validate();
					centerP.validate();
				}
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
			
			repaint();
		}
	}
}