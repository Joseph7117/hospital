/**********************************************************************************************************************
 * # COPYRIGHT (C) 2014 AFRICASTALKING LTD <www.africastalking.com>                                                   *
 **********************************************************************************************************************
 *AFRICAStALKING SMS GATEWAY CLASS IS A FREE SOFTWARE IE. CAN BE MODIFIED AND/OR REDISTRIBUTED                        *
 *UNDER THER TERMS OF GNU GENERAL PUBLIC LICENCES AS PUBLISHED BY THE                                                 *
 *FREE SOFTWARE FOUNDATION VERSION 3 OR ANY LATER VERSION                                                             *
 **********************************************************************************************************************
 *THE CLASS IS DISTRIBUTED ON 'AS IS' BASIS WITHOUT ANY WARRANTY, INCLUDING BUT NOT LIMITED TO                        *
 *THE IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.                      *
 *IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,             *
 *WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE        *
 *OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.                                                                       *
 **********************************************************************************************************************/

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Map.Entry;

import org.json.*;

public class AfricasTalkingGateway
{
    private final String _username;
    private final String _apiKey;
    private int responseCode;
    
    private static final String SMSURLString      = "https://api.africastalking.com/version1/messaging";
    private static final String VOICEURLString    = "https://voice.africastalking.com";
	private static final String SUBSCRIPTION_URL  = "https://api.africastalking.com/version1/subscription";
	private static final String USERDATAURLString = "https://api.africastalking.com/version1/user";
	private static final String AIRTIMEURLString  = "https://api.africastalking.com/version1/airtime";
	private static final int HTTP_CODE_OK         = 200;
	private static final int HTTP_CODE_CREATED    = 201;
	
	//Change debug flag to true to view raw server response
	private static final boolean DEBUG = false;
	
	public AfricasTalkingGateway(String username_, String apiKey_)
    {
		_username = username_;
		_apiKey   = apiKey_;
    }
    
	
	//Bulk messages methods
    public JSONArray sendMessage(String to_, String message_) throws Exception
    {
    
    	HashMap<String, String> data = new HashMap<>();
    	data.put("username", _username);
    	data.put("to", to_);
    	data.put("message", message_);
	
    	return sendMessageImpro(to_, message_, data);
    }
    
   
    public JSONArray sendMessage(String to_, String message_, String from_, int bulkSMSMode_) throws Exception
    {	
    	HashMap<String, String> data = new HashMap<>();
    	data.put("username", _username);
    	data.put("to", to_);
    	data.put("message", message_);
	
    	if ( from_.length() > 0 ) data.put("from", from_);
	
    	data.put("bulkSMSMode", Integer.toString(bulkSMSMode_));
	
    	return sendMessageImpro(to_, message_, data);
    }
    

    public JSONArray sendMessage(String to_, String message_, String from_, int bulkSMSMode_, HashMap<String, String> options_) throws Exception
    {
    	HashMap<String, String> data = new HashMap<>();
    	data.put("username", _username);
    	data.put("to", to_);
    	data.put("message", message_);
	
    	if ( from_.length() > 0 ) data.put("from", from_);
	
    	data.put("bulkSMSMode", Integer.toString(bulkSMSMode_));
	

    	if (options_.containsKey("enqueue")) data.put("enqueue", options_.get("enqueue"));
    	if (options_.containsKey("keyword")) data.put("keyword", options_.get("keyword"));
    	if (options_.containsKey("linkId"))  data.put("linkId", options_.get("linkId"));
    	if (options_.containsKey("retryDurationInHours"))  data.put("retryDurationInHours", options_.get("retryDurationInHours"));
	
    	return sendMessageImpro(to_, message_, data);
    }
    

