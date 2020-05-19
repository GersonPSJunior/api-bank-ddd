package br.com.duosdevelop.apibankddd.domain;

import br.com.duosdevelop.apibankddd.domain.base.EntityBase;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class AccountAccess extends EntityBase<AccountAccess> {

    @ManyToOne
    private Client client;

    private boolean isOwner;

    @ManyToOne
    private Account account;

    public AccountAccess(Client client, boolean isOwner, Account account) {
        this.client = client;
        this.isOwner = isOwner;
        this.account = account;
    }

    public Client getClient() {
        return client;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public Account getAccount() {
        return account;
    }

    @Override
    public String toString() {
        return String.format("%s{client='%s', isOwner='%b', account='%s'}", getClass().getSimpleName(), client, isOwner, account);
    }
}
