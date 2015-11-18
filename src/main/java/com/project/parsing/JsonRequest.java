package com.project.parsing;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Максим on 18.11.2015.
 */
public class JsonRequest {
    public static void send(AddEpisodeRequest addEpisodeRequest) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String ans = mapper.writeValueAsString(addEpisodeRequest);
            String URL = "http://localhost:3000/add/episode";
            URL obj = new URL(URL);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            os.write(ans.getBytes());
            os.flush();
            os.close();
        }catch (Exception e){

        }
    }
}
