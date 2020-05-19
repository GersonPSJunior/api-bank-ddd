package br.com.duosdevelop.apibankddd.domain;

import java.util.Objects;

public class AccountNum {

    private Long number;

    public AccountNum(Long number) {
        if(number == null)
            throw new IllegalArgumentException("null");
        this.number = number;
    }

    public AccountNum(String number) {
        if (number == null)
            throw new IllegalArgumentException("null");
        if (!number.matches("\\d+"))
            throw new IllegalArgumentException("null");
        this.number = Long.parseLong(number);
    }

    public Long toLong(){
        return number;
    }

    public String toString(){
        return number.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountNum accountNum = (AccountNum) o;
        return Objects.equals(number, accountNum.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
