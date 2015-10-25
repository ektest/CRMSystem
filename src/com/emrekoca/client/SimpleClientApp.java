package com.emrekoca.client;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.emrekoca.domain.Action;
import com.emrekoca.domain.Call;
import com.emrekoca.services.calls.CallHandlingService;
import com.emrekoca.services.customers.CustomerNotFoundException;
import com.emrekoca.services.diary.DiaryManagementService;

public class SimpleClientApp {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");
		//CustomerManagementService customerService = container.getBean("customerService", CustomerManagementService.class);
		// Another way to wire bean if there is only one configuration of that class in application.xmll
		//DiaryManagementService diaryService = container.getBean(DiaryManagementService.class);
		CallHandlingService callService = container.getBean(CallHandlingService.class);
		Action action1 = new Action("Call back me!", new GregorianCalendar(2016, 0, 0), "ek");
		Action action2 = new Action("I hate you!", new GregorianCalendar(2018, 0, 0), "ek");
		List<Action> list = new ArrayList<Action>();
		list.add(action1);
		list.add(action2);
		try {
			callService.recordCall("AB123", new Call("Test testing"), list);
		} catch (CustomerNotFoundException e) {
			e.printStackTrace();
		}
		DiaryManagementService diaryService = container.getBean(DiaryManagementService.class);
		for(Action action : diaryService.getAllIncompleteActions("ek"))
		{
			System.out.println(action);
		}
		container.close();
	}
}