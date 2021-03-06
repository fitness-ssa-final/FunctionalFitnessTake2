package gov.ssa.functionalfitness.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import gov.ssa.functionalfitness.entity.User;
import gov.ssa.functionalfitness.service.IUserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	//Bringing in User Services
	@Autowired
	private IUserService userService;
	
	//Where the site will go when path is entered. Most will be blocked due to spring security.
	@RequestMapping("")
	public ModelAndView home(ModelAndView mv) {
		mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping("/login_signup")
	public ModelAndView welcome(ModelAndView mv) {
		mv.setViewName("login_signup");
		return mv;
	}

	@RequestMapping("/exercises")
	public ModelAndView exercises(ModelAndView mv) {
		mv.setViewName("exercises");
		return mv;
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
		User user = userService.getUserById(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/adminuser", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String addUser(@ModelAttribute User user, UriComponentsBuilder builder) {
		boolean flag = userService.addUser(user);
//		if (flag == false) {
//			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/user/{id}").buildAndExpand(user.getProfileID()).toUri());
		return "login_signup";
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		userService.updateUser(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> User(@PathVariable("id") int profileID) {
		userService.deleteUser(profileID);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	//User auth
	@RequestMapping(value = "/login_signup", method = RequestMethod.POST)
	public ResponseEntity<List<User>> userLogin(@RequestBody User user, HttpSession sessionObj){
		List<User> account = userService.login(user.getPassword(), user.getUsername());
	
		
		String isValid = user.getPassword();
		
		if(!(account.get(0).getPassword().equals(isValid))) {
			return new ResponseEntity<List<User>>(HttpStatus.CONFLICT);
		}else{
			sessionObj.setAttribute("user", account.get(0));
			return new ResponseEntity<List<User>>(HttpStatus.OK);
		}
		
	}		
}