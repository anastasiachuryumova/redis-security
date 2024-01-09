package redis.security.com.dto;

import lombok.Data;
import redis.security.com.dao.Client;

@Data
public class ClientDto {

    private String login;
    private Integer age;
    private String name;
    private String lastName;
    private String master;

    public static ClientDto parseClient (Client client) {
        ClientDto clientDto = new ClientDto();
        if (client != null) {
            clientDto.setAge(client.getAge());
            clientDto.setLogin(client.getUsername().getUsername());
            clientDto.setName(client.getName());
            clientDto.setLastName(client.getLastName());
            clientDto.setMaster(client.getMaster() != null ? client.getMaster().getName() : null );
        }
        return clientDto;
    }
}
