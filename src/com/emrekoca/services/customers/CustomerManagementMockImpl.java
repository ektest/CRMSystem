package com.emrekoca.services.customers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.emrekoca.domain.Call;
import com.emrekoca.domain.Customer;

public class CustomerManagementMockImpl implements CustomerManagementService {

	private HashMap<String, Customer> customerMap;

	public CustomerManagementMockImpl() {
		customerMap = new HashMap<String, Customer>();
		customerMap.put("AB123", new Customer("AB123", "Acme ltd", "Notes"));
		customerMap.put("AB124", new Customer("AB124", "Test ltd", "Notes 2"));
		customerMap.put("AB125", new Customer("AB125", "Mac ltd", "Notes 3"));
	}

	@Override
	public void newCustomer(Customer newCustomer) {
		customerMap.put(newCustomer.getCustomerId(), newCustomer);
	}

	@Override
	public void updateCustomer(Customer changedCustomer) {
		// Override the value already there!
		customerMap.put(changedCustomer.getCustomerId(), changedCustomer);
	}

	@Override
	public void deleteCustomer(Customer oldCustomer) {
		customerMap.remove(oldCustomer.getCustomerId());
	}

	@Override
	public Customer findCustomerById(String customerId) throws CustomerNotFoundException {
		Customer foundCustomer = customerMap.get(customerId);
		if (foundCustomer == null)
			throw new CustomerNotFoundException();
		return customerMap.get(customerId);
	}

	@Override
	public List<Customer> findCustomersByName(String name) {
		List<Customer> customerList = new ArrayList<Customer>();
		for (Customer nextCustomer : customerMap.values()) {
			if (nextCustomer.getCompanyName().equals(name)) {
				customerList.add(nextCustomer);
			}
		}
		return customerList;
	}

	@Override
	public List<Customer> getAllCustomers() {
		return new ArrayList<Customer>(customerMap.values());
	}

	@Override
	public Customer getFullCustomerDetail(String customerId) 
			throws CustomerNotFoundException {
		return findCustomerById(customerId);
	}

	@Override
	public void recordCall(String customerId, Call callDetails) 
			throws CustomerNotFoundException {
		Customer customer = getFullCustomerDetail(customerId);
		customer.addCall(callDetails);
	}

}
