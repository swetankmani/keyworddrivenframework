package com.qa.hs.test;

import org.testng.annotations.Test;

import com.qa.hs.keyword.engine.keywordEngine;

public class loginTest {
	
	public  keywordEngine keywrdengn;
	
@Test
public void loginTest() {
	
	keywrdengn =new keywordEngine();
	System.out.println("Before Execution");
	keywrdengn.startExecution("login");
	System.out.println("After Execution");
	
}

@Test
public  void signUpTest() {
	
	
	keywrdengn.startExecution("signup");
	
}


}
