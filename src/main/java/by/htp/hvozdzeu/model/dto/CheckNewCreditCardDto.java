package by.htp.hvozdzeu.model.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * DTO for checking new credit card inserted from client
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CheckNewCreditCardDto {

    private String tokenRest;
    private String cardNumber;
    private String cvCode;
    private String appSecretCode;

    private CheckNewCreditCardDto() {
    }

    public static CheckNewCreditCardDto.Builder getBuilder() {
        return new CheckNewCreditCardDto().new Builder();
    }

    public String getTokenRest() {
        return tokenRest;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCvCode() {
        return cvCode;
    }

    public String getAppSecretCode() {
        return appSecretCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckNewCreditCardDto that = (CheckNewCreditCardDto) o;
        return Objects.equals(tokenRest, that.tokenRest) &&
                Objects.equals(cardNumber, that.cardNumber) &&
                Objects.equals(cvCode, that.cvCode) &&
                Objects.equals(appSecretCode, that.appSecretCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tokenRest, cardNumber, cvCode, appSecretCode);
    }

    @Override
    public String toString() {
        return "CheckNewCreditCardDto{" +
                "tokenRest='" + tokenRest + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cvCode='" + cvCode + '\'' +
                ", appSecretCode='" + appSecretCode + '\'' +
                '}';
    }

    public class Builder {

        private Builder() {
        }

        public Builder tokenRest(String tokenRest) {
            CheckNewCreditCardDto.this.tokenRest = tokenRest;
            return this;
        }

        public Builder cardNumber(String cardNumber) {
            CheckNewCreditCardDto.this.cardNumber = cardNumber;
            return this;
        }

        public Builder cvCode(String cvCode) {
            CheckNewCreditCardDto.this.cvCode = cvCode;
            return this;
        }

        public Builder appSecretCode(String appSecretCode) {
            CheckNewCreditCardDto.this.appSecretCode = appSecretCode;
            return this;
        }

        public CheckNewCreditCardDto build() {
            return CheckNewCreditCardDto.this;
        }

    }


}
