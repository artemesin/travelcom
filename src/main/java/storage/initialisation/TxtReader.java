package storage.initialisation;

import cities.domain.City;
import common.solutions.utils.Months;
import countries.domain.BaseCountry;
import countries.domain.ColdCountry;
import countries.domain.HotCountry;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TxtReader implements SourceReader<List<BaseCountry>> {
    private List<City> cities;

    @Override
    public List<BaseCountry> readDataFromFile(String filePath) throws Exception {
        List<BaseCountry> countries = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)))) {
            String s;
            while ((s = reader.readLine()) != null) {
                BaseCountry country = null;
                cities = new ArrayList<>();
                if ("country".equals(s)) {
                    s = reader.readLine();
                    country = parseCountryFromString(s);
                }

                String cityString;
                while ((cityString = reader.readLine()) != null && !"end country".equals(cityString)) {
                    parseCityFromString(cityString);
                }

                if (country != null) {
                    country.setCities(cities);
                    countries.add(country);
                }
            }
        }
        return countries;
    }


    private BaseCountry parseCountryFromString(String s){
        String[] countryFields = s.split(" ");
        int attrIndex = -1;
        if ("hot".equals(countryFields[++attrIndex])) {
            return new HotCountry(countryFields[++attrIndex], countryFields[++attrIndex], countryFields[++attrIndex], Months.valueOf(countryFields[++attrIndex]), Integer.parseInt(countryFields[++attrIndex]));
        } else if ("cold".equals(countryFields[attrIndex])) {
            return new ColdCountry(countryFields[++attrIndex], countryFields[++attrIndex], countryFields[++attrIndex], Months.valueOf(countryFields[++attrIndex]), Integer.parseInt(countryFields[++attrIndex]), "polarnight".equals(countryFields[++attrIndex]));
        }
        return null;
    }

    private void parseCityFromString(String s){
        int attrIndex = -1;
        City city = new City(s.split(" ")[++attrIndex], Integer.parseInt(s.split(" ")[++attrIndex].trim()), "capital".compareToIgnoreCase(s.split(" ")[++attrIndex]) == 0);
        cities.add(city);
    }
}
