package com.lambdasprint.piggybank.controllers;

import com.lambdasprint.piggybank.models.PiggyBank;
import com.lambdasprint.piggybank.repositories.PiggyBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
