package com.emrekoca.integrationtests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.emrekoca.config.ApplicationConfig;
import com.emrekoca.domain.Action;
import com.emrekoca.domain.Call;
import com.emrekoca.domain.Customer;
import com.emrekoca.services.calls.CallHandlingService;
import com.emrekoca.services.customers.CustomerManagementService;
import com.emrekoca.services.customers.CustomerNotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
/* For xml configuration */
//@ContextConfiguration({ "/daos.xml", "/services.xml", "/misc-beans.xml", "/datasource-test.xml" })
/* For JavaConfig configurations */
@ContextConfiguration( classes = { ApplicationConfig.class } )
@Transactional
@ActiveProfiles("integration")
public class CustomerManagmentIntegrationTest {
	@Autowired
	private CustomerManagementService custoemrService;
	@Autowired
	private CallHandlingService callHandlingService;

	@Test
	public void testCreatingCustomerRecord() {
		Customer newCustomer = new Customer("1212", "Apple", "test@test.com", "1234567890", "hello world");
		custoemrService.newCustomer(newCustomer);
		assertEquals(1, custoemrService.getAllCustomers().size());
	}

	@Test
	public void testFindCustomer() {
		Customer newCustomer = new Customer("1212", "Apple", "test@test.com", "1234567890", "hello world");
		custoemrService.newCustomer(newCustomer);
		try {
			Customer found = custoemrService.findCustomerById(newCustomer.getCustomerId());
			assertEquals(newCustomer, found);
		} catch (CustomerNotFoundException e) {
			fail("Customer not found!");
		}
	}

	@Test
	public void testAddingACallToACustomer() {
		Customer newCustomer = new Customer("1212", "Apple", "test@test.com", "1234567890", "hello world");
		custoemrService.newCustomer(newCustomer);
		Call newCall = new Call("This is a JUnit test case");
		List<Action> actions = new ArrayList<Action>();
		try {
			callHandlingService.recordCall(newCustomer.getCustomerId(), newCall, actions);
			Customer foundCustomer = custoemrService.findCustomerById(newCustomer.getCustomerId());
			assertEquals(newCall, foundCustomer.getCalls().get(0));
		} catch (CustomerNotFoundException ex) {
			fail("Customer not found!");
		}
	}
}