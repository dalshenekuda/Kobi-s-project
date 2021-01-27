package com.example.diplom.repos;

import com.example.diplom.domain.Company;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Company, Long> {
    List<Company> findByReal(Integer real);
    Company findByQr(String qr);
    boolean existsByQr(String qr);

}
