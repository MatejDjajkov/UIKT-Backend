package finki.mk.uiktBackend.repository;

import finki.mk.uiktBackend.model.auth.UserInApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserInApp, Long> {

    UserInApp findByEmail(String email);
}
