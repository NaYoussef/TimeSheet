package tn.esprit.spring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.services.IEntrepriseService;
public class EntrepriseServiceTest {
	@Autowired
	IEntrepriseService entrepriseInt;

	@Test
	public void testAjouterEntreprise() {
		Entreprise e = new Entreprise("ESPRIT", "Tunis");
		entrepriseInt.ajouterEntreprise(e);
		assertEquals(7, e.getId());
	}

	@Test
	public void testAjouterDepartement() {
		Departement d = new Departement("SECURITE");
		entrepriseInt.ajouterDepartement(d);
		assertEquals(6, d.getId());
	}
	
	@Test
	public void testGetAllDepartementsNamesByEntreprise() {
		List<String> listDep = entrepriseInt.getAllDepartementsNamesByEntreprise(5);
		assertNotNull(listDep);
	}
	
	@Test
	public void tesGetEntrepriseById(){
		Entreprise ent=entrepriseInt.getEntrepriseById(6);
		assertEquals("AGILE", ent.getName());
	} 


}
