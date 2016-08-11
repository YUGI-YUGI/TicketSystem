package com.test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.entity.Commant;
import com.test.entity.Customer;
import com.test.entity.Ticket;
import com.test.entity.User;
import com.test.repo.CommantRepo;
import com.test.repo.CustomerRepo;
import com.test.repo.TicketRepo;
import com.test.repo.UserRepository;

import scala.annotation.meta.setter;

@Controller
public class TicketController {

	@Autowired
	private UserRepository repository;

	@Autowired
	private TicketRepo ticketRepo;

	@Autowired
	private CommantRepo commantRepo;

	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	HttpServletRequest request;

	@RequestMapping("/")
	public String home2(ModelMap model) {
		User user=(User) request.getSession().getAttribute("user");
		if(user != null && user.getId()>0) return "homepage";
		return "loginpage";
	}
	@RequestMapping("/home")
	public String login( @ModelAttribute User user) {
		System.out.println(user.getId());
		user=repository.findOne(user.getId());
		if(user != null && user.getId()>0){
			request.getSession().setMaxInactiveInterval(300000);
			request.getSession().setAttribute("user", user);
			return "homepage";
		} else{
			 user=(User) request.getSession().getAttribute("user");
			if(user != null ) {
				return "homepage";
			}
		}return "loginpage";
	
	}
	@RequestMapping("/logout")
	public String logout( @ModelAttribute User user) {
		if(user == null || user.getId()>0) return "loginpage";
		request.getSession().removeAttribute("user");
		return "loginpage";
	}

	@RequestMapping(value = "/getdata", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, List<?>> getData(){
		HashMap<String, List<?>> map=new HashMap<String, List<?>>();
		List<Ticket> ticket = ticketRepo.findAll();
		for (int i = 0; i < ticket.size(); i++) {
			ticket.get(0).setComments(commantRepo.findByTId(ticket.get(i)));
		}
		List<User> users = repository.findAll();
		map.put("tics", ticket);
		map.put("users", users);
		return map;
		
	}

	@RequestMapping(value = "/adduser", method = RequestMethod.GET)
	public @ResponseBody User adduser(ModelMap model, @ModelAttribute User user) {
		System.out.println(user);
		if (user != null) {
			System.out.println(user.getName());
		}
		user = repository.save(user);
		// model.put("user", user);
		return user;
	}

	@RequestMapping(value = "/addComment", method = RequestMethod.GET)
	public @ResponseBody Commant addComment(@ModelAttribute Commant commant) {
		if (commant != null) {
			System.out.println(commant.getComment());
		}
		commant.setCommentAt(new Date());
		commant = commantRepo.save(commant);
		return commant;
	}

	@RequestMapping(value = "/saveTicket", method = RequestMethod.POST)
	public @ResponseBody Ticket addTicket(@ModelAttribute Customer customer,@ModelAttribute Ticket ticket,@ModelAttribute("assignTO") long id ) {
		System.out.println(customer.toString());
		customer = customerRepo.save(customer);
		if(id>0){
			User u=repository.findOne(id);
			ticket.setAssignTO(u);
		}
		 
		ticket.setCustomer(customer);
		
		ticket.setCreatedAt(new Date());
		ticket = ticketRepo.save(ticket);
		return ticket;
	}

	@RequestMapping(value = "/getAllticket", method = RequestMethod.GET)
	public @ResponseBody List<Ticket> getTicket() {
		List<Ticket> ticket = ticketRepo.findAll();
		return ticket;
	}

	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public @ResponseBody List<User> getUser() {
		List<User> users = repository.findAll();
		return users;
	}
	
	@RequestMapping(value = "/deleteticket/{tid}", method = RequestMethod.GET)
	public @ResponseBody String deleteUser(@RequestParam("tid") long tid) {
		ticketRepo.delete(tid);
		return "done";
	}

	@RequestMapping(value = "/updatetick/status", method = RequestMethod.GET)
	public @ResponseBody String updateStatus(@ModelAttribute("tid") long tid,@ModelAttribute("status") String status) {
		Ticket t= ticketRepo.findOne(tid);
		if(t== null) return "{\"error\":\"Ticket not found\"}";
		t.setStatus(status);
		ticketRepo.save(t);
		return "{\"success\":\"Ticket  updated\"}";
	}

	@RequestMapping(value = "/updatetick/assign", method = RequestMethod.GET)
	public @ResponseBody String updateAssignTo(@ModelAttribute("tid") long tid,@ModelAttribute("assignTo") long assignTo) {
	System.out.println(tid);
	System.out.println(assignTo);
		Ticket t= ticketRepo.findOne(tid);
		if(t== null) return "{\"error\":\"Ticket not found\"}";
		User u=repository.getOne(assignTo);
		
		if(u== null) return "{\"error\":\"User not found\"}";
		t.setAssignTO(u);
		
		ticketRepo.save(t);
		return "{\"success\":\"Ticket  updated\"}";
	}
	

}
