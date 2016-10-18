package gov.ssa.functionalfitness.service;

import java.util.List;

import gov.ssa.functionalfitness.entity.User;

public interface IUserService {
	List<User> getAllUsers();

	User getUserById(int profile_ID);

	boolean addUser(User user);

	void updateUser(User user);

	void deleteUser(int profile_ID);

	List<User> login(String password, String username);
}
