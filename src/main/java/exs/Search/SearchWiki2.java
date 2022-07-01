package exs.Search;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

//Задача индетичная, но выполнена во время проверки
public class SearchWiki2 {

    public static void main(String[] args) throws Exception {
        String link = "https://ru.wikipedia.org/w/api.php?action=query&list=search&utf8=&format=json&srsearch=";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String search = URLEncoder.encode(reader.readLine(), StandardCharsets.UTF_8);
        link += search;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(link))
//                .headers("Content-Type", "text/plain;charset=UTF-8")
                .build();

        var client = HttpClient
                .newBuilder()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        var json = response.body();

        //System.out.println(json);

        Map<String, Map<String, List<Map<String, Object>>>> responseAsMap = new Gson().fromJson(json, Map.class);
        List<Map<String, Object>> resultAsList = responseAsMap.get("query").get("search");
        for (int j = 0; j < resultAsList.size(); j++) {
            Map<String, Object> map = resultAsList.get(j);
            String title = (String) map.get("title");
            String snippet = (String) map.get("snippet");
            //return  new ResultRecord(title, snippet);

            System.out.println((j + 1) + ") " + title + " => " + snippet);
        }

        //System.out.println(result);
    }

    private record ResultRecord(String title, String snippet) {
    }

}
