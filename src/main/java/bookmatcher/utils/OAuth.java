package bookmatcher.utils;

import com.google.api.client.auth.oauth.OAuthAuthorizeTemporaryTokenUrl;
import com.google.api.client.auth.oauth.OAuthCredentialsResponse;
import com.google.api.client.auth.oauth.OAuthGetAccessToken;
import com.google.api.client.auth.oauth.OAuthGetTemporaryToken;
import com.google.api.client.auth.oauth.OAuthHmacSigner;
import com.google.api.client.auth.oauth.OAuthParameters;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.io.IOException;

public class OAuth{

    public static final String BASE_GOODREADS_URL = "https://www.goodreads.com";
    public static final String TOKEN_SERVER_URL = BASE_GOODREADS_URL + "/oauth/request_token";
    public static final String AUTHENTICATE_URL = BASE_GOODREADS_URL + "/oauth/authorize";
    public static final String ACCESS_TOKEN_URL = BASE_GOODREADS_URL + "/oauth/access_token";

    public static final String GOODREADS_KEY = "VDcCS8ueY8eL9xrHSvOgew";
    public static final String GOODREADS_SECRET = "lmqjW7v6z8u2rYZjEQCvxodwagiT8mlS24dh4E3qI";

    public String getID(String token, String tokenSecret){
        try{
            OAuthHmacSigner signer = new OAuthHmacSigner();
            signer.clientSharedSecret = GOODREADS_SECRET;

            if (token.equals("") && tokenSecret.equals("")){
                // Get Temporary Token
                OAuthGetTemporaryToken getTemporaryToken = new OAuthGetTemporaryToken(TOKEN_SERVER_URL);  
                getTemporaryToken.signer = signer;
                getTemporaryToken.consumerKey = GOODREADS_KEY;
                getTemporaryToken.transport = new NetHttpTransport();
                OAuthCredentialsResponse temporaryTokenResponse = getTemporaryToken.execute();
                System.out.println(temporaryTokenResponse.toString());

                // Build Authenticate URL
                OAuthAuthorizeTemporaryTokenUrl accessTempToken = new OAuthAuthorizeTemporaryTokenUrl(AUTHENTICATE_URL);
                accessTempToken.temporaryToken = temporaryTokenResponse.token;
                String authUrl = accessTempToken.build();

                // Redirect to Authenticate URL in order to get Verifier Code
                System.out.println("Goodreads oAuth sample: Please visit the following URL to authorize:");
                System.out.println(authUrl);
                System.out.println("Waiting 10s to allow time for visiting auth URL and authorizing...");
                Thread.sleep(20000);

                System.out.println("Waiting time complete - assuming access granted and attempting to get access token");
                // Get Access Token using Temporary token and Verifier Code
                OAuthGetAccessToken getAccessToken = new OAuthGetAccessToken(ACCESS_TOKEN_URL);
                getAccessToken.signer = signer;
                // NOTE: This is the main difference from the StackOverflow example
                signer.tokenSharedSecret = temporaryTokenResponse.tokenSecret;
                getAccessToken.temporaryToken = temporaryTokenResponse.token;
                getAccessToken.transport = new NetHttpTransport();
                getAccessToken.consumerKey = GOODREADS_KEY;
                OAuthCredentialsResponse accessTokenResponse = getAccessToken.execute();

                //This is the final oauth token?
                token = accessTokenResponse.token;
                tokenSecret = accessTokenResponse.tokenSecret;
            }

        System.out.println("Token paremeters generated:");
        System.out.println("Consumer Key: " + GOODREADS_KEY);
        System.out.println("Consumer Secret " + GOODREADS_SECRET);
        System.out.println("Access Token: " +token);
        System.out.println("Access Token Secret: " + tokenSecret);

        // Build OAuthParameters in order to use them while accessing the resource
        OAuthParameters oauthParameters = new OAuthParameters();
        signer.tokenSharedSecret = tokenSecret;
        oauthParameters.signer = signer;
        oauthParameters.consumerKey = GOODREADS_KEY;
        oauthParameters.token = token;

        // Use OAuthParameters to access the desired Resource URL
        HttpRequestFactory requestFactory = new ApacheHttpTransport().createRequestFactory(oauthParameters);
        GenericUrl genericUrl = new GenericUrl("https://www.goodreads.com/api/auth_user");
        HttpResponse resp = requestFactory.buildGetRequest(genericUrl).execute();
        return resp.parseAsString();
        } catch(Exception e) {
            e.printStackTrace();
        }
		return null;
    }
}