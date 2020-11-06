package by.halatsevich.company.model.service;

import by.halatsevich.company.model.entity.CrewDto;
import by.halatsevich.company.model.entity.Status;
import by.halatsevich.company.model.exception.ServiceException;

import java.util.List;

public interface CrewService {
    List<CrewDto> findCrewsByStatus(Status status) throws ServiceException;
}
