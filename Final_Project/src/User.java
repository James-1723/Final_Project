import java.util.ArrayList;
import java.sql.*;

public class User {
	private ArrayList<String> accounts;
	private ArrayList<String> passwords;

	public User() {
		accounts = new ArrayList<String>();
		passwords = new ArrayList<String>();

		//*Setting Data Base */
		String server = "jdbc:mysql://140.119.19.73:3315/";
		String database = "111306017"; // change to your own database
		String url = server + database + "?useSSL=false";
		String username = "111306017"; // change to your own user name
		String password = "9ftmc"; // change to your own password
	}

	public void add(String account, String pw) throws AccountError {
		if (account.length() == 0)throw new AccountError("AccountError:Account can't be empty");
		
		accounts.add(account);
		passwords.add(pw);
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
