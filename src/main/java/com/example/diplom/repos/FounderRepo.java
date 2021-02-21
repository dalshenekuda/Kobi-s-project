package com.example.diplom.repos;

import com.example.diplom.domain.Founder;
import org.springframework.data.repository.CrudRepository;

public interface FounderRepo extends CrudRepository<Founder,Long> {

    Founder findByNameAndFamilyName(String name, String fam);
}
