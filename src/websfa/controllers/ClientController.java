package websfa.controllers;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import websfa.beans.CautareClient;
import websfa.beans.ClientLite;

import websfa.beans.DetaliiClient;
import websfa.beans.Status;
import websfa.model.articole.OperatiiClient;

@Controller
public class ClientController {

	public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
	public static final Charset UTF_8 = Charset.forName("UTF-8");

	@RequestMapping(value = "/cautaclient", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public List<ClientLite> cautaClient(@RequestBody CautareClient cautareClient) {

		return new OperatiiClient().getClient(cautareClient);

	}

	@RequestMapping(value = "/getdetaliiclient", produces = "application/json")
	@ResponseBody
	public DetaliiClient getDetaliiClient(String codClient) {

		return new OperatiiClient().getDetaliiClient(codClient);

	}

	@RequestMapping(value = "/aprobacmd", produces = "application/json")
	@ResponseBody
	public Status AprobaComanda(String idComanda) {
		return null;
	}

}
