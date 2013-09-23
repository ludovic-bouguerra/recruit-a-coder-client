package fr.ludovicbouguerra.ecodigo.result;

public interface IResult {

	public String getInputData();
	public String getOutputData();
	public String getExpectedData();
	public boolean isValid();
	
}
