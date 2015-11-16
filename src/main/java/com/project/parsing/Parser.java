package com.project.parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

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
}
