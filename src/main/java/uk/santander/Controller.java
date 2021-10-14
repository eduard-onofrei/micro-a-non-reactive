package uk.santander;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class Controller {
    private static final int COMPLEXITY = 1000000;

    @Autowired
    private final AccountCrudRepository accountCrudRepository;

    @PostMapping
    public Mono<Account> createAccount(@RequestBody @Valid Account account){
        log.info("Request with {}", account);
        return accountCrudRepository.save(account);
    }

    @GetMapping
    public Flux<Account> getAccount(@RequestParam @Valid String owner){
        return accountCrudRepository.findAllByOwner(owner)
                .map(account -> {
                    for(int i=0;i<COMPLEXITY;i++){
                        account.setResult(account.getValue()*Math.random());
                    }
                    return account;
                });
    }
}
