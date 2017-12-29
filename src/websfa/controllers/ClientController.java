package websfa.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import websfa.beans.client.CautareClient;
import websfa.beans.client.ClientLite;
import websfa.beans.client.DetaliiClient;
import websfa.model.client.OperatiiClient;

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

}
