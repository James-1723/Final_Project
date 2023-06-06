import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;


public class My_status extends JFrame{
	private Connection conn;
	boolean sucess;
	private JTable join;
	private JTable recruit;
	private JScrollPane joinScrollPane;
	private JScrollPane recruitScrollPane;
	ResultSet result;
	JPanel panel = new JPanel();
	public My_status(){
		createTable();
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
			
			// 創建一個新的JTable實例
			//*join = new JTable(data, columnNames);
			}

			// 定義表格的標題
			String[] columnNames2 = {"Course name", "Status of recruit", "Applied request"};
			query = "";
			sucess = stat.execute(query);
			if(sucess){
				result = stat.getResultSet();
				// 定義表格的數據
				
				// 把數據加入表格
				//*recruit = new JTable(data2, columnNames2);
				}
			
			
			// 為了能夠滾動查看所有數據，我們將表格放入一個JScrollPane。
			joinScrollPane = new JScrollPane(join);
			recruitScrollPane = new JScrollPane(recruit);
			result.close();	
			
			JLabel JoinLabel = new JLabel("Join");
			JLabel RecruitLabel = new JLabel("Recruit");
	
			 // 使用BoxLayout佈局管理器
			 BoxLayout boxLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
			 setLayout(boxLayout);

			  // 添加組件
			  add(JoinLabel);
			  add(joinScrollPane);
			  add(RecruitLabel);
			  add(recruitScrollPane);
			} 
		catch (SQLException e1) {
			e1.printStackTrace();
			}
	
		// 添加一個MouseListener到你的表格中
	recruit.addMouseListener(new MouseAdapter() {
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) { // 雙擊事件
            JTable target = (JTable)e.getSource();
            int row = target.getSelectedRow(); // 獲取被點擊的行的索引

            // 獲取該行對應的申請者列表
            

            // 創建一個JDialog來展示申請者的資訊
            JDialog dialog = new JDialog(My_status.this, "Aplicants' Information");
            dialog.setSize(400, 300);

            // 創建一個JList來展示申請者的姓名
            JList<String> list = new JList<>();
            dialog.add(new JScrollPane(list));

            dialog.setVisible(true);
        }
    }
});

	}
	
}
