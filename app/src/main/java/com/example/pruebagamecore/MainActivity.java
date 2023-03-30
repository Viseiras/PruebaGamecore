package com.example.pruebagamecore;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private TextView titulo;
    private TextView fecha_lanzamiento;
    private ImageView portada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa las vistas
        titulo = findViewById(R.id.titulo);
        fecha_lanzamiento = findViewById(R.id.fecha_lanzamiento);
        portada = findViewById(R.id.portada);

        // Crea una nueva cola de solicitudes
        RequestQueue queue = Volley.newRequestQueue(this);

        // Define la URL de la solicitud
        String url = "https://api.igdb.com/v4/games/?search=Final%20Fantasy%20XV&fields=name,release_dates&limit=1";

        // Crea una solicitud GET para la URL
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    try {
                        // Extrae los datos de la respuesta JSON
                        JSONObject game = response.getJSONObject(0);
                        String title = game.getString("name");
                        //String releaseDate = game.getJSONObject("release_dates").getJSONObject("0").getString("human");
                        //String imageUrl = "https://" + game.getJSONObject("cover").getString("url").replace("thumb", "cover_big");

                        // Carga la imagen utilizando Glide
                        //Glide.with(this).load(imageUrl).into(portada);

                        // Aaactualiza las vistas con los datos obtenidos
                        titulo.setText(title);
                        //fecha_lanzamiento.setText(releaseDate);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    // Maneja errores de la solicitud
                    error.printStackTrace();

                }) {
            // Agrega el encabezado "user-key" con tu clave de API
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Client-ID", "83m6yfw5tii03bjg1dtii0ski4tw9y");
                headers.put("Authorization", "Bearer k6kqk0gh55wwr4xsikgj1c44op31ej");
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

// Agrega la solicitud a la cola
        queue.add(jsonArrayRequest);

    }
}

