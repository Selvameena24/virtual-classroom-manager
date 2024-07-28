// Handler interface
abstract class SupportHandler {
    protected SupportHandler nextHandler;

    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleRequest(String request);
}
// Concrete Handlers
class LowLevelSupport extends SupportHandler {
    @Override
    public void handleRequest(String request) {
        if (request.equals("low")) {
            System.out.println("LowLevelSupport handling request: " + request);
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}
class MidLevelSupport extends SupportHandler {
    @Override
    public void handleRequest(String request) {
        if (request.equals("medium")) {
            System.out.println("MidLevelSupport handling request: " + request);
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}

class HighLevelSupport extends SupportHandler {
    @Override
    public void handleRequest(String request) {
        if (request.equals("high")) {
            System.out.println("HighLevelSupport handling request: " + request);
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}
// Usage
public class ChainOfResponsibilityExample {
    public static void main(String[] args) {
        SupportHandler lowLevel = new LowLevelSupport();
        SupportHandler midLevel = new MidLevelSupport();
        SupportHandler highLevel = new HighLevelSupport();

        lowLevel.setNextHandler(midLevel);
        midLevel.setNextHandler(highLevel);

        lowLevel.handleRequest("low");
        lowLevel.handleRequest("medium");
        lowLevel.handleRequest("high");
    }
}
