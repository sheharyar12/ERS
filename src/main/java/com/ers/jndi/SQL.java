package com.ers.jndi;

public class SQL {

	private String sql;
	
	public String reimbSQL(){
		String sql = "select "
				+ "ERS_REIMBURSEMENT.REIMB_AMOUNT,"
				+ "ERS_REIMBURSEMENT.REIMB_DESCRIPTIONS,"
				+ "ERS_REIMBURSEMENT.REIMB_ID,"
				+ "ERS_REIMBURSEMENT.REIMB_RESOLVED,"
				+ "ERS_REIMBURSEMENT.REIMB_SUBMITTED,"
				
				+ "users.ERS_USERS_ID as AuthorID,"
				+ "users.ERS_USERNAME as AuthorUn,"
				+ "users.ERS_PASSWORD as AuthoerPw,"
				+ "users.USER_FIRST_NAME as AuthorFn,"
				+ "users.USER_LAST_NAME as AuthorLn,"
				+ "users.USER_EMAIL as AuthorEm,"
				+ "users.USER_ROLE_ID as AuthorRole,"
				
				+ "resolvers.ERS_USERS_ID as ResolverID,"
				+ "resolvers.ERS_USERNAME as ResolverUn,"
				+ "resolvers.ERS_PASSWORD as ResolverPw,"
				+ "resolvers.USER_FIRST_NAME as ResolverFn,"
				+ "resolvers.USER_LAST_NAME as ResolverLn,"
				+ "resolvers.USER_EMAIL as ResolverEm,"
				+ "resolvers.USER_ROLE_ID as ResolverRole,"
				
				+ "status.REIMB_STATUS_ID as statusID,"
				+ "status.REIMB_STATUS as status,"
				
				+ "type.REIMB_TYPE_ID as typeID,"
				+ "type.REIMB_TYPE as type,"
				
				+ "u.REIMB_USER_ROLES_ID as userroleID,"
				+ "u.REIMB_ROLES as userRole,"
				
				+ "r.REIMB_USER_ROLES_ID as resolverroleID,"
				+ "r.REIMB_ROLES as resolverRole "
				
				+ "from ERS_REIMBURSEMENT "
				+ "inner join ERS_USERS users "
				+ "on ERS_REIMBURSEMENT.REIMB_AUTHOR = users.ERS_USERS_ID "
				
				+ "left join ERS_USERS resolvers "
				+ "on ERS_REIMBURSEMENT.REIMB_RESOLVER = resolvers.ERS_USERS_ID "
				
				+ "inner join ERS_REIMBURSEMENT_STATUS status "
				+ "on ERS_REIMBURSEMENT.REIMB_STATUS_ID = status.REIMB_STATUS_ID "
				
				+ "inner join ERS_REIMBURSEMENT_TYPE type "
				+ "on ERS_REIMBURSEMENT.REIMB_TYPE_ID = type.REIMB_TYPE_ID "
				
				+ "inner join ERS_USER_ROLES u "
				+ "on users.USER_ROLE_ID = u.REIMB_USER_ROLES_ID "
				
				+ "left join ERS_USER_ROLES r "
				+ "on resolvers.USER_ROLE_ID = r.REIMB_USER_ROLES_ID";
		
		return sql;
	}
}
