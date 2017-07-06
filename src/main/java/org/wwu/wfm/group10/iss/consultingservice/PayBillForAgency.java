package org.wwu.wfm.group10.iss.consultingservice;

import java.util.logging.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class PayBillForAgency implements JavaDelegate {

	private final static Logger LOGGER = Logger.getLogger("ISS-REQUESTS");

	 public void execute(DelegateExecution execution) throws Exception {
	    LOGGER.info("################BILL PAYED################");
	 }
}
