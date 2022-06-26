package fr.diginamic.recensement;

import java.util.Scanner;

public class InterfaceUtilisateur {

	Scanner scanner = new Scanner(System.in);
	
	public String demanderCodeDepartement() {
		return scanner.nextLine();
	}
	public String demanderCodeRegion() {
		return scanner.nextLine();
	}
	public static void afficher(String aAfficher) {
		System.out.println(aAfficher);
	}
}
