package com.oracle.vending.api;

import com.oracle.vending.api.model.Change;
import com.oracle.vending.api.model.MachineState;
import com.oracle.vending.api.model.Product;
import com.oracle.vending.api.service.CalculatorService;
import com.oracle.vending.api.service.InitialiseMachine;
import com.oracle.vending.api.service.InputOutputService;
import com.oracle.vending.api.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.oracle.vending.api.model.MachineState.*;

@SpringBootApplication
public class VendingMachine implements CommandLineRunner {

    @Autowired
    private InitialiseMachine initialiseMachine;

    @Autowired
    private ProductService productService;

    @Autowired
    private CalculatorService calculatorService;

    @Autowired
    InputOutputService inputOutputService;

    private static Logger LOG = LoggerFactory
            .getLogger(VendingMachine.class);

    public static void main(String[] args){
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(VendingMachine.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        inputOutputService.println("*************VENDING MACHINE************");
        MachineState state = INIT;
        Change machineChange = new Change();
        Product product = new Product();
        Map<String,Integer> coinMap = new LinkedHashMap<>();

        while (true){
            switch (state){
                case INIT:
                    state = initialiseMachine.registerChange(machineChange);
                    inputOutputService.println("Total Machine Change: " + machineChange);
                    break;
                case READY:
                    product = productService.getProduct();
                    if(product==null){
                        state=READY;
                    }else {
                        LOG.debug("price: £"+ product.getPrice());
                        state=ACCEPT_CHANGE;
                    }
                    break;
                case ACCEPT_CHANGE:
                    inputOutputService.println("machine change before:" + machineChange);
                    Change customerChange = calculatorService.acceptChange(product.getPrice());
                    LOG.debug("Customer change : " + customerChange);
                    try{
                        Change change = calculatorService.calculateChange(machineChange,customerChange,product.getPrice());
                        inputOutputService.println("\nReturning change " + change + " to customer...");
                        inputOutputService.println("\n======================================\n");
                        inputOutputService.println("Please Collect your Change:" + change.displayChange());
                        inputOutputService.println("Enjoy your " + product.getProductName() + "!");

                        inputOutputService.println("Machine Change: " + machineChange);

                    }catch (Exception exception){
                        System.out.println("Error! "+ exception.getMessage());
                        state = ACCEPT_CHANGE;
                        break;
                    }
                    LOG.debug("machine change:" + machineChange);
                    LOG.debug("customer change:" + customerChange);
                    state = READY;
                    break;
            }
        }
    }
   }
