package com.ssafy.trip.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class AuthSocialService {
	 public String getKakaoAccessToken (String authorize_code) {
	        String access_Token = "";
	        String refresh_Token = "";
	        String reqURL = "https://kauth.kakao.com/oauth/token";
	        
	        try {
	            URL url = new URL(reqURL);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            

	            conn.setRequestMethod("POST");
	            conn.setDoOutput(true);
	            

	            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
	            StringBuilder sb = new StringBuilder();
	            sb.append("grant_type=authorization_code");
	            sb.append("&client_id=4b566a63a487519e52bcd20aec5f9326");
	            sb.append("&redirect_uri=http://i3b207.p.ssafy.io:8080/api/social/kakao/code");
	            sb.append("&code=" + authorize_code);
	            bw.write(sb.toString());
	            bw.flush();
	            

	            int responseCode = conn.getResponseCode();
	 

	            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line = "";
	            String result = "";
	            
	            while ((line = br.readLine()) != null) {
	                result += line;
	            }
	            

	            JsonParser parser = new JsonParser();
	            JsonElement element = parser.parse(result);
	            
	            access_Token = element.getAsJsonObject().get("access_token").getAsString();
	            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
	            

	            
	            br.close();
	            bw.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } 
	        
	        return access_Token;
	    }
	 public String getGoogleAccessToken (String authorize_code) {
	        String access_Token = "";
	        String refresh_Token = "";
	        String reqURL = "https://oauth2.googleapis.com/token";
	        
	        try {
	            URL url = new URL(reqURL);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            
	            conn.setRequestMethod("POST");
	            conn.setDoOutput(true);
	            
	            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
	            StringBuilder sb = new StringBuilder();
	            sb.append("grant_type=authorization_code");
	            sb.append("&client_id=692091835929-e5bhto8anq0j3v7k21kb4f87gfn2gt6s.apps.googleusercontent.com");
	            sb.append("&client_secret=OMDDhDy00GIuETkIrQRiTuGl");
	            sb.append("&redirect_uri=http://i3b207.p.ssafy.io:8080/api/social/kakao/code");
	            sb.append("&code=" + authorize_code);
	            bw.write(sb.toString());
	            bw.flush();
	            
	            int responseCode = conn.getResponseCode();
	 
	            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line = "";
	            String result = "";
	            
	            while ((line = br.readLine()) != null) {
	                result += line;
	            }
	            
	            JsonParser parser = new JsonParser();
	            JsonElement element = parser.parse(result);
	            
	            access_Token = element.getAsJsonObject().get("access_token").getAsString();
	            
	            
	            br.close();
	            bw.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } 
	        
	        return access_Token;
	    }
	 public HashMap<String, Object> getKakaoUserInfo (String access_Token) {
		    
		    HashMap<String, Object> userInfo = new HashMap<>();
		    String reqURL = "https://kapi.kakao.com/v2/user/me";
		    try {
		        URL url = new URL(reqURL);
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestMethod("POST");
		        
		        conn.setRequestProperty("Authorization", "Bearer " + access_Token);
		        
		        int responseCode = conn.getResponseCode();
		        
		        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        
		        String line = "";
		        String result = "";
		        
		        while ((line = br.readLine()) != null) {
		            result += line;
		        }
		        
		        JsonParser parser = new JsonParser();
		        JsonElement element = parser.parse(result);
		        
		        JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
		        JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
		        
		        String nickname = properties.getAsJsonObject().get("nickname").getAsString();
		        String email = kakao_account.getAsJsonObject().get("email").getAsString();
		        
		        userInfo.put("nickname", nickname);
		        userInfo.put("email", email);
		        
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    
		    return userInfo;
		}
	 
	 public HashMap<String, Object> getGoogleUserInfo (String access_Token) {
		    
		    HashMap<String, Object> userInfo = new HashMap<>();
		    String reqURL = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=" +access_Token;
		    try {
		        URL url = new URL(reqURL);
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestMethod("GET");
		        
	
		        
		        int responseCode = conn.getResponseCode();
		        
		        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        
		        String line = "";
		        String result = "";
		        
		        while ((line = br.readLine()) != null) {
		            result += line;
		        }
		        
		        JsonParser parser = new JsonParser();
		        JsonElement element = parser.parse(result);
		        
		        
		        String nickname = element.getAsJsonObject().get("name").getAsString();
		        String email = element.getAsJsonObject().get("email").getAsString();
		        
		        userInfo.put("nickname", nickname);
		        userInfo.put("email", email);
		        
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    
		    return userInfo;
		}

}
