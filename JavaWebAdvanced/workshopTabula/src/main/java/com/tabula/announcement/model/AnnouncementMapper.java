package com.tabula.announcement.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AnnouncementMapper {

    AnnouncementMapper INSTANCE= Mappers.getMapper(AnnouncementMapper.class);

    AnnouncementEntity mapAnnouncementDtoToEntity(AnnouncementDto dto);

    AnnouncementDto mapAnnouncementEntityToDto(AnnouncementEntity entity);
}
