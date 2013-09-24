package fr.ludovicbouguerra.ecodigo.bean.front;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import fr.ludovicbouguerra.ecodigo.dao.IUserEpreuveDAO;
import fr.ludovicbouguerra.ecodigo.dao.IUserResponseDAO;
import fr.ludovicbouguerra.ecodigo.model.CheckedTestCase;
import fr.ludovicbouguerra.ecodigo.model.Epreuve;
import fr.ludovicbouguerra.ecodigo.model.Subject;
import fr.ludovicbouguerra.ecodigo.model.TestCases;
import fr.ludovicbouguerra.ecodigo.model.UserEpreuve;
import fr.ludovicbouguerra.ecodigo.model.UserResponse;
import fr.ludovicbouguerra.ecodigo.services.EpreuveAlreadyFinishedException;
import fr.ludovicbouguerra.ecodigo.services.IUserEpreuveService;

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
	private IUserEpreuveDAO userEpreuveDAO;
	
	@EJB 
	private IUserEpreuveService userEpreuveService;
	
	@EJB
	private IUserResponseDAO userResponseDAO;
	
	
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

	public LauncherBean() {

	}

	@PostConstruct
	public void init() {
		if (id != null || launcherSession.getResponses().isEmpty()) {
 
			UserEpreuve userEpreuve = userEpreuveDAO.findById(id);
			launcherSession.setEpreuve(userEpreuve);
			try {
				userEpreuveService.initEpreuve(userEpreuve);
				Epreuve epreuve = userEpreuve.getEpreuve();
				if (epreuve != null) {
					ArrayList<UserResponse> userResponses = new ArrayList<UserResponse>();
					launcherSession.setTestCases(new ArrayList<TestCases>());
					for (Subject sub : epreuve.getSubjects()) {
						UserResponse u = new UserResponse();
						u.setUserEpreuve(userEpreuve);
						u.setSubject(sub);
						u.setCode("");
						u.setDateBegin(new Date());
						
						if (sub.getTestCases().size() > 0){
							launcherSession.getTestCases().add(sub.getTestCases().toArray(new TestCases[sub.getTestCases().size()])[0]);
						}
						
						userResponses.add(u);
					}
					launcherSession.setResponses(userResponses);
			}
			else{
				try {
					((HttpServletResponse) FacesContext.getCurrentInstance()
							.getExternalContext().getResponse())
							.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException e) {

				}
			}
			} catch (EpreuveAlreadyFinishedException e1) {
				try {
					((HttpServletResponse) FacesContext.getCurrentInstance()
							.getExternalContext().getResponse())
							.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException e) {

				}
			}
		}
			

	}

	public String valid() {
		
		for (UserResponse ue : launcherSession.getResponses()){
			userResponseDAO.saveOrUpdate(ue);
		}
		userEpreuveService.send(launcherSession.getEpreuve());	
 
		return "valid.xhtml";

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
