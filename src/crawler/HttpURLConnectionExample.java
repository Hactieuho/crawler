package crawlerconnect;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpURLConnectionExample {

	private final String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) throws Exception {

		HttpURLConnectionExample http = new HttpURLConnectionExample();

		http.sendPost();

	}

	// HTTP GET request
	private void sendGet() throws Exception {

		String url = "http://www.google.com/search?q=mkyong";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}

	// HTTP POST request
	private void sendPost() throws Exception {

		String url = "http://vietcombank.com.vn/ExchangeRates/default.aspx";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("Host","www.vietcombank.com.vn");
		con.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; rv:48.0) Gecko/20100101 Firefox/48.0");
		con.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		con.setRequestProperty("Accept-Language", "vi-VN,vi;q=0.8,en-US;q=0.5,en;q=0.3");
		//con.setRequestProperty("Accept-Encoding","gzip, deflate");
		con.setRequestProperty("Referer","http://www.vietcombank.com.vn/ExchangeRates/default.aspx");
		con.setRequestProperty("Cookie","_ga=GA1.3.1659967290.1473309719; ASP.NET_SessionId=1byhzy554rwh5yzohmtrga45");
		con.setRequestProperty("Connection","keep-alive");
		con.setRequestProperty("Upgrade-Insecure-Requests","1");
		con.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		con.setRequestProperty("Content-Length","1815");

		String urlParameters = 
				  "&__VIEWSTATEGENERATOR=6B3FDDEB"
				+ "&__VIEWSTATEENCRYPTED"
				+ "&ctl00$Content$BranchList=68"
				+ "&ctl00$Content$DateText=19/11/2016"
				+ "&ctl00$Content$ViewButton=Xem";

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		//System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		//print result
		System.out.println(response.toString());

	}

}