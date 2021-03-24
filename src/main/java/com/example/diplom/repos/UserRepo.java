package com.example.diplom.repos;

        import org.springframework.data.jpa.repository.JpaRepository;
        import com.example.diplom.domain.User;


public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findById(long id);
}
