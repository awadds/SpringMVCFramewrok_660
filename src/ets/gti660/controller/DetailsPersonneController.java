package ets.gti660.controller;

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
import org.springframework.web.servlet.support.RequestContextUtils;

import ets.gti660.data.AccessData;
import ets.gti660.model.Film;
import ets.gti660.model.Personne;
import ets.gti660.template.FilmJDBCTemplate;
import ets.gti660.template.PersonneJDBCTemplate;

@Controller
public class DetailsPersonneController {

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

	@RequestMapping(value = "/details_personne", method = RequestMethod.GET)
	public String displayDetails(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		if (!accessData.isSessionActive()) {
			return "redirect:/login";
		}
		@SuppressWarnings("unchecked")
		Map<String, Object> flash = (Map<String, Object>) RequestContextUtils.getInputFlashMap(request);
		if (flash != null) {
			int id = (int) flash.get("id");
			// String type = (String) flash.get("type");
			Personne personne = personneJDBCTemplate.getPersonne(id);
			model.addAttribute("personne", personne);
			List<Film> filmsActeur = filmJDBCTemplate.getFilmByPersonneActeur(id);
			List<Film> filmsRealisateur = filmJDBCTemplate.getFilmByPersonneRealisateur(id);
			model.addAttribute("filmsActeur", filmsActeur);
			model.addAttribute("filmsRealisateur", filmsRealisateur);
			String nom = accessData.getClient().getNomFamille() + " " + accessData.getClient().getPrenom();
			model.addAttribute("nom", nom);
		}
		return "details_personne";
	}

}
