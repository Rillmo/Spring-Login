package rillmo.project.springlogin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rillmo.project.springlogin.dto.account.CreateAccountDTO;
import rillmo.project.springlogin.dto.account.UpdateAccountDTO;
import rillmo.project.springlogin.model.Account;
import rillmo.project.springlogin.model.User;
import rillmo.project.springlogin.repository.AccountRepository;
import rillmo.project.springlogin.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public Account saveAccount(String userId, Account account) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException());
        Account changedAccount = account.setUser(findUser);
        return accountRepository.save(changedAccount);
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Account findAccountById(String id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
    }

    public List<Account> findAccountByUser(String userId) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException());
        return accountRepository.findByUser(findUser);
    }

    @Transactional
    public Account updateAccount(String userId, String accountId, UpdateAccountDTO request) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException());
        Account findAccount = accountRepository.findByUserAndId(findUser, accountId)
                .orElseThrow(() -> new RuntimeException());
        return findAccount.update(request.getNickname());
    }

    public Account deleteAccount(String userId, String accountId) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException());
        Account findAccount = accountRepository.findByUserAndId(findUser, accountId)
                .orElseThrow(() -> new RuntimeException());
        accountRepository.delete(findAccount);
        return findAccount;
    }

}
