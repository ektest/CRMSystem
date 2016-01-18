package com.emrekoca.integrationtests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.emrekoca.domain.Customer;
import com.emrekoca.services.customers.CustomerManagementService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/application.xml")
@Transactional
public class CustomerManagmentIntegrationTest {
	@Autowired
	private CustomerManagementService custoemrService;

	@Test
	public void testCreatingCustomerRecord() {
		Customer newCustomer = new Customer("1212", "Apple", "test@test.com", "1234567890", "hello world");
		custoemrService.newCustomer(newCustomer);
		assertEquals(1, custoemrService.getAllCustomers().size());
	}
}
