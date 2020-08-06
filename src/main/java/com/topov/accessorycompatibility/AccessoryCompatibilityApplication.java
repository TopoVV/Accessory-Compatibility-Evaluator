package com.topov.accessorycompatibility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.FutureAdapter;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.stream.Stream;

@SpringBootApplication
public class AccessoryCompatibilityApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(AccessoryCompatibilityApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Document document = Jsoup.connect("https://ek.ua/AMD-3600-BOX.htm").get();
        Elements op1 = document.select("td.op1");
        Elements op3 = document.select("td.op3");

        op3.removeIf(element -> element.parent().children().size() < 2 && element.text().equals(""));
        op1.removeIf(element -> element.parent().children().size() < 2);

        for(int i = 0; i < op1.size() - 1; i++) {
            Element paramHTML = op1.get(i).getElementsByTag("span").first();
            String paramName = paramHTML.text();

            StringBuilder paramValue = new StringBuilder();
            Element valueHTML = op3.get(i);
            if(valueHTML.text().equals("")) {
                paramValue.append("true");
            } else {
                paramValue.append(valueHTML.text());
            }
            System.out.println(paramName + "   =   " + paramValue.toString());
        }
    }
}
