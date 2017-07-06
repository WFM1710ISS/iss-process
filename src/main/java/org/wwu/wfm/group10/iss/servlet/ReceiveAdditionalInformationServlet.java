package org.wwu.wfm.group10.iss.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.camunda.bpm.engine.MismatchingMessageCorrelationException;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.RuntimeService;

public class ReceiveAdditionalInformationServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("deprecation")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		RuntimeService runtimeService = processEngine.getRuntimeService();
		
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		
		String id = request.getParameter("corrID");
		String additionalInformationForAgency = request.getParameter("additionalInformationForAgency");
		
		if (null == id && null == additionalInformationForAgency) {
			out.println("<h2>Error</h2><p>Parameter id missing!</p>");
		} else {

			try {
				
				//Map<String, Object> map = new HashMap<String, Object>();
				//map.put("additionalInformationForAgency", additionalInformationForAgency);
								
				runtimeService.createMessageCorrelation("receiveAdditionalInformationMessage").processInstanceId(id).setVariable("additionalInformationForAgency",additionalInformationForAgency).correlate();

				out.println("<h1>Message delivered to process</h1><p>ID: " + id + "</p>");
			} catch (MismatchingMessageCorrelationException e) {
				out.println("<h2>Error</h2><p>No correlating process instance TEST SN.</p><p>" + id + additionalInformationForAgency + "</p>");
			}
		}
		out.println("</body></html>");
	}
}