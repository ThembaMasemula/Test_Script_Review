package com.ilabquality.modules.reporting.utils;

import com.ilabquality.modules.global.entities.PdfElement;
import com.ilabquality.modules.global.reference.SystemConstant;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;

import java.nio.file.Path;

public class AddImages {

  public PdfElement addLogoFromClientFile(PDDocument document, Path imagePath) {
    PDPage page = document.getPages().get(0);
    float xStart = SystemConstant.PDF_PADDING;
    float yStart = PDRectangle.A4.getHeight() - SystemConstant.MAX_LOGO_HEIGHT - SystemConstant.PDF_PADDING;
    PdfElement element =  new PdfElement(xStart, yStart, SystemConstant.MAX_LOGO_WIDTH, SystemConstant.MAX_LOGO_HEIGHT);

    try {
      PDImageXObject pdImage = PDImageXObject.createFromFile(imagePath.toString(), document);
      PDPageContentStream contentStream = new PDPageContentStream(
        document,
        page,
        PDPageContentStream.AppendMode.APPEND,
        true,
        true
      );

      float imageWidth = pdImage.getWidth();
      float imageHeight = pdImage.getHeight();
      float scaleFactor = imageHeight / SystemConstant.MAX_LOGO_HEIGHT;

      contentStream.drawImage(
        pdImage,
        xStart,
        yStart,
        imageWidth / scaleFactor,
        imageHeight / scaleFactor
      );

      contentStream.close();

      element =  new PdfElement(xStart, yStart, imageWidth / scaleFactor, imageHeight / scaleFactor);

    } catch (Exception exception) {
      exception.printStackTrace();
    }

    return element;
  }

  public PdfElement addFromFile(PDDocument document, File file, PdfElement testHeader) {
    PDPageTree pages = document.getPages();
    PDPage page = pages.get(pages.getCount() - 1);

    float xStart = testHeader.getLeftBottom().x;
    float yStart = SystemConstant.PDF_PADDING;
    float maxWidth = PDRectangle.A4.getWidth() - (2 * SystemConstant.PDF_PADDING);
    float maxHeight = testHeader.getLeftBottomWithYMargin().y - SystemConstant.PDF_PADDING;
    PdfElement element =  new PdfElement(xStart, yStart, maxWidth, maxHeight);

    try {
      PDImageXObject pdImage = PDImageXObject.createFromFileByExtension(file, document);
      PDPageContentStream contentStream = new PDPageContentStream(
        document,
        page,
        PDPageContentStream.AppendMode.APPEND,
        true,
        true
      );

      float imageWidth = pdImage.getWidth();
      float imageHeight = pdImage.getHeight();
      float scaleYFactor = imageHeight / maxHeight;
      float scaleXFactor = imageWidth / maxWidth;
      float scaleFactor = Math.max(scaleYFactor, scaleXFactor);

      yStart = testHeader.getLeftBottom().y - (imageHeight / scaleFactor) - SystemConstant.STD_PADDING_LRG;

      contentStream.drawImage(
        pdImage,
        xStart,
        yStart,
        imageWidth / scaleFactor,
        imageHeight / scaleFactor
      );

      contentStream.close();

      element =  new PdfElement(xStart, yStart, imageWidth / scaleFactor, imageHeight / scaleFactor);

    } catch (Exception exception) {
      exception.printStackTrace();
    }

    return element;
  }
}
