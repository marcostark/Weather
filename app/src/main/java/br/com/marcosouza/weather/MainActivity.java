package br.com.marcosouza.weather;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
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

    public static String AppId = "690cac557da1ff012090de70f05d808b";
    public static String lat = "-48.99";
    public static String lon = "-28.48";

    private View mViewInfoWeather;

    private ImageView mImageViewIconForecast;
    private EditText mEditTextCity;
    private TextView mTextViewName;
    private TextView mTextViewHumidity;
    private TextView mTextViewMain;
    private TextView mTextViewTemp;
    private TextView mTextViewTempMax;
    private TextView mTextViewTempMin;
    private TextView mTextViewSunset;
    private TextView mTextViewSunrise;
    private InputMethodManager inputMethodManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewInfoWeather = findViewById(R.id.layout_info_weather);

        mImageViewIconForecast = findViewById(R.id.image_view_icon);

        mEditTextCity = findViewById(R.id.edit_text_city);

        mTextViewTemp = findViewById(R.id.text_view_temp);
        mTextViewTempMin = findViewById(R.id.text_view_temp_min);
        mTextViewTempMax = findViewById(R.id.text_view_temp_max);
        mTextViewHumidity = findViewById(R.id.text_view_humidty);
        mTextViewSunrise = findViewById(R.id.text_view_sunrise);
        mTextViewSunset = findViewById(R.id.text_view_sunset);
        mTextViewMain = findViewById(R.id.text_view_main);
        mTextViewName = findViewById(R.id.text_view_city);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchCity = mEditTextCity.getText().toString();
                if(!searchCity.isEmpty()){
                    getCurrentData(searchCity);
                    hideKeybord(v);
                }
                else{
                    Toast.makeText(MainActivity.this, "Informe a cidade", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    void loadComponents(WeatherResponse weatherResponse){

        mTextViewTemp.setText(String.format("%s °", weatherResponse.getTemp()));
        mTextViewTempMin.setText(String.format("%s °", weatherResponse.getTempMin()));
        mTextViewTempMax.setText(String.format("%s °", weatherResponse.getTempMax()));
        mTextViewHumidity.setText(String.format("%s %%", weatherResponse.getHumidity()));
        mTextViewSunrise.setText(String.valueOf(weatherResponse.getSunrise()));
        mTextViewSunset.setText(String.valueOf(weatherResponse.getSunset()));
        mTextViewMain.setText(weatherResponse.getMain());
        mTextViewName.setText(String.format("%s - %s",weatherResponse.getName(), weatherResponse.getCountry() ));

        Picasso.get().load("http://openweathermap.org/img/w/" + weatherResponse.getIcon() + ".png").into(mImageViewIconForecast);
        mViewInfoWeather.setVisibility(View.VISIBLE);

    }

    void getCurrentData(String city) {
        mViewInfoWeather.setVisibility(View.GONE);

        WeatherService service = WeatherClient.createService(WeatherService.class);
        Call<WeatherResponse> call = service.getCurrentWeatherData(city, AppId, "metric");

        // Solicitação assincrona
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                if (response.code() == 200) {
                    WeatherResponse weatherResponse = response.body();
                    assert weatherResponse != null;
                    loadComponents(weatherResponse);
                } else {
                    Toast.makeText(MainActivity.this, "Cidade não encontrada! Erro: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Não foi possível carregar os dados", Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void hideKeybord(View view) {
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch(Exception ignored) {
        }
    }

}