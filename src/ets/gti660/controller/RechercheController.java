package ets.gti660.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ets.gti660.data.AccessData;
import ets.gti660.model.Pret;
import ets.gti660.template.FilmJDBCTemplate;
import ets.gti660.template.PretJDBCTemplate;

@Controller
public class RechercheController {

	@ModelAttribute("accessData")
	public AccessData getAccessData() {
		return this.accessData;
	}
	
	@Autowired
    private AccessData accessData;
	@Autowired
	private PretJDBCTemplate pretJDBCTemplate;
	@Autowired
	private FilmJDBCTemplate filmJDBCTemplate;
	
	@RequestMapping(value = "/recherche", method = RequestMethod.GET)
	public String displayCart(ModelMap model) {
		if (!accessData.isSessionActive()){
			return "redirect:/login";
		}
		System.out.println("Recherche.");
		List<Pret> prets = pretJDBCTemplate.getPretsByIdClient(accessData.getUserID());	
		
		if(prets.size() != 0){
			for(Pret pret : prets){
				pret.setTitreFilm(filmJDBCTemplate.getFilm(pret.getIdFilm()).getTitre());
			}
		}
		String nom = accessData.getClient().getNomFamille() + " " + accessData.getClient().getPrenom();
		model.addAttribute("nom", nom);
		model.addAttribute("prets", prets);
		return "recherche";		
	}
}
