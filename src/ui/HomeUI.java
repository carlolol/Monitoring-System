package ui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class HomeUI extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JPanel centerP;
	private SystemUI systemUI;
	private LoginHandler loginHandler;
	
	private JLabel lblBg, lblOryzaSativa, lblBlock1, lblBlock2, lblAverageMoist, lblAverageTemp;
	private JButton tempB, moistB, homeB, minimizeB, exitB, helpB;
	private JTextField textField2;
	public int h, w, resH, resW;
	
	public HomeUI(SystemUI systemUI) 
	{
		resH = SystemUI.h;
		resW = SystemUI.w;
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
		lblOryzaSativa.setBounds(w-670, h+300, 600, 50);
		centerP.add(lblOryzaSativa);
		
		lblAverageMoist = new JLabel("Average moisture content");
		lblAverageMoist.setForeground(Color.BLACK);
		lblAverageMoist.setHorizontalAlignment(SwingConstants.CENTER);
		lblAverageMoist.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblAverageMoist.setBounds(w+200, h-280, 600, 50);
		centerP.add(lblAverageMoist);
		
		textField2 = new JTextField();
		textField2.setFont(new Font("Tahoma", Font.PLAIN, 48));
		textField2.setForeground(Color.WHITE);
		textField2.setHorizontalAlignment(SwingConstants.CENTER);
		textField2.setText("0%");
		textField2.setEditable(false);
		textField2.setOpaque(false);
		textField2.setBounds(w+400, h-200, 200, 90);
		centerP.add(textField2);
		textField2.setColumns(10);
		
		lblAverageTemp = new JLabel("Average temperature");
		lblAverageTemp.setForeground(Color.BLACK);
		lblAverageTemp.setHorizontalAlignment(SwingConstants.CENTER);
		lblAverageTemp.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblAverageTemp.setBounds(w+200, h-30, 600, 50);
		centerP.add(lblAverageTemp);
		
		textField2 = new JTextField();
		textField2.setFont(new Font("Tahoma", Font.PLAIN, 48));
		textField2.setForeground(Color.WHITE);
		textField2.setHorizontalAlignment(SwingConstants.CENTER);
		textField2.setText("0°F");
		textField2.setEditable(false);
		textField2.setOpaque(false);
		textField2.setBounds(w+400, h+50, 200, 90);
		centerP.add(textField2);
		textField2.setColumns(10);
		
		exitB = new JButton("");
		exitB.setToolTipText("Exit");
		exitB.setIcon(new ImageIcon("../Thesis/Images/x.png"));
		exitB.setRolloverIcon(new ImageIcon("../Thesis/Images/xhover.png"));
		exitB.setBounds(w+625, h-370, 40, 40);
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
		minimizeB.setBounds(w+580, h-370, 40, 40);
		minimizeB.setOpaque(false);
		minimizeB.setContentAreaFilled(false);
		minimizeB.setBorderPainted(false);
		minimizeB.setActionCommand("Minimize");
		minimizeB.addActionListener(loginHandler);
		centerP.add(minimizeB);
		
		helpB = new JButton("");
		helpB.setToolTipText("Help");
		helpB.setIcon(new ImageIcon("../Thesis/Images/help.png"));
		helpB.setRolloverIcon(new ImageIcon("../Thesis/Images/helphover.png"));
		helpB.setBounds(w+535, h-370, 40, 40);
		helpB.setOpaque(false);
		helpB.setContentAreaFilled(false);
		helpB.setBorderPainted(false);
		helpB.setActionCommand("Help");
		helpB.addActionListener(loginHandler);
		centerP.add(helpB);
		
		tempB = new JButton("");
		tempB.setToolTipText("Temperature");
		tempB.setIcon(new ImageIcon("../Thesis/Images/tempIcon.png"));
		tempB.setRolloverIcon(new ImageIcon("../Thesis/Images/tempIconhover.png"));
		tempB.setOpaque(false);
		tempB.setContentAreaFilled(false);
		tempB.setBorderPainted(false);
		tempB.setBounds(w+390, h+290, 60, 60);
		tempB.setActionCommand("Temp");
		tempB.addActionListener(loginHandler);
		centerP.add(tempB);
		
		moistB = new JButton("");
		moistB.setToolTipText("Moisture");
		moistB.setIcon(new ImageIcon("../Thesis/Images/moistureIcon.png"));
		moistB.setRolloverIcon(new ImageIcon("../Thesis/Images/moistureIconhover.png"));
		moistB.setBounds(w+490, h+290, 60, 60);
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
		homeB.setBounds(w+590, h+290, 60, 60);
		homeB.setOpaque(false);
		homeB.setContentAreaFilled(false);
		homeB.setBorderPainted(false);
		homeB.setActionCommand("Home");
		homeB.addActionListener(loginHandler);
		homeB.setEnabled(false);
		centerP.add(homeB);
		
		lblBlock1 = new JLabel();
		lblBlock1.setIcon(new ImageIcon("../Thesis/Images/block.png"));
		lblBlock1.setBounds(w+380, h-300,300,250);
		centerP.add(lblBlock1);
		
		lblBlock2 = new JLabel();
		lblBlock2.setIcon(new ImageIcon("../Thesis/Images/block.png"));
		lblBlock2.setBounds(w+380, h-50,300,250);
		centerP.add(lblBlock2);
		
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
			else if(action.equals("Help"))
			{
				JOptionPane.showMessageDialog(null, "Thesis by: \nMarc Angelo Martinez\nCarl Louie Aruta\nMelvin Uy",
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
			
			repaint();
		}
	}
}