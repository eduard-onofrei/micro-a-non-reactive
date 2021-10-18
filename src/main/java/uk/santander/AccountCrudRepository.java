package uk.santander;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountCrudRepository
        extends CrudRepository<Account, String> {

    List<Account> findAllByValue(String value);
    List<Account> findAllByOwner(String owner);
}