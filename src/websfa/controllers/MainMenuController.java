package websfa.controllers;

import java.io.IOException;

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
import websfa.utils.MailOperations;
import websfa.utils.Utils;

@Controller
public class MainMenuController {

	@Autowired
	UserDAO loginService;

	private User user;

	@RequestMapping(value = { "/auth/login", "login", "/" }, method = RequestMethod.GET)
	public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("login");
		Login login = new Login();
		model.addObject("login", login);
		return model;
	}

	@RequestMapping(value = { "/auth/login", "login", "/" }, method = RequestMethod.POST)
	public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("login") Login login) {

		ModelAndView model = null;
		user = loginService.validateUser(login);

		try {

			if (user.isSuccessLogon()) {
				model = new ModelAndView("login");
				model.addObject("redirectUrl", "/main");

			} else {
				model = new ModelAndView("login");
				model.addObject("login", login);
				request.setAttribute("infoMsg", user.getLogonMessage());
			}
		} catch (Exception e) {
			MailOperations.sendMail(Utils.getStackTrace(e));
		}

		return model;
	}

	@RequestMapping(value = { "/main" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView executeMainMenu(HttpServletRequest request, HttpServletResponse response) throws IOException {

		if (user == null) {
			response.sendRedirect("exit");
		}

		ModelAndView model;
		Gson gson = new GsonBuilder().create();

		model = new ModelAndView("mainMenu");
		model.addObject("user", user);
		model.addObject("userjson", gson.toJson(user));

		return model;
	}

	@RequestMapping(value = "/stocuri", method = RequestMethod.GET)
	public ModelAndView executeStocuri(HttpServletRequest request, HttpServletResponse response) throws IOException {

		if (user == null) {
			response.sendRedirect("exit");
		}

		ModelAndView model;
		Gson gson = new GsonBuilder().create();

		model = new ModelAndView("stocuri");
		model.addObject("user", user);
		model.addObject("userjson", gson.toJson(user));

		return model;
	}

	@RequestMapping(value = "/preturi", method = RequestMethod.GET)
	public ModelAndView executePreturi(HttpServletRequest request, HttpServletResponse response) throws IOException {

		if (user == null) {
			response.sendRedirect("exit");
		}

		ModelAndView model;
		Gson gson = new GsonBuilder().create();

		model = new ModelAndView("preturi");
		model.addObject("user", user);
		model.addObject("userjson", gson.toJson(user));

		return model;
	}

	@RequestMapping(value = "/afiscom", method = RequestMethod.GET)
	public ModelAndView afiseazaComanda(HttpServletRequest request, HttpServletResponse response) throws IOException {

		if (user == null) {
			response.sendRedirect("exit");
		}

		ModelAndView model;
		Gson gson = new GsonBuilder().create();

		model = new ModelAndView("afiseaza_comanda");
		model.addObject("user", user);
		model.addObject("userjson", gson.toJson(user));

		return model;
	}

	@RequestMapping(value = "/aprobacmd", method = RequestMethod.GET)
	public ModelAndView aprobaComanda(HttpServletRequest request, HttpServletResponse response) throws IOException {

		if (user == null) {
			response.sendRedirect("exit");
		}

		ModelAndView model;
		Gson gson = new GsonBuilder().create();

		model = new ModelAndView("aproba_comanda");
		model.addObject("user", user);
		model.addObject("userjson", gson.toJson(user));

		return model;
	}

	@RequestMapping(value = "/modifcmd", method = RequestMethod.GET)
	public ModelAndView modificaComanda(HttpServletRequest request, HttpServletResponse response) throws IOException {

		if (user == null) {
			response.sendRedirect("exit");
		}

		ModelAndView model;
		Gson gson = new GsonBuilder().create();

		model = new ModelAndView("modificare_comanda");
		model.addObject("user", user);
		model.addObject("userjson", gson.toJson(user));

		return model;
	}

	@RequestMapping(value = "/comanda", method = RequestMethod.GET)
	public ModelAndView creeazaComanda(HttpServletRequest request, HttpServletResponse response) throws IOException {

		if (user == null) {
			response.sendRedirect("exit");
		}

		ModelAndView model;
		Gson gson = new GsonBuilder().create();

		model = new ModelAndView("creare_comanda");
		model.addObject("user", user);
		model.addObject("userjson", gson.toJson(user));

		return model;
	}

	@RequestMapping(value = "/exit", method = RequestMethod.GET)
	public String executeExit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		user = null;
		return "redirect:/login";

	}

}
