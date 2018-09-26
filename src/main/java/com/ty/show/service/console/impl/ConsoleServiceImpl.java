package com.ty.show.service.console.impl;

import ch.qos.logback.core.Appender;
import com.ty.show.service.console.ConsoleService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
@Slf4j
public class ConsoleServiceImpl implements ConsoleService {

    @Async
    @Override
    public String getLogMessage() throws IOException {
        String message = "1";
        return message;
    }
}
