package org.wwu.wfm.group10.iss.event.message;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendApprovalProjectMessageDelegate implements JavaDelegate {
	public void execute(DelegateExecution execution) throws Exception { 

		HttpClient client = HttpClients.createDefault();
		
		RequestBuilder requestBuilder = RequestBuilder.get().setUri("https://requestb.in/uwrqbjuw")
				.addParameter("corrID", execution.getProcessInstanceId())
				.addParameter("furtherInputRequired", String.valueOf(execution.getVariable("furtherInputRequired")));		
		
		// execute request
		HttpUriRequest request = requestBuilder.build();
		HttpResponse response = client.execute(request); 
		
		System.out.println(response);
	}
}