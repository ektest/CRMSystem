package com.emrekoca.server;

import java.util.Scanner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RmiServer {

	public static void main(String[] args) 
	{
		ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("/RMI-Config/remoting-server.xml");
		
		System.out.println("Server Running! Please press enter to stop!");
		
		Scanner sc = new Scanner(System.in);
		
		sc.nextLine();
		
		container.close();
		sc.close();
	}

}
