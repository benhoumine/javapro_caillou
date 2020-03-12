package org.isima.caillou.controller;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.isima.caillou.controllers.OpenFoodFactController;
import org.isima.caillou.models.Classe;
import org.isima.caillou.service.InformationProductSerivce;

@WebMvcTest(OpenFoodFactController.class)
public class OpenFoodFactControllerTest {

	@MockBean
	private InformationProductSerivce service;

	@Autowired
	private MockMvc mockMvc;

	
	@Test
	public void verificationNegativeNutritionnelMethode() throws Exception {
		when(service.calculateScoreNegativeNutritionnel("000")).thenReturn(0);
		this.mockMvc.perform(get("/openfoodfact/score/negative/000")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("0")));
	}
	
	
	@Test
	public void verificationPositiveNutritionnelMethode() throws Exception {
		when(service.calculateScorePositiveNutritionnel("000")).thenReturn(0);
		this.mockMvc.perform(get("/openfoodfact/score/positive/000")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("0")));
	}
	
	@Test
	public void verificationNutritionnelMethode() throws Exception {
		when(service.calculateScoreNutritionnel("000")).thenReturn(Classe.BON);
		this.mockMvc.perform(get("/openfoodfact/score/000")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string("Classe BON Couleur LIGHT GREEN"));
	}
	
}
