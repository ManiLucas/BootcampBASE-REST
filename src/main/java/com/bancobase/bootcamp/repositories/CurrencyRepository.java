package com.bancobase.bootcamp.repositories;

import com.bancobase.bootcamp.schemas.CurrencySchema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurrencyRepository extends JpaRepository<CurrencySchema, String> {
    List<CurrencySchema> findAll();
}