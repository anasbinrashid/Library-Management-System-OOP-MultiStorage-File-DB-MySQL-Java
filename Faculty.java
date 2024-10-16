package assignment_1;

// Class representing a Faculty member, extending the User class

public class Faculty extends User
{
	private final int loanlimit = 10;  // Faculty members have a loan limit of 10 books
	
	public Faculty(int uid, String n, String e, String pn, String a) 
	{
		super(uid, n, e, pn, a);
	}

	// Method to get the loan limit specific to Faculty members
	
	@Override
	public int getLoanlimit() 
	{
		return loanlimit;
	}
}
