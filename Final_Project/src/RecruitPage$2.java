import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;;

public class RecruitPage$2 extends JFrame {
	private ArrayList<String>selectedIDs, info;
	private Connection conn;
	private JTextField textField,textField_1,textField_2,textField_3,textField_4,textField_5,textField_6;
	private String groupName, groupLeader, department, studentID, currentNumber, recruitNumber, toSay;
	private JButton postB;
	private JTable table;
	private Date publishTime;
	User user = new User();

	public RecruitPage$2(ArrayList selectedIDs, Connection conn) {
        this.selectedIDs = selectedIDs;
        this.conn = conn;
        //getContentPane().setLayout(new FlowLayout()); 
        this.setTitle("Recruit Page");
        createLayout();
        createButton();
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
	    
	    JLabel lblNewLabel_3 = new JLabel("Syudent ID:");
	    panel_3.add(lblNewLabel_3);
	    textField_3 = new JTextField(25);
	    panel_3.add(textField_3);
	    
	    JLabel lblNewLabel_4 = new JLabel("Curent Members:");
	    panel_4.add(lblNewLabel_4);
	    textField_4 = new JTextField(20);
		textField_4.setToolTipText("請輸入現有組員人數");
	    panel_4.add(textField_4);
	    
	    JLabel lblNewLabel_5 = new JLabel("Recruit Numbers:");
	    panel_5.add(lblNewLabel_5);
	    textField_5 = new JTextField(20);
		textField_5.setToolTipText("請輸入欲招募總人數");
	    panel_5.add(textField_5);
	    
	    JLabel lblNewLabel_6 = new JLabel("Something to say:");
	    panel_6.add(lblNewLabel_6);
	    textField_6 = new JTextField(20);
	    panel_6.add(textField_6);
	    
	    JPanel allPanel = new JPanel();
	    allPanel.setLayout(new GridLayout(7, 1));
	    allPanel.add(panel);
	    allPanel.add(panel_1, BorderLayout.WEST);
	    allPanel.add(panel_2, BorderLayout.WEST);
	    allPanel.add(panel_3, BorderLayout.WEST);
	    allPanel.add(panel_4, BorderLayout.WEST);
	    allPanel.add(panel_5, BorderLayout.WEST);
	    allPanel.add(panel_6, BorderLayout.WEST);
	    getContentPane().add(allPanel);
	}

	public void createButton(){
		postB = new JButton("post");
		postB.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){

        		groupName = textField.getText();
				groupLeader = textField_1.getText();
				department = textField_2.getText();
				studentID = textField_3.getText();
				currentNumber = textField_4.getText();
				recruitNumber = textField_5.getText();
				toSay = textField_6.getText();

				//連接的資料庫&資料庫名稱?
				try(Connection conn = DriverManager.getConnection(user.url, user.usernameLogin, user.password)){
					String query = "INSERT INTO databaseName (groupName, groupLeader, department, studentID, currentMember, recruitNumber, toSay) VALUES (?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement stmt = conn.prepareStatement(query);

					stmt.setString(1, groupName);
					stmt.setString(2, groupLeader);
					stmt.setString(3, department);
					stmt.setString(4, studentID);
					stmt.setString(5, currentNumber);
					stmt.setString(6, recruitNumber);
					stmt.setString(7, toSay);

					stmt.setTimestamp(8, new Timestamp(publishTime.getTime()));

					stmt.executeUpdate();

				}catch(SQLException ex){
					ex.printStackTrace();
				}
        	}
        });

        add(postB);
	}

	/*public String query() {
		String query = "SELECT * FROM databaseName";
		return query;
	}

    public void createButton() {
        postB = new JButton("post");
		postB.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){

        		groupName = textField.getText();
				groupLeader = textField_1.getText();
				department = textField_2.getText();
				studentID = textField_3.getText();
				currentMember = textField_4.getText();
				recruitNumber = textField_5.getText();
				toSay = textField_6.getText();

				//連接的資料庫&資料庫名稱?
				try(Connection conn = DriverManager.getConnection(, )){
					String query = "INSERT INTO databaseName (groupName, groupLeader, department, studentID, currentMember, recruitNumber, toSay) VALUES (?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement stmt = conn.prepareStatement(query);

					stmt.setString(1, groupName);
					stmt.setString(2, groupLeader);
					stmt.setString(3, department);
					stmt.setString(4, studentID);
					stmt.setString(5, currentMember);
					stmt.setString(6, recruitNumber);
					stmt.setString(7, toSay);

					stmt.executeUpdate();

				}catch(SQLException ex){
					ex.printStackTrace();
				}
        	}
        });

		displayRecruitmentData();
        add(postB);
    }

	public void displayRecruitmentData(){

		String[] columnNames = {"課程名稱", "課程ID", "老師", "發布時間", "小組名稱", "組長", "系級", "學號", "Gmail",
		"組員", "組員系級", "組員ID", "Gmail", "預估招募人數", "尚缺人數", "想說的話"};

        // 創建表格模型，設置欄位名稱和資料
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        
		// 從資料庫檢索招募資料並插入到表格中
		try (PreparedStatement stmt = conn.prepareStatement(query());
			ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				info.add(rs.getString("課程名稱"));
				info.add(rs.getString("課程ID"));
				info.add(rs.getString("老師"));
				info.add(rs.getString("發布時間"));
				info.add(rs.getString("組長"));
				info.add(rs.getString("組長科系"));
				info.add(rs.getString("組長學號"));

				Object[] info = new Object[columnNames.length];
				for (int i = 0; i < columnNames.length; i++) {
					info[i] = rs.getObject(i + 1); // 從結果集中獲取資料並存儲到info數組中
				}

				selectedIDs.add(rs.getString("組員"));
				selectedIDs.add(rs.getString("組員科系"));
				selectedIDs.add(rs.getString("組員學號"));

				Object[] rowData = new Object[columnNames.length];
				for (int i = 0; i < columnNames.length; i++) {
					rowData[i] = rs.getObject(i + 1); // 從結果集中獲取資料並存儲到rowData數組中
				}

				model.addRow(info);
				model.addRow(rowData); // 將rowData插入到表格中
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
        
        // 創建表格並設置模型
        table = new JTable(model);
        
        // 將表格放入滾動窗格
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 400));
        
        // 將資料彙整成表格呈現在recruitment data視窗中
		JFrame frame = new JFrame("Recruitment Data");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(scrollPane);
		frame.pack();
		frame.setVisible(true);
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
	    
	    JLabel lblNewLabel_3 = new JLabel("Syudent ID:");
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
	    
	    JLabel lblNewLabel_6 = new JLabel("Something to say:");
	    panel_6.add(lblNewLabel_6);
	    textField_6 = new JTextField(20);
	    panel_6.add(textField_6);
	    
	    JPanel allPanel = new JPanel();
	    allPanel.setLayout(new GridLayout(7, 1));
	    allPanel.add(panel);
	    allPanel.add(panel_1, BorderLayout.WEST);
	    allPanel.add(panel_2, BorderLayout.WEST);
	    allPanel.add(panel_3, BorderLayout.WEST);
	    allPanel.add(panel_4, BorderLayout.WEST);
	    allPanel.add(panel_5, BorderLayout.WEST);
	    allPanel.add(panel_6, BorderLayout.WEST);
	    getContentPane().add(allPanel);
	}*/
}