package websfa.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import websfa.beans.CautaCmdAprob;
import websfa.beans.Comanda;
import websfa.beans.ComandaAprobareAfis;
import websfa.beans.ComandaAprobareDetalii;
import websfa.beans.Status;
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

	@RequestMapping(value = "/salveazaComanda", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Status salveazaComanda(@RequestBody Comanda comanda) {

		return new OperatiiComenzi().salveazaComanda(comanda);

	}

	@RequestMapping(value = "/getCmdAprob", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<ComandaAprobareAfis> getComenziAprobare(CautaCmdAprob cautaCmd) {

		return new OperatiiComenzi().getComenziAprobare(cautaCmd);

	}

	@RequestMapping(value = "/getDetCmdAprob", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ComandaAprobareDetalii getComandaAprobare(String idComanda) {

		return new OperatiiComenzi().getDetaliiComandaAprob(idComanda);

	}

}
