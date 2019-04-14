package cities.repo.impl.jdbc;

import cities.domain.City;

import java.sql.ResultSet;

public class CityMapper {

    private CityMapper() {
    }

    public static City mapCity(ResultSet rs) throws Exception {
        try {
            City city = new City();
            city.setId(rs.getLong("ID"));
            city.setName(rs.getString("NAME"));
            city.setPopulation(rs.getInt("POPULATION"));
            city.setCountryId(rs.getLong("COUNTRY_ID"));
            city.setCapital(rs.getBoolean("CAPITAL"));
            return city;
        } catch (Exception e) {
            throw new Exception("City is bad");
        }
    }
}
