package com.ilabquality.modules.reporting;

import com.ilabquality.modules.global.entities.PdfElement;
import com.ilabquality.modules.global.enums.PropertyFolder;
import com.ilabquality.modules.global.reference.SystemConstant;
import com.ilabquality.modules.global.reference.SystemDefault;
import com.ilabquality.modules.global.utils.GlobalUtils;
import com.ilabquality.modules.global.utils.PropertyManager;
import com.ilabquality.modules.reporting.utils.AddImages;
import com.ilabquality.modules.reporting.utils.AddContent;
import com.ilabquality.modules.testing.BaseContext;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.testng.*;

import java.io.File;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.*;

public class PdfBoxReport {
  private static PdfBoxReport instance = null;

  public static PdfBoxReport getInstance() {
    if (instance == null) {
      instance = new PdfBoxReport();
    }

    return instance;
  }

  public static void setInstance(PdfBoxReport instance) {
    if (instance != null) {
      PdfBoxReport.instance = instance;
    }
  }

  private final BaseContext context = BaseContext.getInstance();
  private final SystemDefault defaults = SystemDefault.getInstance();
  private final AddImages addImages = new AddImages();
  private final AddContent addContent = new AddContent();

  public void setupPaths() {
    try {
      if (context.classNames.indexOf(context.currentTest) == 0) {
        Path pdfFilePath = Paths.get(SystemConstant.ROOT_PATH, defaults.TEST_REPORT_PATH, context.suiteName, context.suiteName + ".pdf");
        Path pdfFileDirectoryPath = GlobalUtils.getFileDirectoryWithoutFile(pdfFilePath);
        Path clientDirectoryPath = Paths.get(SystemConstant.ROOT_PATH + File.separator + PropertyFolder.CLIENT);
        Path clientLogoPath = Paths.get(
          clientDirectoryPath + File.separator + PropertyManager.getProperty(PropertyFolder.CLIENT,
            "client",
            "logoPath")
        );

        context.pdfFileName = pdfFilePath.getFileName();
        context.pdfFilePath = pdfFilePath;
        context.pdfFileDirectoryPath = pdfFileDirectoryPath;
        context.clientDirectoryPath = clientDirectoryPath;
        context.clientLogoPath = clientLogoPath;

        if (!Files.isDirectory(pdfFileDirectoryPath)) {
          Files.createDirectory(pdfFileDirectoryPath);
        }
      }
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  public void builPdf() {
    try {
      if (context.classNames.indexOf(context.currentTest) == 0) {
        Path pdfFilePath = context.pdfFilePath;
        Path imagePath = context.clientLogoPath;

        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);

        PdfElement logo = addImages.addLogoFromClientFile(document, imagePath);
        PdfElement header = addContent.addClientHeader(document, logo);
        PdfElement title = addContent.addClientTitle(document, logo);
        PdfElement context = addContent.addClientContext(document, title);

        document.save(String.valueOf(pdfFilePath));
        document.close();
      }

    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  public void screenCapture(WebDriver driver, ITestResult result, String listenerMethod) {
    try {
      Path filePath = context.pdfFilePath;
      String scenario = context.scenario;
      File existingPdfFile = new File(String.valueOf(filePath));
      PDDocument document = PDDocument.load(existingPdfFile);

      if (driver != null) {
        File screenCaptureFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        PdfElement testTitle = addContent.addTestTitle(document);
        PdfElement testContext = addContent.addTestContext(document, result, testTitle, listenerMethod, scenario);
        PdfElement testHeader = addContent.addTestHeader(document, result, testContext);
        PdfElement screenCapture = addImages.addFromFile(document, screenCaptureFile, testHeader);
      }

      document.save(String.valueOf(filePath));
      document.close();

    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  public void reportCloudSummary(List<ISuite> suites) {
    try {
      boolean lastClass = context.classNames.indexOf(context.currentTest) == context.classNames.size() - 1;

      if (lastClass) {
        Path filePath = context.pdfFilePath;
        File existingPdfFile = new File(String.valueOf(filePath));

        PDDocument document = PDDocument.load(existingPdfFile);
        PdfElement summaryTitle = addContent.addSummaryTitle(document);

        for (ISuite suite : suites) {
          addContent.addCloudSummaryTable(document, suite, summaryTitle);
          addContent.addFooter(document);
        }

        document.save(String.valueOf(filePath));
        document.close();
      }

    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  public void reportXmlSummary(List<ISuite> suites) {
    try {
      boolean firstClass = context.classNames.indexOf(context.currentTest) == context.classNames.size() - 1;

      if (firstClass) {
        Path filePath = context.pdfFilePath;
        File existingPdfFile = new File(String.valueOf(filePath));

        PDDocument document = PDDocument.load(existingPdfFile);
        PdfElement summaryTitle = addContent.addSummaryTitle(document);

        for (ISuite suite : suites) {
          addContent.addXmlSummaryTable(document, suite, summaryTitle);
          addContent.addFooter(document);
        }

        document.save(String.valueOf(filePath));
        document.close();
      }

    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }
}
