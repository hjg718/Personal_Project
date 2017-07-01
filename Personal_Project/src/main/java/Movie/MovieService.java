package Movie;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
	
	public  ArrayList<MovieVO> search(String keyword){
		 String clientId = "VqbXBeoHB8msq9LnVCSv";//애플리케이션 클라이언트 아이디값";
	        String clientSecret = "T9qUz7xnJw";//애플리케이션 클라이언트 시크릿값";
	        try {
	            String text = URLEncoder.encode(keyword, "UTF-8");
	            String apiURL = "https://openapi.naver.com/v1/search/movie.json?query="+ text; // json 결과
	            URL url = new URL(apiURL);
	            HttpURLConnection con = (HttpURLConnection)url.openConnection();
	            con.setRequestMethod("GET");
	            con.setRequestProperty("X-Naver-Client-Id", clientId);
	            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
	            int responseCode = con.getResponseCode();
	            BufferedReader br;
	            if(responseCode==200) { // 정상 호출
	                br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
	            } else {  // 에러 발생
	                br = new BufferedReader(new InputStreamReader(con.getErrorStream(),"UTF-8"));
	            }
	            String inputLine;
	            StringBuffer response = new StringBuffer();
	            while ((inputLine = br.readLine()) != null) {
	                response.append(inputLine);
	            }
	            br.close();
	            JSONObject jobj = new JSONObject();
	            JSONParser jp = new JSONParser();
	            jobj = (JSONObject)jp.parse(response.toString());
	            long total = (Long)jobj.get("display");
	            JSONArray items = (JSONArray)jobj.get("items");
	            ArrayList<MovieVO> arr = new ArrayList<MovieVO>();
	            for(int i = 0 ; i< (int)total;i++){
	            	JSONObject movie  = (JSONObject) items.get(i);
	            	MovieVO vo = new MovieVO();
	            	vo.setActor((String)movie.get("actor"));
	            	vo.setDirector((String)movie.get("director"));
	            	vo.setImage((String)movie.get("image"));
	            	vo.setLink((String)movie.get("link"));
	            	vo.setPubDate((String)movie.get("pubDate"));
	            	vo.setSubtitle((String)movie.get("subtitle"));
	            	vo.setTitle((String)movie.get("title"));
	            	vo.setUserRating((String)movie.get("userRating"));
	            	arr.add(vo);
	            }
	            return arr;
	        } catch (Exception e) {
	            System.out.println(e);
	            return null;
	        }
	}

}
