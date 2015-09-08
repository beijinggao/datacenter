package cn.longhubang.datacenter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GeGuMingXi {
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
	public Map<String, List<String[]>> getGeGuMingxiData(String code,
			String date) {
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
			parameters.put("code", code);
			parameters.put("date", date);

			doc = Jsoup
					.connect(
							"http://data.eastmoney.com/soft/stock/StockDetail.aspx?")
					.userAgent(
							"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36")
					.cookies(cookies).referrer("http://data.eastmoney.com/soft/stock/StockDetail.aspx?code=600016")
					.data(parameters).timeout(3000).get();

			Elements els = doc.select("ul#datelist >li >a");
			
			for (Element el : els) {
				System.out.println(el.text()+"\t"+el.attr("href"));
			}
			
			Elements oldels = doc.select("ul.dateul >li >a");
			
			for (Element el : oldels) {
				
				System.out.println(el.text()+"\t"+el.attr("href"));
			}
			
			Elements tips = doc.select("div.divtips");
			for (Element el : tips) {
				System.out.println(el.text());
			}
			
			Elements thead = doc.select("div#cont1 >table.tab2 >thead.h101");
			for (Element el : thead) {
				System.out.println(el.text());
			}
			
			Elements tab2 = doc.select("div#cont1 >table.tab2 >tbody");
			for (Element el : tab2) {
				System.out.println(el.text());
			}
			
			//最关注该股的证券营业部
			
			//一个月
			Elements toptentrader1_thead = doc.select("div#toptentrader-cont1 >table>thead");
			for (Element el : toptentrader1_thead) {
				System.out.println(el.text());
			}
			Elements toptentrader1_tbody = doc.select("div#toptentrader-cont1 >table>tbody>tr");
			for (Element el : toptentrader1_tbody) {
				for(Element ael:el.select("td >a")){
					if (ael!=null){
						System.out.println(el.text()+"\t"+ael.attr("href"));
					}
				}
			}
			//三个月
			Elements toptentrader2_thead = doc.select("div#toptentrader-cont2 >table>thead");
			for (Element el : toptentrader2_thead) {
				System.out.println(el.text());
			}
			Elements toptentrader2_tbody = doc.select("div#toptentrader-cont2 >table>tbody>tr");
			for (Element el : toptentrader2_tbody) {
				for(Element ael:el.select("td >a")){
					if (ael!=null){
						System.out.println(el.text()+"\t"+ael.attr("href"));
					}
				}
			}
			//六个月
			Elements toptentrader3_thead = doc.select("div#toptentrader-cont3 >table>thead");
			for (Element el : toptentrader3_thead) {
				System.out.println(el.text());
			}
			Elements toptentrader3_tbody = doc.select("div#toptentrader-cont3 >table>tbody>tr");
			for (Element el : toptentrader3_tbody) {
				for(Element ael:el.select("td >a")){
					if (ael!=null){
						System.out.println(el.text()+"\t"+ael.attr("href"));
					}
				}
			}
			
			//一年
			Elements toptentrader4_thead = doc.select("div#toptentrader-cont4 >table>thead");
			for (Element el : toptentrader4_thead) {
				System.out.println(el.text());
			}
			Elements toptentrader4_tbody = doc.select("div#toptentrader-cont4 >table>tbody>tr");
			for (Element el : toptentrader4_tbody) {
				for(Element ael:el.select("td >a")){
					if (ael!=null){
						System.out.println(el.text()+"\t"+ael.attr("href"));
					}
				}
			}
			
			//机构席位买卖统计
			Elements toptentrader_thead = doc.select("div#cont2 > table >thead");
			for (Element el : toptentrader_thead) {
				System.out.println(el.text());
			}
			Elements toptentrader_tr = doc.select("div#cont2 > table >tbody>tr");
			for (Element el : toptentrader_tr) {
				System.out.println(el.text());
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataMap;
	}

	public static void main(String[] args) {
		GeGuMingXi gglh = new GeGuMingXi();
		gglh.getGeGuMingxiData("601766", "2015-06-11");
	}

}
