package org.wwu.wfm.group10.iss.event.message;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendDesignRequirementsMessageDelegate implements JavaDelegate {
	public void execute(DelegateExecution execution) throws Exception { 

		HttpClient client = HttpClients.createDefault();
		//RequestBuilder requestBuilder = RequestBuilder.get().setUri("https://requestb.in/1656hro1")
		//		.addParameter("id", execution.getProcessInstanceId());
		
		RequestBuilder requestBuilder = RequestBuilder.get().setUri("http://localhost:8010/pink_blob-0.1.0-SNAPSHOT/create-process")
				.addParameter("corrID", execution.getProcessInstanceId())
				.addParameter("designreq", String.valueOf(execution.getVariable("designreq")))
				.addParameter("securityreq", String.valueOf(execution.getVariable("securityreq")));
		
		// execute request
		HttpUriRequest request = requestBuilder.build();
		HttpResponse response = client.execute(request); 
		
		System.out.println(response);
	}
}