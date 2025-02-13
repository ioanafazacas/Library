package model;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.stream.Stream;


public class PdfGenerator {
    /*public void generateReportPdf(String filePath, List<Report> reports) throws IOException {
        // Creează un writer pentru fișierul PDF
        PdfWriter writer = new PdfWriter(new File(filePath));
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        // Creează un tabel cu 3 coloane
        Table table = new Table(new float[]{4, 2, 2}); // Lățimi relative pentru coloane
        table.setWidthPercent(100); // Setează lățimea tabelului la 100% din pagină

        // Adaugă antetele coloanelor
        table.addHeaderCell(new Cell().add("Username"));
        table.addHeaderCell(new Cell().add("Nr. Cărți"));
        table.addHeaderCell(new Cell().add("Încăsări"));

        // Adaugă datele din listă
        for (Report report : reports) {
            table.addCell(new Cell().add(report.getUsername()));
            table.addCell(new Cell().add(String.valueOf(report.getNrCarti())));
            table.addCell(new Cell().add(String.valueOf(report.getIncasari())));
        }

        // Adaugă tabelul în document și închide documentul
        document.add(table);
        document.close();
        System.out.println("PDF generat cu succes la " + filePath);
    }*/

    public void generateReportPdf(Report report) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("reportTable.pdf"));

        document.open();

        PdfPTable table = new PdfPTable(3);
        addTableHeader(table);
        addRows(table,report);

        document.add(table);
        document.close();
    }

    private void addTableHeader(PdfPTable table){
        Stream.of("Username", "Book Quantity", "Income")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table, Report report){
        table.addCell(report.getUsername());
        table.addCell(String.valueOf(report.getNrCarti()));
        table.addCell(String.valueOf(report.getIncasari()));
    }

}
