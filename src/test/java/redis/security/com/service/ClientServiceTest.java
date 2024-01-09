package redis.security.com.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import redis.security.com.dao.Master;
import redis.security.com.dao.User;
import redis.security.com.dao.Client;
import redis.security.com.dto.ClientDto;
import redis.security.com.repository.ClientRepository;
import redis.security.com.repository.MasterRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @InjectMocks
    ClientService clientService;

    @Mock
    ClientRepository clientRepository;

    @Mock
    MasterRepository masterRepository;

    public Client client;
    public Master master;
    public User user;

    @BeforeEach
    public void init () {
        client = createClient ();
        master = createMaster ();
        user = createUser ();
    }

    public User createUser () {
        user = new User();
        user.setId(1);
        user.setUsername("Test");
        user.setEnabled(true);
        user.setEmail("gmail.com");
        user.setPassword("test_psw");
        user.setLastActivity("2023-12-12");
        user.setPriority(5);
        user.setIsDeleted(false);
        return user;
    }

    public Master createMaster () {
        master = new Master();
        master.setId(1L);
        master.setLastName("TestMaster lastName");
        master.setName("TestMaster name");
        master.setLogin(user);
        master.setSkill("Skill");
        master.setStudios(null);
        master.setStyle("Style");
        return master;
    }

    public Client createClient () {
        client = new Client();
        client.setClientId(1L);
        client.setAge(18);
        client.setLastName("Test lastname");
        client.setName("Test name");
        client.setUsername(user);
        client.setMaster(null);
        return client;
    }

    @Test
    public void addMasterTest () {
        Optional <Client> clientExpected = Optional.of(createClient());
        Optional <Master> masterExpected = Optional.of(createMaster());
        Mockito.when(clientRepository.findById(client.getClientId())).thenReturn(clientExpected);
        Mockito.when(masterRepository.findById(master.getId())).thenReturn(masterExpected);
        Client expectedClient = createClient();
        expectedClient.setMaster(createMaster());
        ClientDto clientDto = clientService.addMaster(client.getClientId(), master.getId());
        assertEquals(ClientDto.parseClient(expectedClient), clientDto);
    }

    @Test
    public void removeMasterTest () {
        Optional <Client> clientExpected = Optional.of(createClient());
        Mockito.when(clientRepository.findById(client.getClientId())).thenReturn(clientExpected);
        client.setMaster(createMaster());
        ClientDto clientDto = clientService.removeMaster(client.getClientId());
        Client expectedClient = createClient();
        assertEquals(ClientDto.parseClient(expectedClient), clientDto);
    }
}
