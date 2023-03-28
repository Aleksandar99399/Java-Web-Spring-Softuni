package com.tabula.service.impl;

import com.tabula.announcement.model.AnnouncementDto;
import com.tabula.announcement.model.AnnouncementMapper;
import com.tabula.repository.AnnouncementRepository;
import com.tabula.service.AnnouncementService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    @Override
    public List<AnnouncementDto> findAll(){
        return announcementRepository.findAll()
                .stream()
                .map(AnnouncementMapper.INSTANCE::mapAnnouncementEntityToDto)
                .collect(Collectors.toList());
    }
}
