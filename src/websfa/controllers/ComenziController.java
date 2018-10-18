package websfa.controllers;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import websfa.beans.MasinaNeincarcata;

import websfa.model.comenzi.OperatiiMasini;

@Controller
@Scope("session")
public class ComenziController {

	@RequestMapping(value = "/getMasiniNeincarcate", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<MasinaNeincarcata> getMasiniNeincarcate(String filiala) {

		return new OperatiiMasini().getMasiniNeincarcate(filiala);

	}

	@RequestMapping(value = "/setSfarsitIncarcare", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public void setSfarsitIncarre(String document, String codSofer) {

		new OperatiiMasini().setSfarsitIncarcare(document, codSofer);

	}

}
