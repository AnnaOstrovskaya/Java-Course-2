import com.accuweather.WeatherResults;
import com.accuweather.DailyForecast;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;


public class HW6 {

        private static final String BASE_HOST = "dataservice.accuweather.com";
        private static final String FORECAST = "forecasts";
        private static final String API_VERSION = "v1";
        private static final String FORECAST_TYPE = "daily";
        private static final String FORECAST_PERIOD = "5day";

        private static final String CITY_NAME = "Санкт-Петербург";
        private static final String CITY_KEY = "6-295212_1_AL";
        private static final String API_KEY = "mnuOyiAZE0iMbTRZld4AMDuOWrus4czQ";

        public static void main(String[] args) throws IOException {

            OkHttpClient client = new OkHttpClient();

            // Сегментированное построение URL
            HttpUrl url = new HttpUrl.Builder()
                    .scheme("http")
                    .host(BASE_HOST)
                    .addPathSegment(FORECAST)
                    .addPathSegment(API_VERSION)
                    .addPathSegment(FORECAST_TYPE)
                    .addPathSegment(FORECAST_PERIOD)
                    .addPathSegment(CITY_KEY)
                    .addQueryParameter("apikey", API_KEY)
                    .addQueryParameter("language", "ru-ru")
                    .addQueryParameter("metric", "true")
                    .build();

            System.out.println(url.toString());

            // При необходимости указать заголовки
            Request requesthttp = new Request.Builder()
                    .addHeader("accept", "application/json")
                    .url(url)
                    .build();

            String jsonResponse = client.newCall(requesthttp).execute().body().string();

            // https://www.jsonschema2pojo.org/
            ObjectMapper objectMapper = new ObjectMapper();
            WeatherResults weatherResults = objectMapper.readValue(jsonResponse, WeatherResults.class);

            //  В городе CITY на дату DATE ожидается WEATHER_TEXT, температура - TEMPERATURE

            for (DailyForecast dailyForecast: weatherResults.getDailyForecasts()) {
                String temperatureString = dailyForecast.getTemperature().getMinimum().getValue()
                        + " " + dailyForecast.getTemperature().getMinimum().getUnit();
                String outputString = "В городе " + CITY_NAME
                        + " на дату " + dailyForecast.getDate()
                        + " ожидается " + dailyForecast.getDay().getIconPhrase()
                        + ", температура - " + temperatureString;
                System.out.println(outputString);
            }

            client.dispatcher().executorService().shutdown();
            client.connectionPool().evictAll();
            System.exit(0);

        }
    }


