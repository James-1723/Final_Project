import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class My_status extends JFrame {
	private User user = new User();
	private JTable join_table, recruit_table;
	private JLabel join_status, recruit_status;
	private JScrollPane scrollPane_1, scrollPane_2;
	private JButton back,submit;
	private MainPage main = new MainPage();


	public My_status() {
		setTitle("My Status");
		createLayout();
		createTable();

		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.setDefaultCloseOperation(EXIT_ON_CLOSE);
				main.setVisible(true);
				main.setSize(600, 500);
				My_status.this.dispose();
			}
		});

		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
			}
		});
	}

	public void createLayout() {
		back = new JButton("back");

		join_table = new JTable();
		recruit_table = new JTable();
		join_status = new JLabel("join_status");
		recruit_status = new JLabel("recruit_status");

		scrollPane_1 = new JScrollPane(join_table);
		scrollPane_2 = new JScrollPane(recruit_table);

		BoxLayout boxLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
		setLayout(boxLayout);

		add(join_status);
		add(scrollPane_1);
		add(recruit_status);
		add(scrollPane_2);
		add(back);
	}

	public void createTable() {
		try (Connection conn = DriverManager.getConnection(user.url, user.usernameLogin, user.password)) {
			String query = "SELECT * FROM `GroupList` ";
			PreparedStatement stat = conn.prepareStatement(query);
			ResultSet rs = stat.executeQuery(query);
			DefaultTableModel model = new DefaultTableModel() {
				// 指定每一欄的類型
				public Class<?> getColumnClass(int columnIndex) {
					return super.getColumnClass(columnIndex);
				}
			};

			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			int cols = rsmd.getColumnCount();

			// 加入欄位名稱
			for (int i = 1; i <= cols; i++) {
				model.addColumn(rsmd.getColumnName(i));
			}

			// 加入資料 要先創建每一行
			while (rs.next()) {
				Object[] rows = new Object[cols]; // 創建原database資料的欄位數
				for (int i = 1; i <= cols; i++) {
					rows[i - 1] = rs.getObject(i); // 資料庫是從1開始 java從0
				}
				model.addRow(rows);
			}
			join_table.setModel(model);
			recruit_table.setModel(model);

			stat.close();
			conn.close();

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}

}
