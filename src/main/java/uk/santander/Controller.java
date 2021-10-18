package uk.santander;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class Controller {
    private static final int COMPLEXITY = 1000000;

    @Autowired
    private final AccountCrudRepository accountRepository;

    @PostMapping
    public Account createAccount(@RequestBody @Valid Account account){
        log.info("Request with {}", account);
        return accountRepository.save(account);
    }

    @GetMapping
    public List<Account> getAccount(@RequestParam @Valid String owner){
        final List<Account> accounts = accountRepository.findAllByOwner(owner);
        accounts.forEach(account -> {
            for(int i=0;i<COMPLEXITY;i++){
                account.setResult(account.getValue()*Math.random());
            }
        });
        return accounts;
    }
}
