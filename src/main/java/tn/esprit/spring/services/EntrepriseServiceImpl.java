package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	@Autowired
	EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;
	private static final Logger l = LogManager.getLogger(EntrepriseServiceImpl.class);

	public int ajouterEntreprise(Entreprise entreprise) {
		Entreprise ent = null;
		l.info("START METHOD: ajouterEntreprise");
		try {
			ent = entrepriseRepoistory.save(entreprise);
			l.info("END METHOD: ajouterEntreprise");
			return ent.getId();
		} catch (Exception e) {
			l.error("ERROR METHOD: ajouterEntreprise");
			return 0;
		} finally {
			l.info("OUT OF METHOD: ajouterEntreprise");

		}

	}

	public int ajouterDepartement(Departement dep) {
		l.info("START METHOD: ajouterDepartement");
		try {
			deptRepoistory.save(dep);
			l.info("END METHOD: ajouterDepartement");
			return dep.getId();

		} catch (Exception e) {
			l.error("ERROR METHOD: ajouterDepartement");
			return 0;
		} finally {
			l.info("OUT OF METHOD: ajouterDepartement");

		}
	}

	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		l.info("START METHOD: affecterDepartementAEntreprise");
		try {
			Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
			Departement depManagedEntity = deptRepoistory.findById(depId).get();
			depManagedEntity.setEntreprise(entrepriseManagedEntity);
			deptRepoistory.save(depManagedEntity);
			l.info("END METHOD: affecterDepartementAEntreprise");

		} catch (Exception e) {
			l.error("ERROR METHOD: affecterDepartementAEntreprise");
		} finally {
			l.info("OUT OF METHOD: affecterDepartementAEntreprise");

		}

	}

	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		List<String> depNames = new ArrayList<>();
		l.info("START METHOD: getAllDepartementsNamesByEntreprise");
		try {
			Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
			for (Departement dep : entrepriseManagedEntity.getDepartements()) {
				depNames.add(dep.getName());
			}
			l.info("END METHOD: getAllDepartementsNamesByEntreprise");

			return depNames;
		} catch (Exception e) {
			l.error("error METHOD: getAllDepartementsNamesByEntreprise");
			return null;
		} finally {
			l.info("OUT OF METHOD: getAllDepartementsNamesByEntreprise");

		}
	}

	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		l.info("START METHOD: deleteEntrepriseById");
		try {
			entrepriseRepoistory.delete(entrepriseRepoistory.findById(entrepriseId).get());
			l.info("END METHOD: deleteEntrepriseById");
		} catch (Exception e) {
			l.error("ERROR METHOD: deleteEntrepriseById");
		} finally {
			l.info("OUT OF METHOD: deleteEntrepriseById");

		}

	}

	@Transactional
	public void deleteDepartementById(int depId) {
		l.info("START METHOD: deleteDepartementById");
		try {
			deptRepoistory.delete(deptRepoistory.findById(depId).get());
			l.info("END METHOD: deleteDepartementById");

		} catch (Exception e) {
			l.error("ERROR METHOD: deleteDepartementById");
		} finally {
			l.info("out of METHOD: deleteDepartementById");

		}
	}

	public Entreprise getEntrepriseById(int entrepriseId) {
		Entreprise ent;
		l.info("START METHOD: getEntrepriseById");
		try {
			ent = entrepriseRepoistory.findById(entrepriseId).get();
			l.info("END METHOD: getEntrepriseById");
			return ent;

		} catch (Exception e) {
			l.error("ERROR METHOD: getEntrepriseById");
			return null;
		} finally {
			l.info("OUT OF METHOD: getEntrepriseById");
		}

	}

}
