package com.virtusa.eg.client;




import org.apache.log4j.Logger;


import com.virtusa.eg.controller.MainController;

public class Client {
	
	static Logger log = Logger.getLogger(Client.class);
	
	public static void main(String []args) throws Exception {
		MainController maincontroller = new MainController();
		maincontroller.menu();
	}
}


