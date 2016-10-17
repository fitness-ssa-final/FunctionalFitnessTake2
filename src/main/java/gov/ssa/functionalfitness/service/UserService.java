package gov.ssa.functionalfitness.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gov.ssa.functionalfitness.dao.IUserDAO;
import gov.ssa.functionalfitness.entity.User;

@Service
@Transactional
public class UserService implements IUserService {

	@Autowired
	private IUserDAO userDAO;

	@Override
	public User getUserById(int profileID) {
		User obj = userDAO.getUserById(profileID);
		return obj;
	}

	@Override
	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}

	@Override
	public synchronized boolean addUser(User user) {
		userDAO.addUser(user);
		return true;
	}

	@Override
	public void updateUser(User user) {
		userDAO.updateUser(user);
	}

	@Override
	public void deleteUser(int profileID) {
		userDAO.deleteUser(profileID);
	}

	@Override
	public List<User> login(String password, String email) {
		return userDAO.login(password, email);
	}

}
