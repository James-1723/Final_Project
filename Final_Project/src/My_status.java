import javax.swing.*;

import org.w3c.dom.Text;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.sql.*;

public class My_status extends JFrame{
	private Connection conn;
	boolean sucess;
	private JTable join;
	private JTable recruit;
	ResultSet result;
	public My_status(){
		createLayout();
		setTitle("My Status");
	}
	public void createLayout() {
		try{
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/finalproject","root","n2431836");
			Statement stat = conn.createStatement();
			// 定義表格的標題
			String[] columnNames = {"Course name", "Group leader", "Status", "Link"};
			String query = "";
			sucess = stat.execute(query);
			if (sucess) {
			result = stat.getResultSet();
			// 定義表格的數據
			Object[][] data = {
				{"", "", ""},
				{"", "", ""},
				{"", "", ""}
			};
			// 創建一個新的JTable實例
			join = new JTable(data, columnNames);
			// 為了能夠滾動查看所有數據，我們將表格放入一個JScrollPane。
	
			// 創建一個新的JFrame實例並將表格添加到該框架
			
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(scrollPane);
			frame.pack();
			frame.setVisible(true);
			}

			// 定義表格的標題
			String[] columnNames2 = {"Course name", "Status of recruit", "Applied request", "Applicants' name", "Department", "Message from applicant"};
			query = "";
			sucess = stat.execute(query);
			if(sucess){
				ResultSet result = stat.getResultSet();
				// 定義表格的數據
				Object[][] data2 = {
					{"", "", ""},
					{"", "", ""},
					{"", "", ""}
				};
			
			}
			recruit = new JTable(data2, columnNames2);
			// 為了能夠滾動查看所有數據，我們將表格放入一個JScrollPane。
			JScrollPane scrollPane1 = new JScrollPane(join);
			JScrollPane scrollPane2 = new JScrollPane(recruit);
			JFrame frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(scrollPane);
			frame.pack();
			frame.setVisible(true);
			result.close();			
	} catch (SQLException e1) {
			e1.printStackTrace();
		}
	
	}
}
