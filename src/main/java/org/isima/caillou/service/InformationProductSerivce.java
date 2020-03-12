package org.isima.caillou.service;

import java.io.IOException;

import org.isima.caillou.Components.GlobalProperties;
import org.isima.caillou.models.Classe;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service pour la récupération des informations d'un produit
 *
 */
@Service
public class InformationProductSerivce {

	@Autowired
	private JsonBrowseService jsonService;

	@Autowired
	private GlobalProperties globalProperties;

	public InformationProductSerivce() {
		// pour instanciation via Autowired
	}

	public JSONObject getInformation(String codeBarre) throws IOException {
		String url = globalProperties.getApiUrl();
		JSONObject json = jsonService.readJsonFromUrl(url+codeBarre);
		return (JSONObject) json.get("product");
	}

	public Integer calculateScoreNegativeNutritionnel(String codeBarre) {

		JSONObject produit;
		Integer scoreNutrionnel = 0;
		Integer ingredientPoint ; 
		try {
			produit = getInformation(codeBarre);
			JSONObject nutriscoreData = (JSONObject) produit.get("nutriscore_data");
			for(String ingredientNegtif : globalProperties.getNutriments().getListnutritionnegative()) {
				ingredientPoint = Integer.parseInt(nutriscoreData.get(ingredientNegtif).toString());
				scoreNutrionnel+= ingredientPoint  ; 
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return scoreNutrionnel;
	}

	public Integer calculateScorePositiveNutritionnel(String codeBarre) {
		JSONObject produit;
		Integer scoreNutrionnel = 0;
		Integer ingredientPoint ; 
		try {
			produit = getInformation(codeBarre);
			JSONObject nutriscoreData = (JSONObject) produit.get("nutriscore_data");
			for(String ingredientPositif : globalProperties.getNutriments().getListnutritionpositive()) {
				ingredientPoint = Integer.parseInt(nutriscoreData.get(ingredientPositif).toString());
				scoreNutrionnel+= ingredientPoint  ; 
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return scoreNutrionnel;
	}

	public Classe calculateScoreNutritionnel(String codeBarre) {
		Integer score = calculateScoreNegativeNutritionnel(codeBarre) - calculateScorePositiveNutritionnel(codeBarre);
		if (score <= -1) {
			return Classe.TROPBON;
		} else if (score >= 0 && score <= 2) {
			return Classe.BON;
		} else if (score >= 3 && score <= 10) {
			return Classe.MANGEABLE;
		} else if (score >= 11 && score <= 18) {
			return Classe.MOUI;
		} else {
			return Classe.DEGUEU;
		}
	}
}
