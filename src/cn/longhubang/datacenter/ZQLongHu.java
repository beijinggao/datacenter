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

public class ZQLongHu {
	
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
	public Map<String, List<String[]>> getGeGuData(String stat, String p,
			String ps) {
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
			parameters.put("type", "LHB");
			parameters.put("sty", "YYTJ");
			parameters.put("stat", stat);
			parameters.put("sr", "0");
			parameters.put("st", "1");
			parameters.put("p", p);
			parameters.put("ps", ps);
			parameters
					.put("js",
							"var%20EPqOzkJm={%22data%22:[(x)],%22pages%22:%22(pc)%22,%22update%22:%22(ud)%22}");
			parameters.put("rt", "48053901");

			doc = Jsoup
					.connect(
							"http://datainterface.eastmoney.com/EM_DataCenter/JS.aspx?")
					.userAgent(
							"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36")
					.cookies(cookies).referrer("https://www.baidu.com/")
					.data(parameters).timeout(3000).get();

			String jsondata = URLDecoder.decode(doc.body().text(), "utf-8")
					.split("=")[1];
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

		return dataMap;
	}

	public static void main(String[] args) {
		ZQLongHu gglh = new ZQLongHu();
		gglh.getGeGuData("3", "1", "50");
	}

}
