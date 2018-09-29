package by.htp.hvozdzeu.model;

import by.htp.hvozdzeu.model.entity.Entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement(name = "account")
@XmlAccessorType(XmlAccessType.FIELD)
public class Account extends Entity {

    private static final long serialVersionUID = 7207746452172066362L;

    private String login;
    private String password;

    private Account() {}

    public static Builder getBuilder() {
        return new Account().new Builder();
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Account account = (Account) o;
        return Objects.equals(login, account.login) &&
                Objects.equals(password, account.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                super.hashCode(),
                login,
                password
        );
    }

    @Override
    public String toString() {
        return "Account{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public class Builder {

        private Builder(){}

        public Builder id(Long id) {
            Account.this.setId(id);
            return this;
        }

        public Builder login(String login) {
            Account.this.login = login;
            return this;
        }

        public Builder password(String password) {
            Account.this.password = password;
            return this;
        }

        public Account build() {
            return Account.this;
        }

    }
}
