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
public final class ParserLostFilm extends Parser{

    public static final Studio STUDIO = new Studio("Lostfilm", "ru");
    public static final String URL = "http://www.lostfilm.tv";
    public static final String URL_SERIALS = "http://www.lostfilm.tv/serials.php";

    public static void main(String[] args) {
        new ParserLostFilm().parsing();

    }

    /**
     * @returns The method returns nothing. It's do parsing.
     */

    public void parsing() {
        Iterator<Element> serialsIterator = getIterator();
        if (serialsIterator == null) {
            return;
        }
        while (serialsIterator.hasNext()) {
            try {
                Element serial = serialsIterator.next();
                Object[] parsingRes = getEpisodesInfo(serial.attr("href"));
                Token token = (Token) parsingRes[0];
                Serial serial1 = (Serial) parsingRes[1];
                HashSet<Episode> episodes = (HashSet<Episode>) parsingRes[2];
            } catch (NullPointerException e) {
                continue;
            }


        }
        System.out.println("Готовченко!");

    }

    /**
     * @return All available serials as Iterator<Element>
     */
    private Iterator<Element> getIterator() {
        Document doc = getDocument(URL_SERIALS);
        Elements elements = doc.getElementsByClass("mid").get(0).getElementsByClass("bb_a");
        Iterator<Element> iterator = elements.iterator();
        return iterator;
    }

    /*
     * @returns all episode
     */
    protected EpisodeTMP parsingEpisode(Element episode) throws NullPointerException {
        EpisodeTMP parsedEpisode = new EpisodeTMP();
        Integer episodeNum = parseEpisodeNum(episode);
        if (episodeNum == null) {
            return null;
        }
        Elements dateAndSeasonAnsEpisode = episode.select("span:not(style)")
                .not("span[class=micro]")
                .not("span[style=color:#4b4b4b]")
                .not("span[style=line-height:11px;display:block]");
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

    protected Integer parseEpisodeNum(Element episode) {
        Elements element = episode.getElementsByClass("t_episode_num").select("label");
        if (!element.isEmpty()) return null;
        try {
            return Integer.parseInt(episode.getElementsByClass("t_episode_num").text());
        } catch (NumberFormatException e) {
            return Integer.parseInt(episode.getElementsByClass("t_episode_num").text().split("[- ]")[0]);
        }
    }

    /**
     * URL?????????????????????????????
     *
     * @param url_appendix is appendix to serial url
     * @return Object[], where Object[0] is token, Object[1] - serial, Object[2] - set of episodes
     */
   protected Object[] getEpisodesInfo(String url_appendix) throws NullPointerException {
        Document doc = getDocument(URL + url_appendix);
        Serial serial = new Serial();
        EpisodeTMP episodeTMP = new EpisodeTMP();
        String nameSerial = doc.getElementsByTag("title").text();
        serial.setTitle(nameSerial);
        serial.setLanguage("ru");
        Token token = new Token(serial, STUDIO);
        Elements series_Element = doc.getElementsByClass("t_row");
        Set<EpisodeTMP> episodes = new HashSet<EpisodeTMP>();
        for (int i = 0; i < series_Element.size(); i++) {
            episodeTMP = parsingEpisode(series_Element.get(i));
            if (episodeTMP == null) continue;
            episodes.add(episodeTMP);
            AddEpisodeRequest addEpisodeRequest = new AddEpisodeRequest();
            addEpisodeRequest.setEpisode(episodeTMP);
            addEpisodeRequest.setToken(token.toString());
            JsonRequest.send(addEpisodeRequest);
        }
        Object[] ans = {token, serial, episodes};
        return ans;

    }
}
