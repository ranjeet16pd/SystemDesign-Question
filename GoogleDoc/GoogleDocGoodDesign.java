package GoogleDoc;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

interface DocumentElement {
    public abstract String render();
}

class TextElement implements DocumentElement {

    private String text;

    public TextElement(String text) {
        this.text = text;
    }

    @Override
    public String render() {
        return text;
    }
}

class ImageElement implements DocumentElement {
    private String image;

    public ImageElement(String image) {
        this.image = image;
    }

    @Override
    public String render() {
        return "[ImagePath:" + image + "]";
    }
}

class NewLineElement implements DocumentElement {
    private String newLine;

    public NewLineElement(String newLine) {
        this.newLine = newLine;
    }

    @Override
    public String render() {
        return "\n";
    }
}

class TabSpaceElement implements DocumentElement {
    private String tab;

    public TabSpaceElement(String tab) {
        this.tab = tab;
    }

    @Override
    public String render() {
        return "\t";
    }
}

//Document Class Responsible for handling a Collection of Element
class Document {
    List<DocumentElement> elements = new ArrayList<>();

    public void addElement(DocumentElement e) {
        elements.add(e);
    }

    // Reder the document by concatenating the render output of all the elements
    public String render() {
        StringBuilder sb = new StringBuilder();
        for (DocumentElement e : elements) {
            sb.append(e.render());
        }
        return sb.toString();
    }
}

interface Persitence {
    void save(String data) throws IOException;
}


class fileStorage implements Persitence {

    @Override
    public void save(String data) {

        try {
            FileWriter fw = new FileWriter("document.txt");
            fw.write(data);
            fw.close();
            System.out.println("Document save successful to document.txt");

        } catch (Exception e) {
            System.out.println("Document save failed to document.txt");
        }


    }
}

class SaveToDisk implements Persitence {
    @Override
    public void save(String data) {

    }
}


class DocumentEditor2 {
    private Document document;
    private Persitence persitence;
    private String renderDoc="";

    public DocumentEditor2(Document document, Persitence persitence) {
        this.document = document;
        this.persitence = persitence;
    }

    public void addText(String text) {
        document.addElement(new TextElement(text));
    }

    public void addImage(String image) {
        document.addElement(new ImageElement(image));
    }

    public void addTab(String tab) {
        document.addElement(new TabSpaceElement(tab));
    }

    public void addNewLine() {
        document.addElement(new NewLineElement(renderDoc));
    }

    public void addNewTab() {
        document.addElement(new NewLineElement(renderDoc));
    }

    public String renderDoc() {
        if (renderDoc.isEmpty()) {
            renderDoc = document.render();
        }
        return renderDoc;
    }

    public void saveDocument() throws IOException {
        persitence.save(renderDoc);

    }

}

public class GoogleDocGoodDesign {

    public static void main(String[] args) throws IOException {
        Document document = new Document();
        Persitence persitence = new fileStorage();
        DocumentEditor2 documentEditor = new DocumentEditor2(document, persitence);
        documentEditor.addText("My name is Ranjeet");
        documentEditor.addImage("https://www.google.com.png");
        documentEditor.addNewLine();
        documentEditor.addNewTab();
        documentEditor.addImage("rj.png");

        System.out.println(documentEditor.renderDoc());
        documentEditor.saveDocument();


    }
}
