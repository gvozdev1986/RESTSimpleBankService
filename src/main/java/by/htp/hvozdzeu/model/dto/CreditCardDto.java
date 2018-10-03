package by.htp.hvozdzeu.model.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * DTO for mapping CreditCard
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CreditCardDto {

    private String creditCardNumber;
    private String cvCode;
    private String firstName;
    private String lastName;
    private String monthValid;
    private String yearValid;
    private String appSecretCode;

    private CreditCardDto() {
    }

    public static CreditCardDto.Builder getBuilder() {
        return new CreditCardDto().new Builder();
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public String getCvCode() {
        return cvCode;
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

    public String getAppSecretCode() {
        return appSecretCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCardDto that = (CreditCardDto) o;
        return Objects.equals(creditCardNumber, that.creditCardNumber) &&
                Objects.equals(cvCode, that.cvCode) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(monthValid, that.monthValid) &&
                Objects.equals(yearValid, that.yearValid) &&
                Objects.equals(appSecretCode, that.appSecretCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creditCardNumber, cvCode, firstName, lastName, monthValid, yearValid, appSecretCode);
    }

    @Override
    public String toString() {
        return "CreditCardDto{" +
                "creditCardNumber='" + creditCardNumber + '\'' +
                ", cvCode='" + cvCode + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", monthValid='" + monthValid + '\'' +
                ", yearValid='" + yearValid + '\'' +
                ", appSecretCode='" + appSecretCode + '\'' +
                '}';
    }

    public class Builder {

        private Builder() {
        }

        public Builder creditCardNumber(String creditCardNumber) {
            CreditCardDto.this.creditCardNumber = creditCardNumber;
            return this;
        }

        public Builder cvCode(String cvCode) {
            CreditCardDto.this.cvCode = cvCode;
            return this;
        }

        public Builder firstName(String firstName) {
            CreditCardDto.this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            CreditCardDto.this.lastName = lastName;
            return this;
        }

        public Builder monthValid(String monthValid) {
            CreditCardDto.this.monthValid = monthValid;
            return this;
        }

        public Builder yearValid(String yearValid) {
            CreditCardDto.this.yearValid = yearValid;
            return this;
        }

        public Builder appSecretCode(String appSecretCode) {
            CreditCardDto.this.appSecretCode = appSecretCode;
            return this;
        }

        public CreditCardDto build() {
            return CreditCardDto.this;
        }

    }

}
