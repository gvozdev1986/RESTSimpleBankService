package by.htp.hvozdzeu.model;

import by.htp.hvozdzeu.model.entity.Entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * The entity for signature CreditCard
 * extends Entity for getting ID. Use XML elements for fork Jersey library,
 * sending and getting data.
 */
@XmlRootElement(name = "card")
@XmlAccessorType(XmlAccessType.FIELD)
public class CreditCard extends Entity {

    private String creditCardNumber;
    private String cvCode;
    private BigDecimal creditCardBalance;
    private String firstName;
    private String lastName;
    private String monthValid;
    private String yearValid;

    private CreditCard() {
    }

    public static CreditCard.Builder getBuilder() {
        return new CreditCard().new Builder();
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public String getCvCode() {
        return cvCode;
    }

    public BigDecimal getCreditCardBalance() {
        return creditCardBalance;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMonthValid() {
        return monthValid;
    }

    public String getYearValid() {
        return yearValid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CreditCard that = (CreditCard) o;
        return Objects.equals(creditCardNumber, that.creditCardNumber) &&
                Objects.equals(cvCode, that.cvCode) &&
                Objects.equals(creditCardBalance, that.creditCardBalance) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(monthValid, that.monthValid) &&
                Objects.equals(yearValid, that.yearValid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), creditCardNumber, cvCode, creditCardBalance, firstName, lastName, monthValid, yearValid);
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "creditCardNumber='" + creditCardNumber + '\'' +
                ", cvCode='" + cvCode + '\'' +
                ", creditCardBalance=" + creditCardBalance +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", monthValid='" + monthValid + '\'' +
                ", yearValid='" + yearValid + '\'' +
                '}';
    }

    public class Builder {

        private Builder() {
        }

        public Builder id(Long id) {
            CreditCard.this.setId(id);
            return this;
        }

        public Builder creditCardNumber(String creditCardNumber) {
            CreditCard.this.creditCardNumber = creditCardNumber;
            return this;
        }

        public Builder cvCode(String cvCode) {
            CreditCard.this.cvCode = cvCode;
            return this;
        }

        public Builder creditCardBalance(BigDecimal creditCardBalance) {
            CreditCard.this.creditCardBalance = creditCardBalance;
            return this;
        }

        public Builder firstName(String firstName) {
            CreditCard.this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            CreditCard.this.lastName = lastName;
            return this;
        }

        public Builder monthValid(String monthValid) {
            CreditCard.this.monthValid = monthValid;
            return this;
        }

        public Builder yearValid(String yearValid) {
            CreditCard.this.yearValid = yearValid;
            return this;
        }

        public CreditCard build() {
            return CreditCard.this;
        }

    }

}
