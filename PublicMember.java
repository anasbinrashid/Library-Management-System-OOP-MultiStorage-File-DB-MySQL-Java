package assignment_1;

// Class representing a Public Member, extending the User class

public class PublicMember extends User
{
	private final int loanlimit = 3;  // Public Members have a loan limit of 3 books

	public PublicMember(int uid, String n, String e, String pn, String a) 
	{
		super(uid, n, e, pn, a);
	}

	// Method to get the loan limit specific to Public Members
	
	@Override
	public int getLoanlimit() 
	{
		return loanlimit;
	}

	// Method to get the base fee for loaning books, specific to Public Members
	
	public double getBasefee() 
	{
		return 1.5;  // Base fee per book for Public Members
	}	
}
