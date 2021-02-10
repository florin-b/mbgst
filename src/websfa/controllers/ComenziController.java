package websfa.controllers;

import java.io.FileNotFoundException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import websfa.beans.MasinaNeincarcata;

import websfa.model.comenzi.OperatiiMasini;
import websfa.utils.Utils;

@Controller
@Scope("session")
public class ComenziController {

	private static final Logger logger = LogManager.getLogger(ComenziController.class);

	@RequestMapping(value = "/getMasiniNeincarcate", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<MasinaNeincarcata> getMasiniNeincarcate(String filiala) {

		return new OperatiiMasini().getMasiniNeincarcate(filiala);

	}

	@RequestMapping(value = "/setSfarsitIncarcare", method = RequestMethod.POST)
	@ResponseBody
	public void setSfarsitIncarcare(String document, String codSofer, String image) {

		try {

			new OperatiiMasini().saveSfarsitIncImg(document, image);

		} catch (Exception e) {

			logger.error(Utils.getStackTrace(e));
			System.out.println(e.toString());
		}

		// new OperatiiMasini().setSfarsitIncarcare(document, codSofer);

	}

	@RequestMapping(value = "/valideazaMasina", method = RequestMethod.POST)
	@ResponseBody
	public MasinaNeincarcata valideazaMasina(String codUser, String filiala, String image) {

		return new OperatiiMasini().valideazaMasina(codUser, filiala, image);

	}

}
