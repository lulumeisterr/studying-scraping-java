package br.com.scraping.controller;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import br.com.scraping.model.Noticia;


public class RecuperandoInformacaoController {

	private static String URL = "https://noticias.uol.com.br/ultimas/index.htm";
	ArrayList<Noticia> listaNoticias = new ArrayList<Noticia>();


	public List<Noticia> recuperandoTodasNoticias() {
		Document document = null;
		try {
			document = Jsoup.connect(URL).userAgent("Jsoup").get();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Erro na URL");
			return null;
		}

		Elements e = document.getElementsByClass("thumbnail-standard");

		int i = 0;
		for(Element es : e) {
			
			Noticia noticias = new Noticia();
			
			noticias.setId(i);
			noticias.setCanalAbertura(es.select(".thumb-kicker").text());
			noticias.setTitulo(es.select(".thumb-title").text().replace(",", " "));
			noticias.setLink(es.select("a").attr("href"));
			noticias.setHora(es.select("time").first().text());
			listaNoticias.add(noticias);
			i++;
		}
	
		return listaNoticias;
	}

}
