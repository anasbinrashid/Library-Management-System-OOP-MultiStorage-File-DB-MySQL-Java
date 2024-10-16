package assignment_1;

// Abstract class representing a Book

public abstract class Book implements Loanable
{
	private int bookid;               
	private String title;          
	private String author;       
	private String isbn;            
	private int publicationyear;      
	private String genre;            
	private boolean loanstatus;       
	private double baseloanfee;        

	public Book(int bid, String t, String a, String i, int py, String g, double blf)
	{
		super();
		this.author = a;
		this.baseloanfee = blf;
		this.bookid = bid;
		this.genre = g;
		this.isbn = i;
		this.loanstatus = false;          // By default, the book is not loaned
		this.publicationyear = py;
		this.title = t;
	}
	
	// Getter and setter for bookid
	
	public int getBookid() 
	{
		return bookid;
	}
	
	public void setBookid(int bookid) 
	{
		this.bookid = bookid;
	}
	
	// Getter and setter for title
	
	public String getTitle() 
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	// Getter and setter for author
	
	public String getAuthor() 
	{
		return author;
	}
	
	public void setAuthor(String author) 
	{
		this.author = author;
	}
	
	// Getter and setter for ISBN
	
	public String getIsbn() 
	{
		return isbn;
	}
	
	public void setIsbn(String isbn)
	{
		this.isbn = isbn;
	}
	
	// Getter and setter for publication year
	
	public int getPublicationyear()
	{
		return publicationyear;
	}
	
	public void setPublicationyear(int publicationyear)
	{
		this.publicationyear = publicationyear;
	}
	
	// Getter and setter for genre
	
	public String getGenre()
	{
		return genre;
	}
	
	public void setGenre(String genre) 
	{
		this.genre = genre;
	}
	
	// Getter and setter for loan status (true if loaned, false otherwise)
	
	public boolean isLoanstatus() 
	{
		return loanstatus;
	}
	
	public void setLoanstatus(boolean loanstatus) 
	{
		this.loanstatus = loanstatus;
	}
	
	// Getter and setter for the base loan fee
	
	public double getBaseloanfee() 
	{
		return baseloanfee;
	}
	
	public void setBaseloanfee(double baseloanfee)
	{
		this.baseloanfee = baseloanfee;
	}
	
	public void save(PersistenceHandler ph)
	{
		ph.insertbook(this);
	}
}
