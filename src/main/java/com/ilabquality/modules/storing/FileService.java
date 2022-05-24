package com.ilabquality.modules.storing;

import com.google.gson.Gson;

import com.google.gson.JsonObject;
import com.ilabquality.modules.global.reference.SystemConstant;
import com.ilabquality.modules.global.reference.SystemDefault;

import org.json.JSONObject;

import java.io.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FileService implements IFileService {
  private final Path uploadPath = Paths.get("uploads");
  private final SystemDefault defaults = SystemDefault.getInstance();
  private final Gson gson = new Gson();

  public FileService() throws IOException {
    Path propertyPath = Paths.get(defaults.PROP_PATH_DEFAULT);
    if (!Files.isDirectory(propertyPath)) {
      Files.createDirectory(propertyPath);
    }

    Path testReportPath = Paths.get(defaults.TEST_REPORT_PATH);
    if (!Files.isDirectory(testReportPath)) {
      Files.createDirectory(testReportPath);
    }
  }

  @Override
  public void initialiseDirectories() throws Exception {
    try {
      if (!Files.isDirectory(uploadPath)) {
        Files.createDirectory(uploadPath);
      }

    } catch (IOException exception) {
      throw new Exception("[EXECUTOR] Sorry but we could not initialise your file directory");
    }
  }

  @Override
  public String readTextFile(String fileDirectory, String fileName) {
    StringBuilder stringBuilder = new StringBuilder();
    String filePath = SystemConstant.ROOT_PATH + File.separator + fileDirectory + File.separator + fileName;

    try {
      BufferedReader buffer = new BufferedReader(new FileReader(filePath));

      String line;
      while ((line = buffer.readLine()) != null) {
        stringBuilder.append(line).append(System.lineSeparator());
      }

    } catch (IOException exception) {
      System.out.println("[EXECUTOR] Sorry but we could not find your file");
    }

    return stringBuilder.toString();
  }

  @Override
  public FileInfo writeUploadedFile(File file) throws Exception {
    try {
      InputStream stream = new FileInputStream(file);

      Files.copy(
        stream,
        uploadPath.resolve(Objects.requireNonNull(file.getName())),
        StandardCopyOption.REPLACE_EXISTING
      );

    } catch (Exception exception) {
      System.out.println("[EXECUTOR] Sorry but we could not save your file");
    }

    return new FileInfo(file.getName(), uploadPath + File.separator + file.getName());
  }

  @Override
  public void writeTextFile(String fileDirectory, String fileName, String content) {
    try {
      fileDirectory = SystemConstant.ROOT_PATH + File.separator + fileDirectory;

      if (!Files.isDirectory(Path.of(fileDirectory))) {
        Files.createDirectory(Path.of(fileDirectory));
      }

      String filePath = fileDirectory + File.separator + fileName;
      FileWriter myWriter = new FileWriter(filePath);
      myWriter.write(content);
      myWriter.close();

    } catch (IOException e) {
      System.out.println("[EXECUTOR] Sorry but we could not update your file");
    }
  }

  @Override
  public void writePropertyFile(String fileName, JsonObject data) {
    StringBuilder stringBuilder = new StringBuilder();
    String fileContentString = readTextFile(defaults.PROP_PATH_DEFAULT, fileName);

    HashMap<?,?> map = gson.fromJson(data.toString(), HashMap.class);

    List<String> stringList = Arrays.stream(fileContentString.split(System.lineSeparator())).collect(Collectors.toList());
    stringList.forEach(line -> {

      List<String> keyValueList = Arrays.stream(line.split("=")).collect(Collectors.toList());
      String key = keyValueList.get(0);
      String value = "";

      if (keyValueList.size() > 1) {
        value = keyValueList.get(1);
      }

      List<String> propList = Arrays.stream(key.split("\\.")).collect(Collectors.toList());
      String prop = propList.get(1);

      String newValue = (String) map.get(prop);
      String newLine = key + "=";

      if (newValue != null) {
        newLine = newLine + newValue + System.lineSeparator();
      } else {
        newLine = newLine + value + System.lineSeparator();
      }

      stringBuilder.append(newLine);
    });

    writeTextFile(defaults.PROP_PATH_DEFAULT, fileName, stringBuilder.toString());
  }

  @Override
  public String exportFileOutsideJar(String resourceName) throws Exception {
    InputStream stream = null;
    OutputStream resStreamOut = null;
    String jarFolder;

    try {
      //note that each / is a directory down in the "jar tree" been the jar the root of the tree
      stream = com.ilabquality.modules.storing.FileService.class.getResourceAsStream(resourceName);

      if (stream == null) {
        throw new Exception("Cannot get resource \"" + resourceName + "\" from Jar file.");
      }

      int readBytes;
      byte[] buffer = new byte[4096];
      jarFolder = new File(com.ilabquality.modules.storing.FileService.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile().getPath().replace('\\', '/');
      resStreamOut = new FileOutputStream(jarFolder + resourceName);

      while ((readBytes = stream.read(buffer)) > 0) {
        resStreamOut.write(buffer, 0, readBytes);
      }

    } finally {

      if (stream != null) {
        stream.close();
      }

      if (resStreamOut != null) {
        resStreamOut.close();
      }
    }

    return jarFolder + resourceName;
  }
}
