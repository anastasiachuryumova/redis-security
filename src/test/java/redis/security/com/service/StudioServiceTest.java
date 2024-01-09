package redis.security.com.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import redis.security.com.dao.Master;
import redis.security.com.dao.Studio;
import redis.security.com.dao.StudioId;
import redis.security.com.dao.User;
import redis.security.com.dto.StudioDto;
import redis.security.com.repository.MasterRepository;
import redis.security.com.repository.StudioRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class StudioServiceTest {

    @InjectMocks
    StudioService studioService;

    @Mock
    StudioRepository studioRepository;

    @Mock
    MasterRepository masterRepository;

    public Studio studio;
    public User user;
    public Master master;

    @BeforeEach
    public void init () {
        studio = createStudio ();
        user = createUser ();
        master = createMaster ();
    }

    public User createUser () {
        user = new User();
        user.setId(1);
        user.setUsername("Test");
        user.setEnabled(true);
        user.setEmail("gmail.com");
        user.setPassword("test-psw");
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

    public Studio createStudio () {
        studio = new Studio();
        studio.setStudioId(new StudioId("TestTitle", "TestAddress"));
        studio.setMasters(null);
        return studio;
    }

    @Test
    public void addStudio () {
        StudioId studioId = new StudioId("TestTitle", "TestAddress");
        Studio studioTest = studioService.addStudio(studioId);
        assertEquals(studio, studioTest);
    }

    @Test
    public void addMasterTest () {
        Optional <Studio> studioExpected = Optional.of(createStudio());
        Optional <Master> masterExpected = Optional.of(createMaster());
        Mockito.when(studioRepository.findById(studio.getStudioId())).thenReturn(studioExpected);
        Mockito.when(masterRepository.findById(master.getId())).thenReturn(masterExpected);
        StudioDto studioDto = studioService.addMaster(studio.getStudioId(), master.getId());
        Studio expectedStudio = createStudio();
        expectedStudio.setMasters(List.of(createMaster()));
        assertEquals(StudioDto.parseStudio(expectedStudio), studioDto);
    }
}
