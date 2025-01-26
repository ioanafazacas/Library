package model;
/*
import javax.swing.text.Document;
import java.io.IOException;
import java.util.List;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;

import java.io.File;
import java.io.IOException;
import java.util.List;
public class PdfGenerator {
    public void generateReportPdf(String filePath, List<Report> reports) throws IOException {
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
    }
}
*/