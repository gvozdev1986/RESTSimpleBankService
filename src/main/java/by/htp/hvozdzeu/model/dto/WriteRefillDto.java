package by.htp.hvozdzeu.model.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * DTO for mapping write-off and refill (back) amount methods
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class WriteRefillDto {

    private String tokenRest;
    private String cardNumber;
    private BigDecimal amount;
    private String cvCode;
    private String appSecretCode;

    private WriteRefillDto() {
    }

    public static Builder getBuilder() {
        return new WriteRefillDto().new Builder();
    }

    public String getTokenRest() {
        return tokenRest;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public BigDecimal getAmount() {
        return amount;
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
        WriteRefillDto that = (WriteRefillDto) o;
        return Objects.equals(tokenRest, that.tokenRest) &&
                Objects.equals(cardNumber, that.cardNumber) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(cvCode, that.cvCode) &&
                Objects.equals(appSecretCode, that.appSecretCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tokenRest, cardNumber, amount, cvCode, appSecretCode);
    }

    @Override
    public String toString() {
        return "WriteRefillDto{" +
                "tokenRest='" + tokenRest + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", amount='" + amount + '\'' +
                ", cvCode='" + cvCode + '\'' +
                ", appSecretCode='" + appSecretCode + '\'' +
                '}';
    }

    public class Builder {

        private Builder() {
        }

        public Builder tokenRest(String tokenRest) {
            WriteRefillDto.this.tokenRest = tokenRest;
            return this;
        }

        public Builder cardNumber(String cardNumber) {
            WriteRefillDto.this.cardNumber = cardNumber;
            return this;
        }

        public Builder amount(BigDecimal amount) {
            WriteRefillDto.this.amount = amount;
            return this;
        }

        public Builder cvCode(String cvCode) {
            WriteRefillDto.this.cvCode = cvCode;
            return this;
        }

        public Builder appSecretCode(String appSecretCode) {
            WriteRefillDto.this.appSecretCode = appSecretCode;
            return this;
        }

        public WriteRefillDto build() {
            return WriteRefillDto.this;
        }


    }


}
