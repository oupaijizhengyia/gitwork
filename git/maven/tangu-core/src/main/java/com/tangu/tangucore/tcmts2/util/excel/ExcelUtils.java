//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.tangu.tangucore.tcmts2.util.excel;

import com.tangu.common.util.StringUtil;
import com.tangu.tangucore.tcmts2.util.Constants;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.*;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import jxl.write.Number;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

public class ExcelUtils {
    private static final Logger logger = LoggerFactory.getLogger(ExcelUtils.class);
    private static int TITLE_FONT_SIZE = 18;
    private static int FONT_SIZE = 10;
    private static final String sign = ",";
    private static final String tg_empty = "tg_empty";

    public ExcelUtils() {
    }

    public static Workbook getWorkBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FileItem fileItem = null;
        RequestContext requestContext = new ServletRequestContext(request);
        if (FileUploadBase.isMultipartContent(requestContext)) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List items = null;

            try {
                items = upload.parseRequest(requestContext);
            } catch (Exception var10) {
                logger.error(var10.getMessage(), var10);
            }

            for(int i = 0; i < items.size(); ++i) {
                FileItem fi = (FileItem)items.get(i);
                if (!fi.isFormField()) {
                    fileItem = fi;
                    break;
                }
            }
        }

        Workbook wb = null;

        try {
            wb = Workbook.getWorkbook(fileItem.getInputStream());
        } catch (Exception var9) {
            logger.error("获取workbook失败: {}", var9);
        }

        return wb;
    }

    public static WritableWorkbook createWritableWorkBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/vnd.ms-excel");
        WritableWorkbook wb = Workbook.createWorkbook(response.getOutputStream());
        return wb;
    }

    public static Label createHeaderCell(int col, int row, String title) throws Exception {
        WritableFont font = new WritableFont(WritableFont.ARIAL, FONT_SIZE, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
        WritableCellFormat format = new WritableCellFormat(font);
        format.setVerticalAlignment(VerticalAlignment.TOP);
        Label cell = new Label(col, row, title, format);
        return cell;
    }

    public static Label createHeaderCell(int col, int row, String title, int fontSize) throws Exception {
        WritableFont font = new WritableFont(WritableFont.ARIAL, fontSize, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
        WritableCellFormat format = new WritableCellFormat(font);
        format.setVerticalAlignment(VerticalAlignment.TOP);
        Label cell = new Label(col, row, title, format);
        return cell;
    }

    public static Label createSheetTitleCell(int col, int row, String title) throws Exception {
        WritableFont font = new WritableFont(WritableFont.ARIAL, TITLE_FONT_SIZE, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
        WritableCellFormat format = new WritableCellFormat(font);
        format.setAlignment(Alignment.CENTRE);
        format.setVerticalAlignment(VerticalAlignment.TOP);
        Label cell = new Label(col, row, title, format);
        return cell;
    }

    public static Label createTitleCell(int col, int row, String title) throws Exception {
        WritableFont font = new WritableFont(WritableFont.ARIAL, FONT_SIZE, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
        WritableCellFormat format = new WritableCellFormat(font);
        format.setBorder(Border.ALL, BorderLineStyle.THIN);
        format.setWrap(true);
        format.setVerticalAlignment(VerticalAlignment.TOP);
        Label cell = new Label(col, row, title, format);
        return cell;
    }

    public static Label createLabelCell(int col, int row, String label, String alignment) throws Exception {
        WritableFont font = new WritableFont(WritableFont.ARIAL, FONT_SIZE, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
        WritableCellFormat format = new WritableCellFormat(font);
        format.setBorder(Border.ALL, BorderLineStyle.THIN);
        format.setWrap(true);
        if ("center".equalsIgnoreCase(alignment)) {
            format.setAlignment(Alignment.CENTRE);
            format.setVerticalAlignment(VerticalAlignment.CENTRE);
        } else {
            format.setVerticalAlignment(VerticalAlignment.TOP);
        }

        Label cell = new Label(col, row, label, format);
        return cell;
    }

    public static DateTime createDateCell(int col, int row, Date label, String alignment) throws Exception {
        WritableFont font = new WritableFont(WritableFont.ARIAL, FONT_SIZE, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
        WritableCellFormat format = new WritableCellFormat(font);
        format.setBorder(Border.ALL, BorderLineStyle.THIN);
        if ("center".equalsIgnoreCase(alignment)) {
            format.setAlignment(Alignment.CENTRE);
            format.setVerticalAlignment(VerticalAlignment.CENTRE);
        } else {
            format.setVerticalAlignment(VerticalAlignment.TOP);
        }

        DateTime cell = new DateTime(col, row, label, format);
        return cell;
    }

    public static Number createNumberCell(int col, int row, double label, String alignment) throws Exception {
        WritableFont font = new WritableFont(WritableFont.ARIAL, FONT_SIZE, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
        WritableCellFormat format = new WritableCellFormat(font);
        format.setBorder(Border.ALL, BorderLineStyle.THIN);
        format.setVerticalAlignment(VerticalAlignment.TOP);
        format.setAlignment(Alignment.LEFT);
        if ("center".equalsIgnoreCase(alignment)) {
            format.setAlignment(Alignment.CENTRE);
            format.setVerticalAlignment(VerticalAlignment.CENTRE);
        } else {
            format.setVerticalAlignment(VerticalAlignment.TOP);
        }

        Number cell = new Number(col, row, label, format);
        return cell;
    }

    public static Label createLabelCell(int col, int row, String label, int fontSize, String alignment) throws Exception {
        WritableFont font = new WritableFont(WritableFont.ARIAL, fontSize, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
        WritableCellFormat format = new WritableCellFormat(font);
        format.setBorder(Border.ALL, BorderLineStyle.THIN);
        format.setWrap(true);
        if ("center".equalsIgnoreCase(alignment)) {
            format.setAlignment(Alignment.CENTRE);
            format.setVerticalAlignment(VerticalAlignment.CENTRE);
        } else {
            format.setVerticalAlignment(VerticalAlignment.TOP);
        }

        Label cell = new Label(col, row, label, format);
        return cell;
    }

    public static String fillWorkbook(WritableWorkbook wb, ExcelExportVO para) throws Exception {
        String sheetTitle = new String();

        try {
            List<Object> objList = para.getData();
            List<String> fields = new ArrayList();
            List<String> fieldsLabels = new ArrayList();
            List<String> sheetHeaders = new ArrayList();
            List<String> footList = new ArrayList();
            List<String> sheetBottom = new ArrayList();
            List<String> sheetHeadersList = new ArrayList();
            List<String> sheetHeaderLabelList = new ArrayList();
            new ArrayList();
            List<String> sheetBottomList = new ArrayList();
            List<String> sheetBottomLabelList = new ArrayList();
            List<String> fieldSeparatorList = new ArrayList();
            List<String> fieldUnitList = new ArrayList();
            List<String> columnRowList = new ArrayList();
            List<String> btmTableLabeList = new ArrayList();
            List<String> btmTableFontSizeList = new ArrayList();
            List<String> btmTableFieldList = new ArrayList();
            List<String> sheetBtmTable = new ArrayList();
            List<String> informationList = new ArrayList();
            List<String> informationFontSizeList = new ArrayList();
            List<String> btmTotalFieldsList = new ArrayList();
            List<String> btmTotalLabelList = new ArrayList();
            String orientation = "portrait";
            String datePattern = "yyyy/MM/dd";
            String timePattern = "yyyy/MM/dd HH:mm:ss";
            String serialNumber = "yes";
            double leftMargin = 0.5D;
            double rightMargin = 0.5D;
            double topMargin = 0.5D;
            double bottomMargin = 0.5D;
            int bodyHeight = 0;
            int summaryHeight = 0;
            int serialCol = 1;
            boolean mergeCell = false;
            String exportUnique = "";
            String[] exportUniqueList = null;
            int titleHeight = 700;
            int headerHeight = 360;
            int bodyTitleHeight = 800;
            int bottomHeight = 300;
            int bottomTotalHeight = 400;
            int informationHeight = 300;
            boolean isPrintRegularHead = false;
            boolean isFullPaper = false;
            String alignment = "left";
            String regularHead = "no";
            String fullPaper = "no";
            String paperSize = "A4";
            String xmlString = para.xmlString;
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(xmlString)));
            NodeList widthNodeList = document.getElementsByTagName("width");
            NodeList fieldsNL = document.getElementsByTagName("fields");
            NodeList paperSizeNodeList;
            NodeList btmTotalLabelNodeList;
            if (xmlString != null && !"".equals(xmlString)) {
                try {
                    NodeList regularHeadNodeList = document.getElementsByTagName("regularHead");
                    if (regularHeadNodeList != null && regularHeadNodeList.item(0) != null) {
                        regularHead = regularHeadNodeList.item(0).getFirstChild().getNodeValue();
                        if (regularHead != null && "yes".equals(regularHead)) {
                            isPrintRegularHead = true;
                        }
                    }

                    NodeList fullPaperNodeList = document.getElementsByTagName("fullPaper");
                    if (fullPaperNodeList != null && fullPaperNodeList.item(0) != null) {
                        fullPaper = fullPaperNodeList.item(0).getFirstChild().getNodeValue();
                        if (fullPaper != null && "yes".equals(fullPaper)) {
                            isFullPaper = true;
                        }
                    }

                    NodeList alignmentNodeList = document.getElementsByTagName("alignment");
                    if (alignmentNodeList != null && alignmentNodeList.item(0) != null && alignmentNodeList.item(0).getFirstChild().getNodeValue() != null) {
                        alignment = alignmentNodeList.item(0).getFirstChild().getNodeValue();
                    }

                    NodeList pageOrientationNodeList = document.getElementsByTagName("page-orientation");
                    orientation = pageOrientationNodeList.item(0).getFirstChild().getNodeValue();
                    paperSizeNodeList = document.getElementsByTagName("serial-number");
                    if (paperSizeNodeList != null && paperSizeNodeList.item(0) != null) {
                        serialNumber = paperSizeNodeList.item(0).getFirstChild().getNodeValue();
                        if (serialNumber != null && "no".equals(serialNumber)) {
                            serialCol = 0;
                        }
                    }

                    NodeList fontSizeNodeList = document.getElementsByTagName("font-size");
                    if (fontSizeNodeList != null && fontSizeNodeList.item(0) != null && fontSizeNodeList.item(0).getFirstChild() != null) {
                        FONT_SIZE = NumberUtils.toInt(fontSizeNodeList.item(0).getFirstChild().getNodeValue());
                    } else {
                        FONT_SIZE = 10;
                    }

                    NodeList titleFontSizeNodeList = document.getElementsByTagName("title-font-size");
                    if (titleFontSizeNodeList != null && titleFontSizeNodeList.item(0) != null && titleFontSizeNodeList.item(0).getFirstChild() != null) {
                        TITLE_FONT_SIZE = NumberUtils.toInt(titleFontSizeNodeList.item(0).getFirstChild().getNodeValue());
                    } else {
                        TITLE_FONT_SIZE = 18;
                    }

                    NodeList datePatterNodeList = document.getElementsByTagName("body-date-pattern");
                    if (datePatterNodeList != null && datePatterNodeList.item(0) != null && datePatterNodeList.item(0).getFirstChild() != null) {
                        datePattern = datePatterNodeList.item(0).getFirstChild().getNodeValue().toString();
                    }

                    NodeList bodyHeightNodeList = document.getElementsByTagName("body-height");
                    if (bodyHeightNodeList != null && bodyHeightNodeList.item(0) != null && bodyHeightNodeList.item(0).getFirstChild() != null) {
                        bodyHeight = NumberUtils.toInt(bodyHeightNodeList.item(0).getFirstChild().getNodeValue()) * 20;
                    } else {
                        bodyHeight = 0;
                    }

                    NodeList summaryHeightNodeList = document.getElementsByTagName("summary-height");
                    if (summaryHeightNodeList != null && summaryHeightNodeList.item(0) != null && summaryHeightNodeList.item(0).getFirstChild() != null) {
                        summaryHeight = NumberUtils.toInt(summaryHeightNodeList.item(0).getFirstChild().getNodeValue()) * 20;
                    } else {
                        summaryHeight = bodyHeight;
                    }

                    NodeList exportUniqueNodeList = document.getElementsByTagName("export-unique");
                    if (exportUniqueNodeList != null && exportUniqueNodeList.item(0) != null && exportUniqueNodeList.item(0).getFirstChild() != null) {
                        exportUnique = exportUniqueNodeList.item(0).getFirstChild().getNodeValue().toString();
                        exportUniqueList = parasFields(exportUnique, ",");
                    }

                    NodeList leftMarginNodeList = document.getElementsByTagName("left-margin");
                    NodeList rightMarginNodeList = document.getElementsByTagName("right-margin");
                    NodeList topMarginNodeList = document.getElementsByTagName("top-margin");
                    NodeList bottomMarginNodeList = document.getElementsByTagName("bottom-margin");
                    if (leftMarginNodeList != null && leftMarginNodeList.item(0) != null && leftMarginNodeList.item(0).getFirstChild() != null) {
                        leftMargin = NumberUtils.toDouble(leftMarginNodeList.item(0).getFirstChild().getNodeValue().toString());
                    }

                    if (rightMarginNodeList != null && rightMarginNodeList.item(0) != null && rightMarginNodeList.item(0).getFirstChild() != null) {
                        rightMargin = NumberUtils.toDouble(rightMarginNodeList.item(0).getFirstChild().getNodeValue().toString());
                    }

                    if (topMarginNodeList != null && topMarginNodeList.item(0) != null && topMarginNodeList.item(0).getFirstChild() != null) {
                        topMargin = NumberUtils.toDouble(topMarginNodeList.item(0).getFirstChild().getNodeValue().toString());
                    }

                    if (bottomMarginNodeList != null && bottomMarginNodeList.item(0) != null && bottomMarginNodeList.item(0).getFirstChild() != null) {
                        bottomMargin = NumberUtils.toDouble(bottomMarginNodeList.item(0).getFirstChild().getNodeValue().toString());
                    }

                    NodeList titelNL = document.getElementsByTagName("titleLabel");
                    NodeList fieldsLabelNL = document.getElementsByTagName("fieldsLabel");
                    NodeList headerNodeList = document.getElementsByTagName("headerField");
                    NodeList headerLabeNL = document.getElementsByTagName("headerLabel");
                    NodeList footNodeList = document.getElementsByTagName("totalFields");
                    NodeList bottomNodeList = document.getElementsByTagName("bottomField");
                    NodeList bottomLabelNL = document.getElementsByTagName("bottomLabel");
                    NodeList fieldSeparatorNL = document.getElementsByTagName("field-separator");
                    NodeList fieldUnitNL = document.getElementsByTagName("field-unit");
                    NodeList columnRowNodeList = document.getElementsByTagName("column-row");
                    NodeList btmTableLabeNodeList = document.getElementsByTagName("btmtable-label");
                    NodeList btmTableFontSizeNodeList = document.getElementsByTagName("btmtable-font-size");
                    NodeList btmTableFieldNodeList = document.getElementsByTagName("btmtable-field");
                    NodeList informationNodeList = document.getElementsByTagName("information-label");
                    NodeList informationFontSizeNodeList = document.getElementsByTagName("information-font-size");
                    NodeList btmTotalNodeList = document.getElementsByTagName("btmtotal-fields");
                    btmTotalLabelNodeList = document.getElementsByTagName("btmtotal-label");
                    NodeList itemsNodeList = document.getElementsByTagName("items");
                    List itemsList = new ArrayList();
                    itemsList.addAll(getItemsList(itemsNodeList));
                    mergeCell = orderItems(itemsList);

                    int i;
                    for(i = 0; i < titelNL.getLength(); ++i) {
                        if (titelNL.item(i).getFirstChild() != null) {
                            sheetTitle = titelNL.item(i).getFirstChild().getNodeValue().toString();
                        }
                    }

                    for(i = 0; i < fieldsNL.getLength(); ++i) {
                        if (fieldsNL.item(i).getFirstChild() != null) {
                            ((List)fields).add(fieldsNL.item(i).getFirstChild().getNodeValue().toString());
                        }

                        if (fieldsLabelNL.item(i).getFirstChild() != null) {
                            ((List)fieldsLabels).add(fieldsLabelNL.item(i).getFirstChild().getNodeValue().toString());
                        }

                        if (widthNodeList.item(i).getFirstChild() != null) {
                            int var97 = NumberUtils.toInt(widthNodeList.item(i).getFirstChild().getNodeValue(), 10);
                        }

                        if (fieldSeparatorNL.item(i).getFirstChild() != null) {
                            fieldSeparatorList.add(fieldSeparatorNL.item(i).getFirstChild().getNodeValue().toString().replace("tg_empty", " "));
                        }

                        if (fieldUnitNL.item(i).getFirstChild() != null) {
                            fieldUnitList.add(fieldUnitNL.item(i).getFirstChild().getNodeValue().toString().replace("tg_empty", " "));
                        }
                    }

                    for(i = 0; i < headerNodeList.getLength(); ++i) {
                        if (headerNodeList.item(i).getFirstChild() != null) {
                            sheetHeadersList.add(i, headerNodeList.item(i).getFirstChild().getNodeValue());
                        }

                        if (headerLabeNL.item(i).getFirstChild() != null) {
                            sheetHeaderLabelList.add(i, headerLabeNL.item(i).getFirstChild().getNodeValue());
                        }
                    }

                    for(i = 0; i < footNodeList.getLength(); ++i) {
                        if (footNodeList.item(i).getFirstChild() != null) {
                            ((List)footList).add(i, footNodeList.item(i).getFirstChild().getNodeValue());
                        }
                    }

                    for(i = 0; i < bottomNodeList.getLength(); ++i) {
                        if (bottomNodeList.item(i).getFirstChild() != null) {
                            sheetBottomList.add(i, bottomNodeList.item(i).getFirstChild().getNodeValue());
                        }

                        if (bottomLabelNL.item(i).getFirstChild() != null) {
                            sheetBottomLabelList.add(i, bottomLabelNL.item(i).getFirstChild().getNodeValue());
                        }
                    }

                    for(i = 0; i < columnRowNodeList.getLength(); ++i) {
                        if (columnRowNodeList.item(i).getFirstChild() != null) {
                            columnRowList.add(i, columnRowNodeList.item(i).getFirstChild().getNodeValue());
                        }

                        if (btmTableLabeNodeList.item(i).getFirstChild() != null) {
                            btmTableLabeList.add(i, btmTableLabeNodeList.item(i).getFirstChild().getNodeValue());
                        }

                        if (btmTableFontSizeNodeList.item(i).getFirstChild() != null) {
                            btmTableFontSizeList.add(i, btmTableFontSizeNodeList.item(i).getFirstChild().getNodeValue());
                        }

                        if (btmTableFieldNodeList.item(i).getFirstChild() != null) {
                            btmTableFieldList.add(i, btmTableFieldNodeList.item(i).getFirstChild().getNodeValue());
                        }
                    }

                    for(i = 0; i < informationFontSizeNodeList.getLength(); ++i) {
                        if (informationFontSizeNodeList.item(i).getFirstChild() != null) {
                            informationFontSizeList.add(i, informationFontSizeNodeList.item(i).getFirstChild().getNodeValue());
                        }

                        if (informationNodeList.item(i).getFirstChild() != null) {
                            informationList.add(i, informationNodeList.item(i).getFirstChild().getNodeValue());
                        }
                    }

                    for(i = 0; i < btmTotalNodeList.getLength(); ++i) {
                        if (btmTotalNodeList.item(i).getFirstChild() != null) {
                            btmTotalFieldsList.add(i, btmTotalNodeList.item(i).getFirstChild().getNodeValue());
                        }

                        if (btmTotalLabelNodeList.item(i).getFirstChild() != null) {
                            btmTotalLabelList.add(i, btmTotalLabelNodeList.item(i).getFirstChild().getNodeValue());
                        }
                    }
                } catch (Exception var98) {
                    System.out.println("Exception" + var98);
                    return sheetTitle;
                }
            } else {
                fields = para.getFieldsList();
                fieldsLabels = para.getFieldsLabelList();
                sheetHeaders = (List)para.getHeaderList();
                footList = para.getFootList();
            }
            
            List sheetbtmTableList;
            int index;
            if (para.getHeaderList() != null) {
                ((List)sheetHeaders).addAll(topBottomList(para.getHeaderList(), sheetHeadersList, sheetHeaderLabelList));
                sheetBottom.addAll(topBottomList(para.getHeaderList(), sheetBottomList, sheetBottomLabelList));
                if (btmTableFieldList != null && btmTableFieldList.size() > 0) {
                    sheetbtmTableList = new ArrayList();
                    index = 0;
                    sheetbtmTableList.addAll(topBottomList(para.getHeaderList(), btmTableFieldList, (List)null));

                    for(int i = 0; i < btmTableFieldList.size(); ++i) {
                        if (!"".equals(((String) btmTableFieldList.get(i)).trim())) {
                            sheetBtmTable.add(i,(String)sheetbtmTableList.get(index));
                            ++index;
                        } else {
                            sheetBtmTable.add(i, "");
                        }
                    }
                }
            }

            sheetbtmTableList = new ArrayList();
            
            if(para.moreSheets){
            	sheetbtmTableList = para.getData();
    		}else{
    			sheetbtmTableList.add(objList);
    		}
            

            for(index = 0; index < sheetbtmTableList.size(); ++index) {
                String sheetName = "";
                if(para.isMoreSheets()){
                	sheetName = para.getMap().get(""+index).toString();
                	objList = new ArrayList();
    				objList = (List)sheetbtmTableList.get(index);
                }else{
                	sheetName = "sheet" + index;
                }
                WritableSheet sheet = wb.createSheet(sheetName, index);

                int currentRow;
                for(int i = 0; i < fieldsNL.getLength(); ++i) {
                    if (widthNodeList.item(i).getFirstChild() != null) {
                        currentRow = NumberUtils.toInt(widthNodeList.item(i).getFirstChild().getNodeValue(), 10);
                        sheet.setColumnView(i + serialCol, currentRow);
                    }
                }

                paperSizeNodeList = document.getElementsByTagName("paper-size");
                if (paperSizeNodeList != null && paperSizeNodeList.item(0) != null && paperSizeNodeList.item(0).getFirstChild() != null) {
                    paperSize = paperSizeNodeList.item(0).getFirstChild().getNodeValue().toString();
                    sheet.getSettings().setPaperSize(getPaperSize(paperSize));
                }

                int countHeaderHeight;
                for(currentRow = 0; currentRow < fieldsNL.getLength(); ++currentRow) {
                    if (widthNodeList.item(currentRow).getFirstChild() != null) {
                        countHeaderHeight = NumberUtils.toInt(widthNodeList.item(currentRow).getFirstChild().getNodeValue(), 10);
                        sheet.setColumnView(currentRow + serialCol, countHeaderHeight);
                    }
                }

                sheet.getSettings().setDefaultColumnWidth(10);
                if ("landscape".equals(orientation)) {
                    sheet.getSettings().setOrientation(PageOrientation.LANDSCAPE);
                }

                sheet.getSettings().setFitWidth(1);
                sheet.getSettings().getFooter().getCentre().appendPageNumber();
                sheet.getSettings().getFooter().getCentre().append("/");
                sheet.getSettings().getFooter().getCentre().appendTotalPages();
                sheet.getSettings().setLeftMargin(leftMargin);
                sheet.getSettings().setTopMargin(rightMargin);
                sheet.getSettings().setRightMargin(topMargin);
                sheet.getSettings().setBottomMargin(bottomMargin);
                sheet.mergeCells(0, 0, ((List)fields).size() - 1 + serialCol, 0);
                currentRow = 0;
                if (StringUtils.isNotBlank(sheetTitle)) {
                    sheet.addCell(createSheetTitleCell(0, currentRow++, sheetTitle));
                    if (isFullPaper) {
                        sheet.setRowView(currentRow - 1, titleHeight);
                    }
                }

                countHeaderHeight = 0;
                int tou;
                int i;
                if (sheetHeaders != null) {
                    StringBuilder headerSB = new StringBuilder();

                    for(tou = 0; tou < ((List)sheetHeaders).size(); ++tou) {
                        headerSB.append((String)((List)sheetHeaders).get(tou) + "    ");
                    }

                    String[] headerStrings = getStringArrayBySymbols(headerSB.toString());

                    for(i = 0; i < headerStrings.length; ++i) {
                        sheet.addCell(createHeaderCell(0, currentRow++, headerStrings[i]));
                        if (isFullPaper) {
                            sheet.setRowView(currentRow - 1, headerHeight);
                            countHeaderHeight += headerHeight;
                        }
                    }
                }

                int columnHeight1 = 0;
                int maxCoordinateRow;
                if (mergeCell) {
                    tou = 0;
                    if (columnRowList != null && columnRowList.size() > 0) {
                        for(i = 0; i < columnRowList.size(); ++i) {
                            String btmTableLabel = getNewStringByNewlineSymbols((String)btmTableLabeList.get(i));
                            btmTableLabel = btmTableLabel + (String)sheetBtmTable.get(i);
                            String[] coordinate = parasFields((String)columnRowList.get(i), ",");
                            if (coordinate != null) {
                                sheet.mergeCells(NumberUtils.toInt(coordinate[0]), currentRow + NumberUtils.toInt(coordinate[1]), NumberUtils.toInt(coordinate[2]), currentRow + NumberUtils.toInt(coordinate[3]));
                                if ("".equals((String) btmTableFontSizeList.get(i))) {
                                    sheet.addCell(createLabelCell(NumberUtils.toInt(coordinate[0]), currentRow + NumberUtils.toInt(coordinate[1]), btmTableLabel, alignment));
                                } else {
                                    maxCoordinateRow = NumberUtils.toInt((String)btmTableFontSizeList.get(i));
                                    sheet.addCell(createLabelCell(NumberUtils.toInt(coordinate[0]), currentRow + NumberUtils.toInt(coordinate[1]), btmTableLabel, maxCoordinateRow, alignment));
                                }

                                maxCoordinateRow = getMaxNum(NumberUtils.toInt(coordinate[1]), NumberUtils.toInt(coordinate[3]));
                                tou = getMaxNum(tou, maxCoordinateRow);
                            }
                        }

                        currentRow += tou + 2;
                        columnHeight1 = (tou + 1) * sheet.getSettings().getDefaultRowHeight();
                    }
                }

                if (isPrintRegularHead) {
                    if (mergeCell) {
                        sheet.getSettings().setPrintTitlesRow(0, currentRow);
                    } else {
                        sheet.getSettings().setPrintTitlesRow(0, 2);
                    }
                }

                if (serialCol == 1) {
                    sheet.addCell(createTitleCell(0, currentRow, "序号"));
                    sheet.setColumnView(0, 5);
                }

                for(tou = 0; tou < ((List)fieldsLabels).size(); ++tou) {
                    String title = getNewStringByNewlineSymbols((String)((List)fieldsLabels).get(tou));
                    sheet.addCell(createTitleCell(tou + serialCol, currentRow, title));
                    if (isFullPaper) {
                        sheet.setRowView(currentRow, bodyTitleHeight);
                    }
                }

                ++currentRow;
                tou = 0;
                int height = 0;
                boolean cpimtBodyRow = false;
                int btmTotalHeight = 0;
                maxCoordinateRow = 0;
                int countBottomHeight = 0;
                int countInformationHeight = 0;
                int maxRow;
                int printRow;
                int j;
                if (isFullPaper) {
                    if ("portrait".equalsIgnoreCase(orientation)) {
                        height = 15580;
                    } else {
                        height = 10500;
                    }

                    if (columnHeight1 == 0) {
                        tou = titleHeight + countHeaderHeight + bodyTitleHeight;
                    } else {
                        tou = titleHeight + countHeaderHeight + columnHeight1 + bodyTitleHeight;
                    }

                    StringBuffer strBuffer;
                    String[] sheetbottomStrings;
                    if (btmTotalFieldsList != null && btmTotalFieldsList.size() > 0 && objList != null) {
                        strBuffer = new StringBuffer();

                        for(printRow = 0; printRow < btmTotalFieldsList.size(); ++printRow) {
                            String btmTotalField = (String)btmTotalFieldsList.get(printRow);
                            double value = 0.0D;

                            for(int k = 0; k < objList.size(); ++k) {
                                Object obj = objList.get(k);
                                value += StringUtil.forceToDouble(getProperty(obj, btmTotalField).toString());
                            }

                            strBuffer.append((String)btmTotalLabelList.get(printRow)).append(removeTailZero(String.valueOf(value))).append("    ");
                        }

                        sheetbottomStrings = getStringArrayBySymbols(strBuffer.toString());

                        for(j = 0; j < sheetbottomStrings.length; ++j) {
                            btmTotalHeight += bottomTotalHeight;
                        }
                    }

                    if (!mergeCell) {
                        maxRow = 0;
                        if (columnRowList != null && columnRowList.size() > 0) {
                            for(printRow = 0; printRow < columnRowList.size(); ++printRow) {
                                String[] coordinate = parasFields((String)columnRowList.get(printRow), ",");
                                if (coordinate != null) {
                                    maxCoordinateRow = getMaxNum(NumberUtils.toInt(coordinate[1]), NumberUtils.toInt(coordinate[3]));
                                    maxRow = getMaxNum(maxRow, maxCoordinateRow);
                                }
                            }

                            maxCoordinateRow = maxRow * 255;
                        }
                    }

                    if (sheetBottomList != null) {
                        strBuffer = new StringBuffer();

                        for(printRow = 0; printRow < sheetBottom.size(); ++printRow) {
                            strBuffer.append((String)sheetBottom.get(printRow) + "    ");
                        }

                        sheetbottomStrings = getStringArrayBySymbols(strBuffer.toString());

                        for(j = 0; j < sheetbottomStrings.length; ++j) {
                            countBottomHeight += bottomHeight;
                        }
                    }

                    if (informationList != null) {
                        for(maxRow = 0; maxRow < informationList.size(); ++maxRow) {
                            countInformationHeight += informationHeight;
                        }
                    }
                }

                maxRow = btmTotalHeight + maxCoordinateRow + countBottomHeight + countInformationHeight + summaryHeight;
                printRow = 0;
                j = 0;
                maxCoordinateRow = 0;
                int yu = 0;
                boolean isPrintHead = false;
                int currentHeight;
                int ryu;
                String field;
                String separator;
                String title;
                Object vaObject;
                if (objList != null) {
                    currentHeight = 0;
                    int ntou = height - tou - 20;
                    if (isFullPaper) {
                        int cpimtBodyRows = objList.size();
                        j = cpimtBodyRows * bodyHeight;
                        yu = height - tou - maxRow - j - 20;
                        if (yu < 0) {
                            printRow = ntou / bodyHeight;
                        }
                    }

                    for(ryu = 0; ryu < objList.size(); ++ryu) {
                        if (serialCol == 1) {
                            sheet.addCell(createNumberCell(0, currentRow, (double)(ryu + 1), ""));
                        }

                        Object obj = objList.get(ryu);

                        for(j = 0; j < ((List)fields).size(); ++j) {
                            field = (String)((List)fields).get(j);
                            if(Constants.EMPTY_FIELD.equals(field)){
                            	sheet.addCell(createLabelCell(j + serialCol, currentRow, "", alignment));
                            	continue;
                            }
                            separator = (String)fieldSeparatorList.get(j);
                            title = (String)fieldUnitList.get(j);
                            String[] fieldArray = parasFields(field, ",");
                            String[] unitArray = parasFields(title, ",");
                            if (fieldArray != null) {
                                vaObject = "";
                                int k;
                                if (unitArray == null) {
                                    unitArray = new String[fieldArray.length];

                                    for(k = 0; k < unitArray.length; ++k) {
                                        unitArray[k] = "";
                                    }
                                }

                                for(k = 0; k < fieldArray.length; ++k) {
                                    if (fieldArray[k] != null) {
                                        Object property = getProperty(obj, fieldArray[k]);
                                        String converStr = "".equals(converString(property, datePattern)) ? "" : converString(property, datePattern) + unitArray[k].toString();
                                        if (k == fieldArray.length - 1) {
                                            vaObject = vaObject.toString() + converStr;
                                        } else {
                                            vaObject = vaObject.toString() + converStr + getNewlineSymbols(separator);
                                        }
                                    }
                                }

                                if (vaObject.toString().substring(vaObject.toString().length() - 1).equals(separator)) {
                                    vaObject = vaObject.toString().substring(0, vaObject.toString().length() - 1);
                                } else if (vaObject.toString().substring(0, 1).equals(separator)) {
                                    vaObject = vaObject.toString().substring(1);
                                }

                                sheet.addCell(createLabelCell(j + serialCol, currentRow, vaObject.toString(), alignment));
                            } else {
                                btmTotalLabelNodeList = null;
                                vaObject = getProperty(obj, field);
                                if (exportUniqueList != null) {
                                    Object uniqie = getProperty(obj, exportUniqueList[0]);
                                    if (uniqie == null) {
                                        uniqie = "";
                                    }

                                    for(int k = 1; k < exportUniqueList.length; ++k) {
                                        if (field.equals(exportUniqueList[k])) {
                                            Object va = getProperty(obj, field);
                                            if (uniqie.equals(va)) {
                                                vaObject = null;
                                                break;
                                            }
                                        }
                                    }
                                }

                                if (vaObject == null) {
                                    vaObject = "";
                                }

                                if (isInstance(vaObject, Date.class)) {
                                    sheet.addCell(createLabelCell(j + serialCol, currentRow, converDateToString((Date)vaObject, timePattern), alignment));
                                } else if (!isInstance(vaObject, Double.TYPE) && !isInstance(vaObject, Float.TYPE) && !isInstance(vaObject, Double.class) && !isInstance(vaObject, Float.class) && !isInstance(vaObject, BigDecimal.class)) {
                                    sheet.addCell(createLabelCell(j + serialCol, currentRow, vaObject.toString(), alignment));
                                } else if ("0".equals(removeTailZero(vaObject))) {
                                    vaObject = "";
                                    sheet.addCell(createLabelCell(j + serialCol, currentRow, vaObject.toString(), alignment));
                                } else {
                                    sheet.addCell(createNumberCell(j + serialCol, currentRow, NumberUtils.toDouble(vaObject.toString()), ""));
                                }
                            }
                        }

                        if (bodyHeight > 0) {
                            sheet.setRowView(currentRow, bodyHeight);
                        }

                        if (isFullPaper) {
                            ++currentHeight;
                            if (currentRow - 2 != objList.size()) {
                                if (isPrintRegularHead) {
                                    if (currentHeight + 1 > printRow && printRow > 0) {
                                        sheet.addRowPageBreak(currentRow + 1);
                                        currentHeight = 0;
                                        ++maxCoordinateRow;
                                    }
                                } else if (isPrintHead) {
                                    if (currentHeight + 1 > (height - 20) / bodyHeight && printRow > 0) {
                                        sheet.addRowPageBreak(currentRow + 1);
                                        currentHeight = 0;
                                        ++maxCoordinateRow;
                                    }
                                } else if (currentHeight + 1 > printRow && printRow > 0) {
                                    sheet.addRowPageBreak(currentRow + 1);
                                    currentHeight = 0;
                                    ++maxCoordinateRow;
                                    isPrintHead = true;
                                }
                            }
                        }

                        ++currentRow;
                    }
                }

                boolean surplusList = false;
                boolean flage = false;
                ryu = 0;
                int y;
                if (isFullPaper) {
                    int x = 0;
                    if (yu > 0) {
                        ryu = height - x - tou - maxRow;
                    } else if (maxCoordinateRow == 0) {
                        ryu = height - tou - maxRow - x;
                        if (ryu <= 0) {
                            x = (height - tou - x - 20) / bodyHeight;

                            for(x = 0; x < x; ++j) {
                                if (serialCol == 1) {
                                    sheet.addCell(createLabelCell(0, currentRow, "", alignment));
                                }

                                for(y = 0; y < ((List)fieldsLabels).size(); ++y) {
                                    sheet.addCell(createLabelCell(y + serialCol, currentRow, "", alignment));
                                    sheet.setRowView(currentRow, bodyHeight);
                                }

                                ++currentRow;
                            }
                        }

                        ryu = height - tou - maxRow;
                        flage = true;
                    } else {
                        x = x - maxCoordinateRow * printRow * bodyHeight;
                        if (isPrintRegularHead) {
                            ryu = height - x - tou - maxRow;
                            if (ryu < 0) {
                                ryu = height - x - tou;
                                x = (ryu - 20) / bodyHeight;

                                for(y = 0; y < x; ++y) {
                                    if (serialCol == 1) {
                                        sheet.addCell(createLabelCell(0, currentRow, "", alignment));
                                    }

                                    for(y = 0; y < ((List)fieldsLabels).size(); ++y) {
                                        sheet.addCell(createLabelCell(y + serialCol, currentRow, "", alignment));
                                        sheet.setRowView(currentRow, bodyHeight);
                                    }

                                    ++currentRow;
                                }

                                ryu = height - tou - maxRow;
                                flage = true;
                            }
                        } else {
                            x = x - printRow * bodyHeight - (maxCoordinateRow - 1) * ((height - 20) / bodyHeight) * bodyHeight;
                            ryu = height - x - maxRow;
                            if (ryu < 0) {
                                ryu = height - x;
                                x = (ryu - 20) / bodyHeight;

                                for(y = 0; y < x; ++y) {
                                    if (serialCol == 1) {
                                        sheet.addCell(createLabelCell(0, currentRow, "", alignment));
                                    }

                                    for(y = 0; y < ((List)fieldsLabels).size(); ++y) {
                                        sheet.addCell(createLabelCell(y + serialCol, currentRow, "", alignment));
                                        sheet.setRowView(currentRow, bodyHeight);
                                    }

                                    ++currentRow;
                                }

                                flage = true;
                                ryu = 0;
                            }
                        }
                    }

                    currentHeight = (ryu - 20) / bodyHeight;
                    if (flage) {
                        sheet.addRowPageBreak(currentRow + 1);
                    }

                    if (currentHeight != 0 && currentHeight > 0) {
                        for(x = 0; x < currentHeight; ++x) {
                            if (serialCol == 1) {
                                sheet.addCell(createLabelCell(0, currentRow, "", alignment));
                            }

                            for(x = 0; x < ((List)fieldsLabels).size(); ++x) {
                                sheet.addCell(createLabelCell(x + serialCol, currentRow, "", alignment));
                                sheet.setRowView(currentRow, bodyHeight);
                            }

                            ++currentRow;
                        }
                    }
                }

                List<Integer> totalIndexList = new ArrayList();
                if (footList != null && ((List)footList).size() > 0) {
                    if (xmlString != null && !"".equals(xmlString)) {
                        j = 0;

                        while(true) {
                            if (j >= ((List)footList).size()) {
                                List<Double> totaList = new ArrayList();

                                for(i = 0; i < totalIndexList.size(); ++i) {
                                    totaList.add(i, StringUtil.forceToDouble("0"));
                                }

                                int x;
                                if (objList != null) {
                                    for(i = 0; i < objList.size(); ++i) {
                                        Object obj = objList.get(i);

                                        for(x = 0; x < totalIndexList.size(); ++x) {
                                            index = ((Integer)totalIndexList.get(x)).intValue();
                                            field = (String)((List)fields).get(index);
                                            vaObject = getProperty(obj, field);
                                            if (vaObject != null) {
                                                vaObject = vaObject.toString();
                                                if (i == 0) {
                                                    totaList.remove(x);
                                                    totaList.add(x, StringUtil.forceToDouble(vaObject.toString()));
                                                    totaList.set(x, fromartNum(((Double)totaList.get(x)).doubleValue(), para.getDecimalPointIndex()));
                                                } else {
                                                    double value = ((Double)totaList.get(x)).doubleValue();
                                                    totaList.remove(x);
                                                    totaList.add(x, value + StringUtil.forceToDouble(vaObject.toString()));
                                                    totaList.set(x, fromartNum(((Double)totaList.get(x)).doubleValue(), para.getDecimalPointIndex()));
                                                }
                                            }
                                        }
                                    }
                                }

                                if (serialCol == 1) {
                                    sheet.addCell(createLabelCell(0, currentRow, "", alignment));
                                }

                                sheet.addCell(createLabelCell(0 + serialCol, currentRow, "合计", alignment));

                                for(i = 1; i < ((List)fieldsLabels).size(); ++i) {
                                    boolean isSum = false;

                                    for(j = 0; j < totalIndexList.size(); ++j) {
                                        if (((Integer)totalIndexList.get(j)).intValue() == i) {
                                            if (NumberUtils.toDouble(((Double)totaList.get(j)).toString()) != 0.0D) {
                                                sheet.addCell(createNumberCell(i + serialCol, currentRow, NumberUtils.toDouble(((Double)totaList.get(j)).toString()), alignment));
                                            } else {
                                                sheet.addCell(createLabelCell(i + serialCol, currentRow, "", alignment));
                                            }

                                            isSum = true;
                                            break;
                                        }
                                    }

                                    if (!isSum) {
                                        sheet.addCell(createLabelCell(i + serialCol, currentRow, " ", alignment));
                                    }
                                }

                                sheet.setRowView(currentRow, summaryHeight);
                                ++currentRow;
                                break;
                            }

                            field = (String)((List)footList).get(j);

                            for(i = 0; i < ((List)fields).size(); ++i) {
                                title = (String)((List)fields).get(i);
                                if (field.equals(title)) {
                                    totalIndexList.add(i);
                                    break;
                                }
                            }

                            ++j;
                        }
                    } else {
                        for(j = 0; j < ((List)footList).size(); ++j) {
                            sheet.addCell(createLabelCell(0, currentRow++, (String)((List)footList).get(j), alignment));
                        }
                    }
                }

                StringBuffer strBuffer;
                String[] sheetbottomStrings;
                if (btmTotalFieldsList != null && btmTotalFieldsList.size() > 0 && objList != null) {
                    strBuffer = new StringBuffer();
                    i = 0;

                    label912:
                    while(true) {
                        if (i >= btmTotalFieldsList.size()) {
                            sheetbottomStrings = getStringArrayBySymbols(strBuffer.toString());
                            i = 0;

                            while(true) {
                                if (i >= sheetbottomStrings.length) {
                                    break label912;
                                }

                                sheet.mergeCells(0, currentRow, ((List)fieldsLabels).size() - 1, currentRow);
                                sheet.addCell(createLabelCell(0, currentRow++, sheetbottomStrings[i], alignment));
                                if (isFullPaper) {
                                    sheet.setRowView(currentRow - 1, bottomTotalHeight);
                                }

                                ++i;
                            }
                        }

                        separator = (String)btmTotalFieldsList.get(i);
                        double value = 0.0D;

                        for(int k = 0; k < objList.size(); ++k) {
                            vaObject = objList.get(k);
                            value += StringUtil.forceToDouble(getProperty(vaObject, separator).toString());
                        }

                        strBuffer.append((String)btmTotalLabelList.get(i)).append(removeTailZero(String.valueOf(value))).append("    ");
                        ++i;
                    }
                }

                if (!mergeCell) {
                    j = 0;
                    if (columnRowList != null && columnRowList.size() > 0) {
                        for(i = 0; i < columnRowList.size(); ++i) {
                            separator = getNewStringByNewlineSymbols((String)btmTableLabeList.get(i));
                            separator = separator + (String)sheetBtmTable.get(i);
                            String[] coordinate = parasFields((String)columnRowList.get(i), ",");
                            if (coordinate != null) {
                                sheet.mergeCells(NumberUtils.toInt(coordinate[0]), currentRow + NumberUtils.toInt(coordinate[1]), NumberUtils.toInt(coordinate[2]), currentRow + NumberUtils.toInt(coordinate[3]));
                                if ("".equals((String) btmTableFontSizeList.get(i))) {
                                    sheet.addCell(createLabelCell(NumberUtils.toInt(coordinate[0]), currentRow + NumberUtils.toInt(coordinate[1]), separator, alignment));
                                } else {
                                    index = NumberUtils.toInt((String)btmTableFontSizeList.get(i));
                                    sheet.addCell(createLabelCell(NumberUtils.toInt(coordinate[0]), currentRow + NumberUtils.toInt(coordinate[1]), separator, index, alignment));
                                }

                                index = getMaxNum(NumberUtils.toInt(coordinate[1]), NumberUtils.toInt(coordinate[3]));
                                j = getMaxNum(j, index);
                            }
                        }

                        currentRow += j + 1;
                    }
                }

                if (sheetBottomList != null) {
                    strBuffer = new StringBuffer();

                    for(i = 0; i < sheetBottom.size(); ++i) {
                        strBuffer.append((String)sheetBottom.get(i) + "    ");
                    }

                    sheetbottomStrings = getStringArrayBySymbols(strBuffer.toString());

                    for(i = 0; i < sheetbottomStrings.length; ++i) {
                        sheet.addCell(createHeaderCell(0, currentRow++, sheetbottomStrings[i]));
                        if (isFullPaper) {
                            sheet.setRowView(currentRow - 1, bottomHeight);
                        }
                    }
                }

                if (informationList != null) {
                    for(j = 0; j < informationList.size(); ++j) {
                        if (informationFontSizeList != null && informationFontSizeList.get(j) != null && !"".equals(((String) informationFontSizeList.get(j)).trim())) {
                            sheet.addCell(createHeaderCell(0, currentRow, (String)informationList.get(j), NumberUtils.toInt((String)informationFontSizeList.get(j))));
                        } else {
                            sheet.addCell(createHeaderCell(0, currentRow, (String)informationList.get(j)));
                        }

                        if (isFullPaper) {
                            sheet.setRowView(currentRow, informationHeight);
                        }

                        ++currentRow;
                    }
                }
            }
        } catch (Exception var99) {
            logger.error(var99.getMessage(), var99);
        }

        return sheetTitle;
    }

    private static List<Object> List(Object object) {
        return null;
    }

    public static void main(String[] args) throws Exception {
        String value = getStaticProperty("PaperSize", "A4").toString();
    }

    public static Object invokePaperSize(String methodName, Object[] args) throws Exception {
        Class ownerClass = Class.forName("PaperSize");
        Class[] argsClass = new Class[args.length];
        int i = 0;

        for(int j = args.length; i < j; ++i) {
            argsClass[i] = args[i].getClass();
        }

        Method method = ownerClass.getMethod(methodName, argsClass);
        return method.invoke(ownerClass, args);
    }

    public static Object getProperty(Object owner, String fieldName) throws Exception {
        if (fieldName != null && fieldName.startsWith("[") && fieldName.endsWith("]")) {
            return fieldName.replace("[", "").replace("]", "");
        } 
        /*支持公式+ - * / ( ) */
		String[] fieldArray=FormulaParser.parseFieldArray(fieldName);
		if(fieldArray!=null&&fieldArray.length>1){
			String formula=fieldName;
			for(int i=0;i<fieldArray.length;i++){
				String field=fieldArray[i];
				String getter = "get" + StringUtils.capitalize(field);
				Object obj=invokeMethod(owner, getter, new Object[0]);
				String value="";
				if(obj instanceof BigDecimal){
					value=""+((BigDecimal)obj).doubleValue();
				}else{
					value=""+obj;
				}
				formula=formula.replace(field, value);
			}
			OperationStringModel osm = new OperationStringModel();
			return osm.operationResult(formula);
		}
        String getter,getters;
        if (fieldName.indexOf("[") == 0 && fieldName.indexOf("]") > 0 && fieldName.indexOf("]") < fieldName.length() - 1) {
            getter = fieldName.substring(fieldName.indexOf("]") + 1);
            getters = "get" + StringUtils.capitalize(getter);
            Object obj = invokeMethod(owner, getters, new Object[0]);
            String repStr = fieldName.substring(fieldName.indexOf("[") + 1, fieldName.indexOf("]"));
            String[] repList = repStr.split("#");
            List<String> prefixList = new ArrayList();
            List<String> excludeList = new ArrayList();
            List<String> replacepList = new ArrayList();

            int j;
            String repKey;
            String excludeStr;
            for(j = 0; j < repList.length; ++j) {
                repKey = repList[j].substring(0, repList[j].indexOf("="));
                excludeStr = repList[j].substring(repList[j].indexOf("=") + 1);
                if ("prefix".equals(repKey)) {
                    prefixList.add(excludeStr);
                } else if ("exclude".equals(repKey)) {
                    excludeList.add(excludeStr);
                } else {
                    replacepList.add(repList[j]);
                }
            }

            for(j = 0; j < replacepList.size(); ++j) {
                repKey = ((String)replacepList.get(j)).substring(0, ((String)replacepList.get(j)).indexOf("="));
                excludeStr = ((String)replacepList.get(j)).substring(((String)replacepList.get(j)).indexOf("=") + 1);
                String objStr = "" + obj;
                if (objStr.equals(repKey)) {
                    return excludeStr;
                }
            }

            if (prefixList.size() > 0) {
                String objStr = (String)prefixList.get(0) + removeTailZero(obj);

                for(int k = 0; k < excludeList.size(); ++k) {
                    excludeStr = (String)excludeList.get(k);
                    if (removeTailZero(obj).equals(excludeStr)) {
                        return obj;
                    }
                }

                return objStr;
            } else {
                return obj;
            }
        } else {
            getters = "get" + StringUtils.capitalize(fieldName);
            return invokeMethod(owner, getters, new Object[0]);
        }
    }

    public static Object getStaticProperty(String className, String fieldName) throws Exception {
        Class ownerClass = Class.forName(className);
        Field field = ownerClass.getField(fieldName);
        Object property = field.get(ownerClass);
        return property;
    }

    public static Object invokeMethod(Object owner, String methodName, Object[] args) throws Exception {
        Class ownerClass = owner.getClass();
        Class[] argsClass = new Class[args.length];
        int i = 0;

        for(int j = args.length; i < j; ++i) {
            argsClass[i] = args[i].getClass();
        }

        Method method = ownerClass.getMethod(methodName, argsClass);
        return method.invoke(owner, args);
    }

    public static Object invokeStaticMethod(String className, String methodName, Object[] args) throws Exception {
        Class<?> ownerClass = Class.forName(className);
        Class<?>[] argsClass = new Class[args.length];
        int i = 0;

        for(int j = args.length; i < j; ++i) {
            argsClass[i] = args[i].getClass();
        }

        Method method = ownerClass.getMethod(methodName, argsClass);
        return method.invoke((Object)null, args);
    }

    public static Object newInstance(String className, Object[] args) throws Exception {
        Class<?> newoneClass = Class.forName(className);
        Class<?>[] argsClass = new Class[args.length];
        int i = 0;

        for(int j = args.length; i < j; ++i) {
            argsClass[i] = args[i].getClass();
        }

        Constructor<?> cons = newoneClass.getConstructor(argsClass);
        return cons.newInstance(args);
    }

    public static boolean isInstance(Object obj, Class<?> cls) {
        return cls.isInstance(obj);
    }

    public static Object getByArray(Object array, int index) {
        return Array.get(array, index);
    }

    public static String converDateToString(Date date, String datePattern) {
        SimpleDateFormat df = null;
        String returnValue = "";
        if (date != null) {
            df = new SimpleDateFormat(datePattern);
            returnValue = df.format(date);
        }

        return returnValue;
    }

    public static String converString(Object obj, String datePattern) {
        String returnValue = "";
        if (obj == null) {
            return returnValue;
        } else {
            if (isInstance(obj, Date.class)) {
                returnValue = converDateToString((Date)obj, datePattern);
            } else if (!isInstance(obj, Double.TYPE) && !isInstance(obj, Float.TYPE) && !isInstance(obj, Double.class) && !isInstance(obj, Float.class) && !isInstance(obj, BigDecimal.class)) {
                returnValue = obj.toString();
            } else {
                if ("0".equals(removeTailZero(obj))) {
                    return "";
                }

                returnValue = removeTailZero(BigDecimal.valueOf(NumberUtils.toDouble(obj.toString())));
            }

            return returnValue;
        }
    }

    public static String[] parasFields(String fields, String sign) {
        String[] fieldsList = fields.split(sign);
        return fieldsList.length > 1 ? fieldsList : null;
    }

    public static String[] parasFields_new(String fields, String sign) {
        String[] fieldsList = fields.split(sign);
        if (fieldsList.length == 1) {
            return !" ".equals(fieldsList[0]) && !"".equals(fieldsList[0]) ? fieldsList : null;
        } else {
            return fieldsList.length > 1 ? fieldsList : null;
        }
    }

    public static String removeTailZero(BigDecimal b) {
        String s = b.toString();
        int len = s.length();

        int i;
        for(i = 0; i < len && s.charAt(len - 1 - i) == '0'; ++i) {
            ;
        }

        return s.charAt(len - i - 1) == '.' ? s.substring(0, len - i - 1) : s.substring(0, len - i);
    }

    public static String removeTailZero(Object b) {
        if (isInstance(b, BigDecimal.class)) {
            b = NumberUtils.toDouble(b.toString());
        }

        String s = b.toString();
        int len = s.length();

        int i;
        for(i = 0; i < len && s.charAt(len - 1 - i) == '0'; ++i) {
            ;
        }

        return s.charAt(len - i - 1) == '.' ? s.substring(0, len - i - 1) : s.substring(0, len - i);
    }

    public static String removeTailZero(String s) {
        int len = s.length();

        int i;
        for(i = 0; i < len && s.charAt(len - 1 - i) == '0'; ++i) {
            ;
        }

        return s.charAt(len - i - 1) == '.' ? s.substring(0, len - i - 1) : s.substring(0, len - i);
    }

    public static int getMaxNum(int num1, int num2) {
        return num1 > num2 ? num1 : num2;
    }

    public static String getNewlineSymbols(String separator) {
        separator = separator.replace("\\n", "\n");
        return separator;
    }

    public static String getNewStringByNewlineSymbols(String label) {
        String newLabel = getNewlineSymbols(label);
        return newLabel;
    }

    public static String[] getStringArrayBySymbols(String label) {
        return label.split("\\\\n");
    }

    public static List getItemsList(NodeList itemsNodeList) {
        List itemsList = new ArrayList();

        for(int i = 0; i < itemsNodeList.getLength(); ++i) {
            Node item = itemsNodeList.item(i);
            itemsList.add(item.getAttributes().getNamedItem("name").getNodeValue().toString());
        }

        return itemsList;
    }

    public static boolean orderItems(List<?> itemList) {
        int body = 0;
        int bottomTable = 0;
        boolean bo = false;

        for(int i = 0; i < itemList.size(); ++i) {
            if ("body".equals(itemList.get(i))) {
                body = i;
            } else if ("bottomTable".equals(itemList.get(i))) {
                bottomTable = i;
            }
        }

        if (body > bottomTable) {
            bo = true;
        }

        return bo;
    }

    public static List<String> topBottomList(Object obj, List list, List labelList) {
        List<String> reList = new ArrayList();

        label50:
        for(int j = 0; j < list.size(); ++j) {
            HashMap headerHashMap = (HashMap)obj;
            Iterator iterator = headerHashMap.entrySet().iterator();

            while(true) {
                while(true) {
                    while(true) {
                        Object value;
                        Object key;
                        do {
                            if (!iterator.hasNext()) {
                                continue label50;
                            }

                            Entry entry = (Entry)iterator.next();
                            new Object();
                            if (entry.getValue() != null && !"".equals(entry.getValue().toString().trim())) {
                                value = entry.getValue();
                            } else {
                                value = "          ";
                            }

                            key = entry.getKey();
                        } while(!key.equals(list.get(j)));

                        if (labelList != null) {
                            if (value != null && !StringUtils.isBlank(value.toString())) {
                                reList.add(labelList.get(j) + "：" + value);
                            } else {
                                reList.add(labelList.get(j) + "");
                            }
                        } else {
                            reList.add(value.toString());
                        }
                    }
                }
            }
        }

        return reList;
    }

    public static double fromartNum(double num, int point) {
    	String temp=String.valueOf(num);
		if(temp.indexOf(".")==-1){
			return num;
		}
		if(point==0){
		 return Math.round(num);
		}
		int length=temp.substring(temp.indexOf('.')+1,temp.length()).length();
		if(temp.indexOf('.')==-1){
			return num;
			}else{
				if(isZero(num,point)){
					return Double.parseDouble(temp.substring(0,temp.indexOf('.')));
				}else{
					 BigDecimal bd = new BigDecimal(num);  
					 BigDecimal bd2 = bd.setScale(point, BigDecimal.ROUND_HALF_UP);  
					if(length>point){
						num=Double.parseDouble(String.valueOf(bd2).substring(0,String.valueOf(bd2).indexOf('.')+point+1));
					  }
				}
			}
		return num;
    }

    public static boolean isZero(double value, int num) {
        String temp = String.valueOf(value);
        String temps = temp.substring(temp.indexOf(46) + 1, temp.length());
        int length;
        if (temps.length() >= num) {
            temp = temp.substring(temp.indexOf(46) + 1, temp.indexOf(46) + 1 + num);
            length = num;
        } else {
            temp = temps;
            length = temps.length();
        }

        boolean isZero = true;

        for(int i = 0; i < length; ++i) {
            if (temp.toString().charAt(i) != '0') {
                isZero = false;
            }
        }

        return isZero;
    }

    public static PaperSize getPaperSize(String paperSize) {
        PaperSize ps = PaperSize.A4;
        if (paperSize != null && !"".equals(paperSize)) {
            if ("A4".equals(paperSize)) {
                ps = PaperSize.A4;
            } else if ("A2".equals(paperSize)) {
                ps = PaperSize.A2;
            } else if ("A3".equals(paperSize)) {
                ps = PaperSize.A3;
            } else if ("A5".equals(paperSize)) {
                ps = PaperSize.A5;
            } else if ("B5".equals(paperSize)) {
                ps = PaperSize.B5;
            } else if ("B5_EXTRA".equals(paperSize)) {
                ps = PaperSize.B5_EXTRA;
            }

            return ps;
        } else {
            return ps;
        }
    }
}
