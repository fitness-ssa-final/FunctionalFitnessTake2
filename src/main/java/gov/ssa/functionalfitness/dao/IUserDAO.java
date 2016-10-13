package gov.ssa.functionalfitness.dao;

import java.util.List;

import gov.ssa.functionalfitness.entity.User;

public interface IUserDAO {

	List<User> getAllUsers();

	User getUserById(int profileID);

	boolean addUser(User user);

	void updateUser(User user);

	void deleteUser(int profileID);

}
