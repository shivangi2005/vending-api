package com.oracle.vending.api.service;

import com.oracle.vending.api.model.Change;
import com.oracle.vending.api.model.MachineState;

public interface InitialiseMachine {

    MachineState registerChange(Change machineChange);
}
