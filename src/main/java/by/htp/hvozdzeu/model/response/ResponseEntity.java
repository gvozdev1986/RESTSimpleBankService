package by.htp.hvozdzeu.model.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * The entity for send answer to client in format XML
 * Use XML elements for fork Jersey library,
 * sending and getting data.
 */
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseEntity {

    private boolean status;
    private String message;

    private ResponseEntity() {
    }

    public static Builder getBuilder() {
        return new ResponseEntity().new Builder();
    }

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseEntity responseEntity = (ResponseEntity) o;
        return status == responseEntity.status &&
                Objects.equals(message, responseEntity.message);
    }

    @Override
    public int hashCode() {

        return Objects.hash(status, message);
    }

    @Override
    public String toString() {
        return "ResponseEntity{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }

    public class Builder {

        private Builder() {
        }

        public Builder status(boolean status) {
            ResponseEntity.this.status = status;
            return this;
        }

        public Builder message(String message) {
            ResponseEntity.this.message = message;
            return this;
        }

        public ResponseEntity build() {
            return ResponseEntity.this;
        }

    }

}
