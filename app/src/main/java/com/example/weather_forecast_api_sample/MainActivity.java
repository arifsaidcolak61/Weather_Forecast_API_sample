package com.example.weather_forecast_api_sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn_cityID,btn_getWeatherByID, btn_getWeatherByName;
    EditText et_dataInput;
    ListView lv_weatherReports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //values that assigned from activity_xml file
        btn_cityID = findViewById(R.id.btn_get_city_id);
        btn_getWeatherByID = findViewById(R.id.btn_getWeatherByCityID);
        btn_getWeatherByName = findViewById(R.id.btn_getWeatherByCityName);
        et_dataInput = findViewById(R.id.et_dataInput);
        lv_weatherReports = findViewById(R.id.lv_weatherReports);

        //click listener for each button

       final WeatherDataService weatherDataService = new WeatherDataService(MainActivity.this);

        btn_cityID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            weatherDataService.getCityID(et_dataInput.getText().toString(), new WeatherDataService.VolleyResponseListener() {
                @Override
                public void onError(String message) {
                    Toast.makeText(MainActivity.this, "somth wrong ", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(String cityID) {
                    Toast.makeText(MainActivity.this, "Returned ID of "+ cityID, Toast.LENGTH_SHORT).show();
                }

            });


            }
        });

        btn_getWeatherByID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            weatherDataService.getCityForecastByID(et_dataInput.getText().toString(), new WeatherDataService.ForeCastByIDRepsonse() {
                @Override
                public void onError(String message) {
                    Toast.makeText(MainActivity.this, "wrrrong", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onResponse(List<WeatherReportModel> weatherReportModels) {
                    //put list

                    ArrayAdapter arrayAdapter =new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, weatherReportModels);
                    lv_weatherReports.setAdapter(arrayAdapter);
                }
            });


            }
        });

        btn_getWeatherByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weatherDataService.getCityForecastByName(et_dataInput.getText().toString(), new WeatherDataService.GetCityForecastByNameCallBack(){
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "wrrrong", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(List<WeatherReportModel> weatherReportModels) {
                        //put list

                        ArrayAdapter arrayAdapter =new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, weatherReportModels);
                        lv_weatherReports.setAdapter(arrayAdapter);
                    }
                });
            }
        });


    }

}