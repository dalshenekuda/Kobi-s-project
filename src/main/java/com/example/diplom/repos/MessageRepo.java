package com.example.diplom.repos;

import com.example.diplom.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Long> {
    List<Message> findByReal(Integer real);
    Message findByQr(String qr);
    boolean existsByQr(String qr);

}
