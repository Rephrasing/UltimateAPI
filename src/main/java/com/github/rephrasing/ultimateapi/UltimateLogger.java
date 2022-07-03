package com.github.rephrasing.ultimateapi;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class UltimateLogger extends Logger {

    protected UltimateLogger() {
        super("UltimateAPI", null);
        setParent(Logger.getLogger("UltimateAPI"));
        setLevel(Level.ALL);
    }

    @Override
    public void log(LogRecord record) {
        record.setMessage("[UltimateAPI]" + record.getMessage());
        super.log(record);
    }

}
