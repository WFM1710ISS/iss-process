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

public class ReceiveCustomerPaymentServlet extends HttpServlet {

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
		String customerBillPayed = request.getParameter("customerBillPayed");
		
		if (null == id) {
			out.println("<h2>Error</h2><p>Parameter id or contractNegotiated missing!</p>");
		} else {

			try {
				runtimeService.createMessageCorrelation("customerPaymentReceivedMessage").processInstanceId(id).setVariable("customerBillPayed",customerBillPayed).correlate();
				
				out.println("<h1>Message delivered to process</h1><p>ID: " + id  + "</p>");
				
			} catch (MismatchingMessageCorrelationException e) {
				out.println("<h2>Error </h2><p>No correlating process instance.</p><p>" + id  + "</p>");
			}
		}
		out.println("</body></html>");
	}
}