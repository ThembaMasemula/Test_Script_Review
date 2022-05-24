package com.ilabquality.modules.reporting.utils;

import be.quodlibet.boxable.*;
import be.quodlibet.boxable.line.LineStyle;
import be.quodlibet.boxable.utils.FontUtils;
import be.quodlibet.boxable.utils.PDStreamUtils;
import be.quodlibet.boxable.utils.PageContentStreamOptimized;

import com.google.gson.JsonObject;

import com.ilabquality.modules.global.entities.PdfElement;
import com.ilabquality.modules.global.enums.PropertyFolder;
import com.ilabquality.modules.global.enums.ReportStatus;
import com.ilabquality.modules.global.reference.SystemConstant;
import com.ilabquality.modules.global.utils.GlobalUtils;
import com.ilabquality.modules.global.utils.PropertyManager;
import com.ilabquality.modules.testing.BaseContext;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;

import org.testng.*;

import java.awt.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class AddContent {
  private final BaseContext context = BaseContext.getInstance();

  private void createCells(float[] widths, Map.Entry<String, String> entry, Row<PDPage> row) {
    PDFont font = SystemConstant.STD_FONT;
    LineStyle lineStyle = new LineStyle(Color.lightGray, 0.2f);

    int count = 0;
    for (float width : widths) {
      String cellContent;
      float fontSize;
      HorizontalAlignment alignment;
      Color textColor;

      if (count == 0) {
        cellContent = "" + entry.getKey().toUpperCase(Locale.ROOT);
        fontSize = SystemConstant.STD_FONT_SUBSCRIPT;
        alignment = HorizontalAlignment.LEFT;
        textColor = Color.lightGray;
      } else {
        cellContent = entry.getValue();
        fontSize = SystemConstant.STD_FONT_BASE;
        alignment = HorizontalAlignment.RIGHT;
        textColor = Color.black;
      }

      Cell<PDPage> cell = row.createCell(
        width,
        cellContent,
        alignment,
        VerticalAlignment.MIDDLE
      );

      cell.setFont(font);
      cell.setTextColor(textColor);
      cell.setFontSize(fontSize);
      cell.setBorderStyle(lineStyle);
      cell.setHeight(cell.getTextHeight() + SystemConstant.TABLE_CELL_PADDING);

      count++;
    }
  }

  private void createSingleCellTableRow(BaseTable table, String cellContent, float fontSize) {
    PDFont font = SystemConstant.STD_FONT;
    LineStyle lineStyle = new LineStyle(Color.lightGray, 0.2f);
    float rowHeight = ReportingUtils.calculateRowHeight(
      SystemConstant.TABLE_LINE_SPACING,
      SystemConstant.STD_PADDING_SML,
      FontUtils.getHeight(font, SystemConstant.STD_FONT_BASE)
    );

    Row<PDPage> row = table.createRow(rowHeight);
    row.setLineSpacing(SystemConstant.TABLE_LINE_SPACING);

    Cell<PDPage> cell = row.createCell(
      100f,
      cellContent,
      HorizontalAlignment.LEFT,
      VerticalAlignment.MIDDLE
    );

    cell.setFont(font);
    cell.setTextColor(Color.black);
    cell.setFontSize(fontSize);
    cell.setBorderStyle(lineStyle);
    cell.setHeight(cell.getTextHeight() + SystemConstant.TABLE_CELL_PADDING);
  }

  private void createDoubleCellTableRow(int[] cellWidths, Map.Entry<String, String> entry, BaseTable table) {
    PDFont font = SystemConstant.STD_FONT;
    LineStyle lineStyle = new LineStyle(Color.lightGray, 0.2f);

    float rowHeight = ReportingUtils.calculateRowHeight(
      SystemConstant.TABLE_LINE_SPACING,
      SystemConstant.STD_PADDING_SML,
      FontUtils.getHeight(font, SystemConstant.STD_FONT_BASE)
    );

    Row<PDPage> row = table.createRow(rowHeight);
    row.setLineSpacing(SystemConstant.TABLE_LINE_SPACING);

    float[] widthPercentages = ReportingUtils.calculateUnequalCellsContentWidthInRow(
      SystemConstant.TABLE_LINE_SPACING,
      SystemConstant.STD_PADDING_SML,
      row.getWidth(),
      cellWidths
    );

    Cell<PDPage> cellOne = row.createCell(
      widthPercentages[0],
      entry.getKey(),
      HorizontalAlignment.LEFT,
      VerticalAlignment.MIDDLE
    );

    cellOne.setFont(font);
    cellOne.setTextColor(Color.lightGray);
    cellOne.setFontSize(SystemConstant.STD_FONT_SUBSCRIPT);
    cellOne.setBorderStyle(lineStyle);
    cellOne.setHeight(cellOne.getTextHeight() + SystemConstant.TABLE_CELL_PADDING);

    Cell<PDPage> cellTwo = row.createCell(
      widthPercentages[1],
      entry.getValue(),
      HorizontalAlignment.RIGHT,
      VerticalAlignment.MIDDLE
    );

    cellTwo.setFont(font);
    cellTwo.setTextColor(Color.black);
    cellTwo.setFontSize(SystemConstant.STD_FONT_BASE);
    cellTwo.setBorderStyle(lineStyle);
    cellTwo.setHeight(cellTwo.getTextHeight() + SystemConstant.TABLE_CELL_PADDING);
  }

  public PdfElement addClientHeader(PDDocument document, PdfElement logo) {
    PDPageTree pages = document.getPages();
    PDPage page = pages.get(0);
    PDFont font = SystemConstant.STD_FONT;

    float marginBetweenYElements = 22f;
    float maxHeight = logo.getHeight();
    float yStart = logo.getRightTopWithXMargin().y - SystemConstant.STD_PADDING_MED;
    float xStart = logo.getRightTopWithXMargin().x;
    float maxWidth = PDRectangle.A4.getWidth() - xStart - SystemConstant.PDF_PADDING;
    float calculatedHeight = 0;

    PdfElement element = new PdfElement(xStart, yStart, maxWidth, maxHeight);

    try {
      PDPageContentStream contentStream = new PDPageContentStream(
        document,
        page,
        PDPageContentStream.AppendMode.APPEND,
        true,
        true
      );

      PageContentStreamOptimized optimizedStream = new PageContentStreamOptimized(contentStream);

      String companyName = PropertyManager.getProperty(PropertyFolder.CLIENT, "client", "companyName");
      PDStreamUtils.write(optimizedStream, companyName, font, SystemConstant.STD_FONT_TITLE, xStart, yStart, Color.black);

      calculatedHeight += FontUtils.getHeight(font, SystemConstant.STD_FONT_TITLE);
      calculatedHeight += (marginBetweenYElements * 1.20);
      yStart -= (marginBetweenYElements * 1.20);

      String product = PropertyManager.getProperty(PropertyFolder.CLIENT, "client", "productName");
      PDStreamUtils.write(optimizedStream, product, font, SystemConstant.STD_FONT_SUB_TITLE, xStart, yStart, Color.black);

      calculatedHeight += FontUtils.getHeight(font, SystemConstant.STD_FONT_SUB_TITLE);
      calculatedHeight += (marginBetweenYElements * 0.80);
      yStart -= (marginBetweenYElements * 0.80);

      PDStreamUtils.write(optimizedStream,
        String.format("Testing Results as printed on: %s", LocalDate.now()),
        font, SystemConstant.STD_FONT_SUBSCRIPT,
        xStart, yStart, Color.black
      );

      optimizedStream.close();

      calculatedHeight += FontUtils.getHeight(font, SystemConstant.STD_FONT_SUBSCRIPT);
      calculatedHeight = (float) (Math.ceil(calculatedHeight / 10) * 10);

      element = new PdfElement(xStart, yStart, maxWidth, calculatedHeight);

    } catch (Exception exception) {
      exception.printStackTrace();
    }

    return element;
  }

  public PdfElement addClientTitle(PDDocument document, PdfElement logo) {
    PDPageTree pages = document.getPages();
    PDPage page = pages.get(0);
    PDFont font = SystemConstant.STD_FONT;

    float marginBetweenYElements = 22f;
    float maxHeight = 3 * SystemConstant.MAX_TITLE_HEIGHT;
    float maxWidth = PDRectangle.A4.getWidth() - (2 * SystemConstant.PDF_PADDING);
    float xStart = logo.getLeftBottomWithYMargin().x;
    float yStart = logo.getLeftBottomWithYMargin().y - SystemConstant.STD_PADDING_LRG;
    float calculatedHeight = 0;

    PdfElement element = new PdfElement(xStart, yStart, maxWidth, maxHeight);

    try {
      PDPageContentStream contentStream = new PDPageContentStream(
        document,
        page,
        PDPageContentStream.AppendMode.APPEND,
        true,
        true
      );

      PageContentStreamOptimized optimizedStream = new PageContentStreamOptimized(contentStream);

      PDStreamUtils.write(optimizedStream, "Automated System Testing", font, SystemConstant.STD_FONT_TITLE, xStart, yStart, Color.black);

      calculatedHeight += FontUtils.getHeight(font, SystemConstant.STD_FONT_TITLE);
      calculatedHeight += (marginBetweenYElements * 1.10);
      yStart -= (marginBetweenYElements * 1.10);

      String productVersion = PropertyManager.getProperty(PropertyFolder.CLIENT, "client", "productVersion");
      PDStreamUtils.write(optimizedStream, "Version tested: " + productVersion, font, SystemConstant.STD_FONT_SUB_TITLE, xStart, yStart, Color.black);

/*      calculatedHeight += FontUtils.getHeight(font, SystemConstant.fontSubTitle);
      calculatedHeight += marginBetweenYElements;
      yStart -= marginBetweenYElements;

      PDStreamUtils.write(optimizedStream, "General test data", font, SystemConstant.fontSubTitle, xStart, yStart, Color.black);*/

      optimizedStream.close();

      calculatedHeight += FontUtils.getHeight(font, SystemConstant.STD_FONT_SUB_TITLE);
      calculatedHeight = (float) (Math.ceil(calculatedHeight / 10) * 10);

      element = new PdfElement(xStart, yStart, maxWidth, calculatedHeight);

    } catch (Exception exception) {
      exception.printStackTrace();
    }

    return element;
  }

  public PdfElement addClientContext(PDDocument document, PdfElement title) {
    PDPageTree pages = document.getPages();
    PDPage page = pages.get(0);
    PDFont font = SystemConstant.STD_FONT;
    BaseContext context = BaseContext.getInstance();

    float maxWidth = PDRectangle.A4.getWidth() - (2 * SystemConstant.PDF_PADDING);
    float xStart = title.getLeftBottomWithYMargin().x;
    float yStart = title.getLeftBottomWithYMargin().y - SystemConstant.STD_PADDING_LRG;

    PdfElement element = new PdfElement(xStart, yStart, maxWidth, SystemConstant.PDF_FAILED_ELEMENT_HEIGHT);

    try {
      BaseTable table = new BaseTable(
        yStart,
        yStart,
        SystemConstant.PDF_ELEMENT2ELEMENT_MARGIN,
        maxWidth,
        xStart,
        document,
        page,
        true,
        true
      );

      table.setLineSpacing(SystemConstant.TABLE_LINE_SPACING);

      String suiteName = context.suiteName;
      String testClassesString = GlobalUtils.convertListToCommaSeperatedStringList(context.classNames);

      Map<String, String> resultMap = new LinkedHashMap<>();
      resultMap.put("SUITE", suiteName);
      resultMap.put("TESTS", testClassesString);

      int[] cellWidths = new int[]{150, 250};
      float[] cellWidthPercentages = ReportingUtils.calculateUnequalCellsContentWidthInRow(
        SystemConstant.TABLE_LINE_SPACING,
        SystemConstant.STD_PADDING_SML,
        maxWidth,
        cellWidths
      );

      float rowHeight = ReportingUtils.calculateRowHeight(
        SystemConstant.TABLE_LINE_SPACING,
        SystemConstant.STD_PADDING_SML,
        FontUtils.getHeight(font, SystemConstant.STD_FONT_BASE)
      );

      for (Map.Entry<String, String> entry : resultMap.entrySet()) {
        Row<PDPage> row = table.createRow(rowHeight);
        row.setLineSpacing(SystemConstant.TABLE_LINE_SPACING);

        createCells(cellWidthPercentages, entry, row);
      }

      table.draw();

      element = new PdfElement(table, xStart, yStart);

    } catch (Exception exception) {
      exception.printStackTrace();
    }

    return element;
  }

  public PdfElement addTestTitle(PDDocument document) {
    PDPage page = new PDPage(PDRectangle.A4);
    document.addPage(page);
    PDFont font = SystemConstant.STD_FONT;

    float marginBetweenYElements = 22f;
    float maxHeight = 3 * SystemConstant.MAX_TITLE_HEIGHT;
    float maxWidth = PDRectangle.A4.getWidth() - (2 * SystemConstant.PDF_PADDING);
    float xStart = SystemConstant.PDF_PADDING;
    float yStart = PDRectangle.A4.getHeight() - SystemConstant.PDF_PADDING;
    float calculatedHeight = 0;

    PdfElement element = new PdfElement(xStart, yStart, maxWidth, maxHeight);

    try {
      PDPageContentStream contentStream = new PDPageContentStream(
        document,
        page,
        PDPageContentStream.AppendMode.APPEND,
        true,
        true
      );

      PageContentStreamOptimized optimizedStream = new PageContentStreamOptimized(contentStream);

      PDStreamUtils.write(optimizedStream, "Automated System Testing", font, SystemConstant.STD_FONT_TITLE, xStart, yStart, Color.black);

      calculatedHeight += FontUtils.getHeight(font, SystemConstant.STD_FONT_TITLE);
      calculatedHeight += (marginBetweenYElements * 1.10);
      yStart -= (marginBetweenYElements * 1.10);

      String productVersion = PropertyManager.getProperty(PropertyFolder.CLIENT, "client", "productVersion");
      PDStreamUtils.write(optimizedStream, "Version: " + productVersion, font, SystemConstant.STD_FONT_SUB_TITLE, xStart, yStart, Color.black);

      calculatedHeight += FontUtils.getHeight(font, SystemConstant.STD_FONT_SUB_TITLE);
      calculatedHeight += marginBetweenYElements;
      yStart -= marginBetweenYElements;

      PDStreamUtils.write(optimizedStream, "Test result", font, SystemConstant.STD_FONT_SUB_TITLE, xStart, yStart, Color.black);

      optimizedStream.close();

      calculatedHeight += FontUtils.getHeight(font, SystemConstant.STD_FONT_SUB_TITLE);
      calculatedHeight = (float) (Math.ceil(calculatedHeight / 10) * 10);

      element = new PdfElement(xStart, yStart, maxWidth, calculatedHeight);

    } catch (Exception exception) {
      exception.printStackTrace();
    }

    return element;
  }

  public PdfElement addTestContext(PDDocument document, ITestResult result, PdfElement testTitle, String listenerMethod, String scenario) {
    PDPageTree pages = document.getPages();
    PDPage page = pages.get(pages.getCount() - 1);
    PDFont font = SystemConstant.STD_FONT;

    float marginBetweenYElements = 22f;
    float maxHeight = 3 * SystemConstant.MAX_TITLE_HEIGHT;
    float xStart = testTitle.getLeftBottomWithYMargin().x;
    float yStart = testTitle.getLeftBottomWithYMargin().y - SystemConstant.STD_PADDING_LRG;
    float maxWidth = PDRectangle.A4.getWidth() - (2 * SystemConstant.PDF_PADDING);
    float calculatedHeight = 0;

    PdfElement element = new PdfElement(xStart, yStart, maxWidth, maxHeight);

    try {
      String testName = result.getInstanceName();
      List<String> testNameParts = List.of(testName.split("\\."));
      testName = testNameParts.get(testNameParts.size() - 1);

      ITestNGMethod method = result.getMethod();
      String methodName = method.getMethodName().replace("_", " ");;
      String methodPriority = String.valueOf(method.getPriority());

      PDPageContentStream contentStream = new PDPageContentStream(
        document,
        page,
        PDPageContentStream.AppendMode.APPEND,
        true,
        true
      );

      PageContentStreamOptimized optimizedStream = new PageContentStreamOptimized(contentStream);

      PDStreamUtils.write(optimizedStream, "TEST: ", font, SystemConstant.STD_FONT_SUBSCRIPT, xStart + 27f, yStart - 2f, Color.lightGray);
      PDStreamUtils.write(optimizedStream, testName, font, SystemConstant.STD_FONT_SUB_TITLE, xStart + 65f, yStart, Color.black);

      calculatedHeight += FontUtils.getHeight(font, SystemConstant.STD_FONT_SUB_TITLE);
      calculatedHeight += marginBetweenYElements;
      yStart -= marginBetweenYElements;

      PDStreamUtils.write(optimizedStream, "SCENARIO: ", font, SystemConstant.STD_FONT_SUBSCRIPT, xStart, yStart - 2f, Color.lightGray);
      PDStreamUtils.write(optimizedStream, scenario, font, SystemConstant.STD_FONT_SUB_TITLE, xStart + 65f, yStart, Color.black);

      calculatedHeight += FontUtils.getHeight(font, SystemConstant.STD_FONT_SUB_TITLE);
      calculatedHeight += marginBetweenYElements;
      yStart -= marginBetweenYElements;

      PDStreamUtils.write(optimizedStream, "STEP " + methodPriority + ":", font, SystemConstant.STD_FONT_SUBSCRIPT, xStart + 17f, yStart - 3f, Color.lightGray);
      PDStreamUtils.write(optimizedStream, methodName, font, SystemConstant.STD_FONT_SUB_TITLE, xStart + 65f, yStart, Color.black);

      calculatedHeight += FontUtils.getHeight(font, SystemConstant.STD_FONT_SUB_TITLE);
      calculatedHeight += marginBetweenYElements;
      yStart -= marginBetweenYElements;

/*      PDStreamUtils.write(optimizedStream, "STEP NUMBER: ", font, SystemConstant.fontSubscript, xStart + 4f, yStart - 2f, Color.lightGray);
      PDStreamUtils.write(optimizedStream, methodPriority, font, SystemConstant.fontSubTitle, xStart + 82f, yStart, Color.black);*/

/*      calculatedHeight += FontUtils.getHeight(font, SystemConstant.fontSubTitle);
      calculatedHeight += marginBetweenYElements;
      yStart -= marginBetweenYElements;

      PDStreamUtils.write(optimizedStream, "LISTENER: ", font, SystemConstant.fontSubscript, xStart + 26f, yStart - 2f, Color.lightGray);
      PDStreamUtils.write(optimizedStream, listenerMethod, font, SystemConstant.fontSubTitle, xStart + 82f, yStart, Color.black);*/

      optimizedStream.close();

      calculatedHeight += FontUtils.getHeight(font, SystemConstant.STD_FONT_SUB_TITLE);
      calculatedHeight = (float) (Math.ceil(calculatedHeight / 10) * 10);

      element = new PdfElement(xStart, yStart, maxWidth, calculatedHeight);

    } catch (Exception exception) {
      exception.printStackTrace();
    }

    return element;
  }

  public PdfElement addTestHeader(PDDocument document, ITestResult result, PdfElement testContext) {
    PDPageTree pages = document.getPages();
    PDPage page = pages.get(pages.getCount() - 1);
    PDFont font = SystemConstant.STD_FONT;
    LineStyle lineStyle = new LineStyle(Color.lightGray, 0.2f);

    float maxWidth = PDRectangle.A4.getWidth() - (2 * SystemConstant.PDF_PADDING);
    float xStart = testContext.getLeftBottomWithYMargin().x;
    float yStart = testContext.getLeftBottomWithYMargin().y - SystemConstant.STD_PADDING_LRG;

    PdfElement element = new PdfElement(xStart, yStart, maxWidth, SystemConstant.PDF_FAILED_ELEMENT_HEIGHT);

    try {
      BaseTable table = new BaseTable(
        yStart,
        yStart,
        SystemConstant.PDF_ELEMENT2ELEMENT_MARGIN,
        maxWidth,
        xStart,
        document,
        page,
        true,
        true
      );

      table.setLineSpacing(SystemConstant.TABLE_LINE_SPACING);

      DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("E, dd/MM/yy hh:mm:ss");
      long[] durationUnits = GlobalUtils.splitDurationIntimeUnits(result.getEndMillis() - result.getStartMillis());
      Instant startInstant = java.time.Instant.ofEpochMilli(result.getStartMillis());
      ZoneId zone = ZoneId.systemDefault();
      ZonedDateTime startZonedDate = java.time.ZonedDateTime.ofInstant(startInstant, zone);
      Instant endInstant = java.time.Instant.ofEpochMilli(result.getEndMillis());
      ZonedDateTime endZonedDate = java.time.ZonedDateTime.ofInstant(endInstant, zone);

      Map<String, String> resultMap = new LinkedHashMap<>();
      resultMap.put("STATUS", String.valueOf(ReportStatus.getStatusString(String.valueOf(result.getStatus()))));
      resultMap.put("START-TIME", startZonedDate.format(dateFormatter));
      resultMap.put("END-TIME", endZonedDate.format(dateFormatter));
      resultMap.put("TOTAL-TIME",
        String.format("%s min, %s sec, %s ms", durationUnits[1], durationUnits[2], durationUnits[3])
      );

      int[] cellWidths = new int[]{150, 250};
      float[] cellWidthPercentages = ReportingUtils.calculateUnequalCellsContentWidthInRow(
        SystemConstant.TABLE_LINE_SPACING,
        SystemConstant.STD_PADDING_SML,
        maxWidth,
        cellWidths
      );

      float rowHeight = ReportingUtils.calculateRowHeight(
        SystemConstant.TABLE_LINE_SPACING,
        SystemConstant.STD_PADDING_SML,
        FontUtils.getHeight(font, SystemConstant.STD_FONT_BASE)
      );

      for (Map.Entry<String, String> entry : resultMap.entrySet()) {
        Row<PDPage> row = table.createRow(rowHeight);
        row.setLineSpacing(SystemConstant.TABLE_LINE_SPACING);

        int count = 0;
        for (float width : cellWidthPercentages) {
          String cellContent;
          float fontSize;
          HorizontalAlignment alignment;
          Color textColor;

          if (count == 0) {
            cellContent = entry.getKey();
            fontSize = SystemConstant.STD_FONT_SUBSCRIPT;
            alignment = HorizontalAlignment.LEFT;
            textColor = Color.lightGray;
          } else {
            cellContent = entry.getValue();
            fontSize = SystemConstant.STD_FONT_BASE;
            alignment = HorizontalAlignment.RIGHT;
            textColor = Color.black;
          }

          Cell<PDPage> cell = row.createCell(
            width,
            cellContent,
            alignment,
            VerticalAlignment.MIDDLE
          );

          cell.setFont(font);
          cell.setTextColor(textColor);
          cell.setFontSize(fontSize);
          cell.setBorderStyle(lineStyle);
          cell.setHeight(cell.getTextHeight() + SystemConstant.TABLE_CELL_PADDING);

          count++;
        }
      }

      table.draw();

      element = new PdfElement(table, xStart, yStart);

    } catch (Exception exception) {
      exception.printStackTrace();
    }

    return element;
  }

  public PdfElement addSummaryTitle(PDDocument document) {
    PDPage page = new PDPage(PDRectangle.A4);
    document.addPage(page);
    PDFont font = SystemConstant.STD_FONT;

    float marginBetweenYElements = 22f;
    float maxHeight = 3 * SystemConstant.MAX_TITLE_HEIGHT;
    float maxWidth = PDRectangle.A4.getWidth() - (2 * SystemConstant.PDF_PADDING);
    float xStart = SystemConstant.PDF_PADDING;
    float yStart = PDRectangle.A4.getHeight() - SystemConstant.PDF_PADDING;
    float calculatedHeight = 0;

    PdfElement element = new PdfElement(xStart, yStart, maxWidth, maxHeight);

    try {
      PDPageContentStream contentStream = new PDPageContentStream(
        document,
        page,
        PDPageContentStream.AppendMode.APPEND,
        true,
        true
      );

      PageContentStreamOptimized optimizedStream = new PageContentStreamOptimized(contentStream);

      PDStreamUtils.write(optimizedStream, "Automated System Testing", font, SystemConstant.STD_FONT_TITLE, xStart, yStart, Color.black);

      calculatedHeight += FontUtils.getHeight(font, SystemConstant.STD_FONT_TITLE);
      calculatedHeight += (marginBetweenYElements * 1.10);
      yStart -= (marginBetweenYElements * 1.10);

      String productVersion = PropertyManager.getProperty(PropertyFolder.CLIENT, "client", "productVersion");
      PDStreamUtils.write(optimizedStream, "Version tested: " + productVersion, font, SystemConstant.STD_FONT_SUB_TITLE, xStart, yStart, Color.black);

      calculatedHeight += FontUtils.getHeight(font, SystemConstant.STD_FONT_SUB_TITLE);
      calculatedHeight += marginBetweenYElements;
      yStart -= marginBetweenYElements;

      PDStreamUtils.write(optimizedStream, "Test Summary", font, SystemConstant.STD_FONT_SUB_TITLE, xStart, yStart, Color.black);

      optimizedStream.close();

      calculatedHeight += FontUtils.getHeight(font, SystemConstant.STD_FONT_SUB_TITLE);
      calculatedHeight = (float) (Math.ceil(calculatedHeight / 10) * 10);

      element = new PdfElement(xStart, yStart, maxWidth, calculatedHeight);

    } catch (Exception exception) {
      exception.printStackTrace();
    }

    return element;
  }

  public PdfElement addCloudSummaryTable(PDDocument document, ISuite suite, PdfElement summary) {
    PDPageTree pages = document.getPages();
    PDPage page = pages.get(pages.getCount() - 1);

    float maxWidth = PDRectangle.A4.getWidth() - (2 * SystemConstant.PDF_PADDING);
    float xStart = summary.getLeftBottomWithYMargin().x;
    float yStart = summary.getLeftBottomWithYMargin().y - SystemConstant.STD_PADDING_LRG;

    PdfElement element = new PdfElement(xStart, yStart, maxWidth, SystemConstant.PDF_FAILED_ELEMENT_HEIGHT);

    try {
      BaseTable table = new BaseTable(
        yStart,
        yStart,
        SystemConstant.PDF_ELEMENT2ELEMENT_MARGIN,
        maxWidth,
        xStart,
        document,
        page,
        true,
        true
      );

      table.setLineSpacing(SystemConstant.TABLE_LINE_SPACING);
      Map<String, String> resultMapOne = new LinkedHashMap<>();
      resultMapOne.put("TEST SUITE", context.suiteName);

      int[] cellWidths = new int[]{150, 250};
      for (Map.Entry<String, String> entry : resultMapOne.entrySet()) {
        createDoubleCellTableRow(
          cellWidths,
          entry,
          table
        );
      }

      List<String> testNames = new ArrayList<>(suite.getResults().keySet());

      for (String testName: testNames) {
        Map<String, List<ITestResult>> resultMapTwo = new HashMap<>();
        ISuiteResult testResults = suite.getResults().get(testName);

        List<ITestResult> allTestResults = new ArrayList<>(testResults.getTestContext().getPassedTests().getAllResults());
          allTestResults.addAll(testResults.getTestContext().getFailedTests().getAllResults());
          allTestResults.addAll(testResults.getTestContext().getFailedButWithinSuccessPercentageTests().getAllResults());
          allTestResults.addAll(testResults.getTestContext().getSkippedTests().getAllResults());

        Map<String, List<ITestResult>> groupedTestResultMap = allTestResults.stream()
          .collect(Collectors.groupingBy(ITestResult::getInstanceName));

        HashMap<String, HashMap<String, String>> suiteResultMap = ReportingUtils.createTestResultMap(
          groupedTestResultMap,
          context
        );

        List<String> keySetList = new ArrayList<>(suiteResultMap.keySet());
        Collections.reverse(keySetList);

        for (String key : keySetList) {
          HashMap<String, String> suiteEntry = suiteResultMap.get(key);

          createSingleCellTableRow(
            table,
            key,
            SystemConstant.STD_FONT_SUBSCRIPT
          );

          for (Map.Entry<String, String> testEntry : suiteEntry.entrySet()) {
            createDoubleCellTableRow(
              cellWidths,
              testEntry,
              table
            );
          }
        }
      }

      table.draw();

      element = new PdfElement(table, xStart, yStart);

    } catch (Exception exception) {
      exception.printStackTrace();
    }

    return element;
  }

  public PdfElement addXmlSummaryTable(PDDocument document, ISuite suite, PdfElement summary) {
    PDPageTree pages = document.getPages();
    PDPage page = pages.get(pages.getCount() - 1);

    float maxWidth = PDRectangle.A4.getWidth() - (2 * SystemConstant.PDF_PADDING);
    float xStart = summary.getLeftBottomWithYMargin().x;
    float yStart = summary.getLeftBottomWithYMargin().y - SystemConstant.STD_PADDING_LRG;

    PdfElement element = new PdfElement(xStart, yStart, maxWidth, SystemConstant.PDF_FAILED_ELEMENT_HEIGHT);

    try {
      BaseTable table = new BaseTable(
        yStart,
        yStart,
        SystemConstant.PDF_ELEMENT2ELEMENT_MARGIN,
        maxWidth,
        xStart,
        document,
        page,
        true,
        true
      );

      table.setLineSpacing(SystemConstant.TABLE_LINE_SPACING);

      Map<String, String> resultMapOne = new LinkedHashMap<>();
      resultMapOne.put("TEST SUITE", context.suiteName);

      int[] cellWidths = new int[]{150, 250};
      for (Map.Entry<String, String> entry : resultMapOne.entrySet()) {
        createDoubleCellTableRow(
          cellWidths,
          entry,
          table
        );
      }

      Map<String, ISuiteResult> resultMapTwo = suite.getResults();
      HashMap<String, HashMap<String, String>> suiteResultMap = ReportingUtils.createSuiteResultMap(
        resultMapTwo
      );

      List<String> keySetList = new ArrayList<>(suiteResultMap.keySet());
      Collections.reverse(keySetList);

      for (String key : keySetList) {
        HashMap<String, String> suiteEntry = suiteResultMap.get(key);


        createSingleCellTableRow(
          table,
          key,
          SystemConstant.STD_FONT_SUBSCRIPT
        );

        for (Map.Entry<String, String> testEntry : suiteEntry.entrySet()) {
          createDoubleCellTableRow(
            cellWidths,
            testEntry,
            table
          );
        }
      }

      table.draw();

      element = new PdfElement(table, xStart, yStart);

    } catch (Exception exception) {
      exception.printStackTrace();
    }

    return element;
  }

  public PdfElement addFooter(PDDocument document) {
    PDPageTree pages = document.getPages();
    PDFont font = SystemConstant.STD_FONT;

    float maxHeight = 3 * SystemConstant.MAX_TITLE_HEIGHT;
    float maxWidth = PDRectangle.A4.getWidth() - (2 * SystemConstant.PDF_PADDING);
    float xStartLeft = SystemConstant.PDF_PADDING;
    float xStartRight = PDRectangle.A4.getWidth() - SystemConstant.PDF_PADDING - SystemConstant.STD_PADDING_XL;
    float yStart = SystemConstant.STD_PADDING_XL;

    PdfElement element = new PdfElement(xStartLeft, yStart, maxWidth, maxHeight);

    int pageCounter = 1;
    for (PDPage page: pages) {
      try {
        PDPageContentStream contentStream = new PDPageContentStream(
          document,
          page,
          PDPageContentStream.AppendMode.APPEND,
          true,
          true
        );

        PageContentStreamOptimized optimizedStream = new PageContentStreamOptimized(contentStream);
        PDStreamUtils.write(optimizedStream, "iLab (Pty) Ltd - Accelerator - Automated Testing", font, SystemConstant.STD_FONT_BASE, xStartLeft, yStart, Color.black);
        PDStreamUtils.write(optimizedStream, String.format("Page %s", pageCounter), font, SystemConstant.STD_FONT_SUBSCRIPT, xStartRight, yStart, Color.black);

        optimizedStream.close();

      } catch (Exception exception) {
        exception.printStackTrace();
      }

      pageCounter++;
    }

    return element;
  }
}
