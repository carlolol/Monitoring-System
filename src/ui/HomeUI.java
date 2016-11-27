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
	
	private JLabel lblBg;
	
	public HomeUI(SystemUI systemUI) 
	{
		setLayout(new GridLayout(1, 1));
		this.systemUI = systemUI;
		
		centerP = new JPanel();
		centerP.setLayout(null);
		
		loginHandler = new LoginHandler();
		
		JLabel lblOryzaSativa = new JLabel("Oryza Sativa Grains Monitoring System");
		lblOryzaSativa.setForeground(Color.BLACK);
		lblOryzaSativa.setHorizontalAlignment(SwingConstants.CENTER);
		lblOryzaSativa.setFont(new Font("Times New Roman", Font.BOLD, 32));
		lblOryzaSativa.setBounds(380, 120, 600, 50);
		centerP.add(lblOryzaSativa);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setIcon(new ImageIcon("../Thesis/Images/exitIcon.png"));
		btnNewButton_2.setRolloverIcon(new ImageIcon("../Thesis/Images/exitIconY.png"));
		btnNewButton_2.setBounds(1200, 640, 60, 60);
		btnNewButton_2.setOpaque(false);
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setActionCommand("Exit");
		btnNewButton_2.addActionListener(loginHandler);
		centerP.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon("../Thesis/Images/moistureIcon.png"));
		btnNewButton_1.setRolloverIcon(new ImageIcon("../Thesis/Images/moistureIconY.png"));
		btnNewButton_1.setBounds(1100, 640, 60, 60);
		btnNewButton_1.setOpaque(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setActionCommand("Moist");
		btnNewButton_1.addActionListener(loginHandler);
		centerP.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setRolloverIcon(new ImageIcon("../Thesis/Images/tempIconY.png"));
		btnNewButton.setIcon(new ImageIcon("../Thesis/Images/tempIcon.png"));
		btnNewButton.setOpaque(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBounds(1000, 640, 60, 60);
		btnNewButton.setActionCommand("Temp");
		btnNewButton.addActionListener(loginHandler);
		centerP.add(btnNewButton);
		
		/*JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(73, 380, 450, 84);
		centerP.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Melvin\\Documents\\Workspace\\Thesis\\Images\\shadowBg.png"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		*/
		
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
