package ets.gti660.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ets.gti660.data.AccessData;
import ets.gti660.data.Emprunt;
import ets.gti660.model.Film;
import ets.gti660.template.PretJDBCTemplate;

@Controller
public class CartController {
	@Autowired
	private AccessData accessData;

	@Autowired
	private PretJDBCTemplate pretJDBCTemplate;

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String displayCart(ModelMap model) {
		if (!accessData.isSessionActive()) {
			return "redirect:/login";
		}
		System.out.println("Cart.");
		List<Film> films = accessData.getCartFilms();
		model.addAttribute("films", films);
		String nom = accessData.getClient().getNomFamille() + " " + accessData.getClient().getPrenom();
		model.addAttribute("nom", nom);
		return "cart";

	}

	@ResponseBody
	@RequestMapping(value = "/removefilm", method = RequestMethod.GET)
	public String removeFilm(ModelMap model, @RequestParam int id) {
		accessData.deleteCart(id);
		return "success";
	}

	@ResponseBody
	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	public String confirm(ModelMap model) {
		for (Emprunt l : accessData.getCart()) {
			float cout = l.getCout();
			Date debut = l.getDebut();
			Date fin = l.getFin();
			int idCarte = accessData.getCartes().get(0).getId();
			int idClient = accessData.getUserID();
			int idFilm = l.getFilm().getId();
			pretJDBCTemplate.insert(cout, debut, fin, idCarte, idClient, idFilm);
		}
		return "Prêts effectués";
	}
}
