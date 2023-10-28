package rillmo.project.springlogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rillmo.project.springlogin.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
