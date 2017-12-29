package websfa.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import websfa.beans.articole.CautareComanda;
import websfa.beans.articole.ComandaDetalii;
import websfa.beans.articole.ComandaHeader;
import websfa.model.articole.OperatiiComenzi;

@Controller
public class ComenziController {

	@RequestMapping(value = "/getcom", produces = "application/json")
	@ResponseBody
	public List<ComandaHeader> getComenzi(CautareComanda cautareComanda) {

		return new OperatiiComenzi().getComenzi(cautareComanda);

	}

	@RequestMapping(value = "/getdetcom", produces = "application/json")
	@ResponseBody
	public ComandaDetalii getDetaliiComanda(String idCmd) {

		return new OperatiiComenzi().getDetaliiComanda(idCmd);

	}

}
