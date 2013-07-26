package fr.ludovicbouguerra.ecodigo.model;

import java.io.Serializable;

public class CheckedTestCase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9212480181528215839L;
	
	private TestCases testCase;
	private boolean checked;
	
	
	public TestCases getTestCase() {
		return testCase;
	}
	public void setTestCase(TestCases testCase) {
		this.testCase = testCase;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
