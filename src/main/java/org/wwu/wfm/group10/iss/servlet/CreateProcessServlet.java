package org.wwu.wfm.group10.iss.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
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

public class CreateProcessServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		RuntimeService runtimeService = processEngine.getRuntimeService();
		PrintWriter out = response.getWriter();

		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String amount = request.getParameter("amount");
		String comment = request.getParameter("comment");
		
		
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
			map.put("username", username);
			map.put("email", email);
			map.put("amount", amount);
			map.put("comment", comment);
			map.put("fileNew", typedFileValue);

			processInstance = runtimeService.startProcessInstanceByMessage("instantiationMessage", map);
		}
		out.println("<html><body>");
		out.println("<h1>Process Instance Started</h1>");
		out.println("<p>ID: " + processInstance.getId() + "</p>");
		out.println("<p>Username: " + username + "</p>");
		out.println("</body></html>");
	}
}