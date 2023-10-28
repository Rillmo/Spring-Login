package rillmo.project.springlogin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rillmo.project.springlogin.dto.account.CreateAccountDTO;
import rillmo.project.springlogin.dto.account.UpdateAccountDTO;
import rillmo.project.springlogin.model.Account;
import rillmo.project.springlogin.service.AccountService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/account/v1")
public class AccountController {
    private final AccountService accountService;

    // make account for specific user
    @PostMapping("/accounts")
    public ResponseEntity<Object> createAccount(@RequestParam("userId") String userId,
                                                @RequestBody CreateAccountDTO request) {
        Account savedAccount = accountService.saveAccount(userId, request.toEntity());
        return new ResponseEntity<>(savedAccount, HttpStatus.OK);
    }

    // find account list for specific user
    @GetMapping("/accounts")
    public ResponseEntity<Object> accountListForUser(@RequestParam("userId") String userId) {
        List<Account> accountList = accountService.findAccountByUser(userId);
        return new ResponseEntity<>(accountList, HttpStatus.OK);
    }

    // update account
    @PutMapping("/accounts")
    public ResponseEntity<Object> updateAccount(@RequestParam("userId") String userId,
                                                @RequestParam("accountId") String accountId,
                                                @RequestBody UpdateAccountDTO request) {
        Account updatedAccount = accountService.updateAccount(userId, accountId, request);
        return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
    }

    @DeleteMapping("/accounts")
    public ResponseEntity<Object> deleteAccount(@RequestParam("userId") String userId,
                                                @RequestParam("accountId") String accountId) {
        Account deletedAccount = accountService.deleteAccount(userId, accountId);
        return new ResponseEntity<>(deletedAccount, HttpStatus.OK);
    }
}
