package by.halatsevich.company.model.service;

import by.halatsevich.company.model.entity.Crew;
import by.halatsevich.company.model.entity.CrewDto;
import by.halatsevich.company.model.entity.Status;
import by.halatsevich.company.model.entity.User;
import by.halatsevich.company.model.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface CrewService {

    List<Crew> findCrewsByStatus(String status) throws ServiceException;

    Crew findCrewById(int crewId) throws ServiceException;

    boolean addCrew(User dispatcher, String crewName, String numberOfPilots, String numberOfNavigators, String numberOfRadioman, String numberOfStewardesses, Status status) throws ServiceException;

    Optional<CrewDto> findCrewByName(String crewName) throws ServiceException;

    boolean addUserIntoCrew(User user, String crewId) throws ServiceException;

    int countAvailablePlacesInCrew(Crew crew, User user) throws ServiceException;

    boolean updateCrew(Crew crew, String numberOfPilots, String numberOfNavigators, String numberOfRadioman, String numberOfStewardesses, String status) throws ServiceException;

    List<Crew> findUsersCrewsByStatus(User user, String status) throws ServiceException;
}
