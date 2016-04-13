package com.emrekoca.controllers;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.emrekoca.domain.Customer;
import com.emrekoca.services.customers.CustomerManagementService;

@Controller
public class ManageCustomersController {
	// We can't use Autowired due to there is 2 CustomerManagementService bean
	// is available. customerService and customerEndpoint (which implements
	// CustomerManagementService) so we should use @Resource annotation.
	// @Autowired
	@Resource(name = "customerService")
	private CustomerManagementService customers;

	@RequestMapping("/all-customers")
	public ModelAndView displayAllCustomersOnWebPage() {
		List<Customer> allCustomers = customers.getAllCustomers();
		return new ModelAndView("/allCustomersJSPPage.jsp", "customers", allCustomers);
	}
}
