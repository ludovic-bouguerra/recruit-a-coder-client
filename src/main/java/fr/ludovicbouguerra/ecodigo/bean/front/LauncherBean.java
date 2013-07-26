package fr.ludovicbouguerra.ecodigo.bean.front;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;





import fr.ludovicbouguerra.ecodigo.dao.IEpreuveDAO;
import fr.ludovicbouguerra.ecodigo.model.CheckedTestCase;
import fr.ludovicbouguerra.ecodigo.model.Epreuve;
import fr.ludovicbouguerra.ecodigo.model.Subject;
import fr.ludovicbouguerra.ecodigo.model.TestCases;
import fr.ludovicbouguerra.ecodigo.model.UserEpreuve;
import fr.ludovicbouguerra.ecodigo.model.UserResponse;

/**
 * 
 * @author Ludovic Bouguerra <bouguerra.ludovic@gmail.com>
 * 
 *
 */
@ManagedBean
@RequestScoped
public class LauncherBean {

	@EJB
	private IEpreuveDAO epreuveDAO;

	@ManagedProperty("#{param.id}")
	private Integer id;
	
	@ManagedProperty("#{launcherBeanSession}")
	private LauncherBeanSession launcherSession;
	

	
	public LauncherBeanSession getLauncherSession() {
		return launcherSession;
	}

	public void setLauncherSession(LauncherBeanSession launcherSession) {
		this.launcherSession = launcherSession;
	}


	public LauncherBean(){
		
	}
	
	@PostConstruct
	public void init(){
		if (id != null || launcherSession.getResponses().isEmpty()){
			Epreuve epreuve = epreuveDAO.findById(id);
			
			ArrayList<UserResponse> userResponses = new ArrayList<UserResponse>();
			for(Subject sub : epreuve.getSubject()){
				UserResponse u = new UserResponse();
				UserEpreuve userEpreuve = new UserEpreuve();
				userEpreuve.setEpreuve(epreuve);
				u.setUserEpreuve(userEpreuve);
				u.setSubject(sub);
				u.setCode("");
				u.setDateBegin(new Date());
				
				
				ArrayList<CheckedTestCase> checkedTestCases = new ArrayList<CheckedTestCase>();
				for(TestCases test : sub.getTestCases())
				{
					CheckedTestCase c = new CheckedTestCase();
					c.setChecked(true);
					c.setTestCase(test);
					checkedTestCases.add(c);
					
				}
				launcherSession.getCheckedTestCases().add(checkedTestCases);
				userResponses.add(u);
			}
			launcherSession.setResponses(userResponses);
		}

	}
	
	
	public String valider(){
		
		
		return "valid.xhtml";
		
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
