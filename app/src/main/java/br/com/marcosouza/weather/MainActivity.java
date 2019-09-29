package br.com.marcosouza.weather;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.Locale;

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

    private View mViewInfoWeather;

    private ImageView mImageViewIconForecast;
    private TextView mTextViewHumidity;
    private TextView mTextViewTemp;
    private TextView mTextViewTempMax;
    private TextView mTextViewTempMin;
    private TextView mTextViewSunset;
    private TextView mTextViewSunrise;
    private TextView mTextViewCountry;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewInfoWeather = findViewById(R.id.layout_info_weather);

        mImageViewIconForecast = findViewById(R.id.image_view_icon);
        mTextViewTemp = findViewById(R.id.text_view_temp);
        mTextViewTempMin = findViewById(R.id.text_view_temp_min);
        mTextViewTempMax = findViewById(R.id.text_view_temp_max);
        mTextViewHumidity = findViewById(R.id.text_view_humidty);
        mTextViewSunrise = findViewById(R.id.text_view_sunrise);
        mTextViewSunset = findViewById(R.id.text_view_sunset);
        mTextViewCountry = findViewById(R.id.text_view_city);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrentData();
            }
        });
    }

    void loadComponents(WeatherResponse weatherResponse){
        mTextViewTemp.setText(String.format(Locale.getDefault(),String.valueOf(weatherResponse.getTemp())));
        mTextViewTempMin.setText(String.valueOf(weatherResponse.getTempMin()));
        mTextViewTempMax.setText(String.valueOf(weatherResponse.getTempMax()));
        mTextViewHumidity.setText(String.valueOf(weatherResponse.getHumidity()));
        mTextViewSunrise.setText(String.valueOf(weatherResponse.getSunrise()));
        mTextViewSunset.setText(String.valueOf(weatherResponse.getSunset()));
        mTextViewCountry.setText(String.valueOf(weatherResponse.getCountry()));

        Picasso.get().load("http://openweathermap.org/img/w/" + weatherResponse.getIcon() + ".png").into(mImageViewIconForecast);
        mViewInfoWeather.setVisibility(View.VISIBLE);

    }

    void getCurrentData() {
        mViewInfoWeather.setVisibility(View.GONE);

        WeatherService service = WeatherClient.createService(WeatherService.class);
        Call<WeatherResponse> call = service.getCurrentWeatherData(lat, lon, AppId);

        // Solicitação assincrona
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                if (response.code() == 200) {
                    WeatherResponse weatherResponse = response.body();
                    assert weatherResponse != null;
                    loadComponents(weatherResponse);
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Unable to load weather", Toast.LENGTH_SHORT).show();
            }

        });
    }

}