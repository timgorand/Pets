package exs.Search;


import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;
import retrofit2.http.GET;


import javax.annotation.processing.Generated;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
//Написать программу,которая получает запрос в консоли и возвращает ответ на него. Запрос отправляется на Википедию, откуда
//в виде JSON возвращается файл с данными, достать оттуда ответы

//Вариант, как надо
//НЕ РАБОЧИЙ КОД - просто памятник!!!
interface GitHubService {
    @GET("https://ru.wikipedia.org/w/api.php?action=query&list=search&utf8=&format=json")
    Call<List<Parsing>> listRepos(@Query("srsearch") String s);
}

public class SearchWiki {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String link = "https://ru.wikipedia.org/w/api.php?action=query&list=search&utf8=&format=json&srsearch=";
        String search = URLEncoder.encode( "Пиво"/*reader.readLine()*/, StandardCharsets.UTF_8);;
        link += search;
        URLConnection connection = new URL(link).openConnection();
        connection.connect();
        Gson gson = new Gson();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ru.wikipedia.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GitHubService service = retrofit.create(GitHubService.class);
        service = (GitHubService) gson.fromJson(String.valueOf((new URL(link).openConnection()).getInputStream()),Parsing.class);
        Parsing parsing = gson.fromJson(String.valueOf((new URL(link).openConnection()).getInputStream()),Parsing.class);
        System.out.println(parsing.batchcomplete);
        System.out.println(service.listRepos(search));
        System.out.println("А?");
    }
}

@Generated("org.jsonschema2pojo")
class Parsing{
    @SerializedName("batchcomplete")
    @Expose
    public String batchcomplete;
    @SerializedName("continue")
    @Expose
    public Continue continues;
    @SerializedName("query")
    @Expose
    public Queried queried;


    public String getBatchcomplete() {
        return batchcomplete;
    }

    public void setBatchcomplete(String batchcomplete) {
        this.batchcomplete = batchcomplete;
    }

    public Continue getContinue() {
        return continues;
    }

    public void setContinue(Continue _continue) {
        this.continues = _continue;
    }

    public Query getQuery() {
        return (Query) queried;
    }

    public void setQuery(Query query) {
        queried = (Queried) query;
    }

}

@Generated("org.jsonschema2pojo")
class Continue{
    @SerializedName("sroffset")
    @Expose
    public Integer sroffset;
    @SerializedName("continue")
    @Expose
    public String continue_s;

    public Integer getSroffset() {
        return sroffset;
    }

    public void setSroffset(Integer sroffset) {
        this.sroffset = sroffset;
    }

    public String getContinue() {
        return continue_s;
    }

    public void setContinue(String _continue) {
        continue_s = _continue;
    }

}

@Generated("org.jsonschema2pojo")
class Queried{
    @SerializedName("searchinfo")
    @Expose
public Searchinfo searchinfo;
    @SerializedName("search")
    @Expose
    public List<Search> searched = null;

    public Searchinfo getSearchinfo() {
        return searchinfo;
    }

    public void setSearchinfo(Searchinfo searchinfo) {
        this.searchinfo = searchinfo;
    }

    public List<Search> getSearch() {
        return searched;
    }

    public void setSearch(List<Search> search) {
        searched = search;
    }

}
@Generated("org.jsonschema2pojo")
class Searchinfo{
    @SerializedName("totalhits")
    @Expose
public Integer totalhits;
    public Integer getTotalhits() {
        return totalhits;
    }

    public void setTotalhits(Integer totalhits) {
        this.totalhits = totalhits;
    }
}
@Generated("org.jsonschema2pojo")
class Search{
    @SerializedName("ns")
    @Expose
    public Integer ns;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("pageid")
    @Expose
    public Integer pageid;
    @SerializedName("size")
    @Expose
    public Integer size_s;
    @SerializedName("wordcount")
    @Expose
    public Integer wordcount;
    @SerializedName("snippet")
    @Expose
    public String snippet;
    @SerializedName("timestamp")
    @Expose
    public String timestamp;

    public Integer getNs() {
        return ns;
    }

    public void setNs(Integer ns) {
        this.ns = ns;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPageid() {
        return pageid;
    }

    public void setPageid(Integer pageid) {
        this.pageid = pageid;
    }

    public Integer getSize() {
        return size_s;
    }

    public void setSize(Integer size) {
        size_s = size;
    }

    public Integer getWordcount() {
        return wordcount;
    }

    public void setWordcount(Integer wordcount) {
        this.wordcount = wordcount;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}