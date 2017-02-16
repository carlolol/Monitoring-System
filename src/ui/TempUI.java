package ui;

import javax.swing.*;
import dao.FirebaseDAO;
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

public class TempUI extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JPanel centerP, xytemp;
	private SystemUI systemUI;
	private Random rand;
	private int h, w, resH, resW;
	private double tempValue, tempValueBeta;
	private JButton tempB, moistB, homeB, minimizeB, exitB, aboutB, nextB, previousB;
	private JLabel lblBg, lblTemperatureSensor, lblTemperature1, lblTemperature2, lblBlock1, lblBlock2;
	private JTextField textTemperature1, textTemperature2;
	private LoginHandler loginHandler;
	private FirebaseDAO fdao;
	private JFreeChart tempChart;
	private ChartPanel chart;
	private TimeSeries series;
	private TimeSeriesCollection dataset;
	private XYPlot plot;
	private NumberAxis yAxis;
	private ValueAxis axis;
	private NumberFormat formatter;
	private Thread thread;
	
	public TempUI(SystemUI systemUI, FirebaseDAO fdao)
	{
		// set the decimal format for the data
		formatter = new DecimalFormat("#0.00");
		
		// misc
		this.fdao = fdao;
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
		
		lblTemperatureSensor = new JLabel("Temperature Sensor #1");
		lblTemperatureSensor.setForeground(Color.BLACK);
		lblTemperatureSensor.setHorizontalAlignment(SwingConstants.CENTER);
		lblTemperatureSensor.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTemperatureSensor.setBounds(w-600, h-300, 200, 50);
		centerP.add(lblTemperatureSensor);
		
		lblTemperature1 = new JLabel("Temperature Sensor #1");
		lblTemperature1.setForeground(Color.BLACK);
		lblTemperature1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTemperature1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTemperature1.setBounds(w+200, h-300, 600, 50);
		centerP.add(lblTemperature1);
		
		textTemperature1 = new JTextField();
		textTemperature1.setFont(new Font("Tahoma", Font.PLAIN, 48));
		textTemperature1.setForeground(Color.WHITE);
		textTemperature1.setHorizontalAlignment(SwingConstants.CENTER);
		textTemperature1.setText("-");
		textTemperature1.setEditable(false);
		textTemperature1.setOpaque(false);
		textTemperature1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		textTemperature1.setBounds(w+400, h-230, 200, 90);
		centerP.add(textTemperature1);
		
		lblTemperature2 = new JLabel("Temperature Sensor #2");
		lblTemperature2.setForeground(Color.BLACK);
		lblTemperature2.setHorizontalAlignment(SwingConstants.CENTER);
		lblTemperature2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTemperature2.setBounds(w+200, h-110, 600, 50);
		centerP.add(lblTemperature2);
		
		textTemperature2 = new JTextField();
		textTemperature2.setFont(new Font("Tahoma", Font.PLAIN, 48));
		textTemperature2.setForeground(Color.WHITE);
		textTemperature2.setHorizontalAlignment(SwingConstants.CENTER);
		textTemperature2.setText("-");
		textTemperature2.setEditable(false);
		textTemperature2.setOpaque(false);
		textTemperature2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		textTemperature2.setBounds(w+400, h-40, 200, 90);
		centerP.add(textTemperature2);
		
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
		tempB.setEnabled(false);
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
		
		// JPanel for sensor #1
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
		lblTemperatureSensor.setText("Temperature Sensor #1");
		series = new TimeSeries("Sensor Reading Line");
		dataset = new TimeSeriesCollection(series);
		tempChart = ChartFactory.createTimeSeriesChart("Temperature Sensor Reading", "Time (minute)",
				"Temperature Reading(ds18b20 Sensor)", dataset, true, true, false);
		
		plot = (XYPlot) tempChart.getXYPlot();
		ValueAxis axis = plot.getDomainAxis();
		axis.setAutoRange(true);
        axis.setFixedAutoRange(600000.0);  // 600000 seconds
        axis = plot.getRangeAxis();
        
		yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setTickUnit(new NumberTickUnit(5));
		yAxis.setRange(20,60);
		
		chart = new ChartPanel(tempChart);
		chart.setPreferredSize(new Dimension(900, 500));
		chart.setMouseZoomable(false);
		
		xytemp = new JPanel();
		xytemp.setBounds(w-600, h-250, 900, 505);
		xytemp.add(chart, BorderLayout.CENTER);
		xytemp.validate();
		
		centerP.add(xytemp);
	}
	
	// sensor #2
	public void generateGraph2()
	{
		lblTemperatureSensor.setText("Temperature Sensor #2");
		series = new TimeSeries("Sensor Reading Line");
		dataset = new TimeSeriesCollection(series);
		tempChart = ChartFactory.createTimeSeriesChart("Temperature Sensor Reading", "Time (minute)",
				"Temperature Reading(ds18b20 Sensor)", dataset, true, true, false);
		
		plot = (XYPlot) tempChart.getXYPlot();
		plot.getRenderer().setSeriesPaint(0, Color.BLUE);
		axis = plot.getDomainAxis();
		axis.setAutoRange(true);
        axis.setFixedAutoRange(600000.0);  // 600000 seconds
        axis = plot.getRangeAxis();
        
		yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setTickUnit(new NumberTickUnit(5));
		yAxis.setRange(20,60);
		
		chart = new ChartPanel(tempChart);
		chart.setPreferredSize(new Dimension(900, 500));
		chart.setMouseZoomable(false);
		
		xytemp = new JPanel();
		xytemp.setBounds(w-600, h-250, 900, 505);
		xytemp.add(chart, BorderLayout.CENTER);
		xytemp.validate();
		
		centerP.add(xytemp);
	}
	
	// responsible for updating the frame
	public void startThread()
	{
		thread = new Thread()
		{
			public void run(){
				int x = 0;
				
				for(x = 1; x>0; x++)
				{
					try 
					{
						Thread.sleep(60000); //1000 miliseconds = 1 seconds 60000
						tempValue = fdao.getTemperature().getFirst();
						tempValueBeta = fdao.getTemperature().getFirst() + rand.nextInt(20);
						
						if(tempValue > 35)
						{
							textTemperature1.setForeground(Color.RED);	
						}
						else
						{
							textTemperature1.setForeground(Color.WHITE);
						}
						
						if(tempValueBeta > 35)
						{
							textTemperature2.setForeground(Color.RED);
						}
						else if(tempValueBeta < 35)
						{
							textTemperature2.setForeground(Color.WHITE);
						}
						
						textTemperature1.setText(formatter.format(tempValue) + "\u00b0C");
						textTemperature2.setText(formatter.format(tempValueBeta) + "\u00b0C");
						if(lblTemperatureSensor.getText()=="Temperature Sensor #1")
						{
							series.add(new Millisecond(), tempValue);
						}
						else if(lblTemperatureSensor.getText()=="Temperature Sensor #2")
						{
							series.add(new Millisecond(), tempValueBeta);
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
						lblTemperatureSensor.setText("Temperature Sensor #2");
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
						lblTemperatureSensor.setText("Temperature Sensor #1");
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