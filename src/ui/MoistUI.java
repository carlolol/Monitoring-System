package ui;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MoistUI extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JPanel centerP, moisture1, moisture2, xymoisture;
	private SystemUI systemUI;
	private Random rand;
	private int dbl;
	private DateFormat dateFormat;
	private Date date;
	
	private int h, w, resH, resW, dblparse;
	
	private JButton btnConnect, tempB, moistB, homeB, minimizeB, exitB, aboutB, nextB, previousB;
	private JLabel lblBg, lblMoistureSensor1, lblCurrentMoist, lblAverageMoist, lblBlock1, lblBlock2;
	private JTextField textField1, textField2;
	private LoginHandler loginHandler;
	
	private JFreeChart moistLine1, moistLine2, moistChart;
	private ChartPanel chartPanel1, chartPanel2, chart;
	public static DefaultCategoryDataset mdataset1, mdataset2;
	public static int[] statMoist1, statMoist2;
	
	private XYSeries series;
	private XYSeriesCollection dataset;
	private XYPlot plot;
	private NumberAxis xAxis, yAxis;
	
	private Thread thread;
	
	public MoistUI(SystemUI systemUI)
	{		
		// get date
		dateFormat = new SimpleDateFormat("HH:mm:ss");
		date = new Date();	
		
		rand = new Random();
		
		// GUI components
		resH = SystemUI.getH();
		resW = SystemUI.getW();
		h = resH / 2;
		w = resW / 2;
		
		setLayout(new GridLayout(1, 1));
		this.systemUI = systemUI;
		
		centerP = new JPanel();
		centerP.setLayout(null);
		
		loginHandler = new LoginHandler();
		
		lblMoistureSensor1 = new JLabel("Moisture Sensor #1");
		lblMoistureSensor1.setForeground(Color.BLACK);
		lblMoistureSensor1.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoistureSensor1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblMoistureSensor1.setBounds(w-500, h-300, 180, 50);
		centerP.add(lblMoistureSensor1);
			
		btnConnect = new JButton("Connect");
		btnConnect.setBackground(Color.WHITE);
		btnConnect.setForeground(Color.BLACK);
		btnConnect.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnConnect.setBounds(w-600, h-285, 100, 23);
		btnConnect.setActionCommand("Connect");
		btnConnect.addActionListener(loginHandler);
		centerP.add(btnConnect);
				
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
		textField1.setBounds(w+400, h-230, 200, 90);
		centerP.add(textField1);
		textField1.setColumns(10);
				
		lblAverageMoist = new JLabel("Average moisture content");
		lblAverageMoist.setForeground(Color.BLACK);
		lblAverageMoist.setHorizontalAlignment(SwingConstants.CENTER);
		lblAverageMoist.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblAverageMoist.setBounds(w+200, h-110, 600, 50);
		centerP.add(lblAverageMoist);
				
		textField2 = new JTextField();
		textField2.setFont(new Font("Tahoma", Font.PLAIN, 48));
		textField2.setForeground(Color.WHITE);
		textField2.setHorizontalAlignment(SwingConstants.CENTER);
		textField2.setText("0%");
		textField2.setEditable(false);
		textField2.setOpaque(false);
		textField2.setBounds(w+400, h-40, 200, 90);
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
		nextB.setBounds(w+520, h+100, 60, 50);
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
		previousB.setBounds(w+420, h+100, 60, 50);
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
		homeB.setIcon(new ImageIcon("../Thesis/Images/home.png"));
		homeB.setRolloverIcon(new ImageIcon("../Thesis/Images/homehover.png"));
		homeB.setBounds(resW-80, resH-85, 60, 60);
		homeB.setOpaque(false);
		homeB.setContentAreaFilled(false);
		homeB.setBorderPainted(false);
		homeB.setActionCommand("Home");
		homeB.addActionListener(loginHandler);
		centerP.add(homeB);
		
		lblBlock1 = new JLabel();
		lblBlock1.setIcon(new ImageIcon("../Thesis/Images/block.png"));
		lblBlock1.setBounds(w+380, h-290,300,180);
		centerP.add(lblBlock1);
		
		lblBlock2 = new JLabel();
		lblBlock2.setIcon(new ImageIcon("../Thesis/Images/block.png"));
		lblBlock2.setBounds(w+380, h-100,300,180);
		centerP.add(lblBlock2);
				
		generateGraph();
		
		lblBg = new JLabel();
		lblBg.setIcon(new ImageIcon("../Thesis/Images/bg.png"));
		lblBg.setBounds(0,0,resW,resH);
		centerP.add(lblBg);
		
		add(centerP);
		
		// starts updating the home UI
		startThread();
	}
	
	public void generateGraph()
	{
		series = new XYSeries("Moisture Sensor Reading");
		dataset = new XYSeriesCollection(series);
		moistChart = ChartFactory.createXYLineChart("Moisture Sensor Reading", "Time (minute)",
				"Hygrometer Reading(Moisture Sensor)", dataset, PlotOrientation.VERTICAL, true, true, false);
		
			// Assign it to the chart
		plot = (XYPlot) moistChart.getXYPlot();
		ValueAxis axis = plot.getDomainAxis();
		xAxis = (NumberAxis) plot.getDomainAxis();
		xAxis.setTickUnit(new NumberTickUnit(2));
		xAxis.setAutoRangeIncludesZero(false);
		axis = plot.getRangeAxis();
		xAxis.setAutoRange(true);
		xAxis.setFixedAutoRange(60000.0);  // 60 seconds
		xAxis.setRange(0,60);

		yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setTickUnit(new NumberTickUnit(2));
		yAxis.setAutoRangeIncludesZero(false);
		yAxis.setRange(30,40);
		//plot.setDomainAxis(xAxis);
		
		chart = new ChartPanel(moistChart);
		chart.setPreferredSize(new Dimension(900, 500));
		chart.setMouseZoomable(false);
		
		xymoisture = new JPanel();
		xymoisture.setBounds(w-600, h-250, 900, 505);
		xymoisture.add(chart, BorderLayout.CENTER);
		xymoisture.validate();
		
		centerP.add(xymoisture);
	}
	
	// responsible for updating the moisture UI
	public void startThread()
	{
		thread = new Thread()
		{
			public void run(){
				int x;
				
				for(x = 0; x>=0; x++)
				{
					try 
					{
						if(x==60)
						{
							series.clear();
							x=0;
						}
						Thread.sleep(200);
						dbl = rand.nextInt(10) + 30;
						textField1.setText((rand.nextInt(15) + 10) + "%");
						textField2.setText((rand.nextInt(15) + 10) + "%");
						series.add(x, dbl);
					} 
					catch(Exception e) 
					{
						System.out.print("ERROR " + e);
					}
				}
			}
		};
		thread.start();
	}
	
	// functionality
	private class LoginHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			String action = e.getActionCommand();
			
			if(action.equals("Exit"))
			{
				int result = JOptionPane.showConfirmDialog(null, "Are you sure? All connection will be disconnected.", 
						"Confirmation", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

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
			else if(action.equals("Connect"))
			{
				btnConnect.setText("Disconnect");
				btnConnect.setActionCommand("Disconnect");
				//textField1.setText(statMoist1[23] + "%");
			}
			else if(action.equals("Disconnect"))
			{
				btnConnect.setText("Connect");
				btnConnect.setActionCommand("Connect");
				//textField1.setText(statMoist1[23] + "%");
			}
			else if(action.equals("Next"))
			{
				nextB.setEnabled(false);
				previousB.setEnabled(true);
				moisture1.setBounds(w-600, h-250, 0, 0);
				moisture1.validate();
				moisture2.setBounds(w-600, h-250, 900, 505);
				moisture2.validate();
				
				lblMoistureSensor1.setText("Moisture Sensor #2");
			}
			else if(action.equals("Previous"))
			{
				previousB.setEnabled(false);
				nextB.setEnabled(true);
				moisture2.setBounds(w-600, h-250, 0, 0);
				moisture2.validate();
				moisture1.setBounds(w-600, h-250, 900, 505);
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
				thread.suspend();
				HomeUI.thread.resume();
				systemUI.showMain();
			}
			repaint();
		}
	}
}