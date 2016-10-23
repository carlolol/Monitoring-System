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
	
	private JButton btnOn, btnOff, button, button_1;
	private JLabel lblBg;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private LoginHandler loginHandler;
	private int[] statTemp1;
	private int[] statTemp2;
	private JPanel temperature1;
	private JPanel temperature2;
	
	private JFreeChart tempLine1;
	private JFreeChart tempLine2;
	private ChartPanel chartPanel1;
	private ChartPanel chartPanel2;

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
		
		JLabel lblTemperatureSensor = new JLabel("Temperature Sensor #1");
		lblTemperatureSensor.setForeground(Color.BLACK);
		lblTemperatureSensor.setHorizontalAlignment(SwingConstants.CENTER);
		lblTemperatureSensor.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblTemperatureSensor.setBounds(27, 27, 156, 23);
		centerP.add(lblTemperatureSensor);
		
		textField = new JTextField();
		textField.setForeground(Color.WHITE);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText(statTemp1[23] + "");
		textField.setBackground(new Color(255, 0, 0));
		textField.setEditable(false);
		textField.setBounds(323, 29, 51, 20);
		centerP.add(textField);
		textField.setColumns(10);
		
		btnOn = new JButton("On");
		btnOn.setBackground(Color.WHITE);
		btnOn.setForeground(Color.BLACK);
		btnOn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnOn.setBounds(193, 27, 55, 23);
		btnOn.setActionCommand("ON");
		centerP.add(btnOn);
		
		btnOff = new JButton("Off");
		btnOff.setEnabled(false);
		btnOff.setBackground(Color.WHITE);
		btnOff.setForeground(Color.BLACK);
		btnOff.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnOff.setBounds(258, 27, 55, 23);
		btnOff.setActionCommand("OFF");
		centerP.add(btnOff);
		
		JLabel label = new JLabel("Temperature Sensor #2");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label.setBounds(27, 205, 156, 23);
		centerP.add(label);
		
		button = new JButton("On");
		button.setBackground(Color.WHITE);
		button.setForeground(Color.BLACK);
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.setBounds(193, 205, 55, 23);
		button.setActionCommand("ON1");
		centerP.add(button);
		
		button_1 = new JButton("Off");
		button_1.setEnabled(false);
		button_1.setBackground(Color.WHITE);
		button_1.setForeground(Color.BLACK);
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.setBounds(258, 205, 55, 23);
		button_1.setActionCommand("OFF1");
		centerP.add(button_1);
		
		textField_1 = new JTextField();
		textField_1.setForeground(Color.WHITE);
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setText(statTemp2[23] + "");
		textField_1.setBackground(new Color(255, 0, 0));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(323, 207, 51, 20);
		centerP.add(textField_1);
		
		/*
		JLabel label_1 = new JLabel("Temperature Sensor #3");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_1.setBounds(27, 147, 156, 23);
		centerP.add(label_1);
		
		JButton button_2 = new JButton("On");
		button_2.setBackground(Color.WHITE);
		button_2.setForeground(Color.BLACK);
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_2.setBounds(193, 148, 55, 23);
		centerP.add(button_2);
		
		JButton button_3 = new JButton("Off");
		button_3.setBackground(Color.WHITE);
		button_3.setForeground(Color.BLACK);
		button_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_3.setBounds(258, 147, 55, 23);
		centerP.add(button_3);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(323, 147, 51, 20);
		centerP.add(textField_2);
		
		JLabel label_2 = new JLabel("Temperature Sensor #4");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_2.setBounds(27, 181, 156, 23);
		centerP.add(label_2);
		
		JButton button_4 = new JButton("On");
		button_4.setBackground(Color.WHITE);
		button_4.setForeground(Color.BLACK);
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_4.setBounds(193, 182, 55, 23);
		centerP.add(button_4);
		
		JButton button_5 = new JButton("Off");
		button_5.setBackground(Color.WHITE);
		button_5.setForeground(Color.BLACK);
		button_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_5.setBounds(258, 181, 55, 23);
		centerP.add(button_5);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(323, 181, 51, 20);
		centerP.add(textField_3);
		
		JLabel label_3 = new JLabel("Temperature Sensor #5");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(Color.BLACK);
		label_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_3.setBounds(27, 215, 156, 23);
		centerP.add(label_3);
		
		JButton button_6 = new JButton("On");
		button_6.setBackground(Color.WHITE);
		button_6.setForeground(Color.BLACK);
		button_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_6.setBounds(193, 216, 55, 23);
		centerP.add(button_6);
		
		JButton button_7 = new JButton("Off");
		button_7.setBackground(Color.WHITE);
		button_7.setForeground(Color.BLACK);
		button_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_7.setBounds(258, 215, 55, 23);
		centerP.add(button_7);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(323, 215, 51, 20);
		centerP.add(textField_4);
		
		JLabel label_4 = new JLabel("Temperature Sensor #6");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setForeground(Color.BLACK);
		label_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_4.setBounds(27, 249, 156, 23);
		centerP.add(label_4);
		
		JButton button_8 = new JButton("On");
		button_8.setBackground(Color.WHITE);
		button_8.setForeground(Color.BLACK);
		button_8.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_8.setBounds(193, 250, 55, 23);
		centerP.add(button_8);
		
		JButton button_9 = new JButton("Off");
		button_9.setBackground(Color.WHITE);
		button_9.setForeground(Color.BLACK);
		button_9.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_9.setBounds(258, 249, 55, 23);
		centerP.add(button_9);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(323, 249, 51, 20);
		centerP.add(textField_5);
		*/
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("../Thesis/Images/returnIcon.png"));
		btnNewButton.setRolloverIcon(new ImageIcon("../Thesis/Images/returnIconY.png"));
		btnNewButton.setBounds(440, 390, 60, 60);
		btnNewButton.setOpaque(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setActionCommand("Return");
		btnNewButton.addActionListener(loginHandler);
		centerP.add(btnNewButton);

		add(centerP);
		
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
		temperature1.setBounds(15, 66, 565, 130);
		temperature1.add(chartPanel1, BorderLayout.CENTER);
		temperature1.validate();
		centerP.add(temperature1);
		
		temperature2 = new JPanel();
		temperature2.setBounds(15, 245, 565, 130);
		temperature2.add(chartPanel2, BorderLayout.CENTER);
		temperature2.validate();
		centerP.add(temperature2);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(73, 380, 450, 84);
		centerP.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon("../Thesis/Images/shadowBg.png"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblBg = new JLabel();
		lblBg.setIcon(new ImageIcon("../Thesis/Images/IoTBg.png"));
		lblBg.setBounds(0,0,600,500);
		centerP.add(lblBg);
		
		btnOn.addActionListener(loginHandler);
		btnOff.addActionListener(loginHandler);
		button.addActionListener(loginHandler);
		button_1.addActionListener(loginHandler);
		
		centerP.repaint();
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
				btnOn.setEnabled(false);
				btnOff.setEnabled(true);
			}
			else if(action.equals("OFF"))
			{
				btnOn.setEnabled(true);
				btnOff.setEnabled(false);
			}
			else if(action.equals("ON1"))
			{
				button.setEnabled(false);
				button_1.setEnabled(true);
			}
			else if(action.equals("OFF1"))
			{
				button.setEnabled(true);
				button_1.setEnabled(false);
			}
			repaint();
		}
	}
}
