package com.emrekoca.services.calls;

import java.util.Collection;

import com.emrekoca.domain.Action;
import com.emrekoca.domain.Call;
import com.emrekoca.services.customers.CustomerNotFoundException;

public interface CallHandlingService 
{
	// DO NOT IMPLEMENT THIS INTERFACE UNTIL A LATER CHAPTER!
	
	/**
	 * Records a call with the customer management service, and also records
	 * any actions in the diary service
	 */
	public void recordCall(String customerId, Call newCall, Collection<Action> actions) throws CustomerNotFoundException;
}
