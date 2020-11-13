package by.halatsevich.company.model.service;

import by.halatsevich.company.model.entity.Crew;
import by.halatsevich.company.model.entity.CrewDto;
import by.halatsevich.company.model.entity.Status;
import by.halatsevich.company.model.entity.User;
import by.halatsevich.company.model.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * The interface represents crew service.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public interface CrewService {

    /**
     * Add crew.
     *
     * @param dispatcher           the dispatcher
     * @param crewName             the crew name
     * @param numberOfPilots       the number of pilots
     * @param numberOfNavigators   the number of navigators
     * @param numberOfRadioman     the number of radioman
     * @param numberOfStewardesses the number of stewardesses
     * @param status               the status
     * @return true if adding successful, otherwise false
     * @throws ServiceException the service exception
     */
    boolean addCrew(User dispatcher, String crewName, String numberOfPilots, String numberOfNavigators,
                    String numberOfRadioman, String numberOfStewardesses, Status status) throws ServiceException;

    /**
     * Add user into crew.
     *
     * @param user   the user
     * @param crewId the crew id
     * @return true if adding successful, otherwise false
     * @throws ServiceException the service exception
     */
    boolean addUserIntoCrew(User user, String crewId) throws ServiceException;

    /**
     * Count available places in crew.
     *
     * @param crew the crew
     * @param user the user
     * @return the count of available places in crew
     * @throws ServiceException the service exception
     */
    int countAvailablePlacesInCrew(Crew crew, User user) throws ServiceException;

    /**
     * Find crews by status.
     *
     * @param status the status
     * @return the list of crews
     * @throws ServiceException the service exception
     */
    List<Crew> findCrewsByStatus(String status) throws ServiceException;

    /**
     * Find crew by id.
     *
     * @param crewId the crew id
     * @return the crew
     * @throws ServiceException the service exception
     */
    Crew findCrewById(int crewId) throws ServiceException;

    /**
     * Find users crews by status.
     *
     * @param user   the user
     * @param status the status
     * @return the list of crews
     * @throws ServiceException the service exception
     */
    List<Crew> findUserCrewsByStatus(User user, String status) throws ServiceException;

    /**
     * Find crew by name.
     *
     * @param crewName the crew name
     * @return the optional of crew
     * @throws ServiceException the service exception
     */
    Optional<CrewDto> findCrewByName(String crewName) throws ServiceException;

    /**
     * Update crew.
     *
     * @param crew                 the crew
     * @param numberOfPilots       the number of pilots
     * @param numberOfNavigators   the number of navigators
     * @param numberOfRadioman     the number of radioman
     * @param numberOfStewardesses the number of stewardesses
     * @param status               the status
     * @return true if updating successful, otherwise false
     * @throws ServiceException the service exception
     */
    boolean updateCrew(Crew crew, String numberOfPilots, String numberOfNavigators, String numberOfRadioman,
                       String numberOfStewardesses, String status) throws ServiceException;
}
