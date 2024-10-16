package assignment_1;

import java.util.*;

public abstract class PersistenceHandler 
{
	// Storage Read
	
	public abstract List<Book> getbooks();
	public abstract List<User> getusers();
	
	// Book
	
    public abstract void insertbook(Book b);
    public abstract void deletebook(int bid);
	public abstract void updatebook(int bid, boolean loanstatus);
	public abstract void readbooks();

	// User
	
	public abstract void insertuser(User u);
	public abstract void deleteuser(int uid);
	public abstract void updateuser(int uid, int totalloanedbook, double f);
	public abstract void readusers();
	public abstract void updateuserextension(int uid, double loanfee, boolean loanextension);

	// Transaction
	
	public abstract void logloantransaction(int bid, int uid, int ld);
	public abstract void logreturntransaction(int bid, int uid, int dl);
	public abstract void updatetransaction(int bid, int uid, int d);
	public abstract void readtransactions();

	// Connection
	
	public abstract void closeconnection();
}
