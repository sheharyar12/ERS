package com.revature.middle;
import java.util.List;

import com.revature.beans.ERSReimbursement;
import com.revature.data.Facade;
/**
 * calls the Filter method from the facade 
 * @author Shehar
 *
 */
public class FilterService {
	public List<ERSReimbursement> filter(List<ERSReimbursement> reimb, int statusType) throws Exception {
		// TODO Auto-generated method stub
		return new Facade().filterByStatus(reimb, statusType);
	}
}
