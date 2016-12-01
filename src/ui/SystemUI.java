package ui;

import java.awt.CardLayout;
import java.awt.Container;
import javax.swing.*;

public class SystemUI extends JFrame
{
	private static final long serialVersionUID = 1L;
	private Container container;
	private CardLayout card;
	
	private HomeUI homeUI;
	private TempUI tempUI;
	private MoistUI moistUI;
	
	public SystemUI()
	{	
		container = getContentPane();
		
		card = new CardLayout();
		container.setLayout(card);
		
		homeUI = new HomeUI(this);
		container.add(homeUI, "Main");
		
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(true);
		setVisible(true);
	}
	
	public void showMain()
	{
		setTitle("Oryza Sativa Grains Monitoring System");	
		setLocationRelativeTo(null);
		card.show(container, "Main");
		repaint();
	}
	
	public void showTemp()
	{
		setTitle("Oryza Sativa Grains Monitoring System");
		tempUI = new TempUI(this);
		container.add(tempUI, "Temperature");
		setLocationRelativeTo(null);
		card.show(container, "Temperature");
		repaint();
	}
	
	public void showMoist()
	{
		setTitle("Oryza Sativa Grains Monitoring System");
		moistUI = new MoistUI(this);
		container.add(moistUI, "Moisture");
		setLocationRelativeTo(null);
		card.show(container, "Moisture");
		repaint();
	}
	
	public static void main(String [] args)
	{
		new SystemUI().showMain();
	}
}