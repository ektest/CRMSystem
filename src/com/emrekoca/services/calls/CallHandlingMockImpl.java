package com.emrekoca.services.calls;

import java.util.Collection;

import com.emrekoca.domain.Action;
import com.emrekoca.domain.Call;
import com.emrekoca.services.customers.CustomerManagementService;
import com.emrekoca.services.customers.CustomerNotFoundException;
import com.emrekoca.services.diary.DiaryManagementService;

public class CallHandlingMockImpl implements CallHandlingService {

	private CustomerManagementService customerService;
	private DiaryManagementService diaryService;

	public CallHandlingMockImpl(CustomerManagementService customerService, DiaryManagementService diaryService) {
		this.customerService = customerService;
		this.diaryService = diaryService;
	}

	@Override
	public void recordCall(String customerId, Call newCall, Collection<Action> actions)
			throws CustomerNotFoundException {
	}

}
