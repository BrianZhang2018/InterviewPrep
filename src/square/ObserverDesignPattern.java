package square;

import java.util.*;

/**
 * https://refactoringguru.cn/design-patterns/observer/java/example#example-0--editor-Editor-java
 *
 * Created by brianzhang on 3/21/21.
 */
public class ObserverDesignPattern {
    public static void main(String[] args) {
        CustomerNotificationService service = new CustomerNotificationService();
        service.events.subscribe("mail", new MailingListener("1750 Building, Mclean, VA 20164"));
        service.events.subscribe("email", new EmailNotificationListener("jeff@example.com"));
        try {
            service.sendNewProductInfo("Adidas New A1 soccer cleats");
            service.sendByEmail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class CustomerNotificationService {
    public EventManager events;
    private String productName;

    public CustomerNotificationService() {
        this.events = new EventManager("mail", "email");
    }

    public void sendNewProductInfo(String productName) {
        this.productName = productName;
        events.notify("mail", this.productName);
    }

    public void sendByEmail() throws Exception {
        if (this.productName != null) {
            events.notify("email", productName);
        } else {
            throw new Exception("Please open a productName first.");
        }
    }
}

// Publisher
class EventManager {
    Map<String, List<EventListener>> listeners = new HashMap<>();

    public EventManager(String... operations) {
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    public void subscribe(String eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.add(listener);
    }

    public void unsubscribe(String eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.remove(listener);
    }

    public void notify(String eventType, String info) {
        List<EventListener> users = listeners.get(eventType);
        for (EventListener listener : users) {
            listener.update(eventType, info);
        }
    }
}

// listeners
interface EventListener {
    void update(String eventType, String info);
}

// listener-1: 收到通知后, 发送邮件
class EmailNotificationListener implements EventListener {
    private String email;

    public EmailNotificationListener(String email) {
        this.email = email;
    }

    @Override
    public void update(String eventType, String info) {
        System.out.println("Email to " + email + ": Someone has performed " + eventType + " operation with the following productName: " + info);
    }
}

//  listener-2: 收到通知后, Mail to customer address
class MailingListener implements EventListener {
    private String mailingAddress;

    public MailingListener(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    @Override
    public void update(String eventType, String info) {
        System.out.println("Mail to mailingAddress " + mailingAddress + ": Someone has performed " + eventType);
    }
}