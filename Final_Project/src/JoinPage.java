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
	private ArrayList<Integer> courseIDs, mails;
	private ResultSet result;
	private JTable joinTable;
	private User user = new User();
	private MainPage main = new MainPage();

	public JoinPage(ArrayList<Integer> selectedIDs) throws SQLException {
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
				for (int i = 0; i < checkBoxes.length; i++) {
					checkBoxes[i] = new JCheckBox();
					final int index = i;

					checkBoxes[index].addItemListener(new ItemListener(){// 選出被選到的course
						public void itemStateChanged(ItemEvent e) {// itemStateChanged checkBoxes[i]狀態改變
							System.out.println("this");
							if (e.getStateChange() == ItemEvent.SELECTED){
								System.out.print("mails");
								try {
									if (result.absolute(index + 4)) {
										int mail = result.getInt("Leader_ID");
										mails.add(mail);
										System.out.print(mails);
										StringBuilder sb = new StringBuilder();
										for (Integer oneMail : mails) {
											if (oneMail != null) {
												sb.append(oneMail);
												sb.append(",");
											}
										}
									}									
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
							}
						}
					});
				}
			}
		});
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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

			String query = "SELECT * FROM `GroupList` WHERE `GroupID` IN (" + courseIDString + ")";
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
