package websfa.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import websfa.beans.Angajat;
import websfa.model.articole.OperatiiAngajati;

@Controller
public class AngajatiController {

	@RequestMapping(value = "/getAgentiDepart", produces = "application/json")
	@ResponseBody
	public List<Angajat> getAgentiDepartament(String filiala, String departament) {
		return new OperatiiAngajati().getAgenti(filiala, departament);
	}

}
