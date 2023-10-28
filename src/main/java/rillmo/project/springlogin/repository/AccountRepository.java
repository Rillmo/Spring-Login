package rillmo.project.springlogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rillmo.project.springlogin.model.Account;
import rillmo.project.springlogin.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    public List<Account> findByUser(User user);

    public Optional<Account> findByUserAndId(User user, String id);
}
