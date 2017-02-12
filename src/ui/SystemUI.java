package ui;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

import javax.swing.*;

import org.jfree.data.category.DefaultCategoryDataset;

import dao.FirebaseDAO;

public class SystemUI extends JFrame
{
	private static final long serialVersionUID = 1L;
	private Container container;
	private CardLayout card;
	
	private HomeUI homeUI;
	private TempUI tempUI;
	private MoistUI moistUI;
	private static int h, w;
	private ImageIcon img;
	
	private FirebaseDAO fdao;
	
	public SystemUI()
	{	
		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension d = t.getScreenSize();

		img = new ImageIcon("../Thesis/Images/finallogo.png");
		setIconImage(img.getImage());
		
		h = d.height;
		w = d.width;

		// checks if screen resolution is compatible
		if((768>h) || (1366>w))
			screenError();
			
		setSize(w, h);
		
		container = getContentPane();
		
		try {
			fdao = new FirebaseDAO();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		fdao.startRetrieveData();
		
		card = new CardLayout();
		container.setLayout(card);
		
		homeUI = new HomeUI(this, fdao);
		container.add(homeUI, "Main");
		
		tempUI = new TempUI(this, fdao);
		container.add(tempUI, "Temperature");
		
		moistUI = new MoistUI(this);
		container.add(moistUI, "Moisture");
		
		setTitle("Oryza Sativa Grains Monitoring System");	
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
		setLocationRelativeTo(null);
		card.show(container, "Main");
		repaint();
	}
	
	public void showTemp()
	{
//		setTitle("Oryza Sativa Grains Monitoring System");
		setLocationRelativeTo(null);
		card.show(container, "Temperature");
		repaint();
	}
	
	public void showMoist()
	{
//		setTitle("Oryza Sativa Grains Monitoring System");
		setLocationRelativeTo(null);
		card.show(container, "Moisture");
		repaint();
	}
	
	public static int getH()
	{
		return h;
	}
	
	public static int getW()
	{
		return w;
	}
		
	public static void main(String [] args)
	{
		new SystemUI().showMain();
	}
}