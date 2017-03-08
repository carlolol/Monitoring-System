package ui;

import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.RangeType;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import dao.FirebaseDAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MoistUI extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JPanel centerP, xymoisture1, xymoisture2;
	private SystemUI systemUI;
	private Random rand;
	private int h, w, resH, resW, count1, count2;
	private double moistValue, moistValueBeta;
	private NumberFormat formatter;
	private JButton tempB, moistB, homeB, minimizeB, exitB, aboutB, nextB, previousB;
	private JLabel lblBg, lblMoistureSensor, lblMoisture1, lblMoisture2, lblBlock1, lblBlock2;
	private JTextField textMoisture1, textMoisture2;
	private LoginHandler loginHandler;
	private FirebaseDAO fdao;
	private JFreeChart moistChart1, moistChart2;
	private ChartPanel chart1, chart2;
	private TimeSeries series1, series2;
	private TimeSeriesCollection dataset1, dataset2;
	private XYPlot plot1, plot2;
	private NumberAxis yAxis1, yAxis2;
	private DateAxis xAxis1, xAxis2;
	private Thread thread;
	
	public MoistUI(SystemUI systemUI, FirebaseDAO fdao)
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
		textMoisture1.setText("-");
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
		textMoisture2.setText("-");
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
		generateGraph2();
		
		lblBg = new JLabel();
		lblBg.setIcon(new ImageIcon("../Thesis/Images/bg.png"));
		lblBg.setBounds(0,0,resW,resH);
		centerP.add(lblBg);
		
		add(centerP);
		
		// starts updating of frame
		startThread();
	}
	
	// sensor #1
	@SuppressWarnings("serial")
	public void generateGraph1()
	{
		series1 = new TimeSeries("Sensor Reading Line");
		dataset1 = new TimeSeriesCollection(series1);
		moistChart1 = ChartFactory.createTimeSeriesChart("Moisture Sensor Reading", "Time (minute)",
				"Hygrometer Reading(Moisture Sensor)", dataset1, true, true, false);
		
		plot1 = (XYPlot) moistChart1.getXYPlot();
		
		xAxis1 = (DateAxis) plot1.getDomainAxis();
		xAxis1.setAutoRange(true);
		
		final SimpleDateFormat hourFmt = new SimpleDateFormat("HH:mm:ss");
        
        xAxis1.setDateFormatOverride(new DateFormat()
        {

            @Override
            public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) 
            {
                return hourFmt.format(date, toAppendTo, fieldPosition);
            }

			@Override
			public Date parse(String arg0, ParsePosition arg1) 
			{
				return null;
			}

        });
        
		yAxis1 = (NumberAxis) plot1.getRangeAxis();
		yAxis1.setAutoRangeIncludesZero(true);
        yAxis1.setAutoRange(true);
        yAxis1.setRangeType(RangeType.POSITIVE);
        yAxis1.setNumberFormatOverride(new DecimalFormat("##0.0"));
		
		chart1 = new ChartPanel(moistChart1);
		chart1.setPreferredSize(new Dimension(900, 500));
		chart1.setMouseZoomable(false);
		
		xymoisture1 = new JPanel();
		xymoisture1.setBounds(w-600, h-250, 900, 505);
		xymoisture1.add(chart1, BorderLayout.CENTER);
		xymoisture1.validate();
		
		centerP.add(xymoisture1);
	}
	
	// sensor #2
	@SuppressWarnings("serial")
	public void generateGraph2()
	{
		series2 = new TimeSeries("Sensor Reading Line");
		dataset2 = new TimeSeriesCollection(series2);
		moistChart2 = ChartFactory.createTimeSeriesChart("Moisture Sensor Reading", "Time (minute)",
				"Hygrometer Reading(Moisture Sensor)", dataset2, true, true, false);
		
		plot2 = (XYPlot) moistChart2.getXYPlot();
		
//		XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
//		renderer.setBaseShapesVisible(true);
		
		xAxis2 = (DateAxis) plot2.getDomainAxis();
		xAxis2.setAutoRange(true);
		
		final SimpleDateFormat hourFmt = new SimpleDateFormat("HH:mm:ss");
        
        xAxis2.setDateFormatOverride(new DateFormat()
        {

            @Override
            public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) 
            {
                return hourFmt.format(date, toAppendTo, fieldPosition);
            }

			@Override
			public Date parse(String arg0, ParsePosition arg1) 
			{
				return null;
			}

        });
        
		yAxis2 = (NumberAxis) plot2.getRangeAxis();
		yAxis2.setAutoRangeIncludesZero(true);
        yAxis2.setAutoRange(true);
        yAxis2.setRangeType(RangeType.POSITIVE);
        yAxis2.setNumberFormatOverride(new DecimalFormat("##0.0"));

		chart2 = new ChartPanel(moistChart2);
		chart2.setPreferredSize(new Dimension(900, 500));
		chart2.setMouseZoomable(false);
		
		xymoisture2 = new JPanel();
		xymoisture2.setBounds(w-600, h-250, 900, 505);
		xymoisture2.add(chart2, BorderLayout.CENTER);
		xymoisture2.validate();
		xymoisture2.setVisible(false);
		
		centerP.add(xymoisture2);
	}
	
	// responsible for updating the frame
	public void startThread()
	{
		thread = new Thread()
		{
			public void run()
			{
				count1 = 0; count2 = 0;
				while(true)
				{
					try 
					{
						moistValue = fdao.getMoisture().getFirst();
						moistValueBeta = moistValue + rand.nextInt(2);
						
						if(moistValue > 14)
						{
							textMoisture1.setForeground(Color.RED);
							textMoisture1.setFont(new Font("Tahoma", Font.BOLD, 52));
							count1++;
							if(count1>=30)
							{
								count1 = 0;
								JOptionPane.showMessageDialog(null, "ALERT: 30 seconds of moisture reading is"
										+ " still above normal.", "Reading", JOptionPane.WARNING_MESSAGE);
							}
						}
						else
						{
							count1 = 0;
							textMoisture1.setForeground(Color.WHITE);
							textMoisture1.setFont(new Font("Tahoma", Font.PLAIN, 48));
						}
						
						if(moistValueBeta > 14)
						{
							textMoisture2.setForeground(Color.RED);
							textMoisture2.setFont(new Font("Tahoma", Font.BOLD, 52));
							count2++;
							if(count2>=30)
							{
								count2 = 0;
								JOptionPane.showMessageDialog(null, "ALERT: 30 seconds of moisture reading is"
										+ " still above normal.",
										"Reading", JOptionPane.WARNING_MESSAGE);
							}
						}
						else
						{
							count2 = 0;
							textMoisture2.setForeground(Color.WHITE);
							textMoisture2.setFont(new Font("Tahoma", Font.PLAIN, 48));
						}
						
						textMoisture1.setText(formatter.format(moistValue) + "%");
						textMoisture2.setText(formatter.format(moistValueBeta) + "%");

						series1.add(new Millisecond(), moistValue);
						series2.add(new Millisecond(), moistValueBeta);
						
						Thread.sleep(1000);
					} 
					catch(Exception e) 
					{
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
	              System.exit(0);
			}
			else if(action.equals("Minimize"))
				systemUI.setState(Frame.ICONIFIED);
			else if(action.equals("About"))
				JOptionPane.showMessageDialog(null, "Oryza Sativa Grains Monitoring System\nv.20\n\n"
						+ "Thesis by: \nMarc Angelo Martinez\nCarl Louie Aruta\nMelvin Uy",
						"About", JOptionPane.INFORMATION_MESSAGE);
			else if(action.equals("Next"))
			{
				lblMoistureSensor.setText("Moisture Sensor #2");
				xymoisture1.setVisible(false);
				xymoisture2.setVisible(true);
				nextB.setEnabled(false);
				previousB.setEnabled(true);
			}
			else if(action.equals("Previous"))
			{
				lblMoistureSensor.setText("Moisture Sensor #1");
				xymoisture1.setVisible(true);
				xymoisture2.setVisible(false);
				previousB.setEnabled(false);
				nextB.setEnabled(true);
			}
			else if(action.equals("Temp"))
              	systemUI.showTemp();
			else if(action.equals("Home"))
		      	systemUI.showMain();
			
			repaint();
		}
	}
}