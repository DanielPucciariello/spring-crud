package com.example.crud2.repository;

import com.example.crud2.entity.Plato;
import com.example.crud2.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPLatoRepository extends JpaRepository<Plato, Long> {
}
