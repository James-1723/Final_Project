import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.*;

//資料表叫課程id
public class JoinPage extends JFrame{
	private JButton submit;
	private Connection conn;
	private JCheckBox[] checkBoxes;
	private JTextArea groupArea;
	private JScrollPane scrollPane; 
	private ArrayList<Integer> checkList, selectedCourseNum;
	private ResultSet result;
	
	public JoinPage(ArrayList checkList,Connection conn)throws SQLException{
		this.conn = conn;
		this.checkList = checkList;
		this.setTitle("JoinPage");
		this.setSize(100,300);
		selectedCourseNum = new ArrayList<Integer>();
		createPage(conn);
		createButton();
		createLayout();
		
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
										int slctdGrpNum = result.getInt("leader_ID");
										selectedCourseNum.add(slctdGrpNum);
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
	public void createPage(Connection conn) throws SQLException{
		groupArea = new JTextArea();
		checkList = new ArrayList<Integer>();
		for(int i=0; i<checkList.size(); i++) {
			groupArea.append("group_number\t group_leader\t leader_ID\t current_number\t recruit_number\t say_something\t select\n");
			String query = "SELECT * FROM ?";
			PreparedStatement stat = conn.prepareStatement(query);
			stat.setLong(1,(checkList.get(i)));			
			try {
				Statement stmt = conn.createStatement();
		        Boolean success = stmt.execute(query);
				if(success) {
					result = stmt.getResultSet();
		            StringBuilder sb = new StringBuilder();		
		            checkBoxes = new JCheckBox[result.getRow()];
		            int j=0;
		            while(result.next()) {
		                sb.append(result.getString("group_number")).append("\t")
		                  .append(result.getString("group_leader")).append("\t")
		                  .append(result.getString("leader_ID")).append("\t")
		                  .append(result.getString("current_number")).append("\t")
		                  .append(result.getString("recruit_number")).append("\t")
		                  .append(result.getString("say_something")).append("\t")
		                  .append(checkBoxes[j]);
		                j++;
		            }
		            groupArea.append(sb.toString());
		            groupArea.append("\n\n");
				}
			}catch(SQLException e) {
				e.printStackTrace();		
			}
		}
	}
	
	
	public void createButton() {
		submit = new JButton("submit");
	}
	public void createLayout() {
		
		scrollPane = new JScrollPane(groupArea);
		
		JPanel panel = new JPanel();
		panel.add(scrollPane);
		panel.add(submit);
		add(panel,BorderLayout.CENTER);
	}
}
