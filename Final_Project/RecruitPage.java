import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.*;

public class RecruitPage extends JFrame {
	private ArrayList<Integer>selectedIDs;
	private Connection conn;
	private JTextField textField,textField_1,textField_2,textField_3,textField_4,textField_5,textField_6;
	private JButton postB;

	public RecruitPage(ArrayList selectedIDs, Connection conn) {
        this.selectedIDs = selectedIDs;
        this.conn = conn;
        getContentPane().setLayout(new FlowLayout()); 
        this.setTitle("Recruit Page");
        createLayout();
        createButton();
        
        postB.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		String query = query();
        	}
        });
    }
	public String query() {
		String q0 = textField.getText();
		String q1 = textField_1.getText();
		String q2 = textField_2.getText();
		String q3 = textField_3.getText();
		String q4 = textField_4.getText();
		String q5 = textField_5.getText();
		String q6 = textField_6.getText();

		String query = q0+q1+q2+q3+q4+q5+q6;
		return query;

	}

    public void createButton() {
        postB = new JButton("post");
        add(postB);
    }
	
	public void createLayout() {
	    JPanel panel = new JPanel();
	    panel.setBounds(56, 26, 303, 20);
	    
	    JPanel panel_1 = new JPanel();
	    panel_1.setBounds(56, 66, 303, 10);
	    
	    JPanel panel_2 = new JPanel();
	    panel_2.setBounds(56, 106, 303, 30);
	    
	    JPanel panel_3 = new JPanel();
	    panel_3.setBounds(56, 146, 303, 30);
	    
	    JPanel panel_4 = new JPanel();
	    panel_4.setBounds(56, 186, 303, 30);
	    
	    JPanel panel_5 = new JPanel();
	    panel_5.setBounds(56, 226, 303, 30);
	    
	    JPanel panel_6 = new JPanel();
	    panel_6.setBounds(56, 266, 303, 30);
	    
	    JLabel lblNewLabel = new JLabel("Group Name:");
	    panel.add(lblNewLabel);
	    textField = new JTextField(20);
	    panel.add(textField);
	    
	    JLabel lblNewLabel_1 = new JLabel("Group leader:");
	    panel_1.add(lblNewLabel_1);
	    textField_1 = new JTextField(20);
	    panel_1.add(textField_1);
	    
	    JLabel lblNewLabel_2 = new JLabel("Department:");
	    panel_2.add(lblNewLabel_2);
	    textField_2 = new JTextField(20);
	    panel_2.add(textField_2);
	    
	    JLabel lblNewLabel_3 = new JLabel("ID:");
	    panel_3.add(lblNewLabel_3);
	    textField_3 = new JTextField(25);
	    panel_3.add(textField_3);
	    
	    JLabel lblNewLabel_4 = new JLabel("Curent Members:");
	    panel_4.add(lblNewLabel_4);
	    textField_4 = new JTextField(20);
	    panel_4.add(textField_4);
	    
	    JLabel lblNewLabel_5 = new JLabel("Recruit Numbers:");
	    panel_5.add(lblNewLabel_5);
	    textField_5 = new JTextField(20);
	    panel_5.add(textField_5);
	    
	    JLabel lblNewLabel_6 = new JLabel("Something need to know:");
	    panel_6.add(lblNewLabel_6);
	    textField_6 = new JTextField(20);
	    panel_6.add(textField_6);
	    
	    JPanel allPanel = new JPanel();
	    allPanel.setLayout(new GridLayout(7, 1));
	    allPanel.add(panel);
	    allPanel.add(panel_1);
	    allPanel.add(panel_2);
	    allPanel.add(panel_3);
	    allPanel.add(panel_4);
	    allPanel.add(panel_5);
	    allPanel.add(panel_6);
	    getContentPane().add(allPanel);
	}
}
