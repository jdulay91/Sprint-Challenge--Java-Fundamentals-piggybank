package com.lambdasprint.piggybank.repositories;

import com.lambdasprint.piggybank.models.PiggyBank;
import org.springframework.data.repository.CrudRepository;

public interface PiggyBankRepository extends CrudRepository<PiggyBank, Long> {
}
