package andoird.chat.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.net.ParseException;

public class HttpUtils {

	static DefaultHttpClient client = new DefaultHttpClient();
	static String hostname;

	public static void setHostname(String hostname) {
		HttpUtils.hostname = hostname;
	}

	public static String sendHTTPRequest(String url, String parameterName, String parameterValue) {
		//create an HTTP Post request
		HttpPost httpPost = new HttpPost(hostname + url);

		try {
			// Add your parameters to it
			//create a list of parameters pairs (name and value, which is like: "username=kate")
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			//a a pair to the list
			nameValuePairs.add(new BasicNameValuePair(parameterName, parameterValue));
			
			//add the list to the post request but before that encode the list to a proper URL (for example, spaces will be converted to either + or %20)
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			//send the request and wait until the response is returned
			HttpResponse response = client.execute(httpPost);

			//if the response was OK (code 200) then read its content
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				return getResponseBody(entity);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getResponseBody(final HttpEntity entity) throws IOException, ParseException {
		InputStream instream = entity.getContent();
		if (instream == null) {
			return "";
		}

		String charset = HTTP.UTF_8;
		Reader reader = new InputStreamReader(instream, charset);
		StringBuilder buffer = new StringBuilder();
		try {
			char[] tmp = new char[1024];
			int l;
			while ((l = reader.read(tmp)) != -1) {
				buffer.append(tmp, 0, l);
			}
		} finally {
			reader.close();
		}
		return buffer.toString();
	}
}
