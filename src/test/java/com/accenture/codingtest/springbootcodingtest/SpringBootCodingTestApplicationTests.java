package com.accenture.codingtest.springbootcodingtest;
import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import com.accenture.codingtest.springbootcodingtest.entity.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;


@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class SpringBootCodingTestApplicationTests {

	@Autowired
    private TestRestTemplate restTemplate;
	
	@LocalServerPort
    int randomServerPort;
	
	@Test
	void contextLoads() {
	}

	@Test
    public void testCreateUser() throws Exception 
    {
		System.out.println("------ INSIDE METHOD - testCreateUser() ------");
		
		final String baseUrl = "http://localhost:"+randomServerPort+"/api/v1/users/";
        URI uri = new URI(baseUrl);
        User user = new User();
        user.setUsername("Adam");
        user.setPassword("password");
         
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");      
 
        HttpEntity<User> request = new HttpEntity<>(user, headers);
         
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
         
        System.out.println("result.getStatusCodeValue() - " + result.getStatusCodeValue());
        
    }
	
	@Test
    public void testCreateProject() throws Exception 
    {
		System.out.println("------ INSIDE METHOD - testCreateProject() ------");
		
		final String baseUrl = "http://localhost:"+randomServerPort+"/api/v1/projects/";
        URI uri = new URI(baseUrl);
        Project project = new Project();
        project.setName("Test Project Name");
                 
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");      
 
        HttpEntity<Project> request = new HttpEntity<>(project, headers);
         
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
         
        System.out.println("result.getStatusCodeValue() - " + result.getStatusCodeValue());
        
    }
}
