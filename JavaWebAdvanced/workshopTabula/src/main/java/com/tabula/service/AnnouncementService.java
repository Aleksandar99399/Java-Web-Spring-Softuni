package com.tabula.service;

import com.tabula.announcement.model.AnnouncementDto;

import java.util.List;

public interface AnnouncementService {

    List<AnnouncementDto> findAll();
}
