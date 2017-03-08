package twpvsystem.tongwei.com.twpvsystem.bean;

public class User {

	public String custId ;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String custId) {
		this.custId = custId;
	}

	@Override
	public String toString()
	{
//		return "User{" +
//				"username='" + username + '\'' +
//				", password='" + password + '\'' +
//				'}';
		return "User{" +
				"custId='" + custId + '\'' +
				'}';
	}
}
