package redis.security.com.dto;

import lombok.Data;
import redis.security.com.dao.Studio;
import redis.security.com.dao.StudioId;

import java.util.ArrayList;
import java.util.List;

@Data
public class StudioDto {

    private StudioId studioId;
    private List <MasterDto> master;

    public static StudioDto parseStudio (Studio studio) {
        StudioDto studioDto = new StudioDto();
        if (studio != null) {
            studioDto.setStudioId(studio.getStudioId());
            studioDto.setMaster(new ArrayList<>(studio.getMasters().stream().map(MasterDto::parseMaster).toList()));
        }
        return studioDto;
    }
}