    public JSONArray fetchMessages(int lastReceivedId_) throws Exception
    {
    	String requestUrl = SMSURLString + "?" +
    			URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(_username, "UTF-8") +
    			"&" + URLEncoder.encode("lastReceivedId", "UTF-8") + "=" + URLEncoder.encode(Integer.toString(lastReceivedId_), "UTF-8");
    	
    	String response = sendGETRequest(requestUrl);
    	if(responseCode == HTTP_CODE_OK) {
    		JSONObject jsObject = new JSONObject(response);
    		return jsObject.getJSONObject("SMSMessageData").getJSONArray("Messages");
    	}
    	
    	throw new Exception(response);
    }

    
    //Subcscription methods
    public JSONObject createSubscription(String phoneNumber_, String shortCode_, String keyword_) throws Exception
    {
    	if(phoneNumber_.length() == 0 || shortCode_.length() == 0 || keyword_.length() == 0)
    		throw new Exception("Please supply phoneNumber, shortCode and keyword");
    	
		HashMap <String, String> data_ = new HashMap<>();
		data_.put("username", _username);
		data_.put("phoneNumber", phoneNumber_);
		data_.put("shortCode", shortCode_);
		data_.put("keyword", keyword_);
		String requestUrl = SUBSCRIPTION_URL + "/create";
		
		String response = sendPOSTRequest(data_, requestUrl);
		
		if(responseCode != HTTP_CODE_CREATED)
			throw new Exception(response);
		
		JSONObject jsObject = new JSONObject(response);
		return jsObject;
    }

  
    public JSONObject deleteSubscription(String phoneNumber_,String shortCode_, String keyword_) throws Exception
    {
    	if(phoneNumber_.length() == 0 || shortCode_.length() == 0 || keyword_.length() == 0)
		 			throw new Exception("Please supply phone number, short code and keyword");
    	
		HashMap <String, String> data_ = new HashMap<>();
		data_.put("username", _username);
		data_.put("phoneNumber", phoneNumber_);
		data_.put("shortCode", shortCode_);
		data_.put("keyword", keyword_);
		String requestUrl = SUBSCRIPTION_URL + "/delete";
		
		String response = sendPOSTRequest(data_, requestUrl);
		
		if(responseCode != HTTP_CODE_CREATED)
			throw new Exception(response);
		
		JSONObject jsObject = new JSONObject(response);
		return jsObject;
    }

    
    public JSONArray fetchPremiumSubscriptions (String shortCode_, String keyword_, int lastReceivedId_) throws Exception
    {
    	if(shortCode_.length() == 0 || keyword_.length() == 0)
    		throw new Exception("Please supply short code and keyword");
    	
    	lastReceivedId_ = lastReceivedId_ > 0? lastReceivedId_ : 0;
    	String requestUrl = SUBSCRIPTION_URL + "?username="+_username+"&shortCode="+shortCode_+"&keyword="+keyword_+"&lastReceivedId="+lastReceivedId_;
    	
    	String response = sendGETRequest(requestUrl);
    	if(responseCode == HTTP_CODE_OK) {
    		JSONObject jsObject = new JSONObject(response);
    		return jsObject.getJSONArray("responses");
    	}
    	
    	throw new Exception(response);
    }
    
    
    public JSONArray call(String from_, String to_) throws Exception
    {
    	HashMap<String, String> data = new HashMap<>();
    	data.put("username", _username);
    	data.put("from", from_);
    	data.put("to", to_);
    	String urlString = VOICEURLString + "/call";
    	
    	String response   = sendPOSTRequest(data, urlString);
    	
    	JSONObject jsObject = new JSONObject(response);
    	
    	if(jsObject.getString("errorMessage").equals("None"))
    			return jsObject.getJSONArray("entries");
    		throw new Exception(jsObject.getString("errorMessage"));
    }
 
