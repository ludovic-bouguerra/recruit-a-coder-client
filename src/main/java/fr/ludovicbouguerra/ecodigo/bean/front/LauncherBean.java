package fr.ludovicbouguerra.ecodigo.bean.front;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import fr.ludovicbouguerra.ecodigo.dao.IUserEpreuveDAO;
import fr.ludovicbouguerra.ecodigo.dao.IUserResponseDAO;
import fr.ludovicbouguerra.ecodigo.model.Subject;
import fr.ludovicbouguerra.ecodigo.model.TestCases;
import fr.ludovicbouguerra.ecodigo.model.UserEpreuve;
import fr.ludovicbouguerra.ecodigo.model.UserResponse;
import fr.ludovicbouguerra.ecodigo.services.EpreuveAlreadyFinishedException;
import fr.ludovicbouguerra.ecodigo.services.EpreuveNotFound;
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
		if (id != null || launcherSession.getEpreuve() != null) {

				UserEpreuve ue;
				try {
					ue = userEpreuveService.initEpreuve(id);
					launcherSession.setEpreuve(ue);
					launcherSession.setTestCases(new ArrayList<TestCases>());
					for (Subject s : launcherSession.getEpreuve().getEpreuve().getSubjects()){
						if (s.getTestCases().size() > 0){
							launcherSession.getTestCases().add(s.getTestCases().get(0));	
						}
					}
					
					launcherSession.setResponses(ue.getUserReponses());
					launcherSession.setIndex(0);
					launcherSession.setCode("");
					
				} catch (EpreuveAlreadyFinishedException e) {
					try {
						((HttpServletResponse) FacesContext.getCurrentInstance()
								.getExternalContext().getResponse())
								.sendError(HttpServletResponse.SC_NOT_FOUND);
					} catch (IOException e1) {

					}
				} catch (EpreuveNotFound e) {
					try {
						((HttpServletResponse) FacesContext.getCurrentInstance()
								.getExternalContext().getResponse())
								.sendError(HttpServletResponse.SC_NOT_FOUND);
					} catch (IOException e1) {

					}
				}

				//launcherSession.setResponses(userResponses);

		}else{
			try {
				((HttpServletResponse) FacesContext.getCurrentInstance()
						.getExternalContext().getResponse())
						.sendError(HttpServletResponse.SC_NOT_FOUND);
			} catch (IOException e1) {

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
