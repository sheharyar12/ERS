package com.revature.middle;

import com.revature.beans.ErsUser;
import com.revature.data.Facade;

public class insertService {

	public void addReimb(ErsUser user, double amount,String desc,int type) throws Exception{
		try{
			new Facade().addReim(user, amount, desc, type);
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
