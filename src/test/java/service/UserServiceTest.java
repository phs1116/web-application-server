package service;

import model.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by hspark on 2018. 3. 30..
 */
public class UserServiceTest {
//	public static final String URL_PATH = "http://localhost:8080/user/create?userId=javajigi&password=password&name=JaeSung";

	@Test
	public void test_addUser(){
		User user = new User();
		user.setName("JaeSung");
		user.setPassword("password");
		user.setUserId("javajigi");
		User actual = UserService.INSTANCE.addUser(user);

		assertEquals(actual.getName(), "JaeSung");
		assertEquals(actual.getPassword(),"password");
		assertEquals(actual.getUserId(),"javajigi");
	}

}