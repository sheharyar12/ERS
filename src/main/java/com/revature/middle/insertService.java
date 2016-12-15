package com.revature.middle;

import com.revature.beans.ErsUser;
import com.revature.data.Facade;

public class insertService {

	public void addReimb(ErsUser user, double amount,String desc,int type) throws Exception{
		new Facade().addReim(user, amount, desc, type);
	}
}
