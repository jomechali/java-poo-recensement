package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.exceptions.EntreeUtilisateurException;
import fr.diginamic.recensement.utils.RecensementUtils;

/**
 * Recherche et affichage de la population d'une région
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationRegionService extends MenuService {

	@Override
	public void traiter(Recensement rec, Scanner scanner) throws EntreeUtilisateurException {

		System.out.println("Quel est le nom (ou le début de nom) de la région recherchée ? ");
		String choix = scanner.nextLine();
		if (!(RecensementUtils.regionTrouvee(choix, rec.getVilles()))) {
			throw new EntreeUtilisateurException("la donnee entree n est ni un code de region ni un nom de region valide");
		}

		List<Ville> villes = rec.getVilles();
		int somme = 0;
		String nom = null;
		for (Ville ville : villes) {
			if (ville.getNomRegion().toLowerCase().equals(choix.toLowerCase())
					|| ville.getCodeRegion().toLowerCase().equals(choix.toLowerCase())) {
				somme += ville.getPopulation();
				nom = ville.getNomRegion();
			}
		}
		if (somme > 0) {
			System.out.println("Population de la région " + nom + " : " + somme);
		} else {
			System.out.println("Région " + choix + " non trouvée.");
		}
	}

}
