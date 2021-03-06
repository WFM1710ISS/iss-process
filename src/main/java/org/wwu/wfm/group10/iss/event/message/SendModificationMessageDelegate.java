package org.wwu.wfm.group10.iss.event.message;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendModificationMessageDelegate implements JavaDelegate {
	public void execute(DelegateExecution execution) throws Exception { 

		HttpClient client = HttpClients.createDefault();

		//RequestBuilder requestBuilder = RequestBuilder.get().setUri("https://requestb.in/uwrqbjuw")

		RequestBuilder requestBuilder = RequestBuilder.get().setUri("http://localhost:8010/pink_blob-0.1.0-SNAPSHOT/continue-process3")		
				.addParameter("corrID", execution.getProcessInstanceId())
				.addParameter("modificationsRequired", String.valueOf(execution.getVariable("modificationsRequired")))
				.addParameter("customerFeedbackArtwork", String.valueOf(execution.getVariable("customerFeedbackArtwork")));		
				
		// execute request
		HttpUriRequest request = requestBuilder.build();
		HttpResponse response = client.execute(request); 
		
		System.out.println(response);
	}
}