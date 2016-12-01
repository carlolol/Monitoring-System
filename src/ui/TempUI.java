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

public class TempUI extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JPanel centerP;
	private SystemUI systemUI;
	
	private Random rand;
	
	private JButton returnB, btnOn1, btnOff1, btnOn2, btnOff2;
	private JLabel lblBg, lblTemperatureSensor1, lblTemperatureSensor2;
	private JTextField textField1, textField2;
	private LoginHandler loginHandler;
	
	private int[] statTemp1;
	private int[] statTemp2;
	private JPanel temperature1, temperature2;
	
	private JFreeChart tempLine1, tempLine2;
	private ChartPanel chartPanel1, chartPanel2;
	
	public TempUI(SystemUI systemUI)
	{
		rand = new Random();
		
		statTemp1 = new int[24];
		statTemp2 = new int[24];
		
		for(int a = 0; a < statTemp1.length; a++)
			statTemp1[a] = rand.nextInt(10) + 30;
		
		for(int a = 0; a < statTemp2.length; a++)
			statTemp2[a] = rand.nextInt(10) + 30;
		
		setLayout(new GridLayout(1, 1));
		this.systemUI = systemUI;
		
		centerP = new JPanel();
		centerP.setLayout(null);
		
		loginHandler = new LoginHandler();
		
		lblTemperatureSensor1 = new JLabel("Temperature Sensor #1");
		lblTemperatureSensor1.setForeground(Color.BLACK);
		lblTemperatureSensor1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTemperatureSensor1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblTemperatureSensor1.setBounds(70, 80, 156, 23);
		centerP.add(lblTemperatureSensor1);
		
		textField1 = new JTextField();
		textField1.setForeground(Color.WHITE);
		textField1.setHorizontalAlignment(SwingConstants.CENTER);
		textField1.setText(statTemp1[23] + "");
		textField1.setBackground(new Color(255, 0, 0));
		textField1.setEditable(false);
		textField1.setBounds(370, 81, 51, 20);
		centerP.add(textField1);
		textField1.setColumns(10);
		
		btnOn1 = new JButton("On");
		btnOn1.setBackground(Color.WHITE);
		btnOn1.setForeground(Color.BLACK);
		btnOn1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnOn1.setBounds(230, 80, 55, 23);
		btnOn1.setActionCommand("ON");
		centerP.add(btnOn1);
		
		btnOff1 = new JButton("Off");
		btnOff1.setEnabled(false);
		btnOff1.setBackground(Color.WHITE);
		btnOff1.setForeground(Color.BLACK);
		btnOff1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnOff1.setBounds(290, 80, 55, 23);
		btnOff1.setActionCommand("OFF");
		centerP.add(btnOff1);
		
		lblTemperatureSensor2 = new JLabel("Temperature Sensor #2");
		lblTemperatureSensor2.setHorizontalAlignment(SwingConstants.CENTER);
		lblTemperatureSensor2.setForeground(Color.BLACK);
		lblTemperatureSensor2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblTemperatureSensor2.setBounds(70, 380, 156, 23);
		centerP.add(lblTemperatureSensor2);
		
		btnOn2 = new JButton("On");
		btnOn2.setBackground(Color.WHITE);
		btnOn2.setForeground(Color.BLACK);
		btnOn2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnOn2.setBounds(230, 380, 55, 23);
		btnOn2.setActionCommand("ON1");
		centerP.add(btnOn2);
		
		btnOff2 = new JButton("Off");
		btnOff2.setEnabled(false);
		btnOff2.setBackground(Color.WHITE);
		btnOff2.setForeground(Color.BLACK);
		btnOff2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnOff2.setBounds(290, 380, 55, 23);
		btnOff2.setActionCommand("OFF1");
		centerP.add(btnOff2);
		
		textField2 = new JTextField();
		textField2.setForeground(Color.WHITE);
		textField2.setHorizontalAlignment(SwingConstants.CENTER);
		textField2.setText(statTemp2[23] + "");
		textField2.setBackground(new Color(255, 0, 0));
		textField2.setEditable(false);
		textField2.setColumns(10);
		textField2.setBounds(370, 381, 51, 20);
		centerP.add(textField2);
		
		returnB = new JButton("");
		returnB.setIcon(new ImageIcon("../Thesis/Images/returnIcon.png"));
		returnB.setRolloverIcon(new ImageIcon("../Thesis/Images/returnIconY.png"));
		returnB.setBounds(1200, 640, 60, 60);
		returnB.setOpaque(false);
		returnB.setContentAreaFilled(false);
		returnB.setBorderPainted(false);
		returnB.setActionCommand("Return");
		returnB.addActionListener(loginHandler);
		centerP.add(returnB);
		
		DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
		
		for(int a = 0; a < statTemp1.length; a++)
			dataset1.addValue(statTemp1[a], "temperature", "" + a + ":00");
			
		tempLine1 = ChartFactory.createLineChart("Temperature #1", "Time", "Temperature", dataset1);
		chartPanel1 = new ChartPanel(tempLine1);
		chartPanel1.setPreferredSize(new Dimension(595, 145));
		chartPanel1.setMouseZoomable(false);
		
		DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
		
		for(int a = 0; a < statTemp2.length; a++)
			dataset2.addValue(statTemp2[a], "temperature", "" + a + ":00");
		
		tempLine2 = ChartFactory.createLineChart("Temperature #2", "Time", "Temperature", dataset2);
		chartPanel2 = new ChartPanel(tempLine2);
		chartPanel2.setPreferredSize(new Dimension(595, 145));
		chartPanel2.setMouseZoomable(false);

		temperature1 = new JPanel();
		temperature1.setBounds(70, 120, 565, 130);
		temperature1.add(chartPanel1, BorderLayout.CENTER);
		temperature1.validate();
		centerP.add(temperature1);
		
		temperature2 = new JPanel();
		temperature2.setBounds(70, 420, 565, 130);
		temperature2.add(chartPanel2, BorderLayout.CENTER);
		temperature2.validate();
		centerP.add(temperature2);
	
		lblBg = new JLabel();
		lblBg.setIcon(new ImageIcon("../Thesis/Images/nb3.png"));
		lblBg.setBounds(0,0,1366,780);
		centerP.add(lblBg);
		
		btnOn1.addActionListener(loginHandler);
		btnOff1.addActionListener(loginHandler);
		btnOn2.addActionListener(loginHandler);
		btnOff2.addActionListener(loginHandler);
		
		add(centerP);
	}
	
	private class LoginHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			String action = e.getActionCommand();
			
			if(action.equals("Return"))
			{
				systemUI.showMain();
			}
			else if(action.equals("ON"))
			{
				btnOn1.setEnabled(false);
				btnOff1.setEnabled(true);
			}
			else if(action.equals("OFF"))
			{
				btnOn1.setEnabled(true);
				btnOff1.setEnabled(false);
			}
			else if(action.equals("ON1"))
			{
				btnOn2.setEnabled(false);
				btnOff2.setEnabled(true);
			}
			else if(action.equals("OFF1"))
			{
				btnOn2.setEnabled(true);
				btnOff2.setEnabled(false);
			}
			repaint();
		}
	}
}