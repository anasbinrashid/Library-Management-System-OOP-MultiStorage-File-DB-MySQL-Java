package assignment_1;

import java.util.*;

// Main class for the library management system

public class Demo 
{
    public static void main(String[] args) 
    {    	
    	PersistenceHandler ph =  null;
    	
        Scanner sc = new Scanner(System.in);
        
        Library library = new Library();
        
        /* Book novel = new Novel(1, "Great Expectations", "Charles Dickens", "ISBN1234", "Novel", 1970, 150.00); 
        Book textbook = new TextBook(2, "Physics", "Isaac Newton", "ISBN2345", "Education", 1880, 300.00);
        Book referencebook = new ReferenceBook(3, "Glossary", "William Shakespeare", "ISBN6789", "Reference", 1600, 250.00);
        User student = new Student(1, "Anas", "anas@nu.edu.pk", "03000000000", "Islamabad");
        User faculty = new Faculty(2, "Babar", "babar@nu.edu.pk", "03111111111", "Lahore");
        User publicmember = new PublicMember(3, "Shadab", "shadab@nu.edu.pk", "03222222222", "Rawalpindi");

        library.addbook(novel);
        novel.save(ph);
        library.addbook(textbook);
        textbook.save(ph);
        library.addbook(referencebook);
        referencebook.save(ph);   
        library.adduser(student);
        student.save(ph);
        library.adduser(faculty);
        faculty.save(ph);
        library.adduser(publicmember);
        publicmember.save(ph); */
        
        System.out.print("\nChoose Storage Method:\nEnter 1 for File-based storage\nEnter 2 for SQL-based storage\n\nEnter your choice: ");
        int storagechoice = sc.nextInt();

        while(storagechoice > 2 || storagechoice < 1)
        {
        	System.out.print("Invalid Input! Enter again: ");
        	storagechoice = sc.nextInt();
        }
        
        if (storagechoice == 1) 
        {
            ph = new FileHandler();
        } 
        else if(storagechoice == 2)
        {
        	ph = new SQLHandler();
        }
        
        // Prepopulate the library with some books and users
        
        System.out.println("\nPrepopulating the Library!\n");

        List<Book> bfdb = ph.getbooks();
        
        for (Book b : bfdb) 
        {
            library.addbook(b);
        }
                
        List<User> ufdb = ph.getusers();
        
        for (User u: ufdb) 
        {
            library.adduser(u);
        }
        
        int option;

        // Main loop to keep the program running until the user chooses to exit
                
        while (true) 
        {
            // Display the main menu to the user
        	
            System.out.println("\n~~~ Library Management System ~~~");
            System.out.println("\n1. Add new book\n" + "2. Display available books\n" + "3. Remove a book\n" + "4. Add new user\n" + "5. Display user details\n" + "6. Remove a user\n" + "7. Loan a book to a user\n" + "8. Display loan details\n" + "9. Return a book\n" + "10. Search for a User\n" + "11. Search for a Book\n" + "12. Sort Books by Title\n" + "13. Sort Books Based on Loan Status\n" + "14. Sort Users by Name\n" + "15. Request Loan Extension\n" + "16. Display Transaction Details\n" + "17. Exit");
            
            System.out.print("\nEnter your choice: ");

            option = sc.nextInt();

            // Add new book option
            
            if (option == 1) 
            {
                System.out.print("\nEnter the Type of Book\n\"1\" for TextBook, \"2\" for Novel, \"3\" for ReferenceBook: ");
                int ty = sc.nextInt();

                // Validate the input for book type
                
                while (ty > 3 || ty < 1) 
                {
                    System.out.print("Invalid Input! Enter again: ");
                    ty = sc.nextInt();
                }

                System.out.print("Enter Book ID: ");
                int bid = sc.nextInt();
                sc.nextLine(); 
                System.out.print("Enter Book Title: ");
                String t = sc.nextLine();
                System.out.print("Enter Book Author: ");
                String a = sc.nextLine();
                System.out.print("Enter Book ISBN: ");
                String i = sc.nextLine();
                System.out.print("Enter Book Publication Year: ");
                int py = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter Book Genre: ");
                String g = sc.nextLine();
                System.out.print("Enter Base Loan Fee: ");
                double f = sc.nextDouble();

                Book nb;
                
                if (ty == 1) 
                {
                    nb = new TextBook(bid, t, a, i, py, g, f); 
                } 
                else if (ty == 2)
                {
                    nb = new Novel(bid, t, a, i, py, g, f); 
                } 
                else 
                {
                    nb = new ReferenceBook(bid, t, a, i, py, g, f); 
                }
                
                if(library.checkbook(bid) == null)
                {
                    nb.save(ph);	
                } 
                
                library.addbook(nb);           
            }
            
            // Display available books
            
            else if (option == 2) 
            {
                //library.printbooks();
            	
            	ph.readbooks();
            }
            
            // Remove a book from the library
            
            else if (option == 3) 
            {
                System.out.print("\nEnter the ID of the book you want to remove: ");
                int bid = sc.nextInt();
                
                if(library.checkbook(bid) == null)
                {
                	System.out.println("\nThe book does not exist in this Library!");
                }
                else
                {
                	library.removebook(bid); 
                    
                    if(library.checkbook(bid) == null)
                    {
                    	ph.deletebook(bid);
                    }
                }
            }
            
            // Add new user
            
            else if (option == 4) 
            {
                System.out.println("\nEnter the Type of User\n\"1\" for Student, \"2\" for Faculty, \"3\" for PublicMember");
                int ty = sc.nextInt();

                // Validate the input for user type
                
                while (ty > 3 || ty < 1) 
                {
                    System.out.println("Invalid Input!");
                    ty = sc.nextInt();
                }

                System.out.print("Enter User ID: ");
                int uid = sc.nextInt();
                sc.nextLine(); 
                System.out.print("Enter User Name: ");
                String n = sc.nextLine();
                System.out.print("Enter User Email: ");
                String e = sc.nextLine();
                System.out.print("Enter User Phone Number: ");
                String pn = sc.nextLine();
                System.out.print("Enter User Address: ");
                String a = sc.nextLine();

                User nu;
                
                if (ty == 1) 
                {
                    nu = new Student(uid, n, e, pn, a); 
                } 
                else if (ty == 2) 
                {
                    nu = new Faculty(uid, n, e, pn, a);
                } 
                else 
                {
                    nu = new PublicMember(uid, n, e, pn, a); 
                }
                 
                if(library.checkuser(uid) == null)
                {
                    nu.save(ph);	
                }
                
                library.adduser(nu);
            }
            
            // Display user details
            
            else if (option == 5) 
            {
                //library.printusers();
            	
            	ph.readusers();
            }
            
            // Remove a user
            
            else if (option == 6)
            {
            	System.out.print("\nEnter the ID of the user you want to remove: ");
                int uid = sc.nextInt();
                
                if(library.checkuser(uid) == null)
                {
                	System.out.println("\nThe user does not exist in this Library!");
                }
                else
                {
                	library.removeuser(uid); 
                    
                    if(library.checkuser(uid) == null)
                    {
                    	ph.deleteuser(uid);
                    }
                }
            }
            
            // Loan a book to a user
            
            else if (option == 7) 
            {
                int uid, bid, ld;
                
                System.out.print("\nEnter User ID: ");
                uid = sc.nextInt();
                System.out.print("Enter Book ID: ");
                bid = sc.nextInt();
                System.out.print("Enter the number of days you want to Loan the Book: ");
                ld = sc.nextInt();

                User u = library.checkuser(uid);
                Book b = library.checkbook(bid);
                
                if(u!=null)
                {
                    if(b!=null)
                    {
                    	int currentloans = u.getTotalloanedbooks();
                                                
                        if(b.isLoanstatus() == true)
                        {
                            library.loanbook(uid, bid, ld);
                        }
                        else
                        {
                            library.loanbook(uid, bid, ld);
                        	
                            u = library.checkuser(uid);
                            b = library.checkbook(bid);
                            
                        	if(b.isLoanstatus() == true)
                            {
                                ph.updatebook(bid, true);	
                                
                                if(currentloans < u.getTotalloanedbooks())
                                {                        
                                    ph.updateuser(uid, u.getTotalloanedbooks(), u.getTotalloanfee());	
                                    ph.logloantransaction(bid, uid, ld);
                                }
                            }
                        }
                    }
                    else
                    {
                    	System.out.println("\nThe book does not exist in this Library!");
                    }
                }
                else
                {
                	System.out.println("\nThe user does not exist in this Library!");
                }
            }
            
            // Display loan details of a user
            
            else if (option == 8) 
            {
                int uid;
                
                System.out.print("\nEnter User ID: ");
                
                uid = sc.nextInt();
                library.printloans(uid); // Display loan details for a user
            }
            
            // Return a book
            
            else if (option == 9)
            {
                System.out.print("\nEnter User ID: ");
                int uid = sc.nextInt();
                System.out.print("Enter Book ID: ");
                int bid = sc.nextInt();
                System.out.print("Enter Number of Days Late (if any): ");
                int dl = sc.nextInt();
                
                User u = library.checkuser(uid);
                Book b = library.checkbook(bid);
                
                if(u!=null)
                {
                    if(b!=null)
                    {
                    	int currentloans = u.getTotalloanedbooks();
                        
                        if(b.isLoanstatus() == false)
                        {
                            library.returnbook(uid, bid, dl);
                        }
                        else
                        {
                            library.returnbook(uid, bid, dl);
                        	
                            u = library.checkuser(uid);
                            b = library.checkbook(bid);
                            
                        	if(b.isLoanstatus() == false)
                            {
                                ph.updatebook(bid, false);	
                                
                                if(currentloans > u.getTotalloanedbooks())
                                {                        
                                    ph.updateuser(uid, u.getTotalloanedbooks(), u.getTotalloanfee());	
                                    ph.logreturntransaction(bid, uid, dl);
                                }
                            }
                        }
                    }
                    else
                    {
                    	System.out.println("\nThe book does not exist in this Library!");
                    }
                }
                else
                {
                	System.out.println("\nThe user does not exist in this Library!");
                }
            }
            
            else if (option == 10)
            {
            	sc.nextLine();
            	System.out.print("\nEnter the information to search for a User: ");
            	String s = sc.nextLine();
            	
            	library.searchuser(s);
            }
            
            else if (option == 11)
            {
            	sc.nextLine();
            	System.out.print("\nEnter the information to search for a Book: ");
            	String s = sc.nextLine();
            	
            	library.searchbook(s);
            }
            
            else if (option == 12)
            {
            	library.sortBooksByTitle();
            }
            
            else if (option == 13)
            {
            	library.sortBooksByLoanStatus();
            }
            
            else if (option == 14)
            {
            	library.sortUsersByName();
            }
                        
            else if (option == 15)
            {
            	System.out.print("\nEnter User ID: ");
                int uid = sc.nextInt();
                System.out.print("Enter Book ID: ");
                int bid = sc.nextInt();
                System.out.print("Enter the number of days you want to extend the Loan duration: ");
                int ld = sc.nextInt();
            	
                User u = library.checkuser(uid);
                Book b = library.checkbook(bid);
                
                if(u!=null)
                {
                    if(b!=null)
                    {                        
                        library.extendloan(uid, bid, ld);
                        	
                    	if(u.isLoanextension() == true)
                        {                            
                            ph.updateuserextension(uid, u.getTotalloanfee(), u.isLoanextension());	
                            ph.updatetransaction(bid, uid, ld);
                        }
                    }
                    else
                    {
                    	System.out.println("\nThe book does not exist in this Library!");
                    }
                }
                else
                {
                	System.out.println("\nThe user does not exist in this Library!");
                }            
            }
            
            else if (option == 16)
            {
            	ph.readtransactions();
            }
            
            // Exit the program
            
            else if (option == 17)
            {
                ph.closeconnection();

                System.out.println("\nExiting the System!");
                break; 
            }
            
            // Handle invalid input
            
            else 
            {
                System.out.println("\nInvalid Input!");
            }
        }

        // Close the scanner

        sc.close();
    }
}
