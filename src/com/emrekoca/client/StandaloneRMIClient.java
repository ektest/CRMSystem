package com.emrekoca.client;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.emrekoca.domain.Customer;
import com.emrekoca.services.customers.CustomerManagementService;
import com.emrekoca.services.customers.CustomerNotFoundException;

public class StandaloneRMIClient {

	public static void main(String[] args) {
		// Access the remote Customer Management Service
		ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("/RMI-Config/rmi-client.xml");

		CustomerManagementService service = container.getBean(CustomerManagementService.class);

		// This is a NETWORK call!
		List<Customer> allCustomers = service.getAllCustomers();
		for (Customer next : allCustomers) {
			System.out.println(next.getCompanyName() + "; " + next.getCustomerId());
		}

		try {
			Customer fullCustomer = service.getFullCustomerDetail("v1vbrfbvrjthe9phq02rcfcluf");
			System.out.println(fullCustomer.getCalls().size() + " calls");
		} catch (CustomerNotFoundException e) {
			System.out.println("Customer doesn't exisit");
		}

		container.close();
	}

}
