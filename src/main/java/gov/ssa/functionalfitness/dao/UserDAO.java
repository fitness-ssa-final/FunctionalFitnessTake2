package gov.ssa.functionalfitness.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import gov.ssa.functionalfitness.entity.User;

@Repository
public class UserDAO implements IUserDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public User getUserById(int profileID) {
		return hibernateTemplate.get(User.class, profileID);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		String hql = "FROM User as u ORDER BY u.profileID";
		return (List<User>) hibernateTemplate.find(hql);
	}

	@Override
	public boolean addUser(User user) {
		hibernateTemplate.save(user);
		return false;
	}

	@Override
	public void updateUser(User user) {
		User record = getUserById(user.getProfileID());

		record.setUsername(user.getUsername());
		record.setPassword(user.getPassword());
		record.setFirstName(user.getFirstName());
		record.setLastName(user.getLastName());
		record.setEmail(user.getEmail());
		record.setEquipment(user.getEquipment());
		record.setAge(user.getAge());
		record.setWeight(user.getWeight());
		record.setSex(user.getSex());

		hibernateTemplate.update(record);
	}

	@Override
	public void deleteUser(int profileID) {
		hibernateTemplate.delete(getUserById(profileID));
	}
}
