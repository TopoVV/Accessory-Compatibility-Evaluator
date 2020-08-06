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
public class AccessoryCompatibilityApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccessoryCompatibilityApplication.class, args);
    }

}
