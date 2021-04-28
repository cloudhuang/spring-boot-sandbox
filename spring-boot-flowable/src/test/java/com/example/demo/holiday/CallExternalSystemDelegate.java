package com.example.demo.holiday;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class CallExternalSystemDelegate implements JavaDelegate {

    public void execute(DelegateExecution execution) {
        System.out.println("[CallExternalSystemDelegate] - Calling the external system for employee "
                + execution.getVariable("employee"));
    }

}