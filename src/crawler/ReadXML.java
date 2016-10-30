package crawler;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;

import com.sun.glass.ui.Window.Level;
import com.sun.istack.internal.logging.Logger;
import com.sun.org.apache.xml.internal.serializer.ElemDesc;

public class ReadXML {
	private void start() throws ParserConfigurationException, SAXException, IOException{
		
		//Tạo 1 đối tượng DocumentBuilderFactory
		DocumentBuilderFactory fap = DocumentBuilderFactory.newInstance();
		//Tạo 1 đối tượng DocumentBuilder từ đối tượng DocumentBuilderFactory vừa tạo
		DocumentBuilder build = fap.newDocumentBuilder();
		//Tạo 1 đối tượng Document để lấy toàn bộ mã nguồn trang tỷ giá về và convert ra dạng cây
		Document doc = build.parse("http://www.vietcombank.com.vn/ExchangeRates/ExrateXML.aspx");
		
		//Lấy nút gốc của cây
		Element ele = doc.getDocumentElement();
		
		//Lấy danh sách nút có tên thẻ là "Exrate"
		NodeList Exrate = ele.getElementsByTagName("Exrate");
		//n là số thẻ Exrate
		int n = Exrate.getLength();
		//Khai báo 1 đối tượng Element
		Element element = null;
		//Tạo 1 vòng lặp từ đầu danh sách nút đến cuối danh sách nút
		for(int i = 0; i < n; i++){
			//Gán đối tượng element bằng nút thứ i của Exrate
			element = (Element)Exrate.item(i);
			//In ra màn hình các thuộc tính của nút element có từ khóa
			//"CurrencyCode", "CurrencyName", "Buy", "Transfer", "Sell"
			System.out.println("Currency Code: " + element.getAttribute("CurrencyCode")
							 + "  Currency Name: " + element.getAttribute("CurrencyName")
							 + "\tBuy: " + element.getAttribute("Buy")
							 + "\tTransfer: " + element.getAttribute("Transfer")
							 + "\tSell: " + element.getAttribute("Sell"));
		}
	}
	public static void main(String args[]){
		//Tạo 1 đối tượng ReadXML
		ReadXML a = new ReadXML();
		//Tiến hành craw
		try{
			a.start();
		}
		catch(ParserConfigurationException e){
			e.printStackTrace();
		}
		catch(SAXException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}