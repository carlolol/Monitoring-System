package ui;

import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

public class MoistUI extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JPanel centerP, xymoisture;
	private SystemUI systemUI;
	private Random rand;
	private int h, w, resH, resW, x;
	private double moistValue, moistValueBeta;
	private NumberFormat formatter;
	private JButton tempB, moistB, homeB, minimizeB, exitB, aboutB, nextB, previousB;
	private JLabel lblBg, lblMoistureSensor, lblMoisture1, lblMoisture2, lblBlock1, lblBlock2;
	private JTextField textMoisture1, textMoisture2;
	private LoginHandler loginHandler;
	private JFreeChart moistChart;
	private ChartPanel chart;
	private TimeSeries series;
	private TimeSeriesCollection dataset;
	private XYPlot plot;
	private NumberAxis yAxis;
	private Thread thread;
	
	public MoistUI(SystemUI systemUI)
	{		
		// set the decimal format for the data
		formatter = new DecimalFormat("#0.00");
		
		// misc
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
		
		lblMoistureSensor = new JLabel("Moisture Sensor #1");
		lblMoistureSensor.setForeground(Color.BLACK);
		lblMoistureSensor.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoistureSensor.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblMoistureSensor.setBounds(w-610, h-300, 180, 50);
		centerP.add(lblMoistureSensor);
				
		lblMoisture1 = new JLabel("Moisture content #1");
		lblMoisture1.setForeground(Color.BLACK);
		lblMoisture1.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoisture1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblMoisture1.setBounds(w+200, h-300, 600, 50);
		centerP.add(lblMoisture1);
				
		textMoisture1 = new JTextField();
		textMoisture1.setFont(new Font("Tahoma", Font.PLAIN, 48));
		textMoisture1.setForeground(Color.WHITE);
		textMoisture1.setHorizontalAlignment(SwingConstants.CENTER);
		textMoisture1.setText("0%");
		textMoisture1.setEditable(false);
		textMoisture1.setOpaque(false);
		textMoisture1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		textMoisture1.setBounds(w+400, h-230, 200, 90);
		centerP.add(textMoisture1);
				
		lblMoisture2 = new JLabel("Moisture content #2");
		lblMoisture2.setForeground(Color.BLACK);
		lblMoisture2.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoisture2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblMoisture2.setBounds(w+200, h-110, 600, 50);
		centerP.add(lblMoisture2);
				
		textMoisture2 = new JTextField();
		textMoisture2.setFont(new Font("Tahoma", Font.PLAIN, 48));
		textMoisture2.setForeground(Color.WHITE);
		textMoisture2.setHorizontalAlignment(SwingConstants.CENTER);
		textMoisture2.setText("0%");
		textMoisture2.setEditable(false);
		textMoisture2.setOpaque(false);
		textMoisture2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		textMoisture2.setBounds(w+400, h-40, 200, 90);
		centerP.add(textMoisture2);		
	
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
				
		// Jpanel for sensor #1
		generateGraph1();
		
		lblBg = new JLabel();
		lblBg.setIcon(new ImageIcon("../Thesis/Images/bg.png"));
		lblBg.setBounds(0,0,resW,resH);
		centerP.add(lblBg);
		
		add(centerP);
		
		// starts updating of frame
		startThread();
	}
	
	// sensor #1
	public void generateGraph1()
	{
		lblMoistureSensor.setText("Moisture Sensor #1");
		series = new TimeSeries("Sensor Reading Line");
		dataset = new TimeSeriesCollection(series);
		moistChart = ChartFactory.createTimeSeriesChart("Moisture Sensor Reading", "Time (minute)",
				"Hygrometer Reading(Moisture Sensor)", dataset, true, true, false);
		
		plot = (XYPlot) moistChart.getXYPlot();
		plot.getRenderer().setSeriesPaint(0, Color.RED);
		ValueAxis axis = plot.getDomainAxis();
		axis.setAutoRange(true);
        axis.setFixedAutoRange(10000.0);  // 600000 seconds
        axis = plot.getRangeAxis();
        
		yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setTickUnit(new NumberTickUnit(2));
		yAxis.setRange(20,50);
		
		chart = new ChartPanel(moistChart);
		chart.setPreferredSize(new Dimension(900, 500));
		chart.setMouseZoomable(false);
		
		xymoisture = new JPanel();
		xymoisture.setBounds(w-600, h-250, 900, 505);
		xymoisture.add(chart, BorderLayout.CENTER);
		xymoisture.validate();
		
		centerP.add(xymoisture);
	}
	
	// sensor #2
	public void generateGraph2()
	{
		lblMoistureSensor.setText("Moisture Sensor #2");
		series = new TimeSeries("Sensor Reading Line");
		dataset = new TimeSeriesCollection(series);
		moistChart = ChartFactory.createTimeSeriesChart("Moisture Sensor Reading", "Time (minute)",
				"Hygrometer Reading(Moisture Sensor)", dataset, true, true, false);
		
		plot = (XYPlot) moistChart.getXYPlot();
		plot.getRenderer().setSeriesPaint(0, Color.BLUE);
		ValueAxis axis = plot.getDomainAxis();
		axis.setAutoRange(true);
        axis.setFixedAutoRange(10000.0);  // 600000 seconds
        axis = plot.getRangeAxis();
        
		yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setTickUnit(new NumberTickUnit(2));
		yAxis.setRange(20,50);
		
		chart = new ChartPanel(moistChart);
		chart.setPreferredSize(new Dimension(900, 500));
		chart.setMouseZoomable(false);
		
		xymoisture = new JPanel();
		xymoisture.setBounds(w-600, h-250, 900, 505);
		xymoisture.add(chart, BorderLayout.CENTER);
		xymoisture.validate();
		
		centerP.add(xymoisture);
	}
	
	// responsible for updating the frame
	public void startThread()
	{
		thread = new Thread()
		{
			public void run(){
								
				for(x = 0; x>=0; x++)
				{
					try 
					{
						Thread.sleep(1000);
						moistValue = rand.nextInt(10) + 20;
						moistValueBeta = rand.nextInt(10) + 30;
						
						if(moistValue > 35)
						{
							textMoisture1.setForeground(Color.RED);	
						}
						else
						{
							textMoisture1.setForeground(Color.WHITE);
						}
						if(moistValueBeta > 35)
						{
							textMoisture2.setForeground(Color.RED);
						}
						else
						{
							textMoisture2.setForeground(Color.WHITE);
						}
						
						textMoisture1.setText(formatter.format(moistValue) + "%");
						textMoisture2.setText(formatter.format(moistValueBeta) + "%");
						if(lblMoistureSensor.getText()=="Moisture Sensor #1")
						{
							series.add(new Millisecond(), moistValue);
						}
						else if(lblMoistureSensor.getText()=="Moisture Sensor #2")
						{
							series.add(new Millisecond(), moistValueBeta);
						}
					} 
					catch(Exception e) 
					{
						System.out.print("ERROR");
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
				JOptionPane.showMessageDialog(null, "Oryza Sativa Grains Monitoring System\nv.20\n\n"
						+ "Thesis by: \nMarc Angelo Martinez\nCarl Louie Aruta\nMelvin Uy",
						"About", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(action.equals("Next"))
			{
				int result = JOptionPane.showConfirmDialog(null, "Continue to change the sensor? "
					+ "Current connection will be disconnected.", "Current session is active", 
					JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

				if(result == JOptionPane.YES_OPTION)
				{
					series.clear();
					generateGraph2();
					nextB.setEnabled(false);
					previousB.setEnabled(true);
	            }
			}
			else if(action.equals("Previous"))
			{
				int result = JOptionPane.showConfirmDialog(null, "Continue to change the sensor? "
					+ "Current connection will be disconnected.", "Current session is active", 
					JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

				if(result == JOptionPane.YES_OPTION)
				{
					series.clear();
		            generateGraph1();
					previousB.setEnabled(false);
					nextB.setEnabled(true);
				}
			}
			else if(action.equals("Temp"))
			{
              	systemUI.showTemp();
			}
			else if(action.equals("Home"))
			{
		      	systemUI.showMain();
			}
			repaint();
		}
	}
}