package com.gigaspaces;

import com.gigaspaces.async.AsyncFuture;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class MyController {
    private static final Logger logger = Logger.getLogger(MyController.class.getName());
//    private ExecutorService executor = Executors.newFixedThreadPool(32);

    @RequestMapping(value = "/")
    public
    @ResponseBody
    String def() {
        return "Hello There, I'm the rest tool you were waiting for :-)";
    }

    @RequestMapping(value = "/clear")
    public
    @ResponseBody
    String clear() {
        Connector.getGigaSpace().clear(null);
        return "OK" + System.getProperty("line.separator");
    }

    @RequestMapping(value = "/countObject")
    public
    @ResponseBody
    String countObject() {
        String result = "";

        try {
            AsyncFuture<Long> future = Connector.getGigaSpace().execute(new MyDistTask());
            result = "Total objects = " + future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result + System.getProperty("line.separator");
    }

    @ExceptionHandler(OperationFailedException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public
    @ResponseBody
    String
    resolveOperationFailedException(OperationFailedException e) throws IOException {
        if (logger.isLoggable(Level.SEVERE))
            logger.log(Level.SEVERE, "received OperationFailedException exception", e);
        e.printStackTrace();
        return "OperationFailedException: " + e.toString();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public
    @ResponseBody
    String
    resolveException(Exception e) throws IOException {
        if (logger.isLoggable(Level.SEVERE))
            logger.log(Level.SEVERE, "received Exception exception", e);
        e.printStackTrace();
        return "Exception: " + e.toString();
    }

}
