package assignment_1;

import java.io.*;
import java.util.*;

public class FileHandler extends PersistenceHandler
{
	private final String booksFilePath = "books.txt";
    private final String usersFilePath = "users.txt";
    private final String transactionsFilePath = "transactions.txt";
    private static int tid = 1;
    
	@Override
    public void insertbook(Book b) 
	{
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(booksFilePath, true))) 
        {
            writer.write(b.getBookid() + "," + b.getTitle() + "," + b.getAuthor() + "," + b.getIsbn() + "," + b.getPublicationyear() + "," + b.getGenre() + "," + b.getBaseloanfee() + ",false");
            writer.newLine();
            System.out.println("\nBook saved to file!");
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

	@Override
	public void deletebook(int id) 
	{		
		List<String> updatedBooks = new ArrayList<>();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(booksFilePath))) 
        {
            String line;

            while ((line = reader.readLine()) != null) 
            {
                String[] bookData = line.split(",");
                int bookID = Integer.parseInt(bookData[0].trim());

                if (bookID != id) 
                {
                    updatedBooks.add(line);  // Keep all books except the one to be deleted
                } 
                else 
                {
                    found = true;
                }
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        if (found) 
        {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(booksFilePath))) 
            {
                for (String book : updatedBooks) 
                {
                    writer.write(book);
                    writer.newLine();
                }
                
                System.out.println("\nBook deleted from file!");
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        } 
	}
	
	@Override
	public void updatebook(int bookID, boolean loanstatus) 
	{	
		List<String> updatedBooks = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(booksFilePath))) 
        {
            String line;

            while ((line = reader.readLine()) != null) 
            {
                String[] bookData = line.split(",");
                
                int id = Integer.parseInt(bookData[0].trim());

                if (id == bookID)
                {
                    bookData[7] = String.valueOf(loanstatus);  // Update loan status
                    updatedBooks.add(String.join(",", bookData));
                }
                else 
                {
                    updatedBooks.add(line);  // Keep other books unchanged
                }
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(booksFilePath)))
        {
            for (String book : updatedBooks) 
            {
                writer.write(book);
                writer.newLine();
            }
            
            System.out.println("\nBook updated in file!");
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
	}
	
	@Override
	public void readbooks() 
	{
		System.out.println("\nReading books from file!");
		
        List<Book> books = new ArrayList<>();
        List<Book> loanedbooks = new ArrayList<>();
		int count = 0;
		
        try (BufferedReader reader = new BufferedReader(new FileReader(booksFilePath))) 
        {
            String line;

            while ((line = reader.readLine()) != null) 
            {
                String[] bookData = line.split(",");
                
                if (bookData.length == 8) 
                {
                    int bookID = Integer.parseInt(bookData[0].trim());
                    String title = bookData[1].trim();
                    String author = bookData[2].trim();
                    String isbn = bookData[3].trim();
                    int publicationYear = Integer.parseInt(bookData[4].trim());
                    String genre = bookData[5].trim();
                    double baseLoanFee = Double.parseDouble(bookData[6].trim());
                    boolean loanStatus = Boolean.parseBoolean(bookData[7].trim());

                    Book b = null;
                    
                    if(count%3==0)
                    {
                    	b = new TextBook(bookID, title, author, isbn,  publicationYear, genre,baseLoanFee);
                        b.setLoanstatus(loanStatus);
                    }
                    if(count%3==1)
                    {
                    	b = new Novel(bookID, title, author, isbn,  publicationYear, genre,baseLoanFee);
                        b.setLoanstatus(loanStatus);
                    }
                    if(count%3==2)
                    {
                    	b = new ReferenceBook(bookID, title, author, isbn,  publicationYear, genre,baseLoanFee);
                        b.setLoanstatus(loanStatus);
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
    	            
                    System.out.println("Book ID: " + bookID + ", Title: " + title + ", Author: " + author);
                }
                else 
                {
                    System.out.println("Invalid book data format: " + line);
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
        catch (IOException e) 
        {
            e.printStackTrace();
        }
	}
	
	@Override
	public List<Book> getbooks() 
	{
		List<Book> books = new ArrayList<>();

		int count = 0;
		
	    try (BufferedReader reader = new BufferedReader(new FileReader(booksFilePath))) 
		{
            String line;

            while ((line = reader.readLine()) != null) 
            {
                String[] bookData = line.split(",");
                
                if (bookData.length == 8) 
                {
                    int bookID = Integer.parseInt(bookData[0].trim());
                    String title = bookData[1].trim();
                    String author = bookData[2].trim();
                    String isbn = bookData[3].trim();
                    int publicationYear = Integer.parseInt(bookData[4].trim());
                    String genre = bookData[5].trim();
                    double baseLoanFee = Double.parseDouble(bookData[6].trim());
                    boolean loanStatus = Boolean.parseBoolean(bookData[7].trim());

    	            if(count%3==0)
                    {
    	            	Book b = new TextBook(bookID, title, author, isbn, publicationYear, genre, baseLoanFee);
    		            b.setLoanstatus(loanStatus);
    		            books.add(b);
                    }
                    if(count%3==1)
                    {
                    	Book b = new Novel(bookID, title, author, isbn, publicationYear, genre, baseLoanFee);
        	            b.setLoanstatus(loanStatus);
        	            books.add(b);
                    }
                    if(count%3==2)
                    {
                    	Book b = new ReferenceBook(bookID, title, author, isbn, publicationYear, genre, baseLoanFee);
        	            b.setLoanstatus(loanStatus);
        	            books.add(b);
                    }
                    	            
    	            count++;
                } 
            }
        }
	    catch (IOException e) 
	    {
	        e.printStackTrace();
	    }
	    
	    return books;
	}

	@Override
	public void insertuser(User u) 
	{
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(usersFilePath, true))) 
		{
            writer.write(u.getUserid() + "," + u.getName() + "," + u.getAddress() + "," + u.getEmail() + "," + u.getPhonenumber() + "," + u.getTotalloanfee() + "false,0");
            writer.newLine();
            System.out.println("\nUser saved to file!");
        } 
		catch (IOException e) 
		{
            e.printStackTrace();
        }
	}

	@Override
	public void deleteuser(int id) 
	{
		List<String> updatedUsers = new ArrayList<>();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(usersFilePath))) 
        {
            String line;

            while ((line = reader.readLine()) != null) 
            {
                String[] userData = line.split(",");
                
                int userID = Integer.parseInt(userData[0].trim());

                if (userID != id) 
                {
                    updatedUsers.add(line);  // Keep all users except the one to be deleted
                } 
                else 
                {
                    found = true;
                }
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        if (found) 
        {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(usersFilePath))) 
            {
                for (String user : updatedUsers) 
                {
                    writer.write(user);
                    writer.newLine();
                }
            
                System.out.println("\nUser deleted from file!");
            } 
            catch (IOException e)
            {
                e.printStackTrace();
            }
        } 
	}

	@Override
	public void updateuser(int uid, int totalloanedbook, double loanFees) 
	{	
		List<String> updatedUsers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(usersFilePath))) 
        {
            String line;

            while ((line = reader.readLine()) != null) 
            {
                String[] userData = line.split(",");
                
                int id = Integer.parseInt(userData[0].trim());

                if (id == uid) 
                {
                    userData[5] = String.valueOf(loanFees);  // Update loan fees
                    userData[7] = String.valueOf(totalloanedbook);
                    updatedUsers.add(String.join(",", userData));
                } 
                else 
                {
                    updatedUsers.add(line);  // Keep other users unchanged
                }
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(usersFilePath))) 
        {
            for (String user : updatedUsers) 
            {
                writer.write(user);
                writer.newLine();
            }
            
            System.out.println("\nUser updated in file!");
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
	}
	
	@Override
	public void updateuserextension(int uid, double loanfee, boolean loanextension)
	{
		List<String> updatedUsers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(usersFilePath))) 
        {
            String line;

            while ((line = reader.readLine()) != null) 
            {
                String[] userData = line.split(",");
                
                int id = Integer.parseInt(userData[0].trim());

                if (id == uid) 
                {
                    userData[5] = String.valueOf(loanfee);  // Update loan fees
                    userData[6] = String.valueOf(loanextension);
                    updatedUsers.add(String.join(",", userData));
                } 
                else 
                {
                    updatedUsers.add(line);  // Keep other users unchanged
                }
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(usersFilePath))) 
        {
            for (String user : updatedUsers) 
            {
                writer.write(user);
                writer.newLine();
            }
            
            System.out.println("\nUser updated in file!");
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
	}
	
	@Override
	public void readusers() 
	{
		System.out.println("\nReading users from file!");
     
		int count = 0;
		
		try (BufferedReader reader = new BufferedReader(new FileReader(usersFilePath))) 
		{
            String line;

            while ((line = reader.readLine()) != null) 
            {
                String[] userData = line.split(",");
                
                if (userData.length == 8) 
                {
                    int userID = Integer.parseInt(userData[0].trim());
                    String name = userData[1].trim();
                    String address = userData[2].trim();
                    String email = userData[3].trim();
                    String phoneNumber = userData[4].trim();
                    double totalLoanFee = Double.parseDouble(userData[5].trim());
                    boolean loanextension = Boolean.parseBoolean(userData[6].trim());
                    int totalloanedbooks = Integer.parseInt(userData[7].trim());

                    if(count%3==0)
                    {
                    	User user = new Faculty(userID, name, email, phoneNumber, address);
                        user.setTotalloanfee(totalLoanFee);
                        user.setLoanextension(loanextension);
                        user.setTotalloanedbooks(totalloanedbooks);
                    }
                    if(count%3==1)
                    {
                    	User user = new Student(userID, name, email, phoneNumber, address);
                        user.setTotalloanfee(totalLoanFee);
                        user.setLoanextension(loanextension);
                        user.setTotalloanedbooks(totalloanedbooks);
                    }
                    if(count%3==2)
                    {
                    	User user = new PublicMember(userID, name, email, phoneNumber, address);
                        user.setTotalloanfee(totalLoanFee);
                        user.setLoanextension(loanextension);
                        user.setTotalloanedbooks(totalloanedbooks);
                    }
                    
                    count++;
                    
                    System.out.println("User ID: " + userID + ", Name: " + name + ", Email: " + email);
                } 
                else 
                {
                    System.out.println("Invalid user data format: " + line);
                }
            }
        } 
		catch (IOException e) 
		{
            e.printStackTrace();
        }
	}

	@Override
	public List<User> getusers() 
	{
		List<User> users = new ArrayList<>();

		int count = 0;
		
	    try (BufferedReader reader = new BufferedReader(new FileReader(usersFilePath))) 
		{
            String line;

            while ((line = reader.readLine()) != null) 
            {
                String[] userData = line.split(",");
                
                if (userData.length == 8) 
                {
                    int userID = Integer.parseInt(userData[0].trim());
                    String name = userData[1].trim();
                    String address = userData[2].trim();
                    String email = userData[3].trim();
                    String phoneNumber = userData[4].trim();
                    double totalLoanFee = Double.parseDouble(userData[5].trim());
                    boolean loanextension = Boolean.parseBoolean(userData[6].trim());
                    int totalloanedbooks = Integer.parseInt(userData[7].trim());
                    
                    if(count%3==0)
                    {
                    	User user = new Faculty(userID, name, email, phoneNumber, address);
                        user.setTotalloanfee(totalLoanFee);
                        user.setLoanextension(loanextension);
                        user.setTotalloanedbooks(totalloanedbooks);
                        users.add(user);
                    }
                    if(count%3==1)
                    {
                    	User user = new Student(userID, name, email, phoneNumber, address);
                        user.setTotalloanfee(totalLoanFee);
                        user.setLoanextension(loanextension);
                        user.setTotalloanedbooks(totalloanedbooks);
                        users.add(user);
                    }
                    if(count%3==2)
                    {
                    	User user = new PublicMember(userID, name, email, phoneNumber, address);
                        user.setTotalloanfee(totalLoanFee);
                        user.setLoanextension(loanextension);
                        user.setTotalloanedbooks(totalloanedbooks);
                        users.add(user);
                    }
                    
                    count++;
                } 
            }
        }
	    catch (IOException e) 
	    {
	        e.printStackTrace();
	    }
	    
	    return users;
	}
	
	public void logloantransaction(int bid, int uid, int ld)
	{
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(transactionsFilePath, true))) 
        {
            writer.write(tid + "," + bid + "," + uid + "," + "Book Loaned" + "," + ld);
            tid++;
            writer.newLine();
            System.out.println("\nTransaction logged into the File!");
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
	}
	
	public void logreturntransaction(int bid, int uid, int dl)
	{
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(transactionsFilePath, true))) 
        {
            writer.write(tid + "," + bid + "," + uid + "," + "Book Returned" + "," + dl);
            tid++;
            writer.newLine();
            System.out.println("\nTransaction logged into the File!");
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
	}
	
	@Override
	public void updatetransaction(int bid, int uid, int d)
	{
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(transactionsFilePath, true))) 
        {
            writer.write(tid + "," + bid + "," + uid + "," + "Loan Extension" + "," + d);
            tid++;
            writer.newLine();
            System.out.println("\nTransaction updated into the File!");
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
	}
	
	@Override
	public void readtransactions()
	{
		System.out.println("\nReading transactions from file!");
	     		
		try (BufferedReader reader = new BufferedReader(new FileReader(transactionsFilePath))) 
		{
            String line;

            while ((line = reader.readLine()) != null) 
            {
                String[] transactionData = line.split(",");
                
                if (transactionData.length == 5) 
                {
                    int transactionid = Integer.parseInt(transactionData[0].trim());
                    int userid = Integer.parseInt(transactionData[1].trim());
                    int bookid = Integer.parseInt(transactionData[2].trim());
                    String transactiontype = transactionData[3].trim();
                    int durationindays = Integer.parseInt(transactionData[4].trim());
                    
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
                else 
                {
                    System.out.println("Invalid user data format: " + line);
                }
            }
        } 
		catch (IOException e) 
		{
            e.printStackTrace();
        }
	}
	
	@Override
	public void closeconnection() 
	{	
        System.out.println("\nFile-based connection closed!");
	}
}
