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

import java.text.SimpleDateFormat;
import java.util.Date;
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
        String url = "https://api.igdb.com/v4/games/?search=Final%20Fantasy%20XV&fields=name,cover,first_release_date&limit=1";

        // Crea una solicitud GET para la URL
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    try {
                        // OBTENEMOS EL JUEGO COMO JSON
                        JSONObject game = response.getJSONObject(0);

                        //OBTENEMOS EL TITULO
                        String title = game.getString("name");

                        //OBTENEMOS LA FECHA EN FORMATO UNIX Y LA CASTEAMOS A
                        String fechaUnix = game.getString("first_release_date");
                        long fechaUnixLong = Long.parseLong(fechaUnix) * 1000; // convertir a milisegundos
                        Date fecha = new Date(fechaUnixLong);
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        String fechaHumana = format.format(fecha);

                        //OBTENEMOS LA PORTADA
                        int coverId = game.getInt("cover");
                        // Construye la URL de la imagen de la portada
                        String imageUrl = "https://images.igdb.com/igdb/image/upload/t_cover_big/" + coverId + ".jpg";
                        // Carga la imagen utilizando Glide


                        // Actualiza las vistas con los datos obtenidos
                        titulo.setText(title);
                        fecha_lanzamiento.setText(fechaHumana);
                        Glide.with(this).load(imageUrl).into(portada);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                },
                error -> {
                    // Maneja errores de la solicitud
                    error.printStackTrace();

                }) {
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

