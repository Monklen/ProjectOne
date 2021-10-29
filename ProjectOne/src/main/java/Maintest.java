import java.util.List;

import com.projectone.dao.UserDao;
import com.projectone.models.User;

public class Maintest {

	public static void main(String[] args) {
		UserDao uDao = new UserDao();
		
		List<User> uList = uDao.getAllUsers();
		
		System.out.println(uList);
		
		System.out.println(uDao.getUserByUsername("bokostar"));

	}

}
