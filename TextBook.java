package assignment_1;

//Class representing a TextBook, which extends the abstract class Book

public class TextBook extends Book
{
	public TextBook(int bid, String t, String a, String i, int py, String g, double d) 
	{
		super(bid, t, a, i, py, g, d);
	}

	public boolean extendable()
	{
		return true;						// TextBooks are extendable for loans
	}
	
	public double calculateloan(int duration)
	{
		return (getBaseloanfee() + (duration * 2));		// The loan fee is based on duration
	}
	
	public boolean isLoanavailable()
	{
		return !(isLoanstatus());
	}
}