    //Call methods
    public JSONArray getNumQueuedCalls(String phoneNumber, String queueName) throws Exception
    {
    	HashMap<String, String> data = new HashMap<>();
    	data.put("username", _username);
    	data.put("phoneNumber", phoneNumber);
    	data.put("queueName", queueName);
    	
    	return queuedCalls(data);
    }
 
    
	public JSONArray getNumQueuedCalls(String phoneNumber) throws Exception 
    {
    	HashMap<String, String> data = new HashMap<>();
    	data.put("username", _username);
    	data.put("phoneNumbers", phoneNumber);
    	
    	return queuedCalls(data);
    }
 
    
   public void uploadMediaFile(String url_) throws Exception
    {
    	HashMap<String, String> data = new HashMap<>();
    	data.put("username", _username);
    	data.put("url", url_);
    	String requestUrl = VOICEURLString + "/mediaUpload";
    	
    	String response = sendPOSTRequest(data, requestUrl);
    	
    	JSONObject jsObject = new JSONObject(response);
    	
    	if(!jsObject.getString("errorMessage").equals("None"))
    		throw new Exception(jsObject.getString("errorMessage"));
    	
    }
 
    
   //Airtime methods
    public JSONArray sendAirtime(String recipients_) throws Exception 
    {
    	HashMap<String, String> data_ = new HashMap<>();
    	data_.put("username", _username);
    	data_.put("recipients", recipients_);
    	String urlString = AIRTIMEURLString + "/send";
    	
    	String response = sendPOSTRequest(data_, urlString);
    	
    	if(responseCode == HTTP_CODE_CREATED) {
    		JSONObject jsObject = new JSONObject(response);
    		JSONArray results = jsObject.getJSONArray("responses");
    		if(results.length() > 0)
    			return results;
    		throw new Exception(jsObject.getString("errorMessage"));
    	}
    	
    	throw new Exception(response);
    }
    
    
    //User data method
    public JSONObject getUserData() throws Exception
    {
    	String requestUrl = USERDATAURLString + "?username="+_username;
    	
    	String response   = sendGETRequest(requestUrl);
    	if(responseCode == HTTP_CODE_OK) {
    		JSONObject jsObject = new JSONObject(response);
    		return jsObject.getJSONObject("UserData");
    	}
    	
    	throw new Exception(response);
    }

   
    private JSONArray sendMessageImpro(String to_, String message_, HashMap<String, String> data_) throws Exception{
    	String response = sendPOSTRequest(data_, SMSURLString);
    	if (responseCode == HTTP_CODE_CREATED) {
    		JSONObject jsObject = new JSONObject(response);
    		JSONArray  recipients = jsObject.getJSONObject("SMSMessageData").getJSONArray("Recipients");
    		if(recipients.length() > 0)
    			return recipients;
    		
    		throw new Exception(jsObject.getJSONObject("SMSMessageData").getString("Message"));
    	}
    	
    	throw new Exception(response);
    }

    
    //Private accessor methods
    private JSONArray queuedCalls(HashMap<String, String> data_) throws Exception {
    	String requestUrl = VOICEURLString + "/queueStatus";
    	String response = sendPOSTRequest(data_, requestUrl);
    	JSONObject jsObject = new JSONObject(response);
    	if(jsObject.getString("errorMessage").equals("None"))
    		return jsObject.getJSONArray("entries");
    	throw new Exception(jsObject.getString("errorMessage"));
    }
    
    
    private String sendPOSTRequest(HashMap<String, String> dataMap_, String urlString_) throws Exception {
    	try {
    		String data = new String();
    		Iterator<Entry<String, String>> it = dataMap_.entrySet().iterator();
    		while (it.hasNext()) {
    			Map.Entry<String, String> pairs = (Map.Entry<String, String>)it.next();
    			data += URLEncoder.encode(pairs.getKey(), "UTF-8");
    			data += "=" + URLEncoder.encode(pairs.getValue(), "UTF-8");
    			if ( it.hasNext() ) data += "&";
    		}
    		URL url = new URL(urlString_);
    		URLConnection conn = url.openConnection();
    		conn.setRequestProperty("Accept", "application/json");
    		conn.setRequestProperty("apikey", _apiKey);
	    	conn.setDoOutput(true);
	    	OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
	    	writer.write(data);
	    	writer.flush();
	    
	    	HttpURLConnection http_conn = (HttpURLConnection)conn;
	    	responseCode = http_conn.getResponseCode();
			
	    	BufferedReader reader;
	    	boolean passed = true;
	    	
    		if(responseCode == HTTP_CODE_OK || responseCode == HTTP_CODE_CREATED) {
    			reader = new BufferedReader(new InputStreamReader(http_conn.getInputStream()));
    		}
    		else {
    			reader = new BufferedReader(new InputStreamReader(http_conn.getErrorStream()));
    			passed = false;
    		}
    		String response = reader.readLine();
    		
    		if(DEBUG)
    			System.out.println(response);
    		
    		reader.close();
    		
    		if(passed)
    			return response;
    			
    		throw new Exception(response);
	    
    	} catch (Exception e){
    		throw e;
 		}
    }

    private String sendGETRequest(String urlString) throws Exception
    {
    	try {
    		URL url= new URL(urlString);
    		URLConnection connection = (URLConnection)url.openConnection();
    		connection.setRequestProperty("Accept","application/json");
    		connection.setRequestProperty("apikey", _apiKey);
    		
    		HttpURLConnection http_conn = (HttpURLConnection)connection;
    		responseCode = http_conn.getResponseCode();
    		
    		BufferedReader reader;
    		boolean passed = true;
    		if(responseCode == HTTP_CODE_OK || responseCode == HTTP_CODE_CREATED) {
    			reader = new BufferedReader(new InputStreamReader(http_conn.getInputStream()));
    		}
    		else {
    			reader = new BufferedReader(new InputStreamReader(http_conn.getErrorStream()));
    			passed = false;
    		}
    		String response = reader.readLine();
    		
    		if(DEBUG)
    			System.out.println(response);
    		
    		reader.close();
    		
    		if(passed)
    			return response;
    			
    		throw new Exception(response);
    	}
    	catch (Exception e) {throw e;}
    }
}
