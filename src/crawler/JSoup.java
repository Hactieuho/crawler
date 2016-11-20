package crawler;
import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Connection;
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class JSoup {  
 
	public static void main( String[] args ) throws IOException{
		
		//Tao 1 doi tuong conection den trang web cua vcb
		Connection conn = Jsoup.connect("http://www.vietcombank.com.vn/ExchangeRates");
		
		//Tao 1 doi tuong document de phan tich doi tuong connection
		Document doc = conn.get();
		
		//Tao 1 doi tuong element de phan tich nut co id = "ctl00_Content_ExrateView"
		Element ele = doc.getElementById("ctl00_Content_ExrateView");
		//Elements elems = doc.getElementsByAttributeValue("id", "ctl00_Content_ExrateView");
		//Element ele = adsa.first();
		
		//Tao 1 doi tuong elements bao gom tat ca cac the tr
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
      				System.out.println(text);
      			}
      		}
      		catch(IndexOutOfBoundsException e){
      			
      			//Khi khong lay duoc the tr nao nua thi ket thuc ct
      			System.out.println("End");
      			break;
      		}
      		i++;
      	}
	}
}