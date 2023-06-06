import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.util.ArrayList;
import java.awt.event.*;

//資料表叫課程id
public class JoinPage extends JFrame{
	private JButton submit;
	private Connection conn;
	private JCheckBox[] checkBoxes;
	private JScrollPane scrollPane; 
	private ArrayList<Integer> checkList, selectedCourseNum;
	private ResultSet result;
	private JTable joinTable;
	
	public JoinPage(ArrayList checkList,Connection conn)throws SQLException{
		this.conn = conn;
		this.checkList = checkList;
		this.setTitle("JoinPage");
		selectedCourseNum = new ArrayList<Integer>();
		createButton();
		createLayout();
		showTable();
		
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0; i<checkBoxes.length; i++) {
					checkBoxes[i]= new JCheckBox();
					final int index = i;
					checkBoxes[index].addItemListener(new ItemListener() {
						public void itemStateChanged(ItemEvent e){
							if(e.getStateChange()==ItemEvent.SELECTED) {
								try {
									if(result.absolute(index+1)) {
										int slctdGrpNum = result.getInt("ID");
										selectedCourseNum.add(slctdGrpNum);//存放組長學號
										//寄送信
										//回到Main page
									}
								}catch(SQLException e1) {
									e1.printStackTrace();
								}
							}
						}
					});
				}
			}
		});
		
	}
	public void createButton() {
		submit = new JButton("submit");
	}
	public void createLayout() {
		
		joinTable = new JTable();
	    scrollPane = new JScrollPane(joinTable);
	    scrollPane.setPreferredSize(new Dimension(500, 200));
	    getContentPane().add(scrollPane, BorderLayout.CENTER);
	    
	    JPanel downPanel = new JPanel();
	    downPanel.add(submit);
	    getContentPane().add(downPanel, BorderLayout.SOUTH);
	}
	public void showTable() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/finalproject","root","n2431836");
			String query = "SELECT * FROM `join_table` ";
			PreparedStatement stat = conn.prepareStatement(query);
	        ResultSet rs = stat.executeQuery();
	        System.out.print("kok");
            DefaultTableModel model = new DefaultTableModel() {
            	
                //指定每一欄的類型
                public Class<?> getColumnClass(int columnIndex) {
                    if (columnIndex == 0) {
                        return Boolean.class; // 第一欄設為布林類型
                    } else {
                        return super.getColumnClass(columnIndex);
                    }
                }

                //指定哪些欄位是可以編輯的
                public boolean isCellEditable(int row, int column) {
                    return column == 0; // 只有第一欄是可編輯的
                }
            };

            model.addColumn("Select"); // 新增布林欄位

            ResultSetMetaData rsmd = (ResultSetMetaData)rs.getMetaData();
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
                    rows[i] = rs.getObject(i);//將每一行添加到row
                }
                model.addRow(rows);
            }

            joinTable.setModel(model);
            
         // 在每一行前面加 JCheckBox
            TableColumnModel columnModel = joinTable.getColumnModel();//取得負責管理表格的欄位設置
            TableColumn checkBoxColumn = columnModel.getColumn(0);//取得欄位0 就是整欄的checkBox
            checkBoxColumn.setCellEditor(new DefaultCellEditor(new JCheckBox()));//設置欄位編輯器是預設的，並初始化
            checkBoxColumn.setCellRenderer(joinTable.getDefaultRenderer(Boolean.class));

            stat.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
	}
}
