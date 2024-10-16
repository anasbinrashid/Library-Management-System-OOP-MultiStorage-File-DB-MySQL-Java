package assignment_1;

import java.util.*;

// Abstract class representing a User

public abstract class User 
{
	private int userid;
	private String name;
	private String email;
	private double totalloanfee;
	private String phonenumber;
	private String address;
	private List <Book> loanedbooks;  // List to store books currently loaned by the user
	private static int totalloanedbooks;
	private boolean loanextension;
	
	public User(int uid, String n, String e, String pn, String a) 
	{
		super();
		this.address = a;
		this.email = e;
		this.loanedbooks = new ArrayList <> ();
		this.name = n;
		this.phonenumber = pn;
		this.totalloanfee = 0;
		this.userid = uid;
		this.loanextension = false;
	}
	
	// Getter and Setter methods for user attributes
	
	public boolean isLoanextension() 
	{
		return loanextension;
	}

	public void setLoanextension(boolean loanextension) 
	{
		this.loanextension = loanextension;
	}

	public List<Book> getLoanedbooks() 
	{
		return loanedbooks;
	}

	public void setLoanedbooks(List<Book> loanedbooks) 
	{
		this.loanedbooks = loanedbooks;
	}

	public void setTotalloanfee(double totalloanfee) 
	{
		this.totalloanfee = totalloanfee;
	}

	public int getUserid() 
	{
		return userid;
	}
	
	public void setUserid(int userid) 
	{
		this.userid = userid;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getEmail() 
	{
		return email;
	}
	
	public void setEmail(String email) 
	{
		this.email = email;
	}
	
	public double getTotalloanfee() 
	{
		return totalloanfee;
	}
	
	public String getPhonenumber() 
	{
		return phonenumber;
	}
	
	public void setPhonenumber(String phonenumber) 
	{
		this.phonenumber = phonenumber;
	}
	
	public String getAddress() 
	{
		return address;
	}
	
	public void setAddress(String address) 
	{
		this.address = address;
	}
	
	public int getTotalloanedbooks() 
	{
		return totalloanedbooks;
	}

	public void setTotalloanedbooks(int totalloanedbooks) 
	{
		User.totalloanedbooks = totalloanedbooks;
	}
	
	// Abstract method to get loan limit for specific user types (to be implemented in subclasses)
	
	public abstract int getLoanlimit();
	
	// Method to loan a book to the user, updates the loan status and adds loan fee
	
	public void loanbook(Book b, double f)
	{			
		for(Book r : loanedbooks)
		{
			if (r.getBookid() == b.getBookid()) 
            {
				return;
            }
		}
		
		loanedbooks.add(b);
		b.setLoanstatus(true);
		this.totalloanfee += f;
		totalloanedbooks++;
	}
	
	// Method to return a book, updates the loan status
	
	public void returnbook(Book b)
	{					
		if (loanedbooks.contains(b)) 
		{
			loanedbooks.remove(b);
			b.setLoanstatus(false);
			totalloanedbooks--;
		}
	}
	
	// Method to display the list of books currently loaned by the user
	
	public void displayloanedbooks()
	{
		System.out.println("\nLoaned Books");
			
		boolean check = true;
		
		for (Book book : loanedbooks) 
		{
            check = false;
			System.out.println("Book ID: " + book.getBookid() + ", Book Title: " + book.getTitle() + ", Book Author: " + book.getAuthor());
        }
		
		if(check == true)
		{
			System.out.println("No Books on Loan!");
		}
	}
	
	public void save(PersistenceHandler ph)
	{
		ph.insertuser(this);
	}
}
