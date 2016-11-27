package crawler;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Iterator;

import org.jsoup.Connection;
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class FinalProgram1Ngay {
	static String day = "22/11/2015";
	public static String post() throws IOException{
		
		//URL cua website can lay ma nguon
		String url = "http://vietcombank.com.vn/ExchangeRates/default.aspx";
		
		//Tao 1 doi tuong URL va ket noi den website can lay ma nguon
		URL obj = new URL(url);
		
		//Tao 1 doi tuong HttpURLConnection mo ket noi den website
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		//Phan header cua goi tin
		con.setRequestMethod("POST");
		con.setRequestProperty("Host","www.vietcombank.com.vn");
		con.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; rv:48.0) Gecko/20100101 Firefox/48.0");
		con.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		con.setRequestProperty("Accept-Language", "vi-VN,vi;q=0.8,en-US;q=0.5,en;q=0.3");
		//Doan code nay bi loi encode:
		//con.setRequestProperty("Accept-Encoding","gzip, deflate");
		con.setRequestProperty("Referer","http://www.vietcombank.com.vn/ExchangeRates/default.aspx");
		con.setRequestProperty("Cookie","_ga=GA1.3.1659967290.1473309719; ASP.NET_SessionId=1byhzy554rwh5yzohmtrga45");
		con.setRequestProperty("Connection","keep-alive");
		con.setRequestProperty("Upgrade-Insecure-Requests","1");
		con.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		con.setRequestProperty("Content-Length","1815");

		//Tham so cua goi tin ket hop voi ngay thang nam da tinh toan o tren
		String thamso = 
				  "&__VIEWSTATEGENERATOR=6B3FDDEB"
				+ "&__VIEWSTATEENCRYPTED"
				+ "&ctl00$Content$BranchList=68"
				+ "&ctl00$Content$DateText=" + day
				+ "&ctl00$Content$ViewButton=Xem";

		//Dat gia tri SetDoOutput la true de co the su dung ket noi URL cho dau ra
		con.setDoOutput(true);
		//Tao 1 doi tuong DataOutputStream de lay ma nguon tu dau ra cua doi tuong HttpURLConnection
		DataOutputStream out = new DataOutputStream(con.getOutputStream());
		//Chen them Parametter vao goi tin gui di
		out.writeBytes(thamso);
		//Xa toan bo du lieu duoc luu trong DataOutputStream
		out.flush();
		//Dong DataOutputStream
		out.close();

		//Thong bao ma loi cua server (neu co)
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
		//Dua ma nguon vao trong Buffer
		BufferedReader in = new BufferedReader(
	       new InputStreamReader(con.getInputStream()));
		String inputLine;
		String end = "";
		//Doc tung dong ma nguon trong Buffer va noi vao trong bien end
		while ((inputLine = in.readLine()) != null) {
			end = end.concat(inputLine);
		}
				
		//Dong Buffer
		in.close();
		return end;		
	}
	public static void in() throws Exception{
		
		//Tao 1 doi tuong Document phan tich 1 String tra ve tu ham post()
		Document doc = Jsoup.parse(post());
		//In ra ngay lay ty gia
		System.out.println("Ngay lay ty gia: "
						  + doc.getElementsByAttributeValue("name", "ctl00$Content$DateText").first().attr("value"));
		//Tao 1 doi tuong element de phan tich nut co id = "ctl00_Content_ExrateView"
		Element ele = doc.getElementById("ctl00_Content_ExrateView");
		
		//Tao 1 doi tuong Elements bao gom tat ca cac the tr
      	Elements trs = ele.select(">tbody>tr");
      	int i = 1;
      	//Lay ra tung the tr trong doi tuong elements
      	Element tr = trs.get(i);
      	while(true){
      		System.out.println(i);
      		try{
      			
      			//Lay ra tung the <tr>
      			tr = trs.get(i);
      			for(int j = 0; j < 5; j++){
      				
      				//Lay ra tung the <td> trong the <tr> dang xet
      				Element td = tr.select("td").get(j);
      				
      				//In text cua the <td> ra man hinh
      				String text = td.text();
      				System.out.print(text + "\t");
      			}
      			System.out.println();
      		}
      		catch(IndexOutOfBoundsException e){
      			
      			//Khi khong lay duoc the tr nao nua thi ket thuc ct
      			System.out.println("End");
      			break;
      		}
      		i++;
      	}
	}
	public static void main( String[] args ) throws Exception{
		in();
	}
}