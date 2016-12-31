package org.calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JLabel;
import java.awt.Font;

public class CalendarUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalendarUI frame = new CalendarUI();
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
	public CalendarUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1405, 790);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String[] weekDays = new String[7];
		weekDays[3] = "Wed";
		weekDays[4] = "Thu";
		weekDays[5] = "Fri";
		weekDays[6] = "Sat";
		weekDays[0] = "Sun";
		weekDays[1] = "Mon";
		weekDays[2] = "Tue";

		JLabel monthLabel = new JLabel("Month");
		monthLabel.setBounds(63, 67, 54, 14);
		monthLabel.setOpaque(true);
		monthLabel.setBackground(new Color(255, 255, 255));
		monthLabel.setHorizontalAlignment(SwingConstants.CENTER);

		contentPane.add(monthLabel);

		JLabel weekLabel = new JLabel("Week");
		weekLabel.setBounds(63, 84, 46, 14);
		weekLabel.setBackground(Color.YELLOW);
		weekLabel.setForeground(Color.BLUE);
		weekLabel.setHorizontalAlignment(SwingConstants.CENTER);

		contentPane.add(weekLabel);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(296, 11, 199, 31);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(962, 67, 127, 105);
		contentPane.add(panel);

		JLabel[] monthGroup = new JLabel[12];
		String[] months = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sep", "Oct", "Nov",
				"Dec" };
		JLabel[] weekGroup = new JLabel[40];
		int j = 101;
		for (int i = 0; i < 40; i++) {
			int index = i % 7;
			weekGroup[i] = new JLabel(weekDays[(index)]);
			weekGroup[i].setBounds(63, j, 46, 14);
			weekGroup[i].setOpaque(true);
			weekGroup[i].setBackground(Color.YELLOW);
			weekGroup[i].setForeground(Color.BLUE);
			weekGroup[i].setHorizontalAlignment(SwingConstants.CENTER);

			String labelText = weekGroup[i].getText();
			if ("Sat".equals(labelText) || "Sun".equals(labelText)) {
				weekGroup[i].setBackground(new Color(255, 153, 51));
				weekGroup[i].setForeground(new Color(0, 102, 204));
			}

			contentPane.add(weekGroup[i]);
			j += 14;
		}
		j = 117;
		for (int i = 0; i < 12; i++) {
			monthGroup[i] = new JLabel(months[i]);
			monthGroup[i].setBounds(j, 67, 54, 14);
			monthGroup[i].setOpaque(true);
			monthGroup[i].setBackground(new Color(255, 255, 255));
			monthGroup[i].setHorizontalAlignment(SwingConstants.CENTER);

			contentPane.add(monthGroup[i]);
			j += 54;
		}

		int x = 117;
		int y = 101;
		JLabel[][] dayLabel = new JLabel[12][40];
		for (int ii = 0; ii < 12; ii++) {
			for (int jj = 0; jj < 40; jj++) {
				dayLabel[ii][jj] = new JLabel();
				dayLabel[ii][jj].setBounds(x, y, 54, 14);
				dayLabel[ii][jj].setOpaque(true);
				dayLabel[ii][jj].setBackground(Color.YELLOW);
				dayLabel[ii][jj].setForeground(Color.BLUE);
				dayLabel[ii][jj].setHorizontalAlignment(SwingConstants.CENTER);

				String weekType = weekGroup[jj].getText();
				if ("Sat".equals(weekType) || "Sun".equals(weekType)) {
					dayLabel[ii][jj].setBackground(new Color(255, 153, 51));
					dayLabel[ii][jj].setForeground(new Color(0, 102, 204));
				}
				contentPane.add(dayLabel[ii][jj]);
				y += 14;
			}
			y = 101;
			x += 54;
		}
		
		org.calendar.objects.Calendar calendarObj = new org.calendar.objects.Calendar();
		int year = 2017;
		lblNewLabel.setText(year + " Calendar");
		
		Calendar calendar = new GregorianCalendar(year, 0, 1);
		int placeIndex = calendar.get(Calendar.DAY_OF_WEEK);
		placeIndex = (placeIndex - 1) % 7;
		daySetter(placeIndex, 0, dayLabel,year);
	}
	
	public boolean isLeapYear(int year){
		return ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0));
		
	}

	public void daySetter(int placeIndex, int monthIndex, JLabel[][] label,int year) {
		int i = 0;
		int date = 1;
		int daysInMonth[] = new int[] { 31, isLeapYear(year)?29:28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		for (i = placeIndex; i <= placeIndex + daysInMonth[monthIndex] - 1; i++) {
			label[monthIndex][i].setText(String.valueOf(date));
			date += 1;
		}
		if (monthIndex == 11) {
			return;
		} else {
			daySetter(i % 7, monthIndex + 1, label,year);
		}
	}
}

// 1 - Sun 4 - Sun 8
// 2 - Mon 5 - Mon 9
// 3 - Tue 6 - Tue 10
// 4 - Wed 0 - Wed 4
// 5 - Thu 1 - Thu 5
// 6 - Fri 2 - Fri 6
// 7 - Sat 3 - Sat 7
