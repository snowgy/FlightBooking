package database;

import java.util.ArrayList;

import flightbooking.Admin;

public class Admins {
	public static ArrayList<Admin> admins = new ArrayList<>();
	
	static{
		Admin root = new Admin("root", "12345");
		admins.add(root);
	}
	
	public static Admin get(String username){
		Admin admin = null;
		for(int i=0; i<admins.size(); i++){
			if(admins.get(i).getUsername().equals(username))
				admin = admins.get(i);
		}
		
		return admin;
	}
	
	public static void add(Admin admin){
		admins.add(admin);
	}
}
