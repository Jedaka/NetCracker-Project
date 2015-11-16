package com.project.parsing;

import com.project.model.Episode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Date;

/**
 * Created by Максим on 14.11.2015.
 */
public abstract class Parser {

    public static Document getDocument(String URL) {
        try {
            return Jsoup.connect(URL).get();
        } catch (IOException e) {
            return null;
        }
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
    abstract protected  Object[] getEpisodesInfo(String url_appendix) throws NullPointerException;
    abstract protected Integer parseEpisodeNum(Element episode);
    abstract protected Episode parsingEpisode(Element episode) throws NullPointerException;
    abstract public void parsing();
    }
