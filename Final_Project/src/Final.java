import javax.swing.*;

public class Final {
	public static void main(String[] args) {
		Login frame = new Login();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// *寄信 */
		JavaMail mail = new JavaMail("111306015@g.nccu.edu.tw", "Test!!!", "測試內容");
		mail.SendMail();

	}
}
