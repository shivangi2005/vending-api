package com.oracle.vending.api.service;

import com.oracle.vending.api.model.Change;
import com.oracle.vending.api.model.MachineState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class InitialiseMachineImpl implements InitialiseMachine{
    @Autowired
    private InputOutputService inputOutputService;

    private static final Logger LOG = LoggerFactory.getLogger(InitialiseMachineImpl.class);


    @Override
    public MachineState registerChange(Change machineChange) {
        Map<String,Integer> coinMap = new LinkedHashMap<>();
        initialiseCoinMap(coinMap);
        inputOutputService.println("Initialising Machine... ");
        inputOutputService.println("Register initial change: ");
        try{
            for(Map.Entry<String,Integer> e : coinMap.entrySet()){
                inputOutputService.print("Enter number coins for " + e.getKey() + " :");
                int val = inputOutputService.getInt();
                coinMap.put(e.getKey(),val);
            }
        }catch (Exception exception){
            inputOutputService.flushReader();
            inputOutputService.println("Error!: Please try again with integer numbers!");
            return MachineState.INIT;
        }
        inputOutputService.println(coinMap.toString());
        machineChange.setPs1(coinMap.get("1p"));
        machineChange.setPs2(coinMap.get("2p"));
        machineChange.setPs5(coinMap.get("5p"));
        machineChange.setPs10(coinMap.get("10p"));
        machineChange.setPs20(coinMap.get("20p"));
        machineChange.setPs50(coinMap.get("50p"));
        machineChange.setPnd1(coinMap.get("1\u00A3"));
        machineChange.setPnd2(coinMap.get("2\u00A3"));

        return MachineState.READY;
    }
    private static void initialiseCoinMap(Map<String,Integer> coinMap){
        coinMap.put("2\u00A3",0);
        coinMap.put("1\u00A3",0);
        coinMap.put("50p",0);
        coinMap.put("20p",0);
        coinMap.put("10p",0);
        coinMap.put("5p",0);
        coinMap.put("2p",0);
        coinMap.put("1p",0);
    }
}
