import javax.swing.*;
import java.sql.*;

public class My_status extends JFrame{
	private Connection conn;
	boolean sucess;
	private JTable join;
	private JTable recruit;
	private JScrollPane scrollPane1;
	private JScrollPane scrollPane2;
	ResultSet result;
	JPanel panel = new JPanel();
	public My_status(){
		createTable();
		createPanel();
		setTitle("My Status");
	}
	public void createTable() {
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
				{"", "", "", ""},
				{"", "", "", ""},
				{"", "", "", ""}
			};
			// 創建一個新的JTable實例
			join = new JTable(data, columnNames);
			}

			// 定義表格的標題
			String[] columnNames2 = {"Course name", "Status of recruit", "Applied request", "Applicants' name", "Department", "Message from applicant"};
			query = "";
			sucess = stat.execute(query);
			if(sucess){
				result = stat.getResultSet();
				// 定義表格的數據
				Object[][] data2 = {
					{"", "", ""},
					{"", "", ""},
					{"", "", ""}
				};
				recruit = new JTable(data2, columnNames2);
			}
			
			// 為了能夠滾動查看所有數據，我們將表格放入一個JScrollPane。
			scrollPane1 = new JScrollPane(join);
			scrollPane2 = new JScrollPane(recruit);
			result.close();			
	} catch (SQLException e1) {
			e1.printStackTrace();
		}
	
	}
	public void createPanel(){
		panel.add(scrollPane1, scrollPane2);
		panel.setVisible(true);
	}
}
