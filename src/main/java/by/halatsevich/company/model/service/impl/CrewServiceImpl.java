package by.halatsevich.company.model.service.impl;

import by.halatsevich.company.model.dao.CrewDao;
import by.halatsevich.company.model.dao.DaoFactory;
import by.halatsevich.company.model.dao.UserDao;
import by.halatsevich.company.model.entity.Crew;
import by.halatsevich.company.model.entity.CrewDto;
import by.halatsevich.company.model.entity.Status;
import by.halatsevich.company.model.entity.User;
import by.halatsevich.company.model.exception.DaoException;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.CrewService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The class represents crew service implementation.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class CrewServiceImpl implements CrewService {

    @Override
    public boolean addCrew(User dispatcher, String crewName, String numberOfPilots, String numberOfNavigators,
                           String numberOfRadioman, String numberOfStewardesses, Status status) throws ServiceException {
        int parsedNumberOfPilots = Integer.parseInt(numberOfPilots);
        int parsedNumberOfNavigators = Integer.parseInt(numberOfNavigators);
        int parsedNumberOfRadioman = Integer.parseInt(numberOfRadioman);
        int parsedNumberOfStewardesses = Integer.parseInt(numberOfStewardesses);
        CrewDto crewDto = new CrewDto(crewName, dispatcher.getId(), parsedNumberOfPilots, parsedNumberOfNavigators,
                parsedNumberOfRadioman, parsedNumberOfStewardesses, status);
        DaoFactory factory = DaoFactory.getInstance();
        CrewDao dao = factory.getCrewDao();
        boolean isAdded;
        try {
            isAdded = dao.addCrew(crewDto);
        } catch (DaoException e) {
            throw new ServiceException("Error while adding crew", e);
        }
        return isAdded;
    }

    @Override
    public boolean addUserIntoCrew(User user, String crewId) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        CrewDao crewDao = factory.getCrewDao();
        UserDao userDao = factory.getUserDao();
        boolean isAdded = false;
        try {
            int id = Integer.parseInt(crewId);
            Optional<CrewDto> optionalCrew = crewDao.findById(id);
            if (optionalCrew.isPresent() && crewDao.addUserIntoCrew(id, user.getId())) {
                user.setStatus(Status.BUSY);
                isAdded = userDao.update(user);
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while adding user into crew", e);
        }
        return isAdded;
    }

    @Override
    public List<Crew> findCrewsByStatus(String status) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        CrewDao crewDao = factory.getCrewDao();
        List<Crew> allCrews = new ArrayList<>();
        try {
            List<CrewDto> allCrewDto = crewDao.findAllByStatus(Status.valueOf(status.toUpperCase()));
            for (CrewDto crewDto : allCrewDto) {
                allCrews.add(createCrew(crewDto, factory, crewDao));
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while finding crews by status", e);
        }
        return allCrews;
    }

    @Override
    public Crew findCrewById(int crewId) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        CrewDao dao = factory.getCrewDao();
        try {
            Optional<CrewDto> crewDto = dao.findById(crewId);
            if (crewDto.isPresent()) {
                return createCrew(crewDto.get(), factory, dao);
            } else {
                throw new ServiceException("Error while finding crew by id, crew not found");
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while finding crew by id", e);
        }
    }

    @Override
    public Optional<CrewDto> findByCrewName(String crewName) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        CrewDao dao = factory.getCrewDao();
        try {
            return dao.findByCrewName(crewName);
        } catch (DaoException e) {
            throw new ServiceException("Error while finding crew by name", e);
        }
    }

    @Override
    public List<Crew> findUserCrewsByStatus(User user, String status) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        CrewDao crewDao = factory.getCrewDao();
        List<Crew> allUserCrews = new ArrayList<>();
        try {
            List<CrewDto> crewDtos = crewDao.findUserCrewsByStatus(user.getId(), Status.valueOf(status.toUpperCase()));
            for (CrewDto crewDto : crewDtos) {
                allUserCrews.add(createCrew(crewDto, factory, crewDao));
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while finding user crews by status");
        }
        return allUserCrews;
    }

    @Override
    public int countAvailablePlacesInCrew(Crew crew, User user) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        CrewDao crewDao = factory.getCrewDao();
        int maxPlaces = 0;
        int occupiedPlaces;
        switch (user.getRole()) {
            case PILOT:
                maxPlaces = crew.getNumberOfPilots();
                break;
            case NAVIGATOR:
                maxPlaces = crew.getNumberOfNavigators();
                break;
            case RADIOMAN:
                maxPlaces = crew.getNumberOfRadioman();
                break;
            case STEWARDESS:
                maxPlaces = crew.getNumberOfStewardesses();
                break;
        }
        List<User.Role> roles;
        try {
            roles = crewDao.findUserRolesByCrewId(crew.getId())
                    .stream().filter(role -> user.getRole() == role).collect(Collectors.toList());
            occupiedPlaces = roles.size();
        } catch (DaoException e) {
            throw new ServiceException("Error while count available places in crew", e);
        }
        return maxPlaces - occupiedPlaces;
    }

    @Override
    public boolean updateCrew(Crew crew, String numberOfPilots, String numberOfNavigators, String numberOfRadioman,
                              String numberOfStewardesses, String status) throws ServiceException {
        int parsedNumberOfPilots = Integer.parseInt(numberOfPilots);
        int parsedNumberOfNavigators = Integer.parseInt(numberOfNavigators);
        int parsedNumberOfRadioman = Integer.parseInt(numberOfRadioman);
        int parsedNumberOfStewardesses = Integer.parseInt(numberOfStewardesses);
        Status parsedStatus = Status.valueOf(status.toUpperCase());
        CrewDto crewDto = new CrewDto(crew.getId(), crew.getCrewName(), crew.getDispatcher().getId(), parsedNumberOfPilots,
                parsedNumberOfNavigators, parsedNumberOfRadioman, parsedNumberOfStewardesses, parsedStatus);
        DaoFactory factory = DaoFactory.getInstance();
        CrewDao crewDao = factory.getCrewDao();
        UserDao userDao = factory.getUserDao();
        boolean isUpdated;
        try {
            List<User> staff = crew.getStaff();
            if (parsedStatus == Status.ACTIVE || parsedStatus == Status.BUSY) {
                for (User user : staff) {
                    user.setStatus(Status.BUSY);
                    userDao.update(user);
                }
            } else if (parsedStatus == Status.FLY) {
                for (User user : staff) {
                    user.setStatus(Status.FLY);
                    userDao.update(user);
                }
            } else {
                for (User user : staff) {
                    user.setStatus(Status.ACTIVE);
                    userDao.update(user);
                }
            }
            isUpdated = crewDao.update(crewDto);
        } catch (DaoException e) {
            throw new ServiceException("Error while updating crew", e);
        }
        return isUpdated;
    }

    private Crew createCrew(CrewDto crewDto, DaoFactory factory, CrewDao crewDao) throws DaoException {
        UserDao userDao = factory.getUserDao();
        User dispatcher = userDao.findById(crewDto.getDispatcherId()).orElse(null);
        List<Integer> usersIdByCrewId = crewDao.findUserIdsByCrewId(crewDto.getId());
        List<User> staff = new ArrayList<>();
        for (int userId : usersIdByCrewId) {
            staff.add(userDao.findById(userId).orElse(null));
        }
        int id = crewDto.getId();
        int numberOfPilots = crewDto.getNumberOfPilots();
        int numberOfNavigators = crewDto.getNumberOfNavigators();
        int numberOfRadioman = crewDto.getNumberOfRadioman();
        int numberOfStewardesses = crewDto.getNumberOfStewardesses();
        Status crewStatus = crewDto.getStatus();
        String crewName = crewDto.getCrewName();
        return new Crew(id, dispatcher, crewName, staff, numberOfPilots, numberOfNavigators, numberOfRadioman,
                numberOfStewardesses, crewStatus);
    }
}
