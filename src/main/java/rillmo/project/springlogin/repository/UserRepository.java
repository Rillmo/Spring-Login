package rillmo.project.springlogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rillmo.project.springlogin.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByEmail(String email);
}
