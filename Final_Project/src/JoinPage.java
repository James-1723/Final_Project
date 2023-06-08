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
	private ArrayList<Integer> checkList, selectedCourseNum;
	private ResultSet result;
	private JTable joinTable;
	private User user = new User();
	private MainPage main = new MainPage();

	public JoinPage(ArrayList checkList) throws SQLException {
		createButton();
		createLayout();
		showTable();
		this.checkList = checkList;
		this.setTitle("JoinPage");
		selectedCourseNum = new ArrayList<Integer>();

		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < checkBoxes.length; i++) {
					checkBoxes[i] = new JCheckBox();
					final int index = i;
					checkBoxes[index].addItemListener(new ItemListener() {
						public void itemStateChanged(ItemEvent e) {
							if (e.getStateChange() == ItemEvent.SELECTED) {
								try {
									if (result.absolute(index + 1)) {
										int slctdGrpNum = result.getInt("ID");
										selectedCourseNum.add(slctdGrpNum);// 存放組長學號
										// 寄送信
										// 回到Main page
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
		downPanel.add(submit);
		downPanel.add(back);
		getContentPane().add(downPanel, BorderLayout.SOUTH);
	}

	public void showTable() {

		try (Connection conn = DriverManager.getConnection(user.url, user.usernameLogin, user.password)) {
			String query = "SELECT * FROM `GroupList`";
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
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
