package StrategyDesignPattern;


interface Logger {
    public void log(String message);
}


class FileLogger implements Logger {

    @Override
    public void log(String message) {
        System.out.println("[fileLogger] -->" + message);
    }

}

class ConsoleLogger implements Logger {
    @Override
    public void log(String message) {
        System.out.println("[consoleLogger] -->" + message);
    }
}

class DataBaseLogger implements Logger {
    @Override
    public void log(String message) {
        System.out.println("[dataBaseLogger] -->" + message);
    }
}

class LoggerContext {
    private Logger logger;
    public LoggerContext(Logger logger) {
        this.logger = logger;
    }
    public void log(String message) {
        logger.log(message);
    }
}


public class LoggerStrategies {
    public static void main(String[] args) {
        LoggerContext context = new LoggerContext(new FileLogger());
        context.log("Hello World");
    }
}
