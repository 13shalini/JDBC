package com.slotjdbc;
//banking main where all the methods can be accessed
import java.util.*;
public class bankingmain {

		public static void main(String[] args)throws Exception {
			// TODO Auto-generated method stub
			Scanner sha=new Scanner(System.in);
			bankingdao dao=new bankingdao();
			customer c1=new customer();
			
			System.out.println("\t\t----Welcome to Anudip Bank-----");
			System.out.println("Select Operation: \n1 for Register account \n2 for Login");
			int op=sha.nextInt();
			
			switch(op) {

			case 1 :{
				//registration of customer
				System.out.println("Enter details to Register");
				System.out.print("Enter Customer Name : ");
				String cusname=sha.next();
				System.out.print("Enter Customer Password");
				String cuspass=sha.next();
				System.out.println("Enter Customer Phone");
				String cusphone=sha.next();
				System.out.print("Enter Customer minm Balance");
				int cusaccbal=sha.nextInt();
				c1.cname=cusname;
				c1.cpassword=cuspass;
				c1.cphone=cusphone;
				c1.caccbal=cusaccbal;
				//getting connection to db
				dao.connectDB();
				//registering the customer
				int res=dao.registerCustomer(c1);
				if(res==1) {
					System.out.println("Registered successfully");
				}
				else {
					System.out.println("User is already existed");
				}

			}

			case 2:{
				//login into user account
				System.out.println("Enter data to login");
				System.out.println("Enter Customer name");
				String cusname=sha.next();
				System.out.println("Enter Customer password");
				String cuspwd=sha.next();
				dao.connectDB();
				//if login success will get cid;
				int res=dao.login(cusname, cuspwd);
				if(res==0) {
					System.out.println("Invalid username or password");
				}
				else if(res==-1) {
					System.out.println("user not found, please register");
				}
				else {
					System.out.println("Login Success");

					System.out.println("Select Operation: \n1 for Withdraw \n2 for deposit \n3 for check balance \n4 for pin change");
					int op2=sha.nextInt();
					
					int op3=0;
					//iterating the menu
					while(op3<5)
					{
					System.out.println("Select Operation: \n1 for Withdraw \n2 for deposit \n3 for check balance \n4 for pin change\n5 for logout");
					op2=sha.nextInt();
					
					switch(op2) {

					case 1:{
						//withdraw
						System.out.println("Enter amount to withdraw");
						int amount=sha.nextInt();
						int bal=dao.withdraw(res, amount);
						if(bal==-1) {
							System.out.println("Not enough balance");
						}
						else {
							System.out.println("Withdraw done, avaialble balance is: "+bal);
						}
					}
					case 2:{
						//deposit
						System.out.println("Enter amount to deposit");
						int amount=sha.nextInt();
						int bal=dao.deposit(res, amount);
						 
							System.out.println("deposit done, avaialble balance is: "+bal);
						}
					
					case 3:{
						System.out.println("Avaiable balance : "+dao.checkBalance(res));
					}
					
					case 4:{
						System.out.println("Enter present password");
						String oldpwd=sha.next();
						System.out.println("Enter new password");
						String newpwd=sha.next();
						int count=dao.pinChange(res, oldpwd, newpwd);

						if(count==-1)
						{
							System.out.println("Password wrong");
						}
						else {
							System.out.println("Password changed successfully");
						}


					}

					default:System.out.println("Logout Successful");

					}


					}

				}
			}



			}


	}

}
