package by.htp.hvozdzeu.model.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * DTO for mapping Transaction
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TransactionDto {

    private String tokenRest;
    private String cardNumberFrom;
    private String cardNumberTo;
    private BigDecimal amount;
    private String cvCode;
    private String appSecretCode;

    private TransactionDto() {
    }

    public static Builder getBuilder() {
        return new TransactionDto().new Builder();
    }

    public String getTokenRest() {
        return tokenRest;
    }

    public String getCardNumberFrom() {
        return cardNumberFrom;
    }

    public String getCardNumberTo() {
        return cardNumberTo;
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
        TransactionDto that = (TransactionDto) o;
        return Objects.equals(tokenRest, that.tokenRest) &&
                Objects.equals(cardNumberFrom, that.cardNumberFrom) &&
                Objects.equals(cardNumberTo, that.cardNumberTo) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(cvCode, that.cvCode) &&
                Objects.equals(appSecretCode, that.appSecretCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tokenRest, cardNumberFrom, cardNumberTo, amount, cvCode, appSecretCode);
    }

    @Override
    public String toString() {
        return "TransactionDto{" +
                "tokenRest='" + tokenRest + '\'' +
                ", cardNumberFrom='" + cardNumberFrom + '\'' +
                ", cardNumberTo='" + cardNumberTo + '\'' +
                ", amount=" + amount +
                ", cvCode='" + cvCode + '\'' +
                ", appSecretCode='" + appSecretCode + '\'' +
                '}';
    }

    public class Builder {

        private Builder() {
        }

        public Builder tokenRest(String tokenRest) {
            TransactionDto.this.tokenRest = tokenRest;
            return this;
        }

        public Builder cardNumberFrom(String cardNumberFrom) {
            TransactionDto.this.cardNumberFrom = cardNumberFrom;
            return this;
        }

        public Builder cardNumberTo(String cardNumberTo) {
            TransactionDto.this.cardNumberTo = cardNumberTo;
            return this;
        }

        public Builder amount(BigDecimal amount) {
            TransactionDto.this.amount = amount;
            return this;
        }


        public Builder cvCode(String cvCode) {
            TransactionDto.this.cvCode = cvCode;
            return this;
        }

        public Builder appSecretCode(String appSecretCode) {
            TransactionDto.this.appSecretCode = appSecretCode;
            return this;
        }

        public TransactionDto build() {
            return TransactionDto.this;
        }


    }


}
