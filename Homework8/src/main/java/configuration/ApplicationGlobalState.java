package configuration;

public final class ApplicationGlobalState {

    private static ApplicationGlobalState INSTANCE;
    private String selectedCity = null;
    private final String API_KEY = "0d1tNZJPfzzT3qGokM18FGGxAUpt7hpj";
    //private final String API_KEY = "H7XjhVub01TyiCBHV2GE10qVozYWz5NB";
    private final String DB_FILENAME = "application.db";

    private ApplicationGlobalState() {
    }

    public static ApplicationGlobalState getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ApplicationGlobalState();
        }
        return INSTANCE;
    }

    public String getDbFileName() {
        return DB_FILENAME;
    }

    public String getSelectedCity() {
        return selectedCity;
    }

    public void setSelectedCity(String selectedCity) {
        this.selectedCity = selectedCity;
    }

    public String getApiKey() {
        return this.API_KEY;
    }
}
