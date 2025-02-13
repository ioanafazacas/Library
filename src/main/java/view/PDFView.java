package view;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PDFView {
    public PDFView() throws IOException {
        JFrame frame = new JFrame("PDF Viewer");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);

        PDDocument document = Loader.loadPDF(new File("reportTable.pdf"));

        PDFRenderer pdfRenderer = new PDFRenderer(document);

        BufferedImage image = pdfRenderer.renderImage(0); // Renders first page
        JLabel label = new JLabel(new ImageIcon(image));

        JScrollPane scrollPane = new JScrollPane(label);
        frame.add(scrollPane);
        frame.setVisible(true);

        document.close();
    }
}
