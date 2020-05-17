package br.com.duosdevelop.apibankddd.domain;

import br.com.duosdevelop.apibankddd.domain.base.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Client extends EntityBase<Client> {

    private String username;
    private LocalDate birthDate;

    public Client(String username, LocalDate birthDate) {
        this.username = username;
        this.birthDate = birthDate;
    }

    @Column(unique = true, nullable = false)
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
}
