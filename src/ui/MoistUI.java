package ui;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoistUI extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JPanel centerP, moisture1, moisture2;
	private SystemUI systemUI;
	
	private int h, w, resH, resW;
	
	private JButton btnOn1, btnOff1, tempB, moistB, homeB, minimizeB, exitB, aboutB, nextB, previousB;
	private JLabel lblBg, lblMoistureSensor1, lblCurrentMoist, lblAverageMoist;
	private JTextField textField1, textField2;
	private LoginHandler loginHandler;
	
	private JFreeChart moistLine1, moistLine2;
	private ChartPanel chartPanel1, chartPanel2;
	
	public MoistUI(SystemUI systemUI)
	{
		resH = SystemUI.getH();
		resW = SystemUI.getW();
		h = resH / 2;
		w = resW / 2;
		
		setLayout(new GridLayout(1, 1));
		this.systemUI = systemUI;
		
		centerP = new JPanel();
		centerP.setLayout(null);
		
		loginHandler = new LoginHandler();
		
		//-----------------------------------------BLOCK-----------------------------------------
		lblMoistureSensor1 = new JLabel("Moisture Sensor #1");
		lblMoistureSensor1.setForeground(Color.BLACK);
		lblMoistureSensor1.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoistureSensor1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblMoistureSensor1.setBounds(w-610, h-300, 180, 50);
		centerP.add(lblMoistureSensor1);
			
		btnOn1 = new JButton("On");
		btnOn1.setBackground(Color.WHITE);
		btnOn1.setForeground(Color.BLACK);
		btnOn1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnOn1.setBounds(w-390, h-285, 55, 23);
		btnOn1.setActionCommand("ON");
		btnOn1.addActionListener(loginHandler);
		centerP.add(btnOn1);
				
		btnOff1 = new JButton("Off");
		btnOff1.setEnabled(false);
		btnOff1.setBackground(Color.WHITE);
		btnOff1.setForeground(Color.BLACK);
		btnOff1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnOff1.setBounds(w-330, h-285, 55, 23);
		btnOff1.setActionCommand("OFF");
		btnOff1.addActionListener(loginHandler);
		centerP.add(btnOff1);
				
		lblCurrentMoist = new JLabel("Moisture content");
		lblCurrentMoist.setForeground(Color.BLACK);
		lblCurrentMoist.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentMoist.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblCurrentMoist.setBounds(w+200, h-300, 600, 50);
		centerP.add(lblCurrentMoist);
				
		textField1 = new JTextField();
		textField1.setFont(new Font("Tahoma", Font.PLAIN, 48));
		textField1.setForeground(Color.WHITE);
		textField1.setHorizontalAlignment(SwingConstants.CENTER);
		textField1.setText("0%");
		textField1.setEditable(false);
		textField1.setOpaque(false);
		textField1.setBounds(w+400, h-250, 200, 90);
		centerP.add(textField1);
		textField1.setColumns(10);
				
		lblAverageMoist = new JLabel("Average moisture content");
		lblAverageMoist.setForeground(Color.BLACK);
		lblAverageMoist.setHorizontalAlignment(SwingConstants.CENTER);
		lblAverageMoist.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblAverageMoist.setBounds(w+200, h-120, 600, 50);
		centerP.add(lblAverageMoist);
				
		textField2 = new JTextField();
		textField2.setFont(new Font("Tahoma", Font.PLAIN, 48));
		textField2.setForeground(Color.WHITE);
		textField2.setHorizontalAlignment(SwingConstants.CENTER);
		textField2.setText("0%");
		textField2.setEditable(false);
		textField2.setOpaque(false);
		textField2.setBounds(w+400, h-70, 200, 90);
		centerP.add(textField2);
		textField2.setColumns(10);		
	
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
		
		nextB = new JButton("");
		nextB.setToolTipText("Next sensor");
		nextB.setIcon(new ImageIcon("../Thesis/Images/next.png"));
		nextB.setRolloverIcon(new ImageIcon("../Thesis/Images/nexthover.png"));
		nextB.setBounds(w+520, h+70, 60, 50);
		nextB.setOpaque(false);
		nextB.setContentAreaFilled(false);
		nextB.setBorderPainted(false);
		nextB.setActionCommand("Next");
		nextB.addActionListener(loginHandler);
		centerP.add(nextB);
		
		previousB = new JButton("");
		previousB.setToolTipText("Previous sensor");
		previousB.setIcon(new ImageIcon("../Thesis/Images/previous.png"));
		previousB.setRolloverIcon(new ImageIcon("../Thesis/Images/previoushover.png"));
		previousB.setBounds(w+420, h+70, 60, 50);
		previousB.setOpaque(false);
		previousB.setContentAreaFilled(false);
		previousB.setBorderPainted(false);
		previousB.setEnabled(false);
		previousB.setActionCommand("Previous");
		previousB.addActionListener(loginHandler);
		centerP.add(previousB);
		
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
		moistB.setEnabled(false);
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
		centerP.add(homeB);
					
		moistLine1 = ChartFactory.createLineChart("Moisture #1", "Time", "Moisture", SystemUI.mdataset1);
		chartPanel1 = new ChartPanel(moistLine1);
		chartPanel1.setPreferredSize(new Dimension(900, 500));
		chartPanel1.setMouseZoomable(false);
		//chartPanel1.zoomInRange(0, 40);
		
		moisture1 = new JPanel();
		moisture1.setBounds(w-600, h-250, 900, 480);
		moisture1.add(chartPanel1, BorderLayout.CENTER);
		moisture1.validate();
		centerP.add(moisture1);
		
		moistLine2 = ChartFactory.createLineChart("Moisture #2", "Time", "Moisture", SystemUI.mdataset2);
		chartPanel2 = new ChartPanel(moistLine2);
		chartPanel2.setPreferredSize(new Dimension(900, 500));
		chartPanel2.setMouseZoomable(false);	
		
		moisture2 = new JPanel();
		moisture2.setBounds(w-600, h-250, 0, 0);
		moisture2.add(chartPanel2, BorderLayout.CENTER);
		moisture2.validate();
		centerP.add(moisture2);
		
		lblBg = new JLabel();
		lblBg.setIcon(new ImageIcon("../Thesis/Images/bg.png"));
		lblBg.setBounds(0,0,resW,resH);
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
				int result = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirmation",
						JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

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
			else if(action.equals("ON"))
			{
				btnOn1.setEnabled(false);
				btnOff1.setEnabled(true);
				textField1.setText(SystemUI.statMoist1[23] + "%");
			}
			else if(action.equals("OFF"))
			{
				btnOn1.setEnabled(true);
				btnOff1.setEnabled(false);
				textField1.setText("0");
			}
			else if(action.equals("ON1"))
			{
				btnOn1.setEnabled(false);
				btnOff1.setEnabled(true);
				textField1.setText(SystemUI.statMoist2[23] + "%");
			}
			else if(action.equals("OFF1"))
			{
				btnOn1.setEnabled(true);
				btnOff1.setEnabled(false);
				textField1.setText("0");
			}
			else if(action.equals("Next"))
			{
				nextB.setEnabled(false);
				previousB.setEnabled(true);
				btnOn1.setActionCommand("ON1");
				btnOff1.setActionCommand("OFF1");
				moisture1.setBounds(w-600, h-250, 0, 0);
				moisture1.validate();
				moisture2.setBounds(w-600, h-250, 900, 480);
				moisture2.validate();
				
				lblMoistureSensor1.setText("Moisture Sensor #2");
			}
			else if(action.equals("Previous"))
			{
				previousB.setEnabled(false);
				nextB.setEnabled(true);
				btnOn1.setActionCommand("ON");
				btnOff1.setActionCommand("OFF");
				moisture2.setBounds(w-600, h-250, 0, 0);
				moisture2.validate();
				moisture1.setBounds(w-600, h-250, 900, 480);
				moisture1.validate();
				
				lblMoistureSensor1.setText("Moisture Sensor #1");
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