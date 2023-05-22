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
		btn = new JButton("Post");

        btn.addActionListener(e -> {
            // 獲取使用者輸入的招募需求資訊

            // 將招募需求資訊繪製成表格公告
        });
	}
	public void createLabel() {
        JLabel groupNameLabel = new JLabel("Group Name");
        JLabel groupLeaderLabel = new JLabel("Leader Name");
        JLabel departmentLabel = new JLabel("Department");
        JLabel studentIDLabel = new JLabel("Student ID");
        JLabel currentMembersLabel = new JLabel("Current Members");
        JLabel recruitMembersLabel = new JLabel("Recruit Members");
        JLabel remainingSpotsLabel = new JLabel("Group Name");

	}
	public void createTextArea() {
        JTextField groupNameTextField = new JTextField();
        JTextField groupLeaderTextField = new JTextField();
        JTextField departmentTextField = new JTextField();
        JTextField studentIDTextField = new JTextField();
        JTextField currentMembersTextField = new JTextField();
        JTextField recruitMembersTextField = new JTextField();
        JTextField remainingSpotsTextField = new JTextField();
	}

	public void createLayout() {
		e = new JPanel();
        e.setLayout(new GridLayout(5, 8));

        createLabel();
        createTextArea();
        createButton();


	}
}
