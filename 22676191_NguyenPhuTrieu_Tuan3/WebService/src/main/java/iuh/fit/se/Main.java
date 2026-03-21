package iuh.fit.se;

public class Main {
    public static void main(String[] args) {
        XMLSystem oldSystem = new XMLSystem();
        
        WebService adapter = new DataAdapter(oldSystem);

        String inputJson = "{\"user\":\"Gemini\", \"status\":\"active\"}";

        System.out.println("Client bắt đầu gửi yêu cầu...");
        adapter.request(inputJson);
    }
}