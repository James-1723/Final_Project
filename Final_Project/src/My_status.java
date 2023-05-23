import javax.swing.*;

import org.w3c.dom.Text;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.sql.*;

public class My_status extends JFrame{
	public My_status(){
		createLayout();
		setTitle("My Status");
	}
	public void createLayout() {
		
		JPanel join = new JPanel(new GridLayout(0,4));
		JPanel firstRow = new JPanel(new GridLayout(1,1));
		firstRow.add(new JLabel("Join section"));
		join.add(firstRow);
		join.add(new JLabel("Course name"));
		join.add(new JLabel("Group leader"));
		join.add(new JLabel("Status"));
		join.add(new JLabel("Link"));


		
		JPanel recruit = new JPanel(new GridLayout(0,6));
		JPanel name = new JPanel(new GridLayout(1,1));
		name.add(new JLabel("Recruit section"));
		recruit.add(name);
		recruit.add(new JLabel("Course name"));
		recruit.add(new JLabel("Status of recruit"));
		recruit.add(new JLabel("Applied request"));
		recruit.add(new JLabel("Applicants' name"));
		recruit.add(new JLabel("Department"));
		recruit.add(new JLabel("message from applicant"));


		
		JPanel allPanel = new JPanel();
	FlowLayout flowLayout = new FlowLayout();
	allPanel.setLayout(flowLayout);
		allPanel.add(join);
		allPanel.add(recruit);		
		getContentPane().add(allPanel);
	
	}
}
