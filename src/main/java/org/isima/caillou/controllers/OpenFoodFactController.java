package org.isima.caillou.controllers;

import java.io.IOException;

import org.isima.caillou.service.InformationProductionSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("openfoodfact")
public class OpenFoodFactController {
	
	
	@Autowired
	private InformationProductionSerivce informationProductService;
	
	//US 0 : Récuperer les informations d'un produit sur Open Food Fact	
	@GetMapping("/{codeBarre}")
    public String getInformationProduct(@PathVariable String codeBarre) {
        try {
			return informationProductService.getInformation(codeBarre).toString();
		} catch (IOException e) {

		}
        return null ; 
    }
	
	//US 1 : Calcul de la composante N du Score nutritionnel
	@GetMapping("/score/{codeBarre}")
    public Integer getScoreNutrionnel(@PathVariable String codeBarre) {
       return informationProductService.calculateScoreNegativeNutrionnel(codeBarre);
    }
	
	
	
	
	

}
