package assignment_1;

// Class representing a Novel, which extends the abstract class Book

public class Novel extends Book
{
    public Novel(int bid, String t, String a, String i, int py, String g, double d) 
    {
        super(bid, t, a, i, py, g, d); 
    }
    
    public boolean extendable()
    {
        return false; 						// Novels are non-extendable for loans
    }

    public double calculateloan(int duration)
    {
        return getBaseloanfee();			// The loan fee is constant regardless of the loan duration
    }

	public boolean isLoanavailable() 
	{
		return !(isLoanstatus());
	}
}
