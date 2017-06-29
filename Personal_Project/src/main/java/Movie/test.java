package Movie;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class test {

	public static void main(String[] args) {
		 String clientId = "VqbXBeoHB8msq9LnVCSv";//���ø����̼� Ŭ���̾�Ʈ ���̵�";
	        String clientSecret = "T9qUz7xnJw";//���ø����̼� Ŭ���̾�Ʈ ��ũ����";
	        try {
	            String text = URLEncoder.encode("��Ʈ����", "UTF-8");
	            String apiURL = "https://openapi.naver.com/v1/search/movie.json?query="+ text; // json ���
	            URL url = new URL(apiURL);
	            HttpURLConnection con = (HttpURLConnection)url.openConnection();
	            con.setRequestMethod("GET");
	            con.setRequestProperty("X-Naver-Client-Id", clientId);
	            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
	            int responseCode = con.getResponseCode();
	            BufferedReader br;
	            if(responseCode==200) { // ���� ȣ��
	                br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
	            } else {  // ���� �߻�
	                br = new BufferedReader(new InputStreamReader(con.getErrorStream(),"UTF-8"));
	            }
	            String inputLine;
	            StringBuffer response = new StringBuffer();
	            while ((inputLine = br.readLine()) != null) {
	                response.append(inputLine);
	            }
	            br.close();
	            System.out.println(response.toString());
	            JSONObject jobj = new JSONObject();
	            JSONParser jp = new JSONParser();
	            jobj = (JSONObject)jp.parse(response.toString());
	            long total = (Long)jobj.get("display");
	            JSONArray items = (JSONArray)jobj.get("items");
	            for(int i = 0 ; i< (int)total;i++){
	            	JSONObject movie  = (JSONObject) items.get(i);
	            	System.out.println(movie.get("title"));
	            }
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	}

}
