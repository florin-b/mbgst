package websfa.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import websfa.beans.articole.Articol;
import websfa.beans.articole.ArticolInfoVanzare;
import websfa.beans.articole.ArticolPret;
import websfa.beans.articole.ArticolStoc;
import websfa.model.articole.OperatiiArticole;

@Controller
public class ArticoleController {

	@RequestMapping(value = "/cauta", produces = "application/json")
	@ResponseBody
	public List<Articol> cautaArticole(Articol articolCautare) {

		return new OperatiiArticole().cautaArticole(articolCautare);

	}

	@RequestMapping(value = "/stoc", produces = "application/json")
	@ResponseBody
	public List<ArticolStoc> afiseazaStoc(String codArticol, String filiala) {

		return new OperatiiArticole().getStoc(codArticol, filiala);

	}

	@RequestMapping(value = "/pret", produces = "application/json")
	@ResponseBody
	public ArticolPret afiseazaPret(String codArticol, String filiala, String departament) {

		return new OperatiiArticole().getPret(codArticol, filiala, departament);

	}

	@RequestMapping(value = "/stocdepozit", produces = "application/json")
	@ResponseBody
	public ArticolStoc afiseazaStocDepozit(String codArticol, String filiala, String depozit) {

		return new OperatiiArticole().getStoc(codArticol, filiala, depozit);

	}

	@RequestMapping(value = "/articolvanzare", produces = "application/json")
	@ResponseBody
	public ArticolInfoVanzare getInfoArticolVanzare(String codArticol, String filiala, String depozit, String codDepart) {

		ArticolInfoVanzare artInfo = new ArticolInfoVanzare();
		artInfo.setStocArticol(new OperatiiArticole().getStoc(codArticol, filiala, depozit));
		artInfo.setPretArticol(new OperatiiArticole().getPret(codArticol, filiala, codDepart));

		return artInfo;

	}

}
