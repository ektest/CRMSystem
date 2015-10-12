package com.emrekoca.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.emrekoca.domain.Customer;
import com.emrekoca.services.customers.CustomerManagementService;
import com.emrekoca.services.diary.DiaryManagementService;

public class SimpleClientApp {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");
		CustomerManagementService customerService = container.getBean("customerService", CustomerManagementService.class);
		for(Customer next : customerService.getAllCustomers()){
			System.out.println(next);
		}
		// Another way to wire bean if there is only one configuration of that class in application.xmll
		DiaryManagementService diaryService = container.getBean(DiaryManagementService.class);
		container.close();
	}

}
