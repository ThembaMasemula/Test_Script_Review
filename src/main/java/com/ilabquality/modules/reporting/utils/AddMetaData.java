package com.ilabquality.modules.reporting.utils;

import com.ilabquality.modules.global.entities.MetaData;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.common.PDMetadata;

import org.apache.xmpbox.XMPMetadata;
import org.apache.xmpbox.schema.AdobePDFSchema;
import org.apache.xmpbox.schema.DublinCoreSchema;
import org.apache.xmpbox.schema.XMPBasicSchema;
import org.apache.xmpbox.xml.XmpSerializer;

import java.io.ByteArrayOutputStream;

import java.util.GregorianCalendar;

public final class AddMetaData {

  public AddMetaData(PDDocument document, MetaData metaData) {
    try {
      if (document.isEncrypted()) {
        System.err.println("Error: Cannot add metadata to encrypted document.");
        System.exit(1);
      }

      PDDocumentCatalog catalog = document.getDocumentCatalog();
      PDDocumentInformation info = document.getDocumentInformation();

      XMPMetadata metadata = XMPMetadata.createXMPMetadata();

      AdobePDFSchema pdfSchema = metadata.createAndAddAdobePDFSchema();
      pdfSchema.setKeywords(info.getKeywords());
      pdfSchema.setProducer(info.getProducer());

      XMPBasicSchema basicSchema = metadata.createAndAddXMPBasicSchema();
      basicSchema.setModifyDate(info.getModificationDate());
      basicSchema.setCreateDate(info.getCreationDate());
      basicSchema.setCreatorTool(info.getCreator());
      basicSchema.setMetadataDate(new GregorianCalendar());

      DublinCoreSchema dcSchema = metadata.createAndAddDublinCoreSchema();
      dcSchema.setTitle(info.getTitle());
      dcSchema.addCreator("iLab");
      dcSchema.setDescription(info.getSubject());

      PDMetadata metadataStream = new PDMetadata(document);
      catalog.setMetadata(metadataStream);

      XmpSerializer serializer = new XmpSerializer();
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      serializer.serialize(metadata, baos, false);
      metadataStream.importXMPMetadata(baos.toByteArray());

    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }
}
