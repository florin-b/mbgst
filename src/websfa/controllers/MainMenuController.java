package websfa.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import websfa.beans.Login;
import websfa.beans.User;
import websfa.database.user.UserDAO;

@Controller
public class MainMenuController {

	@Autowired
	UserDAO loginService;

	private User user;

	@RequestMapping(value = "/auth/login", method = RequestMethod.GET)
	public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("login");
		Login login = new Login();
		model.addObject("login", login);
		return model;
	}

	@RequestMapping(value = "/auth/login", method = RequestMethod.POST)
	public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("login") Login login) {

		ModelAndView model;
		user = loginService.validateUser(login);

		if (user.isSuccessLogon()) {
			model = new ModelAndView("mainMenu");
			model.addObject("user", user);
		} else {
			model = new ModelAndView("login");
			model.addObject("login", login);
			request.setAttribute("infoMsg", user.getLogonMessage());
		}

		return model;
	}

	@RequestMapping(value = "/stocuri", method = RequestMethod.GET)
	public ModelAndView executeStocuri(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model;

		Gson gson = new GsonBuilder().create();

		model = new ModelAndView("stocuri");
		model.addObject("user", user);
		model.addObject("userjson", gson.toJson(user));

		return model;
	}

	@RequestMapping(value = "/preturi", method = RequestMethod.GET)
	public ModelAndView executePreturi(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model;

		Gson gson = new GsonBuilder().create();

		model = new ModelAndView("preturi");
		model.addObject("user", user);
		model.addObject("userjson", gson.toJson(user));

		return model;
	}

	@RequestMapping(value = "/afiscom", method = RequestMethod.GET)
	public ModelAndView afiseazaComanda(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model;

		Gson gson = new GsonBuilder().create();

		model = new ModelAndView("afiseaza_comanda");
		model.addObject("user", user);
		model.addObject("userjson", gson.toJson(user));

		return model;
	}
	
	
	@RequestMapping(value = "/aprobacmd", method = RequestMethod.GET)
	public ModelAndView aprobaComanda(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model;

		Gson gson = new GsonBuilder().create();

		model = new ModelAndView("aproba_comanda");
		model.addObject("user", user);
		model.addObject("userjson", gson.toJson(user));

		return model;
	}
	
	@RequestMapping(value = "/comanda", method = RequestMethod.GET)
	public ModelAndView creeazaComanda(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model;

		Gson gson = new GsonBuilder().create();

		model = new ModelAndView("creare_comanda");
		model.addObject("user", user);
		model.addObject("userjson", gson.toJson(user));

		return model;
	}

}
