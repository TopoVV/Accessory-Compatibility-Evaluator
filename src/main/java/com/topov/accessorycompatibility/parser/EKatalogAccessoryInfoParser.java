package com.topov.accessorycompatibility.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Service
public class EKatalogAccessoryInfoParser implements AccessoryInfoParser {
    @Async("taskExecutor")
    public CompletableFuture<String> getMotherboardSocket(String motherBoardName) {
        try {
            String path = "https://ek.ua/ek-item.php?resolved_name_=" + motherBoardName + "&view_=tbl";
            return CompletableFuture.completedFuture(parseDom(path));
        } catch (IOException e) {
            e.printStackTrace();
            return CompletableFuture.failedFuture(e);
        }
    }

    @Async("taskExecutor")
    public CompletableFuture<String> getProcessorSocket(String processorName) {
        try {
            String path = "https://ek.ua/" + processorName + ".htm";
            return CompletableFuture.completedFuture(parseDom(path));
        } catch (IOException e) {
            e.printStackTrace();
            return CompletableFuture.failedFuture(e);
        }
    }

    private String parseDom(String path) throws IOException {
        Document document = Jsoup.connect(path).get();
        String socket = document.select("span:contains(Socket)")
                                .first().parent()
                                .nextElementSibling()
                                .select("a").text();
        System.out.println(socket);
        return socket;
    }
}
