package br.com.duosdevelop.apibankddd.domain;

import br.com.duosdevelop.apibankddd.domain.base.EntityBase;

import javax.persistence.Entity;

@Entity
public class Account extends EntityBase<Account> {

    private String name;
    private Amount balance = new Amount(0);

    public Account(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Amount getBalance() {
        return balance;
    }

    public AccountNum accountNum(){
        return new AccountNum(getId());
    }

    public void setBalance(Amount amount) {
        this.balance = amount;
    }

    public static final Amount getMinimumBalance() {
        return new Amount(-1000, 0);
    }

    @Override
    public String toString() {
        return String.format("Account{accountNum=%d, name='%s', balance='%s'}", accountNum(), name, balance);
    }
}
