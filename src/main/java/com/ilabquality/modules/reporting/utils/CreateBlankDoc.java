package com.ilabquality.modules.reporting.utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

public final class CreateBlankDoc {

  public PDDocument create(String fileName) {
    PDDocument doc = new PDDocument();

    try {
      PDPage blankPage = new PDPage(PDRectangle.A4);
      doc.addPage(blankPage);

      doc.save(fileName);

    } catch (Exception exception) {
      exception.printStackTrace();
    }

    return doc;
  }
}
