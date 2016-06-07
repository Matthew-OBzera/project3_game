package edu.noctrl.craig.generic;

import android.util.Log;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by bacraig on 5/25/2016.
 */
public class WebHandler {
    public void sendHighScore( String name, int score, Date datetime) throws IOException {
        URL url = new URL("http://craiginsdev.com/highscore/scores.php");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        Log.i("WebHandler","Send High Score");
        try {
            urlConnection.setDoOutput(true);
            urlConnection.setChunkedStreamingMode(0);
            OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
            writePostData(out, name, score, datetime);
            out.flush();
            out.close();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            //readStream(in);
            in.close();
        }
        finally {
            urlConnection.disconnect();
        }

    }

    private void writePostData(OutputStream out, String name, int score, Date datetime) throws IOException{
        String postString = "";
        postString += "name=" + URLEncoder.encode(name, "UTF-8") + "&";
        postString += "score=" + URLEncoder.encode(score+"", "UTF-8") + "&";
        postString += "game=" + URLEncoder.encode("Test", "UTF-8") + "&";
        postString += "datetime=" + URLEncoder.encode(datetime.toString(), "UTF-8") + "&";
        Log.i("WebHandler","WritePostData: " + postString);
        byte[] postBytes = postString.getBytes();
        out.write(postBytes, 0, postBytes.length);
    }

    public List<Object[]> getHighScores(String game) throws IOException{
        URL url = new URL("http://craiginsdev.com/highscore/scores.php?game="+ URLEncoder.encode(game, "UTF-8"));
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        Log.i("WebHandler","Get High Scores");
        try {

            StringBuilder sb=new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String read;

            while((read=br.readLine()) != null) {
                sb.append(read);
            }

            br.close();
            JSONArray scores = new JSONArray(sb.toString());
            List<Object[]> ret = new ArrayList<>(scores.length());
            for(int i = 0;i< scores.length();i++){
                ret.add(new Object[]{
                        scores.getJSONObject(i).getString("name"),
                        scores.getJSONObject(i).getInt("score"),
                        scores.getJSONObject(i).getString("datetime")
                });
            }
            Collections.sort(ret, new Comparator<Object[]>() {
                @Override
                public int compare(Object[] lhs, Object[] rhs) {
                    int s1 = (int)lhs[1];
                    int s2 = (int)rhs[1];
                    return s2 - s1;
                }
            });
            Log.i("Results",sb.toString());
            return ret;
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally {
            urlConnection.disconnect();
        }
        return null;
    }
}
