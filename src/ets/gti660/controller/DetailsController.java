package ets.gti660.controller;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import ets.gti660.data.AccessData;
import ets.gti660.data.Emprunt;
import ets.gti660.model.Film;
import ets.gti660.model.Personne;
import ets.gti660.template.FilmJDBCTemplate;
import ets.gti660.template.PersonneJDBCTemplate;

@Controller
public class DetailsController {

	@ModelAttribute("accessData")
	public AccessData getAccessData() {
		return this.accessData;
	}

	@Autowired
	private AccessData accessData;
	@Autowired
	private FilmJDBCTemplate filmJDBCTemplate;
	@Autowired
	private PersonneJDBCTemplate personneJDBCTemplate;

	private int id = -1;

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public String displayDetails(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		if (!accessData.isSessionActive()) {
			return "redirect:/login";
		}
		@SuppressWarnings("unchecked")
		Map<String, Object> flash = (Map<String, Object>) RequestContextUtils.getInputFlashMap(request);
		if (flash != null) {
			id = (int) flash.get("id");
			// String type = (String) flash.get("type");
			Film film = filmJDBCTemplate.getFilm(id);
			model.addAttribute("movie", film);

			List<Personne> acteurs = personneJDBCTemplate.getPersonneActeurByFilm(id);
			List<Personne> realisateurs = personneJDBCTemplate.getPersonneRealisateurByFilm(id);
			String nom = accessData.getClient().getNomFamille() + " " + accessData.getClient().getPrenom();
			model.addAttribute("nomUser", nom);
			model.addAttribute("acteurs", acteurs);
			model.addAttribute("realisateurs", realisateurs);
			
		}
		return "details";
	}

	@ResponseBody
	@RequestMapping(value = "/addtocart", method = RequestMethod.GET)
	public String addToCart(ModelMap model, @RequestParam Date debut, @RequestParam Date fin,
			@RequestParam float cout) {
		if (id != -1) {
			Film film = filmJDBCTemplate.getFilm(id);
			Emprunt l = new Emprunt(film, debut, fin, cout);
			accessData.addCart(l);
			return "Film ajouté.";
		}
		return "Erreur.";
	}

}
