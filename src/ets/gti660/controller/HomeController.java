package ets.gti660.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import ets.gti660.data.AccessData;
import ets.gti660.model.Film;
import ets.gti660.model.Genre;
import ets.gti660.model.Langue;
import ets.gti660.model.Pays;
import ets.gti660.model.Personne;
import ets.gti660.template.FilmJDBCTemplate;
import ets.gti660.template.GenreJDBCTemplate;
import ets.gti660.template.LangueJDBCTemplate;
import ets.gti660.template.PaysJDBCTemplate;
import ets.gti660.template.PersonneJDBCTemplate;

@Controller
public class HomeController {

	@Autowired
	ServletContext servletContext;

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
	@Autowired
	private GenreJDBCTemplate genreJDBCTemplate;
	@Autowired
	private LangueJDBCTemplate langueJDBCTemplate;
	@Autowired
	private PaysJDBCTemplate paysJDBCTemplate;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String displayHome(ModelMap model, HttpSession session) {
		if (!accessData.isSessionActive()) {
			return "redirect:/login";
		}
		List<Genre> listGenre = genreJDBCTemplate.listGenres();
		List<Pays> listPays = paysJDBCTemplate.listPays();
		List<Langue> listLangue = langueJDBCTemplate.listLangues();
		model.addAttribute("listGenre", listGenre);
		model.addAttribute("listPays", listPays);
		model.addAttribute("listLangue", listLangue);
		String nom = accessData.getClient().getNomFamille() + " " + accessData.getClient().getPrenom();
		model.addAttribute("nom", nom);
		return "home";
	}

	@RequestMapping(value = "/gotoaccount", method = RequestMethod.GET)
	public String redirectAccount(ModelMap model) {
		return "";
	}

	@RequestMapping(value = "/gotocart", method = RequestMethod.GET)
	public String redirectCart(ModelMap model) {
		return "";
	}

	@RequestMapping(value = "/gotoloan", method = RequestMethod.GET)
	public String redirectLoan(ModelMap model) {
		return "";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		accessData.emptyAll();
		accessData.setSessionActive(false);
		return "";
	}

