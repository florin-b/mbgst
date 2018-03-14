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
import websfa.beans.ComandaAprobareOperare;
import websfa.beans.ComandaModificareDetalii;
import websfa.beans.Status;
import websfa.beans.articole.CautareComanda;
import websfa.beans.articole.ComandaDetalii;
import websfa.beans.articole.ComandaHeader;
import websfa.enums.EnumOpereazaComanda;
import websfa.model.articole.OperatiiComenzi;

@Controller
public class ComenziController {

	@RequestMapping(value = "/getComAfis", produces = "application/json")
	@ResponseBody
	public List<ComandaHeader> getComenzi(CautareComanda cautareComanda) {

		return new OperatiiComenzi().getComenziAfisare(cautareComanda);

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

	@RequestMapping(value = "/getCmdModif", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<ComandaAprobareAfis> getComenziModificare(CautaCmdAprob cautaCmd) {

		return new OperatiiComenzi().getComenziModificare(cautaCmd);

	}

	@RequestMapping(value = "/getDetCmdModif", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ComandaModificareDetalii getDetaliiCmdModificare(String idComanda) {

		return new OperatiiComenzi().getDetaliiComandaModif(idComanda);

	}

	@RequestMapping(value = "/aprobaComanda", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Status aprobaComanda(@RequestBody ComandaAprobareOperare comanda) {

		return new OperatiiComenzi().opereazaComanda(comanda);

	}

	@RequestMapping(value = "/stergeComanda", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Status stergeComanda(@RequestBody ComandaAprobareOperare comanda) {

		return new OperatiiComenzi().opereazaComanda(comanda);

	}

}
