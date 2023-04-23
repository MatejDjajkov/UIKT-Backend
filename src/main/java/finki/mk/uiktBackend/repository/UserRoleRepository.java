package finki.mk.uiktBackend.repository;


import finki.mk.uiktBackend.model.auth.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoles, Long> {
}
