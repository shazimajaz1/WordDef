package com.practice.projects.api_setup;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class NetworkUtility {
    /*
        Class Constants
     */
    private static final String LOG_TAG = "Result Of the String";
    private static final String QUERY_PARAM = "";
    private static final String MAX_RESULTS = "maxResults";
    private static final String API_KEY = "f8d10a1d-be40-45a8-acca-2f432bb387ac";


    /*
        This method gets the information on the word searches.
        The return is a value of type String in the form of string builder.
        The return used in this case is a String builder
     */
    public static String getWordResults(String queryString){

        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String resultJSONString = null;

        //Since the network operations are performed, to avoid failure,
        //the checks are being implemented below
       // https://dictionaryapi.com/api/v3/references/thesaurus/json/test?key=f8d10a1d-be40-45a8-acca-2f432bb387ac

        try{


            //Build URL from String
            URL url = new URL("https://www.dictionaryapi.com/api/v3/references/thesaurus/json/"+ queryString + "?key=" + API_KEY );


            //Open the connection and set GET as the request method
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            String method = connection.getRequestMethod();
            connection.connect();

            //At this point, the connection should be open. Get the response from the server
            //using an input stream
            InputStream inputStream = connection.getInputStream();

            //Read the input stream using a buffered reader
            reader = new BufferedReader(new InputStreamReader(inputStream));

            //Hold the response of the stream into a string builder
            StringBuilder stringBuilder = new StringBuilder();

            //Use a while loop to fill the string builder with the data from
            //the input stream coming in the form of JSON from the server
            String line;
            while ((line = reader.readLine())!= null){
                stringBuilder.append(line);
                //stringBuilder.append("\n");
            }

            //Get rid of the result: that is at the start

            //Check to see if there was a response or not.
            if(stringBuilder.length() == 0){
                return null;
            }

            //At this point, we can be sure that there was a response.
            resultJSONString = stringBuilder.toString();

        }catch(Exception e){

            e.printStackTrace();
        } finally{
            //Close the connection in this phase
            if(connection != null){
                connection.disconnect();
            }

            //Also close the reader along with the connection
            if(reader != null){
                try{
                    reader.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        //Print the value to the log to check if the server is working.
        Log.d(LOG_TAG, resultJSONString);

        //Fix it later
        return resultJSONString;

    }


}
