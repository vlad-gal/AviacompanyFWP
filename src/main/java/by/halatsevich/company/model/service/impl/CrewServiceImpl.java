package by.halatsevich.company.model.service.impl;

import by.halatsevich.company.model.dao.CrewDao;
import by.halatsevich.company.model.dao.DaoFactory;
import by.halatsevich.company.model.entity.CrewDto;
import by.halatsevich.company.model.entity.Status;
import by.halatsevich.company.model.exception.DaoException;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.CrewService;

import java.util.List;
import java.util.stream.Collectors;

public class CrewServiceImpl implements CrewService{
    @Override
    public List<CrewDto> findCrewsByStatus(Status status) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        CrewDao dao = factory.getCrewDao();
        List<CrewDto> allCrewDto;
        try {
            allCrewDto = dao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Error while finding crews by status");
        }
        return allCrewDto.stream()
                .filter(crewDto -> crewDto.getStatus() == status)
                .collect(Collectors.toList());
    }
}
