package service;

import db.DataBase;
import dto.UserDTO;
import model.User;

import java.util.Optional;

/**
 * Created by hspark on 2018. 3. 30..
 */
public enum UserService {
	INSTANCE;

	public User addUser(User user) {
		DataBase.addUser(user);
		return DataBase.findUserById(user.getUserId());
	}

	public Optional<User> findById(UserDTO userDTO) {
		User user = DataBase.findUserById(userDTO.getUserId());
		return Optional.ofNullable(user).filter(u -> u.getPassword().equals(userDTO.getPassword()));
	}
}
