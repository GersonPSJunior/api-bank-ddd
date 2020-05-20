package br.com.duosdevelop.apibankddd.domain;

import br.com.duosdevelop.apibankddd.domain.base.EntityBase;
import br.com.duosdevelop.apibankddd.domain.contracts.AccountAccessRepository;
import br.com.duosdevelop.apibankddd.domain.contracts.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Client extends EntityBase<Client> {

    @Column(unique = true, nullable = false)
    private String username;
    private LocalDate birthDate;

    @SuppressWarnings("unused")
    private Client() {
    }

    public Client(String username, LocalDate birthDate) {
        this.username = username;
        this.birthDate = birthDate;
    }

    public String getUsername() {
        return username;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public String toString() {
        return String.format("Client{id=%d, name='%s', birthDate='%s'}", getId(), username, birthDate);
    }

    @Autowired
    private transient AccountRepository accountRepository;

    @Autowired
    private transient AccountAccessRepository accountAccessRepository;

    public AccountAccess createAccount(String accountName){
        Account account = accountRepository.insert(new Account(accountName));
        AccountAccess accountAccess = new AccountAccess(this, true, account);
        return accountAccessRepository.insert(accountAccess);
    }
}
