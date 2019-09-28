package br.com.marcosouza.weather;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.com.marcosouza.weather.api.WeatherService;
import br.com.marcosouza.weather.api.WeatherClient;
import br.com.marcosouza.weather.model.WeatherResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static String AppId = "2e65127e909e178d0af311a81f39948c";
    public static String lat = "35";
    public static String lon = "139";

    private View mContainer;


    private TextView weatherData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mContainer = findViewById(R.id.activity_main_container);

        weatherData = findViewById(R.id.text_view);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrentData();
            }
        });
    }

    void getCurrentData() {
        //mContainer.setVisibility(View.GONE);

        WeatherService service = WeatherClient.createService(WeatherService.class);
        Call<WeatherResponse> call = service.getCurrentWeatherData(lat, lon, AppId);

        // Solicitação assincrona
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                if (response.code() == 200) {
                    WeatherResponse weatherResponse = response.body();
                    assert weatherResponse != null;

                    String stringBuilder = "Cidade: " +
                            weatherResponse.getCountry() +
                            "\n" +
                            "Temperatura: " +
                            weatherResponse.getTemp() +
                            "\n" +
                            "Min: " +
                            weatherResponse.getTempMin() +
                            "\n" +
                            "Max: " +
                            weatherResponse.getTempMax() +
                            "\n" +
                            "Umidade: " +
                            weatherResponse.getHumidity()+
                            "\n" +
                            "Pôr do Sol " +
                            weatherResponse.getSunset() +
                            "\n" +
                            "Nascer do Sol: " +
                            weatherResponse.getSunrise();

                    weatherData.setText(stringBuilder);
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
                //weatherData.setText(t.getMessage());
                Toast.makeText(MainActivity.this, "Unable to load weather", Toast.LENGTH_SHORT).show();
            }

        });
    }

}