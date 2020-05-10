package com.crud.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.crud.entity.Customer;
import com.crud.entity.CustomerService;

@Controller
public class HelloController {
	
	@Autowired
    private CustomerService customerService;
	
	@RequestMapping("/")
	public ModelAndView home() {
		List<Customer> listCustomer = customerService.listAll();
	    ModelAndView mav = new ModelAndView("index");
	    mav.addObject("listCustomer", listCustomer);
	    return mav;

	}
	
	@RequestMapping("/new")
	public String newCustomerForm(Map<String, Object> model) {
		System.out.println("In new controller");
	    Customer customer = new Customer();
	    model.put("customer", customer);
	    return "new_customer";
	}
	
	@RequestMapping("/save")
	public String saveCustomer(@ModelAttribute Customer customer) {
		customerService.save(customer);
		return "redirect:/";
	}
	
	@RequestMapping("/edit")
	public ModelAndView editCustomer(@ModelAttribute Customer customer) {
		ModelAndView mv = new ModelAndView("edit_customer");
		mv.addObject("customer", customerService.get(customer.getId()));
		return mv;
	}
	
	@RequestMapping("/delete")
	public String delCustomer(@ModelAttribute Customer customer) {
		customerService.delete(customer.getId());
		return "redirect:/";
	}
	
	@RequestMapping("/search")
	public ModelAndView searchCustomer(@RequestParam String keyword) {
		ModelAndView mv = new ModelAndView("search");
		
		List<Customer> foundCustomers = customerService.search(keyword);
		mv.addObject("result", foundCustomers);
		
		return mv;
	}

}
