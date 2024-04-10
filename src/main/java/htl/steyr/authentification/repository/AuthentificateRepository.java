package htl.steyr.authentification.repository;

import htl.steyr.authentification.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface AuthentificateRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

    Optional<User> findUserByEmail(String email);
}
