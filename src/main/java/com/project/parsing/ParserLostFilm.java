package com.project.parsing;

import com.project.model.Episode;
import com.project.model.Serial;
import com.project.model.Studio;
import com.project.model.Token;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Максим on 14.11.2015.
 */
abstract public class ParserLostFilm extends Parser {

    public static final Studio STUDIO = new Studio("Lostfilm", "ru");
    public static final String URL = "http://www.lostfilm.tv";
    public static final String URL_SERIALS = "http://www.lostfilm.tv/serials.php";

    /**
     * @returns The method returns nothing. It's do parsing.
     */
    public static void parsing() {
        Iterator<Element> serials_iterator = getIterator();
        if (serials_iterator == null) {
            return;
        }
        while (serials_iterator.hasNext()) {
            try {
                Element serial = serials_iterator.next();
                Object[] parsingRes = getEpisodesInfo(serial.attr("href"));
                Token token = (Token) parsingRes[0];
                Serial serial1 = (Serial) parsingRes[1];
                HashSet<Episode> episodes = (HashSet<Episode>) parsingRes[2];
            } catch (NullPointerException e) {
                continue;
            }


        }
    }

    /**
     * @return All available serials as Iterator<Element>
     */
    private static Iterator<Element> getIterator() {
        Document doc = getDocument(URL_SERIALS);
        Elements elements = doc.getElementsByClass("mid").get(0).getElementsByClass("bb_a");
        Iterator<Element> iterator = elements.iterator();
        return iterator;
    }

    /*
     * @returns all episode
     */
    private static Episode parsingEpisode(Element episode) throws NullPointerException {
        Episode parsedEpisode = new Episode();
        Integer episodeNum = parseEpisodeNum(episode);
        if (episodeNum == null) {
            return null;
        }
        Elements dateAndSeasonAnsEpisode = episode.select("span:not(style)")
                .not("span[class=micro]")
                .not("span[style=color:#4b4b4b]").not("span[style=line-height:11px;display:block]");
        Date date = convert(dateAndSeasonAnsEpisode.get(0).text());
        String[] strings = dateAndSeasonAnsEpisode.get(1).text().toString().split(" |-");
        int season;
        int episode_number;
        try {
            season = Integer.parseInt(strings[0]);
            episode_number = Integer.parseInt(strings[2]);
        } catch (NumberFormatException ex) {
            return null;
        }
        parsedEpisode.setDate(date);
        parsedEpisode.setEpisodeNumber(episode_number);
        parsedEpisode.setSeasonNumber(season);
        return parsedEpisode;
    }

    private static Integer parseEpisodeNum(Element episode) {
        Elements element = episode.getElementsByClass("t_episode_num").select("label");
        if (!element.isEmpty()) return null;
        try {
            return Integer.parseInt(episode.getElementsByClass("t_episode_num").text());
        } catch (NumberFormatException e) {
            return Integer.parseInt(episode.getElementsByClass("t_episode_num").text().split("[- ]")[0]);
        }
    }

    /**
     * URL??????????????????????????????
     *
     * @param url_appendix is appendix to serial url
     * @return Object[], where Object[0] is token, Object[1] - serial, Object[2] - set of episodes
     */
    private static Object[] getEpisodesInfo(String url_appendix) throws NullPointerException {
        Document doc = getDocument(URL + url_appendix);
        Serial serial = new Serial();
        Episode episode;
        try {
            episode = new Episode();
        } catch (NullPointerException e) {
            return null;
        }
        String nameSerial = doc.getElementsByTag("title").text();
        serial.setTitle(nameSerial);
        serial.setLanguage("ru");
        Token token = new Token(serial, STUDIO);
        Elements series_Element = doc.getElementsByClass("t_row");
        Set<Episode> episodes = new HashSet<Episode>();
        for (int i = 0; i < series_Element.size(); i++) {
            episode = parsingEpisode(series_Element.get(i));
            episode.setSerial(serial);
            episode.setStudio(STUDIO);
            episodes.add(episode);
            System.out.println(episode.toString());
        }
        // System.out.println(episodes.size());
        Object[] ans = {token, serial, episodes};
        return ans;

    }

    @SuppressWarnings("unchecked")
    /**
     * @param date is a date represented as dd.mm.yyyy hh24:mm
     * @return Date format
     */
    public static Date convert(String date) {
        String[] strings = date.split("[.| |:|]");
        int day = Integer.parseInt(strings[0]);
        int month = Integer.parseInt(strings[1]);
        int year = Integer.parseInt(strings[2]);
        int hours = Integer.parseInt(strings[3]);
        int minutes = Integer.parseInt(strings[4]);
        return new Date(year, month, day, hours, minutes);

    }

    public static void main(String[] args) {
        ParserLostFilm.parsing();
    }
}
