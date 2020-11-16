package by.halatsevich.company.entity;

/**
 * The class represents airport entity.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class Airport extends Entity {
    private String country;
    private String city;
    private String airportName;

    /**
     * Instantiates a new Airport.
     */
    public Airport() {
    }

    /**
     * Instantiates a new Airport.
     *
     * @param id          the id
     * @param country     the country
     * @param city        the city
     * @param airportName the airport name
     */
    public Airport(int id, String country, String city, String airportName) {
        super(id);
        this.country = country;
        this.city = city;
        this.airportName = airportName;
    }

    /**
     * Instantiates a new Airport.
     *
     * @param country     the country
     * @param city        the city
     * @param airportName the airport name
     */
    public Airport(String country, String city, String airportName) {
        this.country = country;
        this.city = city;
        this.airportName = airportName;
    }

    /**
     * Gets country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets country.
     *
     * @param country the country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets airport name.
     *
     * @return the airport name
     */
    public String getAirportName() {
        return airportName;
    }

    /**
     * Sets airport name.
     *
     * @param airportName the airport name
     */
    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Airport airport = (Airport) o;

        if (country != null ? !country.equals(airport.country) : airport.country != null) return false;
        if (city != null ? !city.equals(airport.city) : airport.city != null) return false;
        return airportName != null ? airportName.equals(airport.airportName) : airport.airportName == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (airportName != null ? airportName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Airport{");
        sb.append("country='").append(country).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", airportName='").append(airportName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
