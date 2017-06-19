package com.example.user.gezilecekyerler;

import com.example.user.gezilecekyerler.models.Category;
import com.example.user.gezilecekyerler.models.News;
import com.example.user.gezilecekyerler.models.Newses;
import com.example.user.gezilecekyerler.models.NewsesArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class Request {

	static final String KEY_ITEM = "item"; // parent node
	static final String KEY_TITLE = "title";
	static final String KEY_LINK = "link";
	static final String KEY_IMAGE = "content:encoded";
	static final String KEY_DESC = "description";
	static final String KEY_PUBDATE = "pubDate";
	static final String KEY_CONTENT = "content:encoded";
	static final String KEY_MAPS = "content:encoded";

	public static Newses getNews(Category category, Newses newses) {
		News lastNews = null;
		String URL = category.getUrl();

		NewsesArrayList _newses= new NewsesArrayList();
		if (newses.size()>0) {
			lastNews = newses.get(newses.size()-1);
		}
		try {

			URL url=new URL(URL);
			DocumentBuilderFactory dFactory=DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder=dFactory.newDocumentBuilder();

			Document document=dBuilder.parse(new InputSource(url.openStream()));
			document.getDocumentElement().normalize();

			NodeList nodeListCountry=document.getElementsByTagName("item");

			for (int i = 0; i < nodeListCountry.getLength(); i++) {

				Node node=nodeListCountry.item(i);
				Element elementMain=(Element) node;

				News news = new News();

				if (!Validations.isEmpty(getTag(elementMain, KEY_TITLE)))
					news.setTitle(getTag(elementMain, KEY_TITLE));

				if (!Validations.isEmpty(getTag(elementMain, KEY_LINK)))
					news.setLink(getTag(elementMain, KEY_LINK));

				String map = "";
				if (!Validations.isEmpty(getTag(elementMain, KEY_MAPS)))
					news.setLink(getTag(elementMain, KEY_MAPS));
				if (!Validations.isEmpty(map))
					if (map.contains("src=\""))
						map = map.substring(map.indexOf("src=\"") + 5, map.indexOf("\" width=\""));
				news.setMaps(map);


				if (!Validations.isEmpty(getTag(elementMain, KEY_CONTENT)))
					news.setLink(getTag(elementMain, KEY_CONTENT));

				String image="";
				if (!Validations.isEmpty(getTag(elementMain, KEY_IMAGE)))
					news.setImage(getTag(elementMain, KEY_IMAGE));
				if (!Validations.isEmpty(image))
					if (image.contains("src=\""))
						image = image.substring(image.indexOf("src=\"") + 5, image.indexOf("\" alt=\""));
				news.setImage(image);

				String desc = "";
				if (!Validations.isEmpty(getTag(elementMain, KEY_DESC)))
					desc = getTag(elementMain, KEY_DESC);
				if (!Validations.isEmpty(desc))
					if (desc.contains("<p>"))
						desc = desc.substring(desc.indexOf("<p>") + 3, desc.indexOf("</p>"));
				news.setDescription(desc);

				if (!Validations.isEmpty(getTag(elementMain, KEY_PUBDATE)))
					news.setPubDate(getTag(elementMain, KEY_PUBDATE));
				news.setCategoryId(category.getId());
				news.setId(UUID.randomUUID());


				if(lastNews == null || !lastNews.getLink().equals(news.getLink()))
					_newses.add(news);
				else
					break;
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Collections.reverse(_newses);
		for (News news : _newses) {
			newses.add(news);
		}
		return newses;
	}

	private static String getTag(Element elementMain,String tag) {
		try {
			NodeList spotListText=elementMain.getElementsByTagName(tag);
			Element spotText=(Element) spotListText.item(0);
			return spotText.getChildNodes().item(0).getNodeValue();
		} catch (Exception e) {
			return "";
		}
	}
}
