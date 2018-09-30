package by.htp.hvozdzeu.model;

import by.htp.hvozdzeu.model.entity.Entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * The entity for signature Token
 * extends Entity for getting ID.
 */
public class Token extends Entity {

    private static final long serialVersionUID = 7207747575835066362L;

    private String tokenRest;
    private LocalDate date;
    private LocalTime time;
    private boolean available;

    private Token() {
    }

    public static Builder getBuilder() {
        return new Token().new Builder();
    }

    public String getTokenRest() {
        return tokenRest;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public boolean isAvailable() {
        return available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Token token1 = (Token) o;
        return available == token1.available &&
                Objects.equals(tokenRest, token1.tokenRest) &&
                Objects.equals(date, token1.date) &&
                Objects.equals(time, token1.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                super.hashCode(),
                tokenRest,
                date,
                time,
                available
        );
    }

    @Override
    public String toString() {
        return "Token{" +
                "tokenRest='" + tokenRest + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", available=" + available +
                '}';
    }

    public class Builder {

        private Builder() {
        }

        public Builder id(Long id) {
            Token.this.setId(id);
            return this;
        }

        public Builder tokenRest(String tokenRest) {
            Token.this.tokenRest = tokenRest;
            return this;
        }

        public Builder date(LocalDate date) {
            Token.this.date = date;
            return this;
        }

        public Builder time(LocalTime time) {
            Token.this.time = time;
            return this;
        }

        public Builder available(boolean available) {
            Token.this.available = available;
            return this;
        }

        public Token build() {
            return Token.this;
        }

    }

}
