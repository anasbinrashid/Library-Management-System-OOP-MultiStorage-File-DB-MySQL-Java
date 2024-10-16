package assignment_1;

// Class representing a Student, extending the User class

public class Student extends User
{
	private final int loanlimit = 5;  // Students have a loan limit of 5 books

	public Student(int uid, String n, String e, String pn, String a) 
	{
		super(uid, n, e, pn, a);
	}

	// Method to get the loan limit specific to Students
	
	@Override
	public int getLoanlimit() 
	{
		return loanlimit;
	}
}
