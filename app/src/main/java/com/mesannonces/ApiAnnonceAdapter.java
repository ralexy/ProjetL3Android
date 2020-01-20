package com.mesannonces;

import android.util.JsonWriter;
import android.util.Log;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.ToJson;

import java.io.IOException;

public class ApiAnnonceAdapter {

    @FromJson
    Annonce fromJson(JsonReader reader, JsonAdapter<Annonce> delegate) throws IOException {

        Annonce result = null;

        // démarrer le parsing du Json
        reader.beginObject();
        while (reader.hasNext()) {
            // récupérer le nom de la clé
            String name = reader.nextName();
            if (name.equals("success")) {
                boolean success = reader.nextBoolean();
                Log.i("JML", "Success vaut " + success);
                if (!success) {
                    // @todo : récupérer le message d'erreur et le donner à l'exception
                    // @todo : créer une exception spécifique pour la distinguer des IOException
                    throw new IOException("API a répondu FALSE");
                }
            } else if (name.equals("response")) {
                // déléguer l'extraction à l'adapteur qui transforme du Json en Personne
                result = delegate.fromJson(reader);
            } else {
                // dans notre cas on ne devrait pas avoir d'autres clés que success et response dans le Json
                throw new IOException("Response contient des données non conformes");
            }
        }
        // fermer le reader
        reader.endObject();
        return result;
    }

    /*@ToJson
    void ToJson(JsonWriter writer, Class value) {
        try {

        } catch (Exception e) {

        }
    }*/
}