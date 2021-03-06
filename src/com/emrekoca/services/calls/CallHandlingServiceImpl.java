package com.emrekoca.services.calls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emrekoca.domain.Action;
import com.emrekoca.domain.Call;
import com.emrekoca.services.customers.CustomerManagementService;
import com.emrekoca.services.customers.CustomerNotFoundException;
import com.emrekoca.services.diary.DiaryManagementService;

@Transactional
@Service
public class CallHandlingServiceImpl implements CallHandlingService 
{
	@Autowired
	private CustomerManagementService customerService;
	@Autowired
	private DiaryManagementService diaryService;

	public CallHandlingServiceImpl(CustomerManagementService customerService, DiaryManagementService diaryService)
	{
		this.customerService = customerService;
		this.diaryService = diaryService;	
	}

	@Override
	public void recordCall(String customerId, Call newCall, Collection<Action> actions) throws CustomerNotFoundException 
	{
		// 1: call the customer service to record the call
		customerService.recordCall(customerId, newCall);
		
		// 2: call the diary service to record the actions
		for (Action nextAction: actions)
		{
			diaryService.recordAction(nextAction);			
		}
	}

}
