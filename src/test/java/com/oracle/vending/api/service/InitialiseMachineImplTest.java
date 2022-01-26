package com.oracle.vending.api.service;

import com.oracle.vending.api.exception.NotEnoughChangeException;
import com.oracle.vending.api.model.Change;
import com.oracle.vending.api.model.MachineState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class InitialiseMachineImplTest {

    @Mock
    InputOutputService inputOutputService;

    @InjectMocks
    InitialiseMachineImpl initialiseMachineMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testInitialise(){
        Change change = new Change();
        when(inputOutputService.getInt()).thenReturn(1);
        MachineState state = initialiseMachineMock.registerChange(change);
        assertEquals(MachineState.READY,state);
        assertEquals(388,change.getTotal());
    }



}