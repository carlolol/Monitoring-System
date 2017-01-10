package ui;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.*;

import org.jfree.data.category.DefaultCategoryDataset;

public class SystemUI extends JFrame
{
	private static final long serialVersionUID = 1L;
	private Container container;
	private CardLayout card;
	
	private HomeUI homeUI;
	private TempUI tempUI;
	private MoistUI moistUI;
	public static int h, w;
	private Random rand;
	
	public static DefaultCategoryDataset mdataset1, mdataset2;
	public static int[] statMoist1, statMoist2;
	
	public static DefaultCategoryDataset tdataset1, tdataset2;
	public static int[] statTemp1, statTemp2;
	
	public SystemUI()
	{	
		rand = new Random();
		
		statMoist1 = new int[24];
		statMoist2 = new int[24];
		
		//MOISTURE
		for(int a = 0; a < statMoist1.length; a++)
			statMoist1[a] = rand.nextInt(10) + 30;
		
		for(int a = 0; a < statMoist2.length; a++)
			statMoist2[a] = rand.nextInt(10) + 30;
		
		mdataset1 = new DefaultCategoryDataset();
		for(int a = 0; a < statMoist1.length; a++)
			mdataset1.addValue(statMoist1[a], "moisture", "" + a + ":00");
		
		mdataset2 = new DefaultCategoryDataset();
		for(int a = 0; a < statMoist2.length; a++)
			mdataset2.addValue(statMoist2[a], "moisture", "" + a + ":00");
		
		//TEMPERATURE
		statTemp1 = new int[24];
		statTemp2 = new int[24];
		
		for(int a = 0; a < statTemp1.length; a++)
			statTemp1[a] = rand.nextInt(10) + 30;
		
		for(int a = 0; a < statTemp2.length; a++)
			statTemp2[a] = rand.nextInt(10) + 30;
		
		tdataset1 = new DefaultCategoryDataset();
		for(int a = 0; a < statTemp1.length; a++)
			tdataset1.addValue(statTemp1[a], "temperature", "" + a + ":00");
		
		tdataset2 = new DefaultCategoryDataset();
		for(int a = 0; a < statTemp2.length; a++)
			tdataset2.addValue(statTemp2[a], "temperature", "" + a + ":00");
		
		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension d = t.getScreenSize();

		h = d.height;
		w = d.width;

		//checks if screen resolution is compatible
		if((768>h) || (1366>w))
		{
			screenError();
		}
			
		setSize( w , h );
		
		container = getContentPane();
		
		card = new CardLayout();
		container.setLayout(card);
		
		homeUI = new HomeUI(this);
		container.add(homeUI, "Main");
		
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(true);
		setVisible(true);
	}
	
	public void screenError()
	{
		JOptionPane.showMessageDialog(null, "Current screen resolution is not supported.\n" + 
				"Lower screen resolution than 1366x768 is not supported.",
				"Compatibility Error", JOptionPane.WARNING_MESSAGE);
		
		System.exit(0);
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