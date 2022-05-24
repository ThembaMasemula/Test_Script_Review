package com.ilabquality.modules.storing;

import com.google.gson.JsonObject;
import org.json.JSONObject;

import java.io.File;

public interface IFileService {
  void initialiseDirectories() throws Exception;
  String readTextFile(String fileDirectory, String fileName);
  FileInfo writeUploadedFile(File file) throws Exception;
  void writeTextFile(String fileDirectory, String fileName, String content);
  void writePropertyFile(String fileName, JsonObject data);
  String exportFileOutsideJar(String resourceName) throws Exception;
}
