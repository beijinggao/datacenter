package cn.longhubang.datacenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DayLongHu {

	public Map<String, List<String[]>> getDayData() {
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
			doc = Jsoup
					.connect("http://data.eastmoney.com/soft/stock/lhb.html")
					.userAgent(
							"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36")
					.cookies(cookies).referrer("https://www.baidu.com/")
					.timeout(3000).get();

			Elements els = doc.select("thead.h101");

			List<String[]> titlelist = new ArrayList<String[]>();
			for (Element el : els) {
				titlelist.add(el.text().split(" "));
			}

			List<String[]> shlist = new ArrayList<String[]>();

			Elements shels = doc.select("tr.sh");
			for (int i = 0; i < shels.size(); i++) {
				Element el = shels.get(i);
				Elements aels = el.select("a.red");
				if (aels.isEmpty()) {
					String[] old = shlist.get(i - 1);
					String[] newArr = Arrays.copyOf(old, old.length + 1);
					newArr[old.length] = el.text();
					shlist.add(newArr);
				}
				for (Element ael : aels) {
					String[] fields = (el.text() + " "
							+ "http://data.eastmoney.com" + ael.attr("href"))
							.split(" ");
					shlist.add(fields);
				}
			}

			List<String[]> szlist = new ArrayList<String[]>();

			Elements szels = doc.select("tr.sz");
			for (int i = 0; i < szels.size(); i++) {
				Element el = szels.get(i);
				Elements aels = el.select("a.red");
				if (aels.isEmpty()) {
					String[] old = szlist.get(i - 1);
					String[] newArr = Arrays.copyOf(old, old.length + 1);
					newArr[old.length] = el.text();
					szlist.add(newArr);
				}
				for (Element ael : aels) {
					String[] fields = (el.text() + " "
							+ "http://data.eastmoney.com" + ael.attr("href"))
							.split(" ");
					szlist.add(fields);
				}
			}
			dataMap.put("title", titlelist);
			dataMap.put("shdata", shlist);
			dataMap.put("szdata", szlist);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dataMap;
	}

	public static void main(String[] args) {
		DayLongHu dlh = new DayLongHu();
		dlh.getDayData();
	}

}
