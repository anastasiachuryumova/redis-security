package redis.security.com.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.security.com.dao.Master;
import redis.security.com.dao.Studio;
import redis.security.com.dao.StudioId;
import redis.security.com.dto.StudioDto;
import redis.security.com.repository.MasterRepository;
import redis.security.com.repository.StudioRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StudioService {

    private final StudioRepository studioRepository;
    private final MasterRepository masterRepository;

    public Studio addStudio (StudioId id) {
        Studio studio = new Studio();
        studio.setStudioId(id);
        studioRepository.save(studio);
        return studio;
    }

    public List <StudioDto> getAll () {
        return studioRepository.findAll().stream().map(StudioDto::parseStudio).toList();
    }

    public StudioDto addMaster (StudioId id, Long masterId) {
        Studio studio = studioRepository.findById(id).orElse(null);
        Master master = masterRepository.findById(masterId).orElse(null);
        if (studio != null && master != null) {
            List <Master> studioMasters = studio.getMasters() != null ? new ArrayList<>(studio.getMasters()) : new ArrayList<>();
            studioMasters.add(master);
            studio.setMasters(studioMasters);
            studioRepository.save(studio);
        }
        return StudioDto.parseStudio(studio);
    }
}
