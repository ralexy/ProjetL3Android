package com.mesannonces;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.squareup.moshi.Moshi;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.Buffer;

public class AnnonceView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonce_view);
    }
    public void apiCall(View view) {
        makeApiCall("");
    }

    public void parseResponse(String response) {
        Log.i("JML", response);
        Snackbar.make( findViewById(R.id.main), "On parse la réponse", Snackbar.LENGTH_LONG).show();

        // créer Moshi et lui ajouter l'adapteur ApiPersonneAdapter
        Moshi moshi = new Moshi.Builder().add(new ApiPersonneAdapter()).build();
        // créer l'adapteur pour Personne
        JsonAdapter<Annonce> jsonAdapter = moshi.adapter(Annonce.class);

        try {
            // response est la String qui contient le JSON de la réponse
            Personne personne = jsonAdapter.fromJson(response);
            Log.i("JML", "Désérialisation de la réponse");
            Log.i("JML", personne.toString());
            String message2 = personne.getFirstName() + " " + personne.getLastName() + " possède " + personne.getPhones().size() + " email(s).";
            Snackbar.make(findViewById(R.id.main), message2, Snackbar.LENGTH_LONG).show();
        } catch (IOException e) {
            Log.i("JML", "Erreur I/O");
        }
    }


    private void makeApiCall(String url) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected HTTP code " + response);
                    }

                    // ATTENTION responseBody.string() ferme le Stream => string() ne peut être appelé qu'une seule fois
                    final String body = responseBody.string();
                    Log.i("JML", body);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            parseResponse(body);
                        }
                    });
                }
            }
        });

    }
}
