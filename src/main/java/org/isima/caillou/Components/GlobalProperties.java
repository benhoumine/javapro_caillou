package org.isima.caillou.Components;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.yml")
@ConfigurationProperties(prefix="openfoodfact")
public class GlobalProperties {
	
    private String apiurl;
    
    private NutritionProperties nutriments ;
	

	public String getApiUrl() {
		return apiurl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiurl = apiUrl;
	}

	public NutritionProperties getNutriments() {
		return nutriments;
	}

	public void setNutriments(NutritionProperties nutriments) {
		this.nutriments = nutriments;
	}
	
	
    
}
