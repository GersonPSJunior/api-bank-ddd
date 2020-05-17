package br.com.duosdevelop.apibankddd.domain;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Amount {

    private long cents;

    public Amount(final int real, final int cents) {
        final double centsAsDouble = 100.0 * real + cents;
        this.cents = Math.round(centsAsDouble);
    }

    public Amount(final double real) {
        if (real < minValue() || maxValue() < real) {
            throw new IllegalArgumentException(String.format("O valor de %f reais está fora da faixa.", real));
        }
        final long result = Math.round(real * 100.0);

        if (result == Long.MIN_VALUE || result == Long.MAX_VALUE) {
            throw new IllegalArgumentException(String.format("O valor de %f reais está fora da faixa.", real));
        }
        cents = result;
    }

    public Amount plus(final Amount other) {
        final double doubleResult = this.toDouble() + other.toDouble();
        final Amount result = new Amount(doubleResult);
        return result;
    }

    public Amount minus(final Amount other) {
        final double result = this.toDouble() - other.toDouble();
        return new Amount(result);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount = (Amount) o;
        return cents == amount.cents;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cents);
    }

    public long getCents() {
        return cents;
    }

    public static double maxValue() {
        return 9E13;
    }

    public static double minValue() {
        return -maxValue();
    }

    public double toDouble() {
        return cents / 100.0;
    }

    public String toString() {
        return String.format("%.2f", toDouble());
    }

}
