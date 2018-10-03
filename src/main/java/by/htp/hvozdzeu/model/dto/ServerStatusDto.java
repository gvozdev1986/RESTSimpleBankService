package by.htp.hvozdzeu.model.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * DTO for mapping Server status
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ServerStatusDto {

    private String appSecretCode;

    private ServerStatusDto() {
    }

    public static ServerStatusDto.Builder getBuilder() {
        return new ServerStatusDto().new Builder();
    }

    public String getAppSecretCode() {
        return appSecretCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServerStatusDto that = (ServerStatusDto) o;
        return Objects.equals(appSecretCode, that.appSecretCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appSecretCode);
    }

    @Override
    public String toString() {
        return "ServerStatusDto{" +
                "appSecretCode='" + appSecretCode + '\'' +
                '}';
    }

    public class Builder {

        private Builder() {
        }

        public Builder appSecretCode(String appSecretCode) {
            ServerStatusDto.this.appSecretCode = appSecretCode;
            return this;
        }

        public ServerStatusDto build() {
            return ServerStatusDto.this;
        }

    }


}