	@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = "application/json")
	public String search(ModelMap model, @RequestParam String search,
			@RequestParam(name = "genres[]", required = false) int[] genres,
			@RequestParam(name = "pays[]", required = false) int[] pays,
			@RequestParam(name = "langues[]", required = false) int[] langues,
			@RequestParam(name = "min", required = false) int min,
			@RequestParam(name = "max", required = false) int max) {
		boolean SQLcondition = false;
		boolean SQLsearch = false;
		List<Film> films = new ArrayList<Film>();
		List<Film> filmsName = new ArrayList<Film>();
		List<Film> filmsYear = new ArrayList<Film>();
		List<Film> filmsActeurs = new ArrayList<Film>();
		List<Film> filmsRealisateurs = new ArrayList<Film>();
		List<List<Film>> filmsSQL = new ArrayList<List<Film>>();
		List<Personne> personnes = new ArrayList<Personne>();
		List<Film> filmsScenaristes = new ArrayList<Film>();
		List<Film> filmsResume = new ArrayList<Film>();
		if (genres != null || pays != null || langues != null || (min != 0 && max != 0)) {
			SQLcondition = true;
		}
		if (search != "") {
			SQLsearch = true;
		}
		if (!SQLsearch) {
			if (!SQLcondition) {

			} else {
				if (genres != null) {
					for (int g : genres) {
						filmsSQL.add(filmJDBCTemplate.getFilmByGenre(g));
					}
				}
				if (pays != null) {
					for (int p : pays) {
						filmsSQL.add(filmJDBCTemplate.getFilmByPays(p));
					}
				}
				if (langues != null) {
					for (int l : langues) {
						filmsSQL.add(filmJDBCTemplate.getFilmByLangue(l));
					}
				}
				if (min != 0 && max != 0) {
					filmsSQL.add(filmJDBCTemplate.getFilmByDuration(min, max));
				}
				if (filmsSQL.size() > 1) {
					for (int i = 0; i < filmsSQL.size() - 1; i++) {
						films = findDupes(filmsSQL.get(i), filmsSQL.get(i + 1));
					}
				} else {
					films = filmsSQL.get(0);
				}
			}
		} else {
			if (!SQLcondition) {
				filmsName = filmJDBCTemplate.getFilmByName(search);
				filmsYear = filmJDBCTemplate.getFilmByYear(search);
				filmsActeurs = filmJDBCTemplate.getFilmByPersonneActeur(search);
				filmsRealisateurs = filmJDBCTemplate.getFilmByPersonneRealisateur(search);
				filmsScenaristes = filmJDBCTemplate.getFilmByPersonneScenariste(search);
				filmsResume = filmJDBCTemplate.getFilmByResume(search);
				personnes = personneJDBCTemplate.getPersonneByName(search);

				filmsName.addAll(filmsYear);
				filmsName.addAll(filmsActeurs);
				filmsName.addAll(filmsRealisateurs);
				filmsName.addAll(filmsScenaristes);
				filmsName.addAll(filmsResume);
				films = filmsName;
			} else {
				filmsName = filmJDBCTemplate.getFilmByName(search);
				filmsYear = filmJDBCTemplate.getFilmByYear(search);
				filmsActeurs = filmJDBCTemplate.getFilmByPersonneActeur(search);
				filmsRealisateurs = filmJDBCTemplate.getFilmByPersonneRealisateur(search);
				filmsScenaristes = filmJDBCTemplate.getFilmByPersonneScenariste(search);
				filmsResume = filmJDBCTemplate.getFilmByResume(search);
				if (genres != null) {
					for (int g : genres) {
						filmsSQL.add(filmJDBCTemplate.getFilmByGenre(g));
					}
				}
				if (pays != null) {
					for (int p : pays) {
						filmsSQL.add(filmJDBCTemplate.getFilmByPays(p));
					}
				}
				if (langues != null) {
					for (int l : langues) {
						filmsSQL.add(filmJDBCTemplate.getFilmByLangue(l));
					}
				}
				if (min != 0 && max != 0) {
					filmsSQL.add(filmJDBCTemplate.getFilmByDuration(min, max));
				}
				for (int i = 0; i < filmsSQL.size(); i++) {
					filmsName = findDupes(filmsName, filmsSQL.get(i));
				}
				for (int i = 0; i < filmsSQL.size(); i++) {
					filmsYear = findDupes(filmsYear, filmsSQL.get(i));
				}
				for (int i = 0; i < filmsSQL.size(); i++) {
					filmsActeurs = findDupes(filmsActeurs, filmsSQL.get(i));
				}
				for (int i = 0; i < filmsSQL.size(); i++) {
					filmsRealisateurs = findDupes(filmsRealisateurs, filmsSQL.get(i));
				}
				for (int i = 0; i < filmsSQL.size(); i++) {
					filmsScenaristes = findDupes(filmsScenaristes, filmsSQL.get(i));
				}
				for (int i = 0; i < filmsSQL.size(); i++) {
					filmsResume = findDupes(filmsResume, filmsSQL.get(i));
				}
				filmsName.addAll(filmsYear);
				filmsName.addAll(filmsActeurs);
				filmsName.addAll(filmsRealisateurs);
				filmsName.addAll(filmsScenaristes);
				filmsName.addAll(filmsResume);
				films = filmsName;
			}
		}

		Gson gson = new Gson();
		JsonElement filmsJson = gson.toJsonTree(films, new TypeToken<List<Film>>() {
		}.getType());
		JsonElement personnesJson = gson.toJsonTree(personnes, new TypeToken<List<Personne>>() {
		}.getType());

		saveToJsonFile(personnesJson, filmsJson);

		if (!filmsJson.isJsonArray()) {
			System.out.println("fail");
		}
		if (!personnesJson.isJsonArray()) {
			System.out.println("fail");
		}
		JsonArray jsonArrayFilm = filmsJson.getAsJsonArray();
		JsonArray jsonArrayPersonne = personnesJson.getAsJsonArray();
		JsonArray result = concatArray(jsonArrayFilm, jsonArrayPersonne);
		String js = result.toString();
		return js;
	}

	private void saveToJsonFile(JsonElement personnesJson, JsonElement filmsJson) {
		try {
			LocalDateTime now = LocalDateTime.now();
			int year = now.getYear();
			int month = now.getMonthValue();
			int day = now.getDayOfMonth();
			int hour = now.getHour();
			int minute = now.getMinute();
			int second = now.getSecond();
			int millis = now.get(ChronoField.MILLI_OF_SECOND);
			String filename = "" + year + month + day + hour + minute + second + millis;
			String path = servletContext.getRealPath("/resources/download/json/" + filename + ".json");
			File file = new File(path);
			if (file.createNewFile()) {
				System.out.println("File is created in " + path + ".");
			} else {
				System.out.println("File already exists.");
			}
			FileWriter writer = new FileWriter(file);
			String personneString = personnesJson.toString();
			int pIndexOfOpenBracket = personneString.indexOf("[");
			int pIndexOfLastBracket = personneString.lastIndexOf("]");
			String personneJson = personnesJson.toString().substring(pIndexOfOpenBracket + 1, pIndexOfLastBracket);

			String filmString = filmsJson.toString();
			int fIndexOfOpenBracket = filmString.indexOf("[");
			int fIndexOfLastBracket = filmString.lastIndexOf("]");
			String filmJson = filmsJson.toString().substring(fIndexOfOpenBracket + 1, fIndexOfLastBracket);
			if (!personneJson.isEmpty() && !filmJson.isEmpty()) {
				String json = "[" + personneJson + "," + filmJson + "]";
				writer.write(json);
				writer.close();
			} else if (personneJson.isEmpty() && !filmJson.isEmpty()) {
				String json = "[" + filmJson + "]";
				writer.write(json);
				writer.close();
			} else if (!personneJson.isEmpty() && filmJson.isEmpty()) {
				String json = "[" + personneJson + "]";
				writer.write(json);
				writer.close();
			} else {
				file.delete();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Not working!");
		}
	}

	@RequestMapping(value = "/uploadjson", method = RequestMethod.POST)
	public @ResponseBody String uploadJson(@RequestParam("file") MultipartFile file) throws IOException {
		InputStream inputStream = file.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String line, jsonString = "";
		while ((line = bufferedReader.readLine()) != null) {
			jsonString += line;
		}
		return jsonString;
	}

	@RequestMapping(value = "/gotodetails", method = RequestMethod.GET)
	public String redirectDetails(ModelMap model, @RequestParam(name = "id", required = false) Integer id,
			@RequestParam(name = "type", required = false) String type, RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> flash = RequestContextUtils.getOutputFlashMap(request);
		flash.put("id", id);
		flash.put("type", type);
		response.sendRedirect(request.getContextPath() + "/details");
		return "redirect:/details";
	}

	@RequestMapping(value = "/gotodetailspersonne", method = RequestMethod.GET)
	public String redirectDetailsPersonne(ModelMap model, @RequestParam(name = "id", required = false) Integer id,
			@RequestParam(name = "type", required = false) String type, RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> flash = RequestContextUtils.getOutputFlashMap(request);
		// populate the flash map with attributes you
		// want to pass to redirected controller
		flash.put("id", id);
		flash.put("type", type);
		response.sendRedirect(request.getContextPath() + "/details_personne");
		return "redirect:/details_personne";
	}

	private JsonArray concatArray(JsonArray... arrs) {
		JsonArray result = new JsonArray();
		for (JsonArray arr : arrs) {
			for (int i = 0; i < arr.size(); i++) {
				result.add(arr.get(i));
			}
		}
		return result;
	}

	private List<Film> findDupes(List<Film> a, List<Film> b) {
		List<Film> dupes = new ArrayList<Film>();
		for (int i = 0; i < a.size(); i++) {
			for (int j = 0; j < b.size(); j++) {
				if (a.get(i).getId() == b.get(j).getId()) {
					dupes.add(a.get(i));
				}
			}
		}
		return dupes;
	}
}
