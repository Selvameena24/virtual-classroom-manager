// Subject Interface
interface Document {
    void display();
}
// RealSubject class
class RealDocument implements Document {
    private String content;

    public RealDocument(String content) {
        this.content = content;
        loadFromDatabase();
    }

    private void loadFromDatabase() {
        System.out.println("Loading document from database...");
    }

    @Override
    public void display() {
        System.out.println("Displaying document content: " + content);
    }
}
// Proxy class
class DocumentProxy implements Document {
    private RealDocument realDocument;
    private String content;
    private String userRole;

    public DocumentProxy(String content, String userRole) {
        this.content = content;
        this.userRole = userRole;
    }

    @Override
    public void display() {
        if (userRole.equals("Admin") || userRole.equals("Manager")) {
            if (realDocument == null) {
                realDocument = new RealDocument(content);
            }
            realDocument.display();
        } else {
            System.out.println("Access denied. You do not have permission to view this document.");
        }
    }
}
// Client code
public class ProxyPatternExample {
    public static void main(String[] args) {
        Document document1 = new DocumentProxy("Sensitive Content", "Admin");
        Document document2 = new DocumentProxy("Sensitive Content", "Employee");

        // Admin can view the document
        document1.display();

        // Employee cannot view the document
        document2.display();
    }
}

