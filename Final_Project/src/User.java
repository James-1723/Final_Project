import java.util.ArrayList;

public class User {
	private ArrayList<String> accounts;
	private ArrayList<String> passwords;

	public User() {
		accounts = new ArrayList<String>();
		passwords = new ArrayList<String>();
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
