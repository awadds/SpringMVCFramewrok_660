package ets.gti660.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ets.gti660.data.AccessData;
import ets.gti660.data.Emprunt;
import ets.gti660.model.CarteCredit;
import ets.gti660.model.Client;
import ets.gti660.model.CompteUtilisateur;
import ets.gti660.template.CarteCreditJDBCTemplate;
import ets.gti660.template.ClientJDBCTemplate;
import ets.gti660.template.CompteUtilisateurJDBCTemplate;



@Controller
public class LoginController {

	@ModelAttribute("accessData")
	public AccessData getAccessData() {
		return this.accessData;
	}

	@Autowired
	private AccessData accessData;
	@Autowired
	private CompteUtilisateurJDBCTemplate compteUtilisateurJDBCTemplate;
	@Autowired
	private ClientJDBCTemplate clientJDBCTemplate;
	@Autowired
	private CarteCreditJDBCTemplate carteCreditJDBCTemplate;
	
	@RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
	public String displayLogin(ModelMap model) {
		accessData.emptyAll();
		accessData.setSessionActive(false);
		return "login";
	}

	@ResponseBody
	@RequestMapping(value = "/login_submit", method = RequestMethod.GET)
	public String loginSubmit(@RequestParam("email") String email, @RequestParam("password") String password, HttpServletResponse response) {
		/*System.out.println("email : " + email);*/
		CompteUtilisateur compte;
		Client client;
		List<CarteCredit> cartes;
		try {
			compte = compteUtilisateurJDBCTemplate.getCompteUtilisateurByAttributes(email, password);
		} catch (DataAccessException e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			String accountNotFound = "Compte inexistant ou identifiants erronés";
			return accountNotFound;
		}
		/*System.out.println("Compte ID : " + compte.getId());*/
		accessData.setUserID(compte.getId());
		accessData.setCompte(compte);
		client = clientJDBCTemplate.getClient(compte.getId());
		accessData.setClient(client);
		cartes = carteCreditJDBCTemplate.getCartesByIdClient(compte.getId());
		accessData.setCartes(cartes);
		accessData.setCart(new ArrayList<Emprunt>());
		accessData.setSessionActive(true);	
		return ""; // Redirection is done in script_login.js



	}
}
