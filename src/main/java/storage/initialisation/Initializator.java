package storage.initialisation;


import countries.domain.BaseCountry;
import countries.service.CountryService;


import java.util.ArrayList;
import java.util.List;


public class Initializator {

    public enum DataSourceType {
        TXT_FILE, XML_FILE, JSON_FILE
    }

    public enum ParserType {
        DOM, SAX, STAX, JAXB
    }

    private CountryService countryService;


    public Initializator(CountryService countryService) {
        this.countryService = countryService;
    }

    public void concurrentInit(DataSourceType type){
        InitThread thread1 = new InitThread(type);
        InitThread thread2 = new InitThread(type);

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        countryService.insert((BaseCountry) thread1.getCountries());
        countryService.insert((BaseCountry) thread2.getCountries());
    }



    private static class InitThread implements Runnable{
        private String filePath;
        private DataSourceType type;
        private List<Object> countries = new ArrayList<Object>();
        private Thread thread;

        public InitThread(String filePath, DataSourceType type) {
            this.filePath = filePath;
            this.type = type;
            thread = new Thread(this);
        }

        public InitThread(DataSourceType type) {
        }

        @Override
        public void run() {
            try {
                countries = Initializator.initCountriesAndCities(filePath, type);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void start(){
            thread.start();
        }

        public void join() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public List<Object> getCountries() {
            return countries;
        }
    }

    public static List<BaseCountry> initCountriesAndCities(String filePath, DataSourceType sourceType) throws Exception {
        TxtReader sourceReader = null;
        ParserType parserType = ParserType.JAXB;
        List<BaseCountry> countries = new ArrayList<>();
        switch (sourceType) {
            case TXT_FILE: {
                sourceReader = new TxtReader();
                break;
            }

            case XML_FILE: {
                switch (parserType){
                    case DOM: {
                        sourceReader = filePath1 -> readData(filePath);
                        break;
                    }
                    case SAX:
                        break;
                    case STAX:
                        break;
                    case JAXB:
                        break;
                }
                break;
            }
            case JSON_FILE:
                break;
        }

        if (sourceReader != null) {
            countries = sourceReader.readDataFromFile(filePath);
        }

        return countries;
    }

}
