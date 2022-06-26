package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.exceptions.EntreeUtilisateurException;
import fr.diginamic.recensement.utils.RecensementUtils;

/**
 * Recherche et affichage de toutes les villes d'un département dont la
 * population est comprise entre une valeur min et une valeur max renseignées
 * par l'utilisateur.
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationBorneService extends MenuService {

	@Override
	public void traiter(Recensement rec, Scanner scanner) throws EntreeUtilisateurException {

		System.out.println("Quel est le code du département recherché ? ");
		String choix = scanner.nextLine();
		if (!(RecensementUtils.departementTrouve(choix, rec.getVilles()))) {
			throw new EntreeUtilisateurException("le code de departement n a pas ete trouve");
		}

		System.out.println("Choississez une population minimum (en milliers d'habitants): ");
		String saisieMin = scanner.nextLine();
		if (!(NumberUtils.isDigits(saisieMin))) {
			throw new EntreeUtilisateurException(saisieMin + " n est pas un nombre!");
		}
		int min = Integer.parseInt(saisieMin) * 1000;
		if (min < 0) {
			throw new EntreeUtilisateurException(saisieMin + " < 0 !");
		}

		System.out.println("Choississez une population maximum (en milliers d'habitants): ");
		String saisieMax = scanner.nextLine();
		if (!(NumberUtils.isDigits(saisieMax))) {
			throw new EntreeUtilisateurException(saisieMax + " n est pas un nombre!");
		}
		int max = Integer.parseInt(saisieMax) * 1000;
		if (max < 0) {
			throw new EntreeUtilisateurException(saisieMax + " < 0 !");
		}

		if (max < min) {
			throw new EntreeUtilisateurException("le min saisi est superieur au max saisi!");
		}

		List<Ville> villes = rec.getVilles();
		for (Ville ville : villes) {
			if (ville.getCodeDepartement().equalsIgnoreCase(choix)) {
				if (ville.getPopulation() >= min && ville.getPopulation() <= max) {
					System.out.println(ville);
				}
			}
		}
	}

}
