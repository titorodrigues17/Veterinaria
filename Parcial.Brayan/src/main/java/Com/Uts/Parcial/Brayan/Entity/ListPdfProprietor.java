package Com.Uts.Parcial.Brayan.Entity;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("listar")

public class ListPdfProprietor extends AbstractPdfView {

  private static final String[] header = { "Id", "Nombre", "Teléfono", "Email", "Ubicación" };

  @Override
  protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {

    @SuppressWarnings("unchecked")
    List<Proprietor> listadoProprietor = (List<Proprietor>) model.get("propietarios");

    document.setPageSize(PageSize.LETTER.rotate());
    document.open();

    PdfPTable tablaTitulo = new PdfPTable(1);
    PdfPCell celda = null;
    celda = new PdfPCell(new Phrase("Listado Propietarios"));
    celda.setHorizontalAlignment(Element.ALIGN_CENTER);

    tablaTitulo.addCell(celda);
    tablaTitulo.setSpacingAfter(30);

    PdfPTable tablaPropietarios = new PdfPTable(6);

    for (int i = 0; i < header.length; i++) {
      tablaPropietarios.addCell(header[i]);
    }

    listadoProprietor.forEach(propietario -> {

      tablaPropietarios.addCell(propietario.getId().toString());
      tablaPropietarios.addCell(propietario.getName());
      tablaPropietarios.addCell(propietario.getCellphone());
      tablaPropietarios.addCell(propietario.getEmail());
      tablaPropietarios.addCell(propietario.getLocation());
    });

    document.add(tablaTitulo);
    document.add(tablaPropietarios);

  }
}
