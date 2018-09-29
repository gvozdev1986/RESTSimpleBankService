package by.htp.hvozdzeu.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "cards")
@XmlAccessorType(XmlAccessType.FIELD)
public class CreditCard {

    private String creditCardNo;
    private String creditCardOwner;
    private BigDecimal creditCardBalance;

    private CreditCard() {

    }

    public CreditCard(String creditCardNo, String creditCardOwner, BigDecimal creditCardBalance) {
        this.creditCardNo = creditCardNo;
        this.creditCardOwner = creditCardOwner;
        this.creditCardBalance = creditCardBalance;
    }

    public String getCreditCardNo() {
        return creditCardNo;
    }

    public void setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

    public String getCreditCardOwner() {
        return creditCardOwner;
    }

    public void setCreditCardOwner(String creditCardOwner) {
        this.creditCardOwner = creditCardOwner;
    }

    public BigDecimal getCreditCardBalance() {
        return creditCardBalance;
    }

    public void setCreditCardBalance(BigDecimal creditCardBalance) {
        this.creditCardBalance = creditCardBalance;
    }


}
