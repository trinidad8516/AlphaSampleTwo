package esparzat;

import esparzat.AccountingAndProducts.ExtractProductsandInventory;
import esparzat.AccountingAndProducts.InitialSalesDisplay;
import esparzat.AccountingAndProducts.ProductAndInventoryDisplay;
import esparzat.Personnel.AddRemoveEmployeesDisplay;
import esparzat.Personnel.ExtractEmployees;
import esparzat.Personnel.LoginDisplay;

public class POPapp {

	public static void main(String[] args) {
		AddRemoveEmployeesDisplay.doesFileExist();
		ExtractEmployees.readEmployeeFile();
		ProductAndInventoryDisplay.doesFileExist();
		ExtractProductsandInventory.readProductFile();
		LoginDisplay.username();
		InitialSalesDisplay.initialDisplay();

	}

}
