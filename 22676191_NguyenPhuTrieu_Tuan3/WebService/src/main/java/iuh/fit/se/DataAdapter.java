package iuh.fit.se;

class DataAdapter implements WebService {
    private XMLSystem xmlSystem;

    public DataAdapter(XMLSystem xmlSystem) {
        this.xmlSystem = xmlSystem;
    }

    @Override
    public void request(String jsonData) {
        System.out.println("--- Adapter ---");
        System.out.println("Nhận định dạng JSON: " + jsonData);

        String xmlData = convertJsonToXml(jsonData);

        System.out.println("Đã chuyển đổi sang XML thành công.");

        xmlSystem.receiveXML(xmlData);
    }

    private String convertJsonToXml(String json) {
        return "<data>" + json.replace("{", "").replace("}", "").replace(":", "=") + "</data>";
    }
}
