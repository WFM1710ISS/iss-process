package org.wwu.wfm.group10.iss.event.message;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendFurtherSpecificationMessageDelegate implements JavaDelegate {
	public void execute(DelegateExecution execution) throws Exception { 

		HttpClient client = HttpClients.createDefault();
		RequestBuilder requestBuilder = RequestBuilder.get().setUri("https://requestb.in/1656hro1")
				.addParameter("id", execution.getProcessInstanceId());
		
		for (String variable : execution.getVariableNames()) {
			requestBuilder.addParameter(variable, String.valueOf(execution.getVariable(variable)));
		}
		
		// execute request
		HttpUriRequest request = requestBuilder.build();
		HttpResponse response = client.execute(request); 
		
		System.out.println(response);
	}
}