package websfa.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import websfa.beans.CautareClient;
import websfa.beans.ClientLite;
import websfa.beans.DetaliiClient;
import websfa.beans.Status;
import websfa.model.articole.OperatiiClient;

@Controller
public class ClientController {

	@RequestMapping(value = "/cautaclient", produces = "application/json")
	@ResponseBody
	public List<ClientLite> cautaClient(CautareClient cautareClient) {

		return new OperatiiClient().getClient(cautareClient);

	}

	@RequestMapping(value = "/getdetaliiclient", produces = "application/json")
	@ResponseBody
	public DetaliiClient getDetaliiClient(String codClient) {

		return new OperatiiClient().getDetaliiClient(codClient);

	}
	
	@RequestMapping(value = "/aprobacmd", produces = "application/json")
	@ResponseBody
	public Status AprobaComanda(String idComanda)
	{
		return null;
	}

}
