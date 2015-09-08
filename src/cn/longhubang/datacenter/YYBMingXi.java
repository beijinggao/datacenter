package cn.longhubang.datacenter;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class YYBMingXi {
	
	/**
	 * 
	 * @param stat
	 *            月份 只有 1(一个月)、 3(三个月)、 6(六个月)、 12(一年)
	 * @param p
	 *            第几页
	 * @param ps
	 *            每页显示条数
	 * @return
	 */
	public Map<String, List<String[]>> getYingYeBuData(String id,String page,String ps) {
		Map<String, List<String[]>> dataMap = new HashMap<String, List<String[]>>();
		Document doc = null;
		try {
			Map<String, String> cookies = new HashMap<String, String>();
			cookies.put("emstat_bc_emcount", "2213893627220419694");
			cookies.put("em_hq_fls", "new");
			cookies.put(
					"HAList",
					"f-0-000001-%u4E0A%u8BC1%u6307%u6570%2Ca-sh-601169-%u5317%u4EAC%u94F6%u884C%2Cf-0-399001-%u6DF1%u8BC1%u6210%u6307%2Ca-sh-600555-%u4E5D%u9F99%u5C71%2Ca-sz-300059-%u4E1C%u65B9%u8D22%u5BCC");
			cookies.put("emstat_ss_emcount", "5_1441195211_1810412189");
			cookies.put("pgv_pvi", "4512741828");

			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("id", id);
			doc = Jsoup
					.connect(
							"http://data.eastmoney.com/soft/stock/TradeSearchHistory.aspx?id=80095368")
					.userAgent(
							"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36")
					.cookies(cookies).referrer("http://data.eastmoney.com/soft/stock/StockDetail.aspx?code=600016")
					.data(parameters).timeout(3000).get();

			Elements els = doc.select("div#datatitle >div#tit");
			
			for (Element el : els) {
				System.out.println(el.text());
			}
			
			Elements yybtitle = doc.select("table#dt_1> thead.h101 >tr");
			
			for (Element el : yybtitle) {
				System.out.println(el.text());
			}
			
			Map<String, String> parameters2 = new HashMap<String, String>();
			parameters2.put("type", "LHB");
			parameters2.put("sty", "YYHSIU");
			parameters2.put("code", id);
			parameters2.put("p", page);
			parameters2.put("ps", ps);
			parameters2.put("js", "var%20VRttXPSa={%22data%22:[(x)],%22pages%22:%22(pc)%22,%22update%22:%22(ud)%22}");
			parameters2.put("rt", "48056892");
			
			Map<String, String> parameters3 = new HashMap<String, String>();
			parameters3.put("type", "LHB");
			parameters3.put("sty", "ZLYYMM");
			parameters3.put("stat", "3");
			parameters3.put("code", id);
			parameters3.put("sr", "0");
			parameters3.put("st", "2");
			parameters3.put("p", page);
			parameters3.put("ps", ps);
			parameters3.put("js", "var%20ksIxxlPR={%22data%22:[(x)],%22pages%22:%22(pc)%22,%22update%22:%22(ud)%22}");
			parameters3.put("rt", "48056919");
			
			connect(parameters2,cookies);
			connect(parameters3,cookies);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataMap;
	}
	
	public void connect(Map<String, String> parameters,Map<String, String> cookies){
		Document doc = null;
		try {
			doc = Jsoup
					.connect(
							"http://datainterface.eastmoney.com/EM_DataCenter/JS.aspx?")
					.userAgent(
							"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36")
					.cookies(cookies).referrer("http://data.eastmoney.com/soft/stock/StockDetail.aspx?code=600016")
					.data(parameters).timeout(3000).get();
			String jsondata = URLDecoder.decode(doc.body().text().replaceAll("%", "%25"), "utf-8")
					.split("=")[1].replaceAll("%22", "\"");
			System.out.println(jsondata);

			JSONParser parser = new JSONParser();
			Object obj=parser.parse(jsondata);
			JSONObject jobj = (JSONObject)obj;
			
			JSONArray jsonarr=(JSONArray)jobj.get("data");
			
			System.out.println(jsonarr.get(0));
			
			System.out.println(jobj.get("data"));
			System.out.println(jobj.get("pages"));
			System.out.println(jobj.get("update"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

	public static void main(String[] args) {
		YYBMingXi gglh = new YYBMingXi();
		gglh.getYingYeBuData("80095368","1","50");
	}

}
