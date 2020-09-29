package by.halatsevich.company.entity;

public class Airport extends Entity {
    private String country;
    private String city;
    private String airportName;

    public Airport() {
    }

    public Airport(long id, String country, String city, String airportName) {
        super(id);
        this.country = country;
        this.city = city;
        this.airportName = airportName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Airport airport = (Airport) o;

        if (!country.equals(airport.country)) return false;
        if (!city.equals(airport.city)) return false;
        return airportName.equals(airport.airportName);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + country.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + airportName.hashCode();
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
