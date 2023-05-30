import java.util.ArrayList;
import java.sql.*;

public class User {
	private ArrayList<String> accounts;
	private ArrayList<String> passwords;
	private String studentName;

	//*Setting Data Base */
	public String server = "jdbc:mysql://140.119.19.73:3315/";
	public String database = "111306017"; // change to your own database
	public String url = server + database + "?useSSL=false";
	public String username = "111306017"; // change to your own user name
	public String password = "9ftmc"; // change to your own password　　

	public User() {

		accounts = new ArrayList<String>();
		passwords = new ArrayList<String>();
	
	}

	public void add(String studentName, int department, String account, String pw) throws AccountError {

		//*Throw exception if password format is wrong */
		if (account.length() == 0)throw new AccountError("AccountError:Account can't be empty");
		accounts.add(account);
		passwords.add(pw);
		
		//*Try whether system connect to DB or not */
		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			Statement stat = conn.createStatement();
			String query;
			boolean success;
			int intAccount = Integer.parseInt(account);

			query = "SELECT * FROM `Student_Info`";
			success = stat.execute(query);

			String gmail = account + "@gmail.com";

			if (success) {
                
				query = "INSERT INTO `Student_Info` (Name, ID, Department, Gmail) VALUES" + String.format("('%s', %d, %d, '%s')", studentName, intAccount, department, gmail);
            	success = stat.execute(query);

			}

		} catch (Exception e) {
		
			e.printStackTrace();

		}
	}

	public void checkAccountExist(String account) throws AccountError {
		if (accounts.contains(account))return;//arrayList可以直接用contains
		throw new AccountError("UserError:Can't find the user");
	}

	public void checkPassword(String account, String PW) throws PasswordError {
		int id = accounts.indexOf(account);//arrayList可以用indexOf用關鍵字找index
		if (passwords.get(id).equals(PW))
			return;
		
		throw new PasswordError("PasswordError:Password is wrong");
	}
}

class AccountError extends Exception {
	public AccountError(String Error) {
		super(Error);
	}
}

class PasswordError extends Exception {
	public PasswordError(String Error) {
		super(Error);
	}
}
