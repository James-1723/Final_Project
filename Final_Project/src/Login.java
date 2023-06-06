import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

//111306017
//9ftmc
public class Login extends JFrame {

	private MainPage main;
	private JTextField account, password, studentName;
	private JButton enroll, login;
	private JComboBox<String> jComboBox;
	public String server = "jdbc:mysql://140.119.19.73:3315/";
	public String database = "111306017"; // change to your own database
	public String url = server + database + "?useSSL=false";
	public String username = "111306017"; // change to your own user name
	public String DBpassword = "9ftmc"; // change to your own password
	
	
	public Login() {
		account = new JTextField(10);
		password = new JTextField(10);
		studentName = new JTextField(10);
		enroll = new JButton("enroll");
		login = new JButton("login");
		User user = new User();

		createLayout();
		setSize(600, 400);
		setTitle("Login");

		enroll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String ac = account.getText();
				String pw = password.getText();
				String stuName = studentName.getText();
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

				String ac = account.getText();
				String pw = password.getText();

				try {

					user.stat = user.conn.createStatement();
					user.checkAccountExist(ac, user.stat);
					user.checkPassword(ac, pw);
					main = new MainPage(); 
					main.setDefaultCloseOperation(EXIT_ON_CLOSE);
					main.setAccount(user); //*User ID to main page*/
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

		String[] optionsToChoose = { "中文系", "歷史系", "哲學系", "教育系", "政治系", "社會系", "財政系", "公行系", "地政系", "經濟系", "民族系", "外交系",
				"國貿系", "金融系", "會計系",
				"統計系", "企管系", "資管系", "財管系", "風管系", "新聞系", "廣告系", "廣電系", "傳播學程", "英文系" };
		jComboBox = new JComboBox<>(optionsToChoose);
		jComboBox.setBounds(80, 50, 140, 20);

		JPanel upanel = new JPanel();
		upanel.add(new JLabel("Account"));
		upanel.add(account);
		upanel.add(new JLabel("@nccu.edu.tw"));
		upanel.setPreferredSize(new Dimension(500, 40));

		JPanel ppanel = new JPanel();
		ppanel.add(new JLabel("Password"));
		ppanel.add(password);
		ppanel.setPreferredSize(new Dimension(500, 40));

		JPanel dePanel = new JPanel();
		dePanel.add(new JLabel("Department"));
		dePanel.add(jComboBox);
		dePanel.setPreferredSize(new Dimension(500, 40));

		JPanel stPanel = new JPanel();
		stPanel.add(new JLabel("StudentName"));
		stPanel.add(studentName);
		stPanel.setPreferredSize(new Dimension(500, 40));

		JPanel bpanel = new JPanel();
		bpanel.add(enroll);
		bpanel.add(login);
		bpanel.setPreferredSize(new Dimension(500, 40));

		JPanel allPanel = new JPanel();
		allPanel.add(upanel);
		allPanel.add(ppanel);
		allPanel.add(dePanel);
		allPanel.add(stPanel);
		allPanel.add(bpanel);

		setLayout(new BorderLayout(20, 40));
		getContentPane().add(new JPanel(), BorderLayout.NORTH);
		getContentPane().add(allPanel, BorderLayout.CENTER);

	}
}
