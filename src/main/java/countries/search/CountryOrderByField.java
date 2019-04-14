package countries.search;

public enum CountryOrderByField {
    NAME("countryname"), LANGUAGE("countrylanguage");

    CountryOrderByField(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }
}
