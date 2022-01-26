package com.oracle.vending.api.service;


import com.oracle.vending.api.exception.NotEnoughChangeException;
import com.oracle.vending.api.model.Change;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CalculatorServiceImplTest {

    @Mock
    InputOutputService inputOutputService;

    @InjectMocks
    CalculatorServiceImpl mockCalc;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private final CalculatorServiceImpl calculatorService= new CalculatorServiceImpl();

    @Test
    @DisplayName("test_change_is_calculated")
    void testGetChange() {
        Change machine = new Change();
        machine.setPnd2(3);
        machine.setPnd1(2);
        machine.setPs50(10);
        machine.setPs20(20);
        machine.setPs10(10);
        machine.setPs5(10);
        machine.setPs2(10);
        machine.setPs1(20);

        Change customer = new Change();
        customer.setPnd1(1);
        customer.setPs20(2);
        Change change = calculatorService.calculateChange(machine,customer,1.25F);
        assertEquals(1,change.getPs10());
        assertEquals(1,change.getPs5());
        assertEquals(0,change.getPnd1());
        assertEquals(0,change.getPnd2());
        assertEquals(0,change.getPs50());
        assertEquals(15,change.getTotal());
    }

    @Test
    @DisplayName("test_change_returned_when_out_of_5p")
    void testGetChangeNo5p() {
        Change machine = new Change();
        machine.setPnd2(3);
        machine.setPnd1(2);
        machine.setPs50(10);
        machine.setPs20(20);
        machine.setPs10(10);
        machine.setPs2(10);
        machine.setPs1(20);

        Change customer = new Change();
        customer.setPnd1(1);
        customer.setPs20(2);
        Change change = calculatorService.calculateChange(machine,customer,1.25F);
        assertEquals(15,change.getTotal());
        assertEquals(1,change.getPs10());
        assertEquals(2,change.getPs2());
        assertEquals(1,change.getPs1());
        assertEquals(0,change.getPnd2());
        assertEquals(0,change.getPs50());
    }

    @Test
    @DisplayName("test_change_when_machine_is_out_of_change")
    void testGetChangeWithException() {
        Change machine = new Change();
        machine.setPnd2(3);
        machine.setPnd1(2);
        machine.setPs50(10);


        Change customer = new Change();
        customer.setPnd1(1);
        customer.setPs20(2);
        NotEnoughChangeException exception = assertThrows(NotEnoughChangeException.class,
                ()-> calculatorService.calculateChange(machine,customer,1.25F),"Machine is out of Change. Please try again with exact change!!");
        assertTrue(exception.getMessage().contains("Machine is out of Change. Please try again with exact change!!"));
        assertEquals(1300,machine.getTotal());
    }

   @Test
    @DisplayName("test_change_when_machine_returns_2_pounds")
    void testGetChangeReturns2Pnd() {
        Change machine = new Change();
       machine.setPnd2(3);
       machine.setPnd1(2);
       machine.setPs50(10);
       machine.setPs20(20);
       machine.setPs10(10);
       machine.setPs2(10);
       machine.setPs1(20);

        Change customer = new Change();
        customer.setPs1(1);
        customer.setPnd1(2);
        customer.setPnd2(1);
       Change change = calculatorService.calculateChange(machine,customer,1.25F);

       assertEquals(276,change.getTotal());
       assertEquals(1,change.getPnd2());
       assertEquals(1,change.getPs50());
       assertEquals(1,change.getPs20());
       assertEquals(3,change.getPs2());

    }

    @Test
    @DisplayName("test_change_when_machine_includes_1_pound")
    void testGetChangeReturns1Pnd() {
        Change machine = new Change();
        machine.setPnd2(3);
        machine.setPnd1(2);
        machine.setPs50(10);
        machine.setPs20(20);
        machine.setPs10(10);
        machine.setPs5(10);
        machine.setPs1(20);

        Change customer = new Change();
        customer.setPs50(1);
        customer.setPnd2(1);
        Change change = calculatorService.calculateChange(machine,customer,1.25F);

        assertEquals(125,change.getTotal());
        assertEquals(1,change.getPnd1());
        assertEquals(1,change.getPs20());
        assertEquals(1,change.getPs5());

    }


    @Test
    @DisplayName("test_accept_change_1_pound")
    public void testAcceptChange(){
        when(inputOutputService.getString()).thenReturn("1pnd");
        Change change =mockCalc.acceptChange(0.50f);
        assertEquals(100,change.getTotal());
        assertEquals(1,change.getPnd1());

    }

    @Test
    @DisplayName("test_accept_change_2_pound")
    public void testAcceptChange2pnd(){
        when(inputOutputService.getString()).thenReturn("2pnd");
        Change change =mockCalc.acceptChange(0.50f);
        assertEquals(200,change.getTotal());
        assertEquals(1,change.getPnd2());
    }

    @Test
    @DisplayName("test_accept_change_50p")
    public void testAcceptChange50p(){
        when(inputOutputService.getString()).thenReturn("50p");
        Change change =mockCalc.acceptChange(0.50f);
        assertEquals(50,change.getTotal());
        assertEquals(1,change.getPs50());
    }

    @Test
    @DisplayName("test_accept_change_20p")
    public void testAcceptChange20p(){
        when(inputOutputService.getString()).thenReturn("20p");
        Change change =mockCalc.acceptChange(0.20f);
        assertEquals(20,change.getTotal());
        assertEquals(1,change.getPs20());
    }

    @Test
    @DisplayName("test_accept_change_10p")
    public void testAcceptChange10p(){
        when(inputOutputService.getString()).thenReturn("10p");
        Change change =mockCalc.acceptChange(0.10f);
        assertEquals(10,change.getTotal());
        assertEquals(1,change.getPs10());
    }

    @Test
    @DisplayName("test_accept_change_5p")
    public void testAcceptChange5p(){
        when(inputOutputService.getString()).thenReturn("5p");
        Change change =mockCalc.acceptChange(0.02f);
        assertEquals(5,change.getTotal());
        assertEquals(1,change.getPs5());
    }

    @Test
    @DisplayName("test_accept_change_2p")
    public void testAcceptChange2p(){
        when(inputOutputService.getString()).thenReturn("2p");
        Change change =mockCalc.acceptChange(0.02f);
        assertEquals(2,change.getTotal());
        assertEquals(1,change.getPs2());
    }

    @Test
    @DisplayName("test_accept_change_1p")
    public void testAcceptChange1p(){
        when(inputOutputService.getString()).thenReturn("1p");
        Change change =mockCalc.acceptChange(0.01f);
        assertEquals(1,change.getTotal());
        assertEquals(1,change.getPs1());
    }

}