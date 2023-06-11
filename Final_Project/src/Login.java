
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.*;
import java.sql.*;

//111306017
//9ftmc
public class Login extends JFrame {

	final private Font mainFont = new Font("Segoe print", Font.BOLD, 16);
	private MainPage main;
	private JButton enroll, login;
	private JComboBox<String> jComboBox;
	public String server = "jdbc:mysql://140.119.19.73:3315/";
	public String database = "111306017"; // change to your own database
	public String url = server + database + "?useSSL=false";
	public String username = "111306017"; // change to your own user name
	public String DBpassword = "9ftmc"; // change to your own password

	private JTextField textField;// ac
	private JTextField textField_1;// pa
	private JTextField textField_2;// st

	public Login() {
		User user = new User();

		createLayout();
		setSize(600, 400);
		setTitle("Login");

		enroll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String ac = textField.getText();
				String pw = textField_1.getText();
				String stuName = textField_2.getText();
				String dep = (String) jComboBox.getSelectedItem();

				try {

					user.add(stuName, dep, ac, pw);

				} catch (AccountError e1) {

					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

				}
			}
		});

		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.print("user");
				String ac = textField.getText();
				String pw = textField_1.getText();

				try {

					// user.stat = user.conn.createStatement();
					user.checkAccountExist(ac);
					user.checkPassword(ac, pw);
					main = new MainPage();
					main.setDefaultCloseOperation(EXIT_ON_CLOSE);
					// main.setAccount(user); //*User ID to main page*/
					main.setVisible(true);
					main.setSize(600, 500);

				} catch (AccountError | PasswordError e1) {

					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

				} catch (SQLException k) {

					k.getMessage();

				}
			}
		});
	}

	public void createLayout() {
		getContentPane().setBackground(new Color(214, 218, 212));

		JLabel lblNewLabel = new JLabel("Account");
		textField = new JTextField();
		textField.setColumns(10);
		JLabel lblNewLabel_1 = new JLabel("@nccu.edu.tw");

		JLabel lblNewLabel_2 = new JLabel("Password");
		textField_1 = new JTextField();
		textField_1.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Department");
		JLabel lblNewLabel_4 = new JLabel("StudentName");

		String[] optionsToChoose = { "中文系", "歷史系", "哲學系", "教育系", "政治系", "社會系", "財政系", "公行系", "地政系", "經濟系", "民族系", "外交系",
				"國貿系", "金融系", "會計系", "統計系", "企管系", "資管系", "財管系", "風管系", "新聞系", "廣告系", "廣電系", "傳播學程", "英文系" };
		jComboBox = new JComboBox<>(optionsToChoose);

		textField_2 = new JTextField();
		textField_2.setColumns(10);

		login = new JButton("login");
		login.setForeground(UIManager.getColor("Button.highlight"));
		login.setBackground(new Color(76, 77, 76));
		login.setContentAreaFilled(false);
		login.setBorderPainted(false);
		login.setOpaque(true);
		login.setFont(mainFont);

		enroll = new JButton("enroll");
		enroll.setForeground(UIManager.getColor("Button.highlight"));
		enroll.setBackground(new Color(78, 79, 77));
		enroll.setContentAreaFilled(false);
		enroll.setBorderPainted(false);
		enroll.setOpaque(true);
		enroll.setFont(mainFont);

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGap(108)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(lblNewLabel_3)
												.addGap(18)
												.addComponent(jComboBox, 0, 284, Short.MAX_VALUE))
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblNewLabel_2)
												.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
												.addComponent(textField_1, 297, 297, 297))
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 63,
														GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(textField, GroupLayout.PREFERRED_SIZE, 190,
														GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(lblNewLabel_1))
										.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addGroup(groupLayout.createSequentialGroup()
																.addComponent(lblNewLabel_4)
																.addGap(18)
																.addComponent(textField_2, GroupLayout.DEFAULT_SIZE,
																		274, Short.MAX_VALUE))
														.addGroup(Alignment.TRAILING, groupLayout
																.createSequentialGroup()
																.addComponent(enroll, GroupLayout.PREFERRED_SIZE, 184,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(18)
																.addComponent(login, GroupLayout.DEFAULT_SIZE, 174,
																		Short.MAX_VALUE)))))
								.addContainerGap(116, GroupLayout.PREFERRED_SIZE)));
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGap(74)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNewLabel_1)
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel))
								.addGap(27)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_2))
								.addGap(29)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(jComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_3))
								.addGap(26)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNewLabel_4)
										.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(enroll)
										.addComponent(login))
								.addContainerGap(74, Short.MAX_VALUE)));
		getContentPane().setLayout(groupLayout);
	}
}