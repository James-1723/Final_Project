import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import java.util.ArrayList;
import java.awt.event.*;

public class JoinPage extends JFrame {

	private JButton submit, back;
	private JCheckBox[] checkBoxes;
	private JScrollPane scrollPane;
	private ArrayList<Integer> courseIDs, mails, selectedGroupIDs;
	private ResultSet result;
	private JTable joinTable;
	private User user = new User();
	private MainPage main = new MainPage();

	public JoinPage(ArrayList<Integer> selectedIDs, User users) throws SQLException {

		this.user = users;
		createButton();
		createLayout();
		this.courseIDs = new ArrayList<>(selectedIDs);
		showTable();
		this.setTitle("JoinPage");
		result = null;
		this.checkBoxes = new JCheckBox[selectedIDs.size()];
		System.out.println(checkBoxes.length);

		submit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {

					int columnCount = joinTable.getRowCount();
					selectedGroupIDs = new ArrayList<Integer>();

					for (int i = 0; i < columnCount; i++) {

						Boolean selected = (Boolean) joinTable.getValueAt(i, 0); // 第i行第0列 就是checkBox
						if (selected) {

							// *Add */
							int selectedGroupID = (int) joinTable.getValueAt(i, 2);// 第i行第2列 就是groupID
							selectedGroupIDs.add(selectedGroupID);// 加入一個list

							String leaderName = (String) joinTable.getValueAt(i, 4);// 第i行第3列 就是leaderName
							user.leaderName = leaderName;

							// *Group ID */

							try {

								String query = String.format("SELECT GroupID FROM GroupList");
								user.result = user.stat.executeQuery(query);
								query = String.format("SELECT GroupID FROM GroupList WHERE CourseID=%d", user.courseID);
								ResultSet r = user.stat.executeQuery(query);

								while (r.next()) {

									user.groupID = Integer.parseInt(r.getString("GroupID"));

								}

								System.out.println("user's GroupID = " + user.groupID);

								query = "INSERT INTO `Total_Register_List` (CourseID, GroupID, LeaderName, StudentName, Department, StuID) VALUES"
										+ String.format("(%d, %d, '%s', '%s', '%s', '%s')", user.courseID, user.groupID,
												leaderName, user.userName, user.userDep, user.userAccount);
								user.stat.execute(query);

								JOptionPane.showMessageDialog(null,
										"You will be informed with a letter after the group leader adds you to his/her group!",
										"Success Applied", JOptionPane.INFORMATION_MESSAGE);

							} catch (Exception ae) {

								ae.printStackTrace();

							}

						}
					}

					main.setAccount(user);
					main.setDefaultCloseOperation(EXIT_ON_CLOSE);
					main.setVisible(true);
					main.setSize(600, 500);
					JoinPage.this.dispose();

				} catch (Exception x) {

					x.printStackTrace();

				}

			}
		});
		back.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {


				main.setAccount(user);
				main.setDefaultCloseOperation(EXIT_ON_CLOSE);
				main.setVisible(true);
				main.setSize(600, 500);
				JoinPage.this.dispose();

			}
		});

	}

	public void createButton() {

		submit = new JButton("submit");
		back = new JButton("back");

	}

	public void createLayout() {

		joinTable = new JTable();
		scrollPane = new JScrollPane(joinTable);
		scrollPane.setPreferredSize(new Dimension(500, 200));
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		JPanel downPanel = new JPanel();
		downPanel.setBackground(new Color(214, 218, 212));
		downPanel.add(submit);
		downPanel.add(back);
		getContentPane().add(downPanel, BorderLayout.SOUTH);

	}

	public void showTable() {

		try (Connection conn = DriverManager.getConnection(user.url, user.usernameLogin, user.password)) {

			StringBuilder sb = new StringBuilder();

			for (Integer courseID : courseIDs) {

				if (courseID != null) {

					sb.append(courseID);
					sb.append(",");

				}
			}

			String courseIDString = sb.toString(); // 把 courseID 變字串

			if (!courseIDString.isEmpty()) {

				courseIDString = courseIDString.substring(0, courseIDString.length() - 1); // 移除最后一个逗號

			}

			String query = "SELECT * FROM `GroupList` WHERE `CourseID` IN (" + courseIDString + ")";
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
			joinTable.setModel(model);

			// 在每一行前面加 JCheckBox
			TableColumnModel columnModel = joinTable.getColumnModel();// 取得負責管理表格的欄位設置
			TableColumn checkBoxColumn = columnModel.getColumn(0);// 取得欄位0 就是整欄的checkBox
			checkBoxColumn.setCellEditor(new DefaultCellEditor(new JCheckBox()));// 設置欄位編輯器是預設的，並初始化
			checkBoxColumn.setCellRenderer(joinTable.getDefaultRenderer(Boolean.class));

			rs.close();
			stat.close();

		} catch (SQLException e1) {

			e1.printStackTrace();
			
		}
	}
}
