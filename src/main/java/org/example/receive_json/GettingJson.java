package org.example.receive_json;

import org.json.JSONObject;

//Класс для создания и передачи нового объекта JSONObject c параметром URL
public class GettingJson {
    public static JSONObject receiveJson (String url) {
        return new JSONObject(UrlContent.getUrlContent(url));
    }
}
