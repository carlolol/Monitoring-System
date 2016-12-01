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
	
	private JLabel lblBg, lblOryzaSativa;
	private JButton tempB, moistB, exitB;
	
	public HomeUI(SystemUI systemUI) 
	{
		setLayout(new GridLayout(1, 1));
		this.systemUI = systemUI;
		
		centerP = new JPanel();
		centerP.setLayout(null);
		
		loginHandler = new LoginHandler();
		
		lblOryzaSativa = new JLabel("Oryza Sativa Grains Monitoring System");
		lblOryzaSativa.setForeground(Color.BLACK);
		lblOryzaSativa.setHorizontalAlignment(SwingConstants.CENTER);
		lblOryzaSativa.setFont(new Font("Times New Roman", Font.BOLD, 32));
		lblOryzaSativa.setBounds(380, 120, 600, 50);
		centerP.add(lblOryzaSativa);
		
		exitB = new JButton("");
		exitB.setIcon(new ImageIcon("../Thesis/Images/exitIcon.png"));
		exitB.setRolloverIcon(new ImageIcon("../Thesis/Images/exitIconY.png"));
		exitB.setBounds(1200, 640, 60, 60);
		exitB.setOpaque(false);
		exitB.setContentAreaFilled(false);
		exitB.setBorderPainted(false);
		exitB.setActionCommand("Exit");
		exitB.addActionListener(loginHandler);
		centerP.add(exitB);
		
		moistB = new JButton("");
		moistB.setIcon(new ImageIcon("../Thesis/Images/moistureIcon.png"));
		moistB.setRolloverIcon(new ImageIcon("../Thesis/Images/moistureIconY.png"));
		moistB.setBounds(1100, 640, 60, 60);
		moistB.setOpaque(false);
		moistB.setContentAreaFilled(false);
		moistB.setBorderPainted(false);
		moistB.setActionCommand("Moist");
		moistB.addActionListener(loginHandler);
		centerP.add(moistB);
		
		tempB = new JButton("");
		tempB.setRolloverIcon(new ImageIcon("../Thesis/Images/tempIconY.png"));
		tempB.setIcon(new ImageIcon("../Thesis/Images/tempIcon.png"));
		tempB.setOpaque(false);
		tempB.setContentAreaFilled(false);
		tempB.setBorderPainted(false);
		tempB.setBounds(1000, 640, 60, 60);
		tempB.setActionCommand("Temp");
		tempB.addActionListener(loginHandler);
		centerP.add(tempB);
		
		lblBg = new JLabel();
		lblBg.setIcon(new ImageIcon("../Thesis/Images/nb3.png"));
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
				System.exit(0);
			}
			else if(action.equals("Temp"))
			{
				systemUI.showTemp();
			}
			else if(action.equals("Moist"))
			{
				systemUI.showMoist();
			}
			repaint();
		}
	}
}