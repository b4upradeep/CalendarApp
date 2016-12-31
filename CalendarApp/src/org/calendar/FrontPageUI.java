package org.calendar;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.calendar.objects.Calendar;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;

public class FrontPageUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrontPageUI frame = new FrontPageUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrontPageUI() {
		setTitle("Calendar Generator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Calendar Year");
		lblNewLabel.setBounds(73, 38, 85, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblSelectImageTo = new JLabel("Select Image to Upload");
		lblSelectImageTo.setBounds(37, 64, 121, 14);
		contentPane.add(lblSelectImageTo);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(190, 35, 80, 20);
		comboBox.addItem("<Select>");
		comboBox.addItem("2016");
		comboBox.addItem("2017");
		comboBox.addItem("2018");
		comboBox.addItem("2019");
		comboBox.addItem("2020");
		comboBox.addItem("2021");
		comboBox.addItem("2022");
		comboBox.addItem("2023");
		comboBox.addItem("2024");
		
		
		contentPane.add(comboBox);
		
		JButton button = new JButton("..");
		button.setBounds(190, 60, 28, 23);
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				JFileChooser chooser = new JFileChooser();
				chooser.setDialogTitle("Select Image File to Upload");
				int checker = chooser.showOpenDialog(null);
				String filePath = chooser.getSelectedFile().getPath();
				boolean isImageFile = filePath.endsWith(".jpg") || filePath.endsWith(".png") || filePath.endsWith(".gif") || filePath.endsWith(".jpeg");
				
			}
		});
		contentPane.add(button);
		
		JButton btnGenerateCalendar = new JButton("Generate Calendar");
		btnGenerateCalendar.setBounds(117, 105, 151, 23);
		btnGenerateCalendar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if("<Select>".equals(comboBox.getSelectedItem())){
					JOptionPane.showMessageDialog(null, "Select a calendar year to proceed");
				} else {
					Calendar object = new Calendar();
					
					object.setYear(Integer.parseInt(comboBox.getSelectedItem().toString()));
					new CalendarUI();
				}
				
				
				
				
				
			}
			
		});
		contentPane.add(btnGenerateCalendar);
		
	}
}
