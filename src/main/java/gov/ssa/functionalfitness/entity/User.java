package gov.ssa.functionalfitness.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//Making a user object.

@Entity
@Table(name = "user_profile")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_profile_id")
	private int profileID;

	@Column(name = "user_username")
	private String username;

	@Column(name = "user_password")
	private String password;

	@Column(name = "user_first_name")
	private String firstName;

	@Column(name = "user_last_name")
	private String lastName;

	@Column(name = "user_email")
	private String email;

	@Column(name = "user_equipment")
	private String equipment;

	@Column(name = "user_age")
	private int age;

	@Column(name = "user_weight")
	private double weight;

	@Column(name = "user_sex")
	private String sex;

	public int getProfileID() {
		return profileID;
	}

	public void setProfileID(int profileID) {
		this.profileID = profileID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "User [profileID=" + profileID + ", username=" + username + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", equipment=" + equipment + ", age="
				+ age + ", weight=" + weight + ", sex=" + sex + "]";
	}

}
