package by.htp.hvozdzeu.model.report;

import by.htp.hvozdzeu.model.entity.Entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Objects;

@XmlRootElement(name = "balance")
@XmlAccessorType(XmlAccessType.FIELD)
public class BalanceAccount extends Entity {

    private static final long serialVersionUID = -1277828775835068862L;

    private String cardNumber;
    private BigDecimal balanceBankAccount;

    private BalanceAccount() {
    }

    public static Builder getBuilder() {
        return new BalanceAccount().new Builder();
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public BigDecimal getBalanceBankAccount() {
        return balanceBankAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BalanceAccount that = (BalanceAccount) o;
        return Objects.equals(cardNumber, that.cardNumber) &&
                Objects.equals(balanceBankAccount, that.balanceBankAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cardNumber, balanceBankAccount);
    }

    @Override
    public String toString() {
        return "BalanceAccount{" +
                "cardNumber='" + cardNumber + '\'' +
                ", balanceBankAccount=" + balanceBankAccount +
                '}';
    }

    public class Builder {

        private Builder() {
        }

        public Builder id(Long id) {
            BalanceAccount.this.setId(id);
            return this;
        }

        public Builder cardNumber(String cardNumber) {
            BalanceAccount.this.cardNumber = cardNumber;
            return this;
        }

        public Builder balanceBankAccount(BigDecimal balanceBankAccount) {
            BalanceAccount.this.balanceBankAccount = balanceBankAccount;
            return this;
        }

        public BalanceAccount build() {
            return BalanceAccount.this;
        }

    }

}
