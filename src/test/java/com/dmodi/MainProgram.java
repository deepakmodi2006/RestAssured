package com.dmodi;

import org.json.simple.JSONObject;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class MainProgram {
    
    public void test(){
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type","application/json");
        
        JSONObject json =new JSONObject();
        json.put("instanceId", "8888");
        json.put("async", "false");
        json.put("channelId", "POS");
        json.put("cardNo", "4466688111113016");
        json.put("merchantName", "DeepakModi1");
        json.put("merchantCity", "Bangalore");
        json.put("merchantCountry", "IN");
        json.put("merchantId", "DKGroupCmpanies");
        json.put("mcc", "6011");
        json.put("clientRefKey", "DeepakModi");
        json.put("purchaseAmount", "21000");
        json.put("purchaseCurrencyCode", "356");
        json.put("sourceAmount", "21000");
        json.put("sourceCurrencyCode", "356");
        json.put("txnSourceType", "CP");
        json.put("details", "true");
        json.put("nodeIdentifier", "ISO1");
        json.put("timeStamp", "20181228110630");
        
        request.body(json.toJSONString());
        
        // Let us send Post Request for 1st API
        System.out.println("Sending REST Request");
        Response response  = request.post("http://192.168.108.33:8443/analyse/POS/request");
        ResponseBody body = response.getBody();
        int code = response.getStatusCode();
        System.out.println("Return Status Code: "+code);
        Assert.assertEquals(code, 200);
        
        System.out.println("Complete Response..."+body.asString());
        
        // Let us fetch Client Id from the response of 1st API
        JsonPath jsonPath = response.jsonPath();
        String clientid = jsonPath.get("clientId");
        System.out.println("\n\nclientId: " + clientid);
        System.out.println("ruleRating: " + jsonPath.get("ruleRating"));
        System.out.println("ruleSuggestion: " + jsonPath.get("ruleSuggestion"));
        
        json.clear();
        json.put("clientId", clientid);
        json.put("finalStatus", "100");
        
        request.body(json.toJSONString());
        
        // Let us send Post Request for 2nd API
        Response response1  = request.post("http://192.168.108.33:8443/analyse/POS/updateTxnStatus");
        body = response1.getBody();
        System.out.println("Report Response is: " + body.asString());
    }
    public static void main(String[] args) {
        MainProgram t1 =new MainProgram();
        t1.test();
    }
}
