package rillmo.project.springlogin;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import rillmo.project.springlogin.dto.account.UpdateAccountDTO;
import rillmo.project.springlogin.model.Account;
import rillmo.project.springlogin.model.User;
import rillmo.project.springlogin.repository.AccountRepository;
import rillmo.project.springlogin.service.AccountService;
import rillmo.project.springlogin.service.UserService;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class AccountTest {

    @Autowired
    private AccountService accountService;    
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private EntityManager em;

    private static User user1;
    private static Account account1;
    private static Account account2;
    private static Account account3;

    @BeforeAll
    static void setAccount() {
        // make user
        user1 = User.builder()
                .name("name1")
                .email("email")
                .password("password1")
                .build();
        // make Account
        account1 = Account.builder()
                .user(user1)
                .nickname("nickname1")
                .build();
        account2 = Account.builder()
                .user(user1)
                .nickname("nickname2")
                .build();
        account3 = Account.builder()
                .user(user1)
                .nickname("nickname3")
                .build();
    }

    @Test
    void saveAccount() {
        userService.saveUser(user1);
        Account savedAccount = accountRepository.save(account1);

        assertThat(account1.getId()).isEqualTo(savedAccount.getId());
        assertThat(user1.getId()).isEqualTo(savedAccount.getUser().getId());
    }

    @Test
    void findAll() {
        // save
        userService.saveUser(user1);
        accountRepository.save(account1);
        accountRepository.save(account2);
        accountRepository.save(account3);

        em.flush();
        em.clear();

        List<Account> accountList = accountService.findAll();

        assertThat(accountList.size()).isEqualTo(3);
    }

    @Test
    void findById() {
        // save
        userService.saveUser(user1);
        accountRepository.save(account1);
        accountRepository.save(account2);
        accountRepository.save(account3);

        em.flush();
        em.clear();

        Account findAccount = accountService.findAccountById(account1.getId());

        assertThat(findAccount.getId()).isEqualTo(account1.getId());
    }

    @Test
    void findAllAccount() {
        // save
        userService.saveUser(user1);
        accountRepository.save(account1);
        accountRepository.save(account2);
        accountRepository.save(account3);

        em.flush();
        em.clear();

        List<Account> accountList = accountService.findAccountByUser(user1.getId());

        assertThat(accountList.size()).isEqualTo(3);
    }

    @Test
    void updateAccount() {
        // save
        userService.saveUser(user1);
        accountRepository.save(account1);
        accountRepository.save(account2);
        accountRepository.save(account3);

        em.flush();
        em.clear();

        Account updatedAccount = accountService.updateAccount(user1.getId(), account1.getId(), new UpdateAccountDTO("updated!"));

        assertThat(updatedAccount.getNickname()).isEqualTo("updated!");
    }

    @Test
    void deleteAcocunt() {
        // save
        userService.saveUser(user1);
        accountRepository.save(account1);
        accountRepository.save(account2);
        accountRepository.save(account3);

        em.flush();
        em.clear();

        Account deletedAccount = accountService.deleteAccount(user1.getId(), account1.getId());

        assertThat(deletedAccount.getId()).isEqualTo(account1.getId());
    }
}
