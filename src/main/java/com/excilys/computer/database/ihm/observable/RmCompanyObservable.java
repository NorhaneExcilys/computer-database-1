package main.java.com.excilys.computer.database.ihm.observable;

import java.util.Scanner;

import main.java.com.excilys.computer.database.ihm.observer.IObservable;
import main.java.com.excilys.computer.database.modele.Company;
import main.java.com.excilys.computer.database.services.ServiceCompany;

public class RmCompanyObservable implements IObservable {	
	
	Scanner sc;
	
	public int ajoutId(Company company) {
		sc = new Scanner(System.in);
		System.out.print("Enter the id of the company: ");				
		try {
			long id = sc.nextLong();
			company.setId(id);
		} catch(Exception e) {
			return -1;
		}
		return 0 ;
	}
	
	/**
	 * Permet de supprimer un computer et retourne un message en fonction de la suppresion!
	 */
	public Boolean execute() {
		Company company = new Company(); 
		ServiceCompany service = ServiceCompany.getService();
		
		if (ajoutId(company)==0) {
			if (service.rmCompany(company)==0) {
				System.out.print("Company and all computers related to it are deleted!\n\n");
				return true;
			}
			
			System.out.print("Error deleting the company, check the input ID!\n\n");
			return true;
		}
		System.out.print("Please enter next time a valid ID!\n\n");
		return true;
	}
}
