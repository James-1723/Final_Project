import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
//資料庫叫courses
public class MainPage extends JFrame{
	private JLabel searchLabel;
	private JTextField searchField;
	private JTextArea courseData;
	private JScrollPane scrollPane;
	private Connection conn;
	private JButton join, recruit, myStatus, searchButton;
	private JCheckBox[] checkBoxes;
	private ArrayList<Integer> checkList;
	
	public MainPage(Connection conn) {
		this.conn = conn;
		this.setTitle("MainPage");
		this.setSize(100,300);
		createLabel();
		createTextField();
		createPanel();
		
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String search = searchField.getText();
				String query = "SELECT * FROM courses WHERE course_name LIKE ? OR course_id LIKE ? OR teacher LIKE ?";
				try {
					PreparedStatement stat = conn.prepareStatement(query);
					stat.setString(1, "%" + search + "%");
			        stat.setString(2, "%" + search + "%");
			        stat.setString(3, "%" + search + "%");
			        ResultSet result = stat.executeQuery();
			        
			        StringBuilder sb = new StringBuilder();
			        
			        checkBoxes = new JCheckBox[result.getRow()];
			        courseData.append("course_name/t course_id/t");
			        int i =0;
			        while(result.next()) {
			        	checkBoxes[i] = new JCheckBox();
			        	sb.append(result.getString("course_name")).append("\t")
			              .append(result.getString("course_id")).append("\t")
			              .append(result.getString("teacher")).append("\t")
			              .append(result.getString("time")).append("\t")
			              .append(result.getInt("credit")).append("\t")
			              .append(result.getString("place")).append("\t")
			              .append(checkBoxes[i]);
			        	i++;
			        }
		            courseData.append(sb.toString());
		            
		            //存取選到ckeckBox的courseID
		            for(int j=0; j<checkBoxes.length; j++) {
		            	checkBoxes[j] = new JCheckBox(); //每個checkBox初始化
		                final int index = j;
		            	checkBoxes[index].addItemListener(new ItemListener() {
		            		public void itemStateChanged(ItemEvent e) {
		            			if(e.getStateChange()==ItemEvent.SELECTED) {
		            				try {
		            					if(result.absolute(index+1)) {//移動到指定行
			            					int courseID = result.getInt("course_id");//拿到id
			            					checkList.add(courseID);//加入list
			            				}
		            				} catch (SQLException ex) {
		                                ex.printStackTrace();
		                            }
		            				
		            			}else if(e.getStateChange()==ItemEvent.DESELECTED) {
		            				try {
		            					if(result.absolute(index+1)) {//移動到指定行
		            						int courseID = result.getInt("course_id");//拿到id
			            					checkList.remove(courseID);//從list移除
			            				}
		            				} catch (SQLException ex) {
		                                ex.printStackTrace();
		                            }
		            			}
		            		}

		            	});
		            }
//		            //checkBox的監聽 並找到courseName
//		            for(int j=0; j<checkBoxes.length ; j++) {
//		            	JCheckBox checkBox = checkBoxes[j];
//		            	checkBox.addItemListener(new ItemListener() {
//		            		public void itemStateChanged(ItemEvent e) {
//		                        if (e.getStateChange() == ItemEvent.SELECTED) {
//		                        	JCheckBox selectedCheckBox = (JCheckBox) e.getSource();
//		                            int index = Arrays.binarySearch(checkBoxes, selectedCheckBox);
//		                            if (index >= 0 && index < checkBoxes.length) {
//		                            	try {
//		                            		result.absolute(index);//移動到第index行
//		                            		courseName = result.getString("course_name");
//		                            	} catch (SQLException e1) {
//		                            		e1.printStackTrace();
//		                            	}
//		                            }
//		                        }
//		                    }
//		            	});
//		            }

				}catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkList.isEmpty()) {
					JOptionPane.showMessageDialog(null,"Select some courses!","Error",JOptionPane.ERROR_MESSAGE);
				}else {
					try {
						JoinPage join = new JoinPage(checkList,conn);
						join.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						join.setVisible(true);
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
					
				}
			}
		});
		
		recruit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecruitPage recruit = new RecruitPage(checkList,conn);
				recruit.setVisible(true);
				recruit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
		myStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});

	}
	public void createLabel() {
		searchLabel = new JLabel("search");
	}
	public void createButton() {
		searchButton = new JButton("Search");
		join = new JButton("Join");
		recruit = new JButton("Recruit");
		myStatus = new JButton("my status");
	}
	public void createTextField() {
		searchField = new JTextField("Search by course's name, ID, or teacher.",20);
	}
	public void createTextArea() {
        courseData = new JTextArea("課程\t課程編號\t教師\t時間\t學分\t地點\n");
        courseData.setEditable(false);
	}

	public void createPanel() {
		JPanel upPanel = new JPanel();
		upPanel.add(searchLabel);
		upPanel.add(searchField);
		add(upPanel,BorderLayout.NORTH);
		
		
		scrollPane = new JScrollPane(courseData);
		add(scrollPane,BorderLayout.CENTER);
		
		JPanel downPanel = new JPanel();
		downPanel.add(join);
		downPanel.add(recruit);
		downPanel.add(myStatus);
		add(downPanel,BorderLayout.SOUTH);
	}
}
