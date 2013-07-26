package fr.ludovicbouguerra.ecodigo.dao;

import java.util.List;

import fr.ludovicbouguerra.ecodigo.model.Subject;

public interface ISubjectDAO {
	public void remove(int id);
	public Subject findById(int id);
	public void createSubject(Subject subject);
	public List<Subject> findAllSubjects();
}
