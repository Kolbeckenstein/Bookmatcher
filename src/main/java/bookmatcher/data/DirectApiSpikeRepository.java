package bookmatcher.data;

import bookmatcher.utils.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class DirectApiSpikeRepository implements UserRepository{
    public String getUsername(){
        HttpCaller caller = new HttpCaller();
        return caller.get("https://www.goodreads.com/search/index.xml?key=VDcCS8ueY8eL9xrHSvOgew&q=Ender%27s+Game");
    }

    public String getUserJSONBlob(){
        return "";
    }
}