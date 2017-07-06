package org.wwu.wfm.group10.iss.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.FileValue;

public class StartProcessServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		RuntimeService runtimeService = processEngine.getRuntimeService();
		PrintWriter out = response.getWriter();

		String company = request.getParameter("company");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String street = request.getParameter("street");
		String zip = request.getParameter("zip");
		String city = request.getParameter("city");
		String designreq = request.getParameter("designreq");
		String securityreq = request.getParameter("securityreq");
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("file/test.pdf").getFile());
	    
		FileValue typedFileValue = Variables
				  .fileValue("/test.pdf")
				  .file(file)
				  .mimeType("application/pdf")
				  .encoding("UTF-8")
				  .create();

		ProcessInstance processInstance;
		if (null == username) {
			processInstance = runtimeService.startProcessInstanceByMessage("instantiationMessage");
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("company", company);
			map.put("username", username);
			map.put("email", email);
			map.put("phone", phone);
			map.put("street", street);
			map.put("zip", zip);
			map.put("city", city);
			map.put("designreq", designreq);
			map.put("securityreq", securityreq);
			map.put("fileNew", typedFileValue);

			processInstance = runtimeService.startProcessInstanceByMessage("instantiationMessage", map);
		}

		out.println("Your request was submitted successfully. Your process-ID:");
		out.println(processInstance.getId());
		
	}
}