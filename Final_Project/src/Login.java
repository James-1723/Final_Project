import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*; 
import javax.swing.*; 
import java.awt.*;


public class Login extends JFrame{
	
	private MainPage main;
	private JTextField account, password;
	private JButton enroll, login;
	
	public Login(){
		User user = new User();
		account = new JTextField(10);
		password = new JTextField(10);
		enroll = new JButton("enroll");
		login = new JButton("login");
		createLayout();
		setSize(600,300);
		setTitle("Login");
		
		enroll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String ac = account.getText();
				String pw = password.getText();
				try {
					user.add(ac,pw);
				}catch(AccountError e1) {
		            JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		
		
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ac = account.getText();
				String pw = password.getText();
				
				try {
					user.checkAccountExist(ac);
					user.checkPassword(ac, pw);
					main = new MainPage();
					main.setDefaultCloseOperation(EXIT_ON_CLOSE);
					main.setVisible(true);
					main.setSize(600,500);
				}catch(AccountError|PasswordError e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	
	public void createLayout() {
		
		JPanel upanel = new JPanel();
		upanel.add(new JLabel("Account"));
		upanel.add(account);
		upanel.add(new JLabel("@nccu.edu.tw"));
		upanel.setPreferredSize(new Dimension(500,40));
		
		JPanel ppanel = new JPanel();
		ppanel.add(new JLabel("Password"));
		ppanel.add(password);
		ppanel.setPreferredSize(new Dimension(500,40));
		
		JPanel bpanel = new JPanel();
		bpanel.add(enroll);
		bpanel.add(login);
		bpanel.setPreferredSize(new Dimension(500,40));
		
		JPanel allPanel = new JPanel();
		allPanel.add(upanel);
		allPanel.add(ppanel);
		allPanel.add(bpanel);
		
		setLayout(new BorderLayout(20,40));
		getContentPane().add(new JPanel(),BorderLayout.NORTH);
		getContentPane().add(allPanel,BorderLayout.CENTER);
		

	}
}


