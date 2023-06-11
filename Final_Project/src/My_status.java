import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.Color;

public class My_status extends JFrame {
	private User user = new User();
	private JTable join_table, recruit_table;
	private JLabel join_status, recruit_status;
	private JScrollPane scrollPane_1, scrollPane_2;
	private JButton back, submit;
	private MainPage main = new MainPage();
	private JPanel bPanel;
	private JComboBox comboBox = new JComboBox();

	public My_status(User users) {
		setTitle("My Status");
		createLayout();
		createJoinTable();
		createRecruitTable();

		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.setDefaultCloseOperation(EXIT_ON_CLOSE);
				main.setVisible(true);
				main.setSize(600, 500);
				My_status.this.dispose();
			}
		});

		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog.setDefaultLookAndFeelDecorated(true);
				int response = JOptionPane.showConfirmDialog(null,
						"just to make sure you won't regrate to recruit them.", "Confirm",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.NO_OPTION) {// 按否
					System.out.println("No button is clicked");
				} else if (response == JOptionPane.YES_OPTION) {// 按確認

					try (Connection conn = DriverManager.getConnection(user.url, user.usernameLogin, user.password)) {

						comboBox.getSelectedIndex();
						String query = "INSERT INTO`GroupList` ";
						PreparedStatement stat = conn.prepareStatement(query);
						ResultSet rs = stat.executeQuery(query);

					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					// 加入後從frame消失
					// if滿了，從user介面消失
					// 寄信給被加入者、如果滿了沒被加入的人也要寄
					JOptionPane.showMessageDialog(null, "You have successfully added the members!",
							"Success", JOptionPane.INFORMATION_MESSAGE);

				} else if (response == JOptionPane.CLOSED_OPTION) {
					System.out.println("JOptionPane closed");
				}
			}
		});
	}

	public void createLayout() {
		back = new JButton("back");
		submit = new JButton("submit");

		join_table = new JTable();
		recruit_table = new JTable();
		join_status = new JLabel("join_status");
		recruit_status = new JLabel("recruit_status");

		scrollPane_1 = new JScrollPane(join_table);
		scrollPane_2 = new JScrollPane(recruit_table);

		BoxLayout boxLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
		getContentPane().setLayout(boxLayout);

		bPanel = new JPanel();
		bPanel.setBackground(new Color(210, 212, 207));
		bPanel.add(back);
		bPanel.add(submit);

		getContentPane().add(join_status);
		getContentPane().add(scrollPane_1);
		getContentPane().add(recruit_status);
		getContentPane().add(scrollPane_2);
		getContentPane().add(bPanel);
	}

	public void createJoinTable() {
		try (Connection conn = DriverManager.getConnection(user.url, user.usernameLogin, user.password)) {

			String name = user.userName;
			System.out.print("ede" + name);
			String query = String.format("SELECT * FROM `GroupList` WHERE `Current_members' names` LIKE '%%%s%%'",
					name);

			PreparedStatement stat = conn.prepareStatement(query);
			// String.format("'%%'+'%s'+'%%'", name);
			// stat.setString(1, "%" + '%james,33%' + "%");
			ResultSet rs = stat.executeQuery();

			DefaultTableModel model = new DefaultTableModel() {
				// 指定每一欄的類型
				public Class<?> getColumnClass(int columnIndex) {
					return super.getColumnClass(columnIndex);
				}
			};
			ResultSetMetaData rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();

			// 加入欄位名稱
			for (int i = 1; i <= cols; i++) {
				model.addColumn(rsmd.getColumnName(i));
			}

			// 加入資料 要先創建每一行
			while (rs.next()) {
				Object[] rows = new Object[cols];
				for (int i = 1; i <= cols; i++) {
					rows[i - 1] = rs.getObject(i);
				}
				model.addRow(rows);

				// 檢查資料
				for (int i = 0; i < model.getRowCount(); i++) {
					for (int j = 0; j < model.getColumnCount(); j++) {
						Object value = model.getValueAt(i, j);
						System.out.println(value);
					}
				}
			}
			join_table.setModel(model);

			stat.close();
			conn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void createRecruitTable() {
		try (Connection conn = DriverManager.getConnection(user.url, user.usernameLogin, user.password)) {
			int courseID = user.courseID;
			String query = String.format("SELECT * FROM `GroupList` WHERE `CourseID` = %s", courseID);
			PreparedStatement stat = conn.prepareStatement(query);
			ResultSet rs = stat.executeQuery(query);
			DefaultTableModel model = new DefaultTableModel() {
				// 指定每一欄的類型
				public Class<?> getColumnClass(int columnIndex) {
					if (columnIndex == 0) {
						return Boolean.class; // 第一欄設為布林類型
					} else {
						return super.getColumnClass(columnIndex);
					}
				}

				// 指定哪些欄位是可以編輯的
				public boolean isCellEditable(int row, int column) {

					return column == 0; // 只有第一欄是可編輯的

				}
			};
			model.addColumn("Select"); // 新增布林欄位
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			int cols = rsmd.getColumnCount();

			// 加入欄位名稱
			for (int i = 1; i <= cols; i++) {
				model.addColumn(rsmd.getColumnName(i));
			}

			// 加入資料 要先創建每一行
			while (rs.next()) {
				Object[] rows = new Object[cols + 1]; // 創建原database資料的欄位數加一
				rows[0] = false; // 預設未選中
				for (int i = 1; i <= cols; i++) {
					rows[i] = rs.getObject(i);// 將每一行添加到row
				}
				model.addRow(rows);
			}
			recruit_table.setModel(model);
			// 在每一行前面加 JCheckBox
			TableColumnModel columnModel = recruit_table.getColumnModel();// 取得負責管理表格的欄位設置
			TableColumn checkBoxColumn = columnModel.getColumn(0);// 取得欄位0 就是整欄的checkBox
			checkBoxColumn.setCellEditor(new DefaultCellEditor(new JCheckBox()));// 設置欄位編輯器是預設的，並初始化
			checkBoxColumn.setCellRenderer(recruit_table.getDefaultRenderer(Boolean.class));

			stat.close();
			conn.close();

			scrollPane_2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

			// Add combo box to each row
			// for (int i = 0; i < recruit_table.getRowCount(); i++) {
			// recruit_table.getColumnModel().getColumn(i).setCellEditor(new
			// DefaultCellEditor(comboBox));
			// }
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
