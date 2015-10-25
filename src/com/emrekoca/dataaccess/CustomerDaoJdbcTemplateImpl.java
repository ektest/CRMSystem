package com.emrekoca.dataaccess;

import java.util.List;

import com.emrekoca.domain.Call;
import com.emrekoca.domain.Customer;

public class CustomerDaoJdbcTemplateImpl implements CustomerDao {

	@Override
	public void create(Customer customer) {
		// TODO Auto-generated method stub

	}

	@Override
	public Customer getById(String customerId) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Customer customerToUpdate) throws RecordNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Customer oldCustomer) throws RecordNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getFullCustomerDetail(String customerId) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCall(Call newCall, String customerId) throws RecordNotFoundException {
		// TODO Auto-generated method stub

	}

}
