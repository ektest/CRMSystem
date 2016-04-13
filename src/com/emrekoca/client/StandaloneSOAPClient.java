package com.emrekoca.client;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.emrekoca.domain.Action;
import com.emrekoca.domain.Call;
import com.emrekoca.domain.Customer;
import com.emrekoca.services.calls.CallHandlingService;
import com.emrekoca.services.customers.CustomerManagementService;
import com.emrekoca.services.customers.CustomerNotFoundException;
import com.emrekoca.services.diary.DiaryManagementService;

public class StandaloneSOAPClient {

	public static void main(String[] args) {
		// Access the remote Customer Management Service
		ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("rmi-client.xml");

		try {
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

			// Second part!
			CallHandlingService callService = container.getBean(CallHandlingService.class);
			CustomerManagementService customerService = container.getBean(CustomerManagementService.class);
			String customerID = new BigInteger(130, new SecureRandom()).toString(32);
			customerService
					.newCustomer(new Customer(customerID, "Apple", "test@test.com", "1234567890", "hello world"));
			Action action1 = new Action("Call back me!", new GregorianCalendar(2016, 0, 0), customerID);
			Action action2 = new Action("I hate you!", new GregorianCalendar(2018, 0, 0), customerID);
			List<Action> list = new ArrayList<Action>();
			list.add(action1);
			list.add(action2);
			try {
				callService.recordCall(customerID, new Call("Test testing"), list);
			} catch (CustomerNotFoundException e) {
				System.out.println("Customer not found!");
				// e.printStackTrace();
			}
			DiaryManagementService diaryService = container.getBean(DiaryManagementService.class);
			for (Action action : diaryService.getAllIncompleteActions(customerID)) {
				System.out.println(action);
			}
		} finally {
			container.close();
		}
	}

}
