package com.emrekoca.client;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.AbstractEnvironment;

import com.emrekoca.config.ApplicationConfig;
import com.emrekoca.domain.Action;
import com.emrekoca.domain.Call;
import com.emrekoca.domain.Customer;
import com.emrekoca.services.calls.CallHandlingService;
import com.emrekoca.services.customers.CustomerManagementService;
import com.emrekoca.services.customers.CustomerNotFoundException;
import com.emrekoca.services.diary.DiaryManagementService;

public class SimpleClientJavaConfigApp {
	public static AnnotationConfigApplicationContext container;

	public static void main(String[] args) {
		try {
			System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "production");
			container = new AnnotationConfigApplicationContext(ApplicationConfig.class);
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
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			container.close();
		}
	}
}