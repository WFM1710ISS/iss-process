package org.wwu.wfm.group10.iss.consultingservice;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class ConfirmMailService implements JavaDelegate {

  public void execute(DelegateExecution delegate) throws Exception {

    System.out.println("Spring Bean invoked by Stephan");

  }
}