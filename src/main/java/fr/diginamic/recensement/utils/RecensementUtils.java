package fr.diginamic.recensement.utils;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;

/** Classe permettant de lire le contenu d'un fichier
 * @author DIGINAMIC
 *
 */
public class RecensementUtils {

	/** Lit le contenu du fichier en paramétre, contenant des données de recensement avec le format attendu,
	 * et retourne une instance de la classe Recensement avec toutes ses villes
	 * @param cheminFichier chemin d'accés au fichier sur le disque dur
	 * @return {@link Recensement}
	 */
	public static Recensement lire(String cheminFichier){
		Recensement recensement = new Recensement();
		
		List<String> lignes = null;
		try {
			File file = new File(cheminFichier);
			lignes = FileUtils.readLines(file, "UTF-8");
			
			// On supprime la ligne d'entéte avec les noms des colonnes
			lignes.remove(0);
			
			for (String ligne: lignes){
				ParseurVille.ajoutLigne(recensement, ligne);
			}
			return recensement;
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return null;
		}
		
	}

	/**
	 * Methode pour identifier si au moins une ville d une liste est dans un departement donne
	 * Si aucune n est trouvee dans la liste globale, ce code n existe pas
	 * 
	 * @param codeDepartement le code du departement teste
	 * @param villes la liste
	 * @return
	 */
	public static boolean departementTrouve(String codeDepartement, List<Ville> villes) {
		boolean trouve = false;
		Iterator<Ville> iterator = villes.iterator();
		while (iterator.hasNext() && !trouve) {
			Ville ville = (Ville) iterator.next();
			trouve = ville.getCodeDepartement().toLowerCase().startsWith(codeDepartement.toLowerCase());
		}

		return trouve;
	}

	/**
	 * Methode pour identifier si au moins une ville d une liste est dans une region donnee
	 * Si aucune n est trouvee dans la liste globale, ce code n existe pas
	 * 
	 * @param identifiantRegion le nom de la region ou le code de la region
	 * @param villes la liste
	 * @return
	 */
	public static boolean regionTrouvee(String identifiantRegion, List<Ville> villes) {
		boolean trouve = false;
		Iterator<Ville> iterator = villes.iterator();
		while (iterator.hasNext() && !trouve) {
			Ville ville = (Ville) iterator.next();
			trouve = ville.getCodeRegion().toLowerCase().startsWith(identifiantRegion.toLowerCase()) || ville.getNomRegion().toLowerCase().startsWith(identifiantRegion.toLowerCase());
		}

		return trouve;
	}
}
