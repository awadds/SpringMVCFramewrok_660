package ets.gti660.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ets.gti660.data.AccessData;
import ets.gti660.model.CarteCredit;
import ets.gti660.model.Client;
import ets.gti660.template.CarteCreditJDBCTemplate;
import ets.gti660.template.ClientJDBCTemplate;

@Controller
public class AccountController {

	@Autowired
	private AccessData accessData;

	@Autowired
	private ClientJDBCTemplate clientJDBCTemplate;

	@Autowired
	private CarteCreditJDBCTemplate carteCreditJDBCTemplate;

	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String displayAccount(ModelMap model) {
		if (!accessData.isSessionActive()) {
			return "redirect:/login";
		}
		System.out.println("Account.");
		Client c = accessData.getClient();
		List<CarteCredit> cartes = accessData.getCartes();
		model.addAttribute("nom", c.getNomFamille());
		model.addAttribute("prenom", c.getPrenom());
		model.addAttribute("courriel", c.getCourriel());
		model.addAttribute("tel", c.getTel());
		model.addAttribute("anniversaire", c.getAnniversaire());
		model.addAttribute("adresse", c.getAdresse());
		model.addAttribute("ville", c.getVille());
		model.addAttribute("province", c.getProvince());
		model.addAttribute("codepostal", c.getCodePostal());
		model.addAttribute("forfait", c.getForfait());
		model.addAttribute("cartes", cartes);
		List<String> forfaits = clientJDBCTemplate.listForfait();
		model.addAttribute("forfaits", forfaits);
		List<String> types = carteCreditJDBCTemplate.listType();
		model.addAttribute("types", types);		return "account";

	}

	@RequestMapping(value = "/saveGeneral", method = RequestMethod.GET)
	public String saveGeneral(ModelMap model, @RequestParam String nom, @RequestParam String prenom,
			@RequestParam String courriel, @RequestParam String tel, @RequestParam Date anniversaire,
			@RequestParam String adresse, @RequestParam String ville, @RequestParam String province,
			@RequestParam String codepostal, @RequestParam String forfait) {
		System.out.println("SaveGeneral");
		Client client = new Client(-1, nom, prenom, courriel, tel, anniversaire, adresse, ville, province, codepostal,
				forfait);
		client.setId(accessData.getClient().getId());
		clientJDBCTemplate.update(client);
		accessData.setClient(client);
		return "account";
	}

	@RequestMapping(value = "/saveCarte", method = RequestMethod.GET)
	public String saveCarte(ModelMap model, @RequestParam int id, @RequestParam String numero,
			@RequestParam String expmois, @RequestParam String expannee, @RequestParam String type) {
		System.out.println("SaveCarte");
		CarteCredit carte = new CarteCredit(id, accessData.getClient().getId(), numero, expmois, expannee, type);
		carteCreditJDBCTemplate.update(carte);
		List<CarteCredit> cartes = accessData.getCartes();
		cartes = removeByIdCarte(cartes, id);
		cartes.add(carte);
		accessData.setCartes(cartes);
		return "account";
	}

	private List<CarteCredit> removeByIdCarte(List<CarteCredit> cartes, int id) {
		for (CarteCredit c : cartes) {
			if (c.getId() == id) {
				cartes.remove(c);
				return cartes;
			}
		}
		return cartes;
	}
}
