package redis.security.com.dto;

import lombok.Data;
import redis.security.com.dao.Master;

@Data
public class MasterDto {

    private String name;
    private String lastName;
    private String skill;
    private String style;
    private String login;

    public static MasterDto parseMaster (Master master) {
        MasterDto masterDto = new MasterDto();
        if (master != null) {
            masterDto.setLastName(master.getLastName());
            masterDto.setName(master.getName());
            masterDto.setSkill(master.getSkill());
            masterDto.setStyle(master.getStyle());
            masterDto.setLogin(master.getLogin().getUsername());
        }
        return masterDto;
    }
}
