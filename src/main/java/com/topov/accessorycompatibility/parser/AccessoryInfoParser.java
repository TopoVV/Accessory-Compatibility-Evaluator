package com.topov.accessorycompatibility.parser;

import org.springframework.scheduling.annotation.Async;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public interface AccessoryInfoParser {
    CompletableFuture<String> getMotherboardSocket(String motherBoardName);;
    CompletableFuture<String> getProcessorSocket(String processorName);
}
