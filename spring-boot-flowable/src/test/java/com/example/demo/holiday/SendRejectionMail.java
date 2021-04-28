package com.example.demo.holiday;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class SendRejectionMail implements JavaDelegate {

    public void execute(DelegateExecution execution) {
        System.out.println("[SendRejectionMail] - Calling the external system for employee "
                + execution.getVariable("employee"));
    }

}