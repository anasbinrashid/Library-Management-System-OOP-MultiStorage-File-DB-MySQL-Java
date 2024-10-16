package assignment_1;

// Implementation of an interface for loanable items

public interface Loanable 
{
	// Method to calculate the loan fee based on the loan duration
	
    double calculateloan(int duration);

    // Method to check if the item can be extended for a longer loan duration
    
    boolean extendable();

    // Method to check if the item is available for loan
    
    boolean isLoanavailable();
}
