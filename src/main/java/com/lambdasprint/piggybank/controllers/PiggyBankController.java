package com.lambdasprint.piggybank.controllers;

import com.lambdasprint.piggybank.models.PiggyBank;
import com.lambdasprint.piggybank.repositories.PiggyBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PiggyBankController {

    @Autowired
    private PiggyBankRepository piggyrepos;

    private List<PiggyBank> findPiggies(List<PiggyBank> myList, CheckPiggyBank tester) {
        List<PiggyBank> tempList = new ArrayList<>();

        for (PiggyBank p: myList) {
            if(tester.test(p)){
                tempList.add(p);
            }
        }
        return tempList;
    }
    private void getTotalCount(List<PiggyBank> coinList,String name){
        int totalCount = 0;
        for(PiggyBank p : coinList){
            totalCount += p.getQuantity();
        }
        if(totalCount == 1) {
            System.out.println(totalCount + name);
        } else {
            if(name.equals("Penny")){
                System.out.println(totalCount + "Pennies");
            }else{
                System.out.println(totalCount + name + "s");
            }
        }
    }
    @GetMapping( value = "/total", produces = {"application/json"})
    public ResponseEntity<?> listTotalCoins(){

        List<PiggyBank> myList = new ArrayList<>();
        piggyrepos.findAll().iterator().forEachRemaining(myList::add);

        List<PiggyBank> quarterList = findPiggies(myList, e-> e.getName().equals("Quarter"));
        List<PiggyBank> nickelList = findPiggies(myList, e-> e.getName().equals("Nickel"));
        List<PiggyBank> dimeList = findPiggies(myList, e-> e.getName().equals("Dime"));
        List<PiggyBank> pennyList = findPiggies(myList, e-> e.getName().equals("Penny"));
        List<PiggyBank> dollarList = findPiggies(myList, e-> e.getName().equals("Dollar"));
        double total = 0.0;
        for(PiggyBank p : myList){
            total+= p.getQuantity() * p.getValue();
        }

        getTotalCount(quarterList,"Quarter");
        getTotalCount(nickelList,"Nickel");
        getTotalCount(dimeList,"Dime");
        getTotalCount(pennyList,"Penny");
        getTotalCount(dollarList,"Dollar");

        System.out.println("The piggy Bank holds " + total);
        return new ResponseEntity<>( HttpStatus.OK);
    }


}
