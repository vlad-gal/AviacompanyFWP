package by.halatsevich.company.model.factory.creator;

import by.halatsevich.company.model.dao.ColumnName;
import by.halatsevich.company.entity.CrewDto;
import by.halatsevich.company.entity.Status;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public class CrewDtoCreatorTest {
    CrewDtoCreator creator;
    Map<String, Object> crewDtoParameter;

    @BeforeClass
    public void setUp() {
        creator = new CrewDtoCreator();
        crewDtoParameter = new HashMap<>();
        crewDtoParameter.put(ColumnName.CREW_ID, 1);
        crewDtoParameter.put(ColumnName.DISPATCHER_ID, 2);
        crewDtoParameter.put(ColumnName.NUMBER_OF_PILOTS, 2);
        crewDtoParameter.put(ColumnName.NUMBER_OF_NAVIGATORS, 1);
        crewDtoParameter.put(ColumnName.NUMBER_OF_RADIOMAN, 1);
        crewDtoParameter.put(ColumnName.NUMBER_OF_STEWARDESSES, 1);
        crewDtoParameter.put(ColumnName.STATUS_NAME, "active".toUpperCase());
        crewDtoParameter.put(ColumnName.CREW_NAME, "Eagles");
    }

    @AfterClass
    public void tearDown() {
        creator = null;
        crewDtoParameter = null;
    }

    @Test
    public void testCreateCrewDtoSuccess() {
        CrewDto actual = creator.create(crewDtoParameter);
        CrewDto expected = new CrewDto(1,"Eagles",2,2,1,1,1, Status.ACTIVE);
        assertEquals(actual,expected);
    }

    @Test
    public void testCreateCrewDtoFailure() {
        CrewDto actual = creator.create(crewDtoParameter);
        CrewDto expected = new CrewDto("Eagles",2,2,1,1,1, Status.ACTIVE);
        assertNotEquals(actual,expected);
    }
}