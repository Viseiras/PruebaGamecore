package com.example.pruebagamecore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static void main(String[] args)
    {
        OkHttpClient client = new OkHttpClient();

        String url = "https://api.rawg.io/api/games/3498?key=[fb8206eba55f4d3694c9bd5c8dc7391f]";
        Request request = new Request.Builder()
                .url(url)
                .build();


        try
        {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                // do something with the response body
            } else {
                // handle error
            }
        }

        catch (IOException e) {
            // handle exception
        }
    }



    /*
    RequestQueue queue = Volley.newRequestQueue(this);
    String url = "https://api.rawg.io/api/games/3498?key=[TU_API_KEY]";

    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
            (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    // Analiza la respuesta JSON y extrae los datos que necesitas
                    String title = response.getString("name");
                    String imageUrl = response.getJSONObject("background_image").getString("original");

                    // Usa Glide para cargar la imagen en una ImageView
                    ImageView imageView = findViewById(R.id.imageView);
                    Glide.with(MainActivity.this).load(imageUrl).into(imageView);

                    // Actualiza la vista con el título del juego
                    TextView textView = findViewById(R.id.textView);
                    textView.setText(title);
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    // Maneja errores de la solicitud
                }
            });

queue.add(jsonObjectRequest);
*/
}