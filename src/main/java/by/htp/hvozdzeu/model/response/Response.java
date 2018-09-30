package by.htp.hvozdzeu.model.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class Response {

    private boolean status;
    private String message;

    private Response() {
    }

    public static Builder getBuilder() {
        return new Response().new Builder();
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
        Response response = (Response) o;
        return status == response.status &&
                Objects.equals(message, response.message);
    }

    @Override
    public int hashCode() {

        return Objects.hash(status, message);
    }

    @Override
    public String toString() {
        return "Response{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }

    public class Builder {

        private Builder() {
        }

        public Builder status(boolean status) {
            Response.this.status = status;
            return this;
        }

        public Builder message(String message) {
            Response.this.message = message;
            return this;
        }

        public Response build() {
            return Response.this;
        }

    }


}
