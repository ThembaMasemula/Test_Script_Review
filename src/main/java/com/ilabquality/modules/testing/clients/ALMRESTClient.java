package com.ilabquality.modules.testing.clients;

import com.google.gson.JsonParser;
import com.ilabquality.modules.global.reference.SystemConstant;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import okhttp3.*;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.naming.AuthenticationException;
import javax.ws.rs.core.NewCookie;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class ALMRESTClient extends SystemConstant {

  private static HttpEntity entity;
  private static CloseableHttpClient httpClient = HttpClientBuilder.create().build();
  private static CloseableHttpResponse response;
  private static HttpPost request;
  private static CookieManager cm = new CookieManager();
  private static String id;
  private static List<NewCookie> Cookies;

  private static Response login() throws ClientProtocolException, IOException {
    OkHttpClient client = new OkHttpClient().newBuilder().build();
    MediaType mediaType = MediaType.parse("application/json");
    RequestBody body = RequestBody.create(mediaType, "");

    String MICROFOCUS_URL = "http://10.6.32.22:8080/qcbin/api/authentication/sign-in";
    String sALMLoginUserName = "blushaba";
    String sALMLoginpass = "Z6nhliva!";
    String auth = new String(com.sun.jersey.core.util.Base64.encode(sALMLoginUserName + ":" + sALMLoginpass));

    Request request = new Request.Builder().url(MICROFOCUS_URL + "/api/authentication/sign-in")
      .method("POST", body).addHeader("Authorization", "Basic " +
        auth)// .addHeader("Cookie", "JSESSIONID=ww1hohh2iao61tp6sb11iipny;
      .build();

    return client.newCall(request).execute();

  }

  private static ClientResponse loginALM() throws ClientProtocolException, IOException, AuthenticationException {
    String cycleUrl = MICROFOCUS_URL + "/api/authentication/sign-in";
    String auth = new String(com.sun.jersey.core.util.Base64.encode(MICROFOCUS_USERNAME + ":" + MICROFOCUS_KEY));

    return invokePostMethodALM(auth, cycleUrl, "");
  }

  public static void updateTestStatus(int instanceID, String status, File attachmentPath) throws Exception {
    String jsonInputString = createJsonInputStringBody(status);
    StringBuilder headerCookie = new StringBuilder();
    OkHttpClient client = new OkHttpClient().newBuilder().build();
    ClientResponse responseLogin = loginALM();
    Cookies = responseLogin.getCookies();

    for (NewCookie cookie : Cookies) {
      headerCookie.append(cookie);
    }

    String cookieOk = headerCookie.toString()
      .replace("Path=/;", "")
      .replace("Secure;", "")
      .replace("HttpOnly", "")
      .replace("Path=/", "")
      .replace("Secure", "")
      .replace("HTTPOnly", "");

    String InstanceURL = MICROFOCUS_URL + "/rest/domains/"
      + MICROFOCUS_DOMAIN + "/projects/"
      + MICROFOCUS_PROJECT + "/test-instances/" + instanceID;

    String auth = new String(com.sun.jersey.core.util.Base64.encode(MICROFOCUS_USERNAME + ":" + MICROFOCUS_KEY));
    String test = invokePutMethodALM(auth, InstanceURL, jsonInputString, cookieOk);
    attachFileALMInstance(instanceID, attachmentPath, cookieOk);
    System.out.println(test);
  }

  public static void attachFileALMInstance(int instanceID, File attachmentPath, String cookieOk) throws IOException, AuthenticationException {
    String InstanceURL = MICROFOCUS_URL + "/rest/domains/" + MICROFOCUS_DOMAIN + "/projects/" + MICROFOCUS_PROJECT + "/test-instances/" + instanceID + "/attachments";
    String auth = new String(com.sun.jersey.core.util.Base64.encode(MICROFOCUS_USERNAME + ":" + MICROFOCUS_KEY));
    OkHttpClient client = new OkHttpClient().newBuilder().build();
    String fileName = attachmentPath.getName();
    String filePath = attachmentPath.getAbsolutePath();
    MediaType mediaType = MediaType.parse("application/octet-stream");

    RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
      .addFormDataPart("filename", fileName).addFormDataPart("file", filePath,
        RequestBody.create(MediaType.parse("application/octet-stream"), attachmentPath))
      .build();

    Request request = new Request.Builder()
      .url(InstanceURL)
      .method("POST", body)
      .addHeader("Authorization", "Basic " + auth)
      .addHeader("Content-Type", "application/octet-stream").addHeader("Cookie", cookieOk).build();

    Response response = client.newCall(request).execute();
  }

  private static String invokePutMethodALM(String auth, String url, String data, String cookieOk) throws AuthenticationException, ClientHandlerException {
    Client client = Client.create();
    WebResource webResource = client.resource(url);
    ClientResponse response = webResource.header("Authorization", "Basic " + auth).header("Cookie", cookieOk).type("application/json")
      .accept("application/json").put(ClientResponse.class, data);
    int statusCode = response.getStatus();

    if (statusCode == 401) {
      throw new AuthenticationException("Invalid Username or Password");
    }

    return response.getEntity(String.class);
  }

  public static String createJsonInputStringBody(String sStatus) {
    return "{\n" +
      "\"Fields\":" + " [  {\n" +
      "\"Name\":" + "\"status\",\n" +
      "\"values\":" + " [\n" +
      "{\n" +
      "\"value\":" + "\"" + sStatus + "\"" + "     \n" +
      "}\n" +
      "] \n" +
      "}, \n" +
      "{  \n" +
      "\"Name\":" + "\"subtype-id\", \n" +
      "\"values\":" + " [\n" +
      "{\n" +
      "\"value\":" + "\"hp.qc.test-instance.VAPI-XP-TEST\" \n" +
      "}\n" +
      "] \n" +
      "}\n" +
      "]\n" +
      "}";
  }

  private static String createJsonInputStringBody(
    String summary,
    String status,
    String detectedBy,
    String creationDate,
    String severity,
    String owner,
    String project,
    String subject
  ) {

    String jsonInputString = "" +
      "{"
      + "\"user-05\":" + "\"" + MICROFOCUS_ISSUE + "\"";
    if (!(status == "" || status == null))
      jsonInputString += "," + "\"status\":" + "\"" + status + "\"";
    if (!(detectedBy == "" || detectedBy == null))
      jsonInputString += "," + "\"detected-by\":" + "\"" + MICROFOCUS_USERNAME + "\"";
    if (!(creationDate == "" || creationDate == null))
      jsonInputString += "," + "\"creation-time\":" + "\"" + creationDate + "\"";
    if (!(severity == "" || severity == null))
      jsonInputString += "," + "\"severity\":" + "\"" + MICROFOCUS_SEVERITY + "\"";
    if (!(summary == "" || summary == null))
      jsonInputString += "," + "\"name\":" + "\"" + summary + "\"";
    if (!(owner == "" || owner == null))
      jsonInputString += "," + "\"owner\":" + "\"" + MICROFOCUS_OWNER + "\"";
    if (!(project == "" || project == null))
      jsonInputString += "," + "\"project\":" + "\"" + project + "\"";
    if (!(subject == "" || subject == null))
      jsonInputString += "," + "\"subject\":" + subject;
    jsonInputString += "}";

    return jsonInputString;
  }

  private static String createJsonInputStringBody(
    String summary,
    String status,
    String detectedBy,
    String creationDate,
    String severity,
    String owner,
    String project,
    String subject,
    String description
  ) {

    String[] arrDesc = description.split(System.lineSeparator());
    String jsonInputString = "" +
      "{"
      + "\"user-05\":" + "\"" + MICROFOCUS_ISSUE + "\"";
    if (!(status == "" || status == null))
      jsonInputString += "," + "\"status\":" + "\"" + status + "\"";
    if (!(detectedBy == "" || detectedBy == null))
      jsonInputString += "," + "\"detected-by\":" + "\"" + MICROFOCUS_USERNAME + "\"";
    if (!(creationDate == "" || creationDate == null))
      jsonInputString += "," + "\"creation-time\":" + "\"" + creationDate + "\"";
    if (!(severity == "" || severity == null))
      jsonInputString += "," + "\"severity\":" + "\"" + MICROFOCUS_SEVERITY + "\"";
    if (!(summary == "" || summary == null))
      jsonInputString += "," + "\"name\":" + "\"" + summary + "\"";
    if (!(owner == "" || owner == null))
      jsonInputString += "," + "\"owner\":" + "\"" + MICROFOCUS_OWNER + "\"";
    if (!(project == "" || project == null))
      jsonInputString += "," + "\"project\":" + "\"" + project + "\"";
    if (!(subject == "" || subject == null))
      jsonInputString += "," + "\"subject\":" + subject;
    if (!(description == "" || description == null)) {
      jsonInputString += "," + "\"description\":" + "\"";

      for (int i = 0; i < arrDesc.length; i++) {
        jsonInputString += arrDesc[i];

        if (i < arrDesc.length)
          jsonInputString += "%n";
      }

      jsonInputString += "\"";

    }
    jsonInputString += "}";

    Pattern pattern = Pattern.compile(Pattern.quote("\r\n?|\n"));

    return jsonInputString;
  }

  public static String converter(String inputString) {
    return inputString.replaceAll("\\s+", "");

  }

  public static void updateDefect(String[] id, String summary, String status, String detectedBy, String creationDate, String severity, String owner, String project, String subject) throws Exception {
    for (String s : id) {
      updateDefect(s, summary, status, detectedBy, creationDate, severity, owner, project, subject);
    }
  }

  @Deprecated
  private void createDefectOld(String summary) throws IOException {

    URL url = new URL(MICROFOCUS_URL
      + "/api/domains/" + MICROFOCUS_DOMAIN
      + "/projects/" + MICROFOCUS_PROJECT + "/defects"
    );

    CookieManager cm = new CookieManager();
    CookieHandler.setDefault(cm);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("POST");

    conn.setRequestProperty(
      "Authorization",
      "Basic " + Base64.getEncoder().encodeToString((MICROFOCUS_USERNAME + ":" + MICROFOCUS_KEY).getBytes())
    );

    conn.setUseCaches(true);
    conn.setDoOutput(true);

    Map<String, String> parameters = new HashMap<>();

    conn.setRequestProperty("Content-Type", "application/json");
    String pattern = "yyyy-MM-dd";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    String date = simpleDateFormat.format(new Date());

    String jsonInputString = "{" + "\"user-05\":" + "\"" + MICROFOCUS_ISSUE + "\"" + "," + "\"status\":"
      + "\"New\"," + "\"detected-by\":" + "\"" + MICROFOCUS_USERNAME + "\"" + "," + "\"creation-time\":"
      + "\"" + date + "\"" + "," + "\"severity\":" + "\"" + MICROFOCUS_SEVERITY + "\"" + "," + "\"name\":"
      + "\"" + summary + "\"" + "," + "\"owner\":" + "\"" + MICROFOCUS_OWNER + "\"" + ","
      + "\"project\":" + "\"Mercury Flight Reservation Desktop Application\","
      + "}";

    try (OutputStream os = conn.getOutputStream()) {
      byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
      os.write(input, 0, input.length);
    }

    conn.connect();
  }

  public static ClientResponse invokePostMethodALM(String auth, String url, String data) throws AuthenticationException, ClientHandlerException {
    Client client = Client.create();
    WebResource webResource = client.resource(url);
    ClientResponse response = webResource.header("Authorization", "Basic " + auth).type("application/json")
      .accept("application/json").post(ClientResponse.class, data);
    int statusCode = response.getStatus();

    if (statusCode == 401) {
      throw new AuthenticationException("Invalid Username or Password");
    }

    return response;
  }

  private static void invokePutMethodALM(String auth, String url, String data) throws AuthenticationException, ClientHandlerException {
    Client client = Client.create();
    WebResource webResource = client.resource(url);
    ClientResponse response = webResource.header("Authorization", "Basic " + auth).type("application/json")
      .accept("application/json").put(ClientResponse.class, data);
    int statusCode = response.getStatus();

    if (statusCode == 401) {
      throw new AuthenticationException("Invalid Username or Password");
    }
  }


  public static void attachFile(String issueId, File file) throws IOException {
    String fileName = file.getName();
    String filePath = file.getAbsolutePath();
    StringBuilder headerCookie = new StringBuilder();
    OkHttpClient client = new OkHttpClient().newBuilder().build();
    Response responseLogin = login();

    for (int i = 0; i < responseLogin.headers().size(); i++) {
      if (responseLogin.headers().name(i).equals("Set-Cookie")) {
        headerCookie.append(responseLogin.headers().value(i));
      }
    }

    String cookieOk = headerCookie.toString()
      .replace("Path=/;", "")
      .replace("Secure;", "")
      .replace("HttpOnly", "")
      .replace("Path=/", "")
      .replace("Secure", "")
      .replace("HTTPOnly", "");

    MediaType mediaType = MediaType.parse("application/octet-stream");

    RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
      .addFormDataPart("filename", fileName).addFormDataPart("file", filePath,
        RequestBody.create(MediaType.parse("application/octet-stream"), file))
      .build();

    Request request = new Request.Builder()
      .url(MICROFOCUS_URL + "/rest/domains/" + MICROFOCUS_DOMAIN + "/projects/" + MICROFOCUS_PROJECT
        + "/defects/" + issueId + "/attachments")
      .method("POST", body)
      .addHeader("Authorization",
        "Basic " + Base64.getEncoder()
          .encodeToString((MICROFOCUS_USERNAME + ":" + MICROFOCUS_KEY).getBytes()))
      .addHeader("Content-Type", "application/octet-stream").addHeader("Cookie", cookieOk).build();

    Response response = client.newCall(request).execute();
  }

  public static String createDefect(String summary) throws IOException {
    String pattern = "yyyy-MM-dd";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    String date = simpleDateFormat.format(new Date());

    String jsonInputString = createJsonInputStringBody(
      summary, "New",
      MICROFOCUS_USERNAME, date,
      MICROFOCUS_SEVERITY,
      MICROFOCUS_OWNER,
      MICROFOCUS_DEFECT_PROJECT,
      MICROFOCUS_DEFECT_SUBJECT
    );

    StringBuilder headerCookie = new StringBuilder();
    OkHttpClient client = new OkHttpClient().newBuilder().build();
    Response responseLogin = login();

    for (int i = 0; i < responseLogin.headers().size(); i++) {
      if (responseLogin.headers().name(i).equals("Set-Cookie")) {
        headerCookie.append(responseLogin.headers().value(i));
      }
    }

    String cookieOk = headerCookie.toString()
      .replace("Path=/;", "")
      .replace("Secure;", "")
      .replace("HttpOnly", "")
      .replace("Path=/", "")
      .replace("Secure", "")
      .replace("HTTPOnly", "");

    MediaType mediaType = MediaType.parse("application/json");
    RequestBody body = RequestBody.create(mediaType, jsonInputString);

    Request request = new Request.Builder()
      .url(MICROFOCUS_URL + "/api/domains/" + MICROFOCUS_DOMAIN + "/projects/" +
        MICROFOCUS_PROJECT + "/defects")
      .method("POST", body)
      .addHeader("Authorization", "Basic " +
        Base64.getEncoder().encodeToString((MICROFOCUS_USERNAME + ":" +
          MICROFOCUS_KEY).getBytes()))
      .addHeader("Content-Type", "application/json").addHeader("Cookie", cookieOk).build();

    Response response = client.newCall(request).execute();

    id = new JsonParser().parse(response.body().string()).getAsJsonObject().get("id").getAsString();
    System.out.println("ID : " + id);

    return id;
  }

  public static String createDefect(String summary, String description) throws IOException {
    String pattern = "yyyy-MM-dd";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    String date = simpleDateFormat.format(new Date());

    String jsonInputString = createJsonInputStringBody(
      summary, "New",
      MICROFOCUS_USERNAME, date,
      MICROFOCUS_SEVERITY,
      MICROFOCUS_OWNER,
      MICROFOCUS_DEFECT_PROJECT,
      MICROFOCUS_DEFECT_SUBJECT,
      description
    ).replace("%n", "\\n");

    StringBuilder headerCookie = new StringBuilder();
    OkHttpClient client = new OkHttpClient().newBuilder().build();
    Response responseLogin = login();

    for (int i = 0; i < responseLogin.headers().size(); i++) {
      if (responseLogin.headers().name(i).equals("Set-Cookie")) {
        headerCookie.append(responseLogin.headers().value(i));
      }
    }
    String cookieOk = headerCookie.toString()
      .replace("Path=/;", "")
      .replace("Secure;", "")
      .replace("HttpOnly", "")
      .replace("Path=/", "")
      .replace("Secure", "")
      .replace("HTTPOnly", "");

    MediaType mediaType = MediaType.parse("application/json");
    RequestBody body = RequestBody.create(mediaType, jsonInputString);

    Request request = new Request.Builder()
      .url(MICROFOCUS_URL + "/api/domains/" + MICROFOCUS_DOMAIN + "/projects/" +
        MICROFOCUS_PROJECT + "/defects")
      .method("POST", body)
      .addHeader("Authorization", "Basic " +
        Base64.getEncoder().encodeToString((MICROFOCUS_USERNAME + ":" +
          MICROFOCUS_KEY).getBytes()))
      .addHeader("Content-Type", "application/json").addHeader("Cookie", cookieOk).build();

    Response response = client.newCall(request).execute();
    id = new JsonParser().parse(response.body().string()).getAsJsonObject().get("id").getAsString();
    System.out.println("ID : " + id);

    return id;
  }

  public static void updateDefect(String id, String summary, String status, String detectedBy, String creationDate, String severity, String owner, String project, String subject) throws Exception {
    if (Objects.equals(id, "") || id == null) {
      throw new Exception("Id is required!");
    }

    String jsonInputString = createJsonInputStringBody(summary, status, detectedBy, creationDate, severity, owner,
      project, subject);
    StringBuilder headerCookie = new StringBuilder();
    OkHttpClient client = new OkHttpClient().newBuilder().build();
    Response responseLogin = login();

    for (int i = 0; i < responseLogin.headers().size(); i++) {
      if (responseLogin.headers().name(i).equals("Set-Cookie")) {
        headerCookie.append(responseLogin.headers().value(i));
      }
    }

    String cookieOk = headerCookie.toString()
      .replace("Path=/;", "")
      .replace("Secure;", "")
      .replace("HttpOnly", "")
      .replace("Path=/", "")
      .replace("Secure", "");

    MediaType mediaType = MediaType.parse("application/json");
    RequestBody body = RequestBody.create(mediaType, jsonInputString);
    Request request = new Request.Builder()
      .url(MICROFOCUS_URL + "/api/domains/" + MICROFOCUS_DOMAIN + "/projects/" +
        MICROFOCUS_PROJECT + "/defects/" + id)
      .method("PUT", body)
      .addHeader("Authorization", "Basic " +
        Base64.getEncoder().encodeToString((MICROFOCUS_USERNAME + ":" +
          MICROFOCUS_KEY).getBytes()))
      .addHeader("Content-Type", "application/json").addHeader("Cookie", cookieOk).build();

    Response response = client.newCall(request).execute();
  }
}
