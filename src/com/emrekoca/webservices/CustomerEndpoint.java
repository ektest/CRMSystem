package com.emrekoca.webservices;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.emrekoca.domain.Call;
import com.emrekoca.domain.Customer;
import com.emrekoca.services.customers.CustomerManagementService;
import com.emrekoca.services.customers.CustomerNotFoundException;

@WebService(serviceName = "customerService")
public class CustomerEndpoint implements CustomerManagementService {

	private CustomerManagementService customerService;

	@WebMethod(exclude = true)
	public void setCustomerService(CustomerManagementService customerService) {
		this.customerService = customerService;
	}

	@Override
	public void newCustomer(Customer newCustomer) {
		customerService.newCustomer(newCustomer);
	}

	@Override
	public void updateCustomer(Customer changedCustomer) throws CustomerNotFoundException {
		customerService.updateCustomer(changedCustomer);
	}

	@Override
	public void deleteCustomer(Customer oldCustomer) throws CustomerNotFoundException {
		customerService.deleteCustomer(oldCustomer);
	}

	@Override
	public Customer findCustomerById(String customerId) throws CustomerNotFoundException {
		Customer customer = customerService.findCustomerById(customerId);
		// We are doing this due to JPA, Hybernate since
		// it proxy the calls but this method does not return calls
		customer.setCalls(null);
		return customer;
	}

	@Override
	public List<Customer> findCustomersByName(String name) {
		List<Customer> customers = customerService.findCustomersByName(name);
		// We are doing this due to JPA, Hybernate since
		// it proxy the calls but this method does not return calls
		for (Customer next : customers) {
			next.setCalls(null);
		}
		return customers;
	}

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customers = customerService.getAllCustomers();
		// We are doing this due to JPA, Hybernate since
		// it proxy the calls but this method does not return calls
		// do this via AOP instead of copy paste
		for (Customer next : customers) {
			next.setCalls(null);
		}
		return customers;
	}

	@Override
	public Customer getFullCustomerDetail(String customerId) throws CustomerNotFoundException {
		return customerService.getFullCustomerDetail(customerId);
	}

	@Override
	public void recordCall(String customerId, Call callDetails) throws CustomerNotFoundException {
		customerService.recordCall(customerId, callDetails);
	}

}
