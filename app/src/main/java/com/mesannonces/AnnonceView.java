package com.mesannonces;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

public class AnnonceView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonce_view);

        this.makeApiCall("https://ensweb.users.info.unicaen.fr/android-api/mock-api/completeAd.json");
    }

    public void apiCall(View view) {
        //makeApiCall("https://ensweb.users.info.unicaen.fr/android-api/mock-api/completeAd.json");
    }

    public void parseResponse(String response) {
        Log.i("JML", response);
        Snackbar.make( findViewById(android.R.id.content), "On parse la réponse", Snackbar.LENGTH_LONG).show();

        // créer Moshi et lui ajouter l'adapteur ApiPersonneAdapter
        Moshi moshi = new Moshi.Builder().add(new ApiAnnonceAdapter()).build();
        // créer l'adapteur pour Personne
        JsonAdapter<Annonce> jsonAdapter = moshi.adapter(Annonce.class);

        try {
            // response est la String qui contient le JSON de la réponse
            Annonce annonce = jsonAdapter.fromJson(response);
            Log.i("JML", "Désérialisation de la réponse");
            Log.i("JML", annonce.toString());
            String message2 = annonce.getTitre() + " à " + annonce.getPrix() + " publié par " + annonce.getPseudo();
            Snackbar.make(findViewById(android.R.id.content), message2, Snackbar.LENGTH_LONG).show();
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
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
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
