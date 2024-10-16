package assignment_1;

import java.sql.*;
import java.util.*;

public class SQLHandler extends PersistenceHandler
{
	private Connection connection; 
	
	public SQLHandler() 
	{	
        String url = "jdbc:mysql://localhost:3306/assignment_1"; 
        String user = "root";  
        String password = "anasbinrashid";  

        try 
        {
        	connection = DriverManager.getConnection(url, user, password);
            System.out.println("\nConnection to the database was successful!");
        } 
        catch (SQLException e) 
        {
            System.out.println("\nFailed to connect to the database!");
            e.printStackTrace();
        }
    }

	@Override
    public void insertbook(Book b) 
    {
        String sql = "insert into book (bookid, title, author, isbn, publicationyear, genre, loanstatus, baseloanfee) values (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) 
        {
            pstmt.setInt(1, b.getBookid());
            pstmt.setString(2, b.getTitle());
            pstmt.setString(3, b.getAuthor());
            pstmt.setString(4, b.getIsbn());
            pstmt.setInt(5, b.getPublicationyear()); 
            pstmt.setString(6, b.getGenre());
            pstmt.setBoolean(7, b.isLoanstatus());
            pstmt.setDouble(8, b.getBaseloanfee());
            
            pstmt.executeUpdate();
            System.out.println("\nBook inserted into the database!");
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
	
	@Override
    public void deletebook(int bid) 
    {
        String sql = "delete from book where bookid = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) 
        {
            pstmt.setInt(1, bid);
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) 
            {
                System.out.println("\nBook deleted from the database!");
            } 
            else 
            {
                System.out.println("\nBook does not exist!");
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    @Override
    public void updatebook(int bid, boolean loanstatus) 
    {
        String sql = "update book set loanstatus = ? where bookid = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) 
        {
            pstmt.setBoolean(1, loanstatus);
            pstmt.setInt(2, bid);
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) 
            {
                System.out.println("\nBook loan status updated in the database!");
            } 
            else 
            {
                System.out.println("\nBook does not exist!");
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    @Override
    public void readbooks() 
    {
        String sql = "select * from book";
        
        List<Book> books = new ArrayList<>();
        List<Book> loanedbooks = new ArrayList<>();
        int count = 0;
        
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) 
        {
            while (rs.next()) 
            {
                int bookID = rs.getInt("bookid");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String isbn = rs.getString("isbn");
                int publicationYear = rs.getInt("publicationyear");
                String genre = rs.getString("genre");
                boolean loanstatus = rs.getBoolean("loanstatus");
                double baseLoanFee = rs.getDouble("baseloanfee");
                
                Book b = null;
                
                if(count%3==0)
                {
                	b = new TextBook(bookID, title, author, isbn,  publicationYear, genre,baseLoanFee);
                    b.setLoanstatus(loanstatus);
                }
                if(count%3==1)
                {
                	b = new Novel(bookID, title, author, isbn,  publicationYear, genre,baseLoanFee);
                    b.setLoanstatus(loanstatus);
                }
                if(count%3==2)
                {
                	b = new ReferenceBook(bookID, title, author, isbn,  publicationYear, genre,baseLoanFee);
                    b.setLoanstatus(loanstatus);
                }
                	            
	            count++;
	            
	            if(b.isLoanstatus())
	            {
	            	loanedbooks.add(b);
	            }
	            else
	            {
	            	books.add(b);
	            }	            	
            }
            
            System.out.println("\nBooks in the Library:\n");
            
            for(Book b : books)
            {
                System.out.println("Book ID: " + b.getBookid() + ", Title: " + b.getTitle() + ", Author: " + b.getAuthor());
            }
            
            System.out.println("\nBooks on loan:\n");
            
            if(loanedbooks.size() == 0)
            {
            	System.out.println("No books on Loan!");
            }
            
            for(Book b : loanedbooks)
            {
                System.out.println("Book ID: " + b.getBookid() + ", Title: " + b.getTitle() + ", Author: " + b.getAuthor());
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
    
    public List<Book> getbooks()
	{
		List<Book> books = new ArrayList<>();
		
	    String sql = "select * from book";
	    
	    int count = 0;
	    
	    try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) 
	    {
	    	while (rs.next()) 
	    	{
	            int id = rs.getInt("bookid");
	            String title = rs.getString("title");
	            String author = rs.getString("author");
	            String isbn = rs.getString("isbn");
	            int year = rs.getInt("publicationyear");
	            String genre = rs.getString("genre");
	            double fee = rs.getDouble("baseloanfee");
	            boolean isLoaned = rs.getBoolean("loanstatus");
	            
	            if(count%3==0)
                {
	            	Book b = new TextBook(id, title, author, isbn, year, genre, fee);
		            b.setLoanstatus(isLoaned);
		            books.add(b);
                }
                if(count%3==1)
                {
                	Book b = new Novel(id, title, author, isbn, year, genre, fee);
    	            b.setLoanstatus(isLoaned);
    	            books.add(b);
                }
                if(count%3==2)
                {
                	Book b = new ReferenceBook(id, title, author, isbn, year, genre, fee);
    	            b.setLoanstatus(isLoaned);
    	            books.add(b);
                }
                	            
	            count++;
	        }
	    } 
	    catch (SQLException e) 
	    {
	        e.printStackTrace();
	    }
	    
	    return books;
	}

    // User operations

    @Override
    public void insertuser(User u) 
    {
        String sql = "insert into user (userid, name, email, phonenumber, address, totalloanfee, loanextension, totalloanedbooks) values (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) 
        {
            pstmt.setInt(1, u.getUserid());
            pstmt.setString(2, u.getName());
            pstmt.setString(3, u.getEmail());
            pstmt.setString(4, u.getPhonenumber());
            pstmt.setString(5, u.getAddress());
            pstmt.setDouble(6, u.getTotalloanfee());
            pstmt.setBoolean(7, u.isLoanextension()); 
            pstmt.setInt(8, u.getTotalloanedbooks());
            pstmt.executeUpdate();
            
            System.out.println("\nUser inserted into the database!");
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteuser(int uid) 
    {
        String sql = "delete from user where userid = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) 
        {
            pstmt.setInt(1, uid);
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) 
            {
                System.out.println("\nUser deleted from the database!");
            } 
            else 
            {
                System.out.println("\nUser does not exist!");
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    @Override
    public void updateuser(int uid, int totalloanedbook ,double loanFees) 
    {
        String sql = "update user set totalloanfee = ?, totalloanedbooks = ? where userid = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) 
        {
            pstmt.setDouble(1, loanFees);
            pstmt.setInt(2, totalloanedbook);
            pstmt.setInt(3, uid);
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) 
            {
                System.out.println("\nUser updated in the database!");
            } 
            else 
            {
                System.out.println("\nUser does not exist!");
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
    
    @Override
	public void updateuserextension(int uid, double loanfee, boolean loanextension)
	{
    	String sql = "update user set totalloanfee = ?, loanextension = ? where userid = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) 
        {
            pstmt.setDouble(1, loanfee);
            pstmt.setBoolean(2, loanextension);
            pstmt.setInt(3, uid);
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) 
            {
                System.out.println("\nUser loan fee updated in the database!");
            } 
            else
            {
                System.out.println("\nUser does not exist!");
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
	}


    @Override
    public void readusers() 
    {
        String sql = "select * from user";
        
        int count = 0;
        
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) 
        {
            while (rs.next()) 
            {
                int userID = rs.getInt("userid");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phonenumber");
                String address = rs.getString("address");
                double totalLoanFee = rs.getDouble("totalloanfee");
                boolean loanExtension = rs.getBoolean("loanextension");
                int totalLoanedBooks = rs.getInt("totalloanedbooks");

                if(count%3==0)
                {
                	User user = new Faculty(userID, name, email, phone, address);
                    user.setTotalloanfee(totalLoanFee);
                    user.setLoanextension(loanExtension);
                    user.setTotalloanedbooks(totalLoanedBooks);
                }
                if(count%3==1)
                {
                	User user = new Student(userID, name, email, phone, address);
                    user.setTotalloanfee(totalLoanFee);
                    user.setLoanextension(loanExtension);
                    user.setTotalloanedbooks(totalLoanedBooks);
                }
                if(count%3==2)
                {
                	User user = new PublicMember(userID, name, email, phone, address);
                    user.setTotalloanfee(totalLoanFee);
                    user.setLoanextension(loanExtension);
                    user.setTotalloanedbooks(totalLoanedBooks);
                }
                
                count++;
                
                System.out.println("User ID: " + userID + ", Name: " + name + ", Email: " + email);
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
	
	public List<User> getusers()
	{
		List<User> users = new ArrayList<>();
		
	    String sql = "select * from user";
	    
	    int count = 0;
	    
	    try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) 
	    {
	    	while (rs.next()) 
	    	{
	            int userID = rs.getInt("userid");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phonenumber");
                String address = rs.getString("address");
                double totalLoanFee = rs.getDouble("totalloanfee");
                boolean loanExtension = rs.getBoolean("loanextension");
                int totalLoanedBooks = rs.getInt("totalloanedbooks");

                if(count%3==0)
                {
                	User user = new Faculty(userID, name, email, phone, address);
                    user.setTotalloanfee(totalLoanFee);
                    user.setLoanextension(loanExtension);
                    user.setTotalloanedbooks(totalLoanedBooks);
                    users.add(user);
                }
                if(count%3==1)
                {
                	User user = new Student(userID, name, email, phone, address);
                    user.setTotalloanfee(totalLoanFee);
                    user.setLoanextension(loanExtension);
                    user.setTotalloanedbooks(totalLoanedBooks);
                    users.add(user);
                }
                if(count%3==2)
                {
                	User user = new PublicMember(userID, name, email, phone, address);
                    user.setTotalloanfee(totalLoanFee);
                    user.setLoanextension(loanExtension);
                    user.setTotalloanedbooks(totalLoanedBooks);
                    users.add(user);
                }
                
                count++;
	        }
	    } 
	    catch (SQLException e) 
	    {
	        e.printStackTrace();
	    }
	    
	    return users;
	}
    
	public void logloantransaction(int bid, int uid, int ld)
	{  
	    String sql = "insert into transaction (bookid, userid, transactiontype, durationindays) values (?, ?, ?, ?)";
	    
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) 
        {
            pstmt.setInt(1, bid);
            pstmt.setInt(2, uid);
            pstmt.setString(3, "Book Loaned");
            pstmt.setInt(4, ld);
            
            pstmt.executeUpdate();
            System.out.println("\nTransaction logged into the database!");
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
	}
	
	public void logreturntransaction(int bid, int uid, int dl)
	{
		String sql = "insert into transaction (bookid, userid, transactiontype, durationindays) values (?, ?, ?, ?)";
	    
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) 
        {
            pstmt.setInt(1, bid);
            pstmt.setInt(2, uid);
            pstmt.setString(3, "Book Returned");
            pstmt.setInt(4, dl);
            
            pstmt.executeUpdate();
            System.out.println("\nTransaction logged into the database!");
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
	}
	
	public void updatetransaction(int bid, int uid, int d)
	{
		String sql = "insert into transaction (bookid, userid, transactiontype, durationindays) values (?, ?, ?, ?)";
	    
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) 
        {
            pstmt.setInt(1, bid);
            pstmt.setInt(2, uid);
            pstmt.setString(3, "Loan Extension");
            pstmt.setInt(4, d);
            
            pstmt.executeUpdate();
            System.out.println("\nTransaction updated into the database!");
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
	}
	
	@Override
	public void readtransactions()
	{
		String sql = "select * from transaction";
                
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) 
        {
            while (rs.next()) 
            {
                int transactionid = rs.getInt("transactionid");
                int bookid = rs.getInt("bookid");
                int userid = rs.getInt("userid");
                String transactiontype = rs.getString("transactiontype");
                int durationindays = rs.getInt("durationindays");
                                
                if(transactiontype.equals("Book Returned"))
                {
                    System.out.println("Transaction ID: " + transactionid + ", Book ID: " + bookid + ", User ID: " + userid + ", Transaction Type: " + transactiontype + ", Days Late: " + durationindays);
                } 

                if(transactiontype.equals("Book Loaned"))
                {
                    System.out.println("Transaction ID: " + transactionid + ", Book ID: " + bookid + ", User ID: " + userid + ", Transaction Type: " + transactiontype + ", Loaned for Days: " + durationindays);
                }

                if(transactiontype.equals("Loan Extension"))
                {
                    System.out.println("Transaction ID: " + transactionid + ", Book ID: " + bookid + ", User ID: " + userid + ", Transaction Type: " + transactiontype + ", Days Extended: " + durationindays);
                }
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
	}
	
    // Close connection

    @Override
    public void closeconnection() 
    {
        if (connection != null) 
        {
            try 
            {
                connection.close();
                System.out.println("\nDatabase connection closed!");
            } 
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
        }
    }
}
