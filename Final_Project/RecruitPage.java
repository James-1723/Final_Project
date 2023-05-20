import java.awt.*;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.*;

public class RecruitPage extends JFrame {
	private JPanel e;
	private JLabel q;
	private JButton btn;
	private JPanel panel;
	private Connection conn;
	private ArrayList<JCheckBox> checkList;
	
	public RecruitPage(ArrayList checkList,Connection conn){
		this.checkList = checkList;
		this.conn = conn;
		createButton();
	}
	
	public void createButton() {
		
	}
	public void createLabel() {
		
	}
	public void createTextArea() {
		
	}
	public void createLayout() {
		
	}
}
