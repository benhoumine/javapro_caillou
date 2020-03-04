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

/****
 * 
 * 
 * Ce Service nous permet de parcourir un fichier json afin de determiner les
 * informations nécessaire pour le calcul de la valeur nutritionnelle
 *
 */

@Service
public class JsonBrowseService {

	public JsonBrowseService() {
		// Pour l'injection

	}

	private String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public JSONObject readJsonFromUrl(String url) throws IOException {
		InputStream is = new URL(url).openStream();
		try (BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));) {
			String jsonText = readAll(rd);
			return new JSONObject(jsonText);
		} finally {
			is.close();
		}
	}

}
