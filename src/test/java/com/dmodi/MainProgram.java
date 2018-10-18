package com.dmodi;


import org.json.simple.JSONObject;
import org.testng.Assert;
//import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class MainProgram {
	
	 public void test()
	 
	 {
		 RequestSpecification request = RestAssured.given();
		 
		 request.header("Content-Type","application/json");
		 
		
		 JSONObject json =new JSONObject();
		 
		 json.put("instanceId", "8888");
		 json.put("async", "false");
		 json.put("channelId", "POS");
		 json.put("cardNo", "4346780991043888");
		 json.put("merchantName", "Enstage2891");
		 json.put("merchantCity", "Bangalore");
		 json.put("merchantCountry", "UK");
		 json.put("merchantId", "573638022338106");
		 json.put("mcc", "6011");
		 json.put("clientRefKey", "DeepakModi");
		 json.put("purchaseAmount", "21000");
		 json.put("purchaseCurrencyCode", "356");
		 json.put("sourceAmount", "6770000");
		 json.put("sourceCurrencyCode", "356");
		 json.put("txnSourceType", "CP");
		 json.put("details", "true");
		 json.put("nodeIdentifier", "ISO1");
		 json.put("timeStamp", "20180309190200");
		 
		 
		 request.body(json.toJSONString());
		 
		// Let us send Post Request for 1st API
		 System.out.println("here");
		 Response response  = request.post("https://X.X.X.X:8443/analyse/POS/request");
		 System.out.println("again here");
		 int code = response.getStatusCode();
		 System.out.println(code);
		 Assert.assertEquals(code, 200);
		 
		// Let us fetch Client Id from the response of 1st API
		 JsonPath jsonPathEvaluator = response.jsonPath();
		 System.out.println("Client Id received from Response " + jsonPathEvaluator.get("clientId"));
		 
		 String Clientid = jsonPathEvaluator.get("clientId");
		 json.put("clientId", Clientid);
		 json.put("finalStatus", "100");
		 
	      request.body(json.toJSONString());
	      

	      
	   // Let us send Post Request for 2nd API
		 
		 Response response1  = request.post("http://X.X.X.X:8443/analyse/POS/updateTxnStatus");
		 
		 ResponseBody body = response1.getBody();
		 System.out.println("Response Body is: " + body.asString());
		 
	 }
	 public static void main(String[] args) {
		 TridentRules t1 =new TridentRules();
		 t1.test();
	
		
	}
	 	 

}
