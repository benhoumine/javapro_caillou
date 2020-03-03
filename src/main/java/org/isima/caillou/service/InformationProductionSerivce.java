package org.isima.caillou.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONObject;
import org.springframework.stereotype.Service;



@Service
public class InformationProductionSerivce {

	public InformationProductionSerivce() {
		//pour instanciation via Autowired
	}
	
	private static final String URL = "https://fr.openfoodfacts.org/api/v0/produit/";

	
	 private String readAll(Reader rd) throws IOException {
		    StringBuilder sb = new StringBuilder();
		    int cp;
		    while ((cp = rd.read()) != -1) {
		      sb.append((char) cp);
		    }
		    return sb.toString();
		  }
	 
	 
	 public JSONObject readJsonFromUrl(String url) throws IOException{
		    InputStream is = new URL(url).openStream();
		    try(  BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));) {
		      String jsonText = readAll(rd);
		      return new JSONObject(jsonText);
		    } finally {
		      is.close();
		    }
		  }
	 
	 public JSONObject getInformation(String codeBarre) throws IOException {
		    JSONObject json = readJsonFromUrl(URL+codeBarre);
            return (JSONObject) json.get("product") ; 
	 }
	 
	 
	 public Integer calculateScoreNegativeNutrionnel(String codeBarre) {
		 
		 JSONObject produit;
		 Integer scoreNutrionnel = 0 ; 
		try {
			produit = getInformation(codeBarre);
			 JSONObject nutriscoreData = (JSONObject)produit.get("nutriscore_data") ;
			 Integer energyPoints = Integer.parseInt(nutriscoreData.get("energy_points").toString());
			 Integer saturatedFatRatioPoints = Integer.parseInt(nutriscoreData.get("saturated_fat_ratio_points").toString());
			 Integer sodiumPoints = Integer.parseInt(nutriscoreData.get("sodium_points").toString());
			 Integer sugarsPoints = Integer.parseInt(nutriscoreData.get("sugars_points").toString());
			 scoreNutrionnel =  energyPoints+saturatedFatRatioPoints+sodiumPoints+sugarsPoints;

		} catch (IOException e) {
			e.printStackTrace();
		} 
		 return scoreNutrionnel ; 
	 }
	 
	 public Integer calculateScorePositiveNutrionnel(String codeBarre) {
		 
		 JSONObject produit;
		 Integer scoreNutrionnel = 0 ; 
		try {
			produit = getInformation(codeBarre);
			JSONObject nutriscoreData = (JSONObject)produit.get("nutriscore_data") ;
			Integer fiberPoints = Integer.parseInt(nutriscoreData.get("fiber_points").toString());
			Integer proteinsPoints = Integer.parseInt(nutriscoreData.get("proteinsPoints").toString());
			scoreNutrionnel =  fiberPoints+proteinsPoints;

		} catch (IOException e) {
			e.printStackTrace();
		} 
		 return scoreNutrionnel ; 
	 }
	 
	 public String calculateScoreNutrionnel(String codeBarre) {
		 Integer score =  calculateScoreNegativeNutrionnel(codeBarre) - calculateScorePositiveNutrionnel(codeBarre); 
		 String classe = "";
		 return classe;
	 }
}
