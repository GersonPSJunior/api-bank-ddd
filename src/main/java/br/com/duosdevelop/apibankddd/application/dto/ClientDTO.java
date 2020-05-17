package br.com.duosdevelop.apibankddd.application.dto;

import br.com.duosdevelop.apibankddd.application.Util;
import br.com.duosdevelop.apibankddd.domain.Client;
import io.swagger.annotations.ApiModelProperty;

public class ClientDTO {

    @ApiModelProperty(notes = "The database generated client ID")
    public Long id;

    @ApiModelProperty(notes = "The username of the client. Must be unique", required = true)
    public String username;

    @ApiModelProperty(notes = "The birth date of the client in format 31/12/2000", required = true)
    public String birthDate;

    public ClientDTO() {
    }

    public ClientDTO(Client client) {
        id = client.getId();
        username = client.getUsername();
        birthDate = Util.MEDIUM_DATE_FORMATTER.format(client.getBirthDate());
    }

    @Override
    public String toString() {
        return String.format("Client{id=%d, username='%s', birthDate='%s'}", id, username, birthDate);
    }
}
