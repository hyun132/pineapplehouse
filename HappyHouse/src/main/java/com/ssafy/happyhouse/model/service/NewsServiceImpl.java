package com.ssafy.happyhouse.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.NewsDto;

@Service
public class NewsServiceImpl implements NewsService {

	@Override
	public Map<String, Object> getNewsList(String cityno) throws Exception {
		int page = 1;
		String headUrl = "https://land.naver.com";
		
		Map<String, Object> map = new HashMap<>();
		List<NewsDto> newslist = new ArrayList<>();

//		for (int j = 1; j < page; j++) {
		String url = headUrl + "/news/region.naver?" + "city_no=" + cityno + "&dvsn_no=&page=" + page;
		Document doc = Jsoup.connect(url).get();
		Elements elements = doc.getElementsByAttributeValue("class", "section_headline"); // 기사들 body

		Element element = elements.get(0);
		Elements totalArticleElements = element.getElementsByTag("li"); // 기사 tag
		Elements contentElements = element.getElementsByTag("dd");

		for (int i = 0; i < totalArticleElements.size(); i++) {
			NewsDto newsDto = new NewsDto();
			
			Element articleElement = totalArticleElements.get(i); // 기사 하나
			Elements aElements = articleElement.select("a"); // a tag
			Element aElement = aElements.get(0); // url & img 속성 추출 위해
			String articleUrl = aElement.attr("href"); // 기사 url
			Element contentElement = contentElements.get(i); // 기사 내용 element
			String content = contentElement.text(); // 기사내용 추출
			String title = null;
			if (articleElement.getElementsByAttributeValue("class", "photo").size() > 0) { // 사진 있는 기사
				Element imgElement = aElement.select("img").get(0);
				String imgUrl = imgElement.attr("src"); // 사진링크
				title = imgElement.attr("alt"); // 기사제목
				newsDto.setImgUrl(imgUrl);
			} else {
				title = articleElement.getElementsByTag("dt").text(); // 사진 없는 기사
			}
			Map<String, String> queryMap = getQueryMap(headUrl + articleUrl);

			newsDto.setContent(content);
			newsDto.setNewsid(queryMap.get("arti_id"));
			newsDto.setTitle(title);
			newsDto.setUrl(headUrl + articleUrl);
			
			newslist.add(newsDto);
		}

//		}
		
		map.put("newslist", newslist);
		return map;

	}

	// URL 파아미터 파싱
	public static Map<String, String> getQueryMap(String query) {
		if (query == null)
			return null;

		int pos1 = query.indexOf("?");
		if (pos1 >= 0) {
			query = query.substring(pos1 + 1);
		}

		String[] params = query.split("&");
		Map<String, String> map = new HashMap<String, String>();
		for (String param : params) {
			String name = param.split("=")[0];
			String value = param.split("=")[1];
			map.put(name, value);
		}
		return map;
	}

}
