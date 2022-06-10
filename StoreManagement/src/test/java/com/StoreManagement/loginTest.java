package com.StoreManagement;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class loginTest {

	@Test
	public void test() {
		//fail("Not yet implemented");
		
		StoreFunctions sf1 = new StoreFunctions();
		try {
			String loginString = sf1.login();
			
			assertEquals("admin",loginString);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
