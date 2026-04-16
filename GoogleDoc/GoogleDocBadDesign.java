package GoogleDoc;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class DocumentEditor {

    private List<String> documentList;
    private String renderDocument;

    DocumentEditor() {
        documentList = new ArrayList<>();
        renderDocument = "";
    }

    // methods

    public void addText(String text) {
        documentList.add(text);
    }

    public void addImage(String image) {
        documentList.add(image);
    }

    //Render the document by checking the file or Image At run time
    public String renderDocument() {
        if (renderDocument.isEmpty()) {
            StringBuilder result = new StringBuilder();
            for (String s : documentList) {
                if (s.length() > 4 && (s.endsWith(".png") || s.endsWith(".jpg") || s.endsWith(".gif"))) {
                    result.append("[Image: ").append(s).append("]\n");
                } else {
                    result.append(s).append("\n");
                }
            }
            renderDocument = result.toString();

        }
        return renderDocument;
    }

    public void saveToFile() {
        try {
            FileWriter fw = new FileWriter("documents.txt");
            fw.write(renderDocument);
            fw.close();
            System.out.println("Document Saved to documents.txt");

        } catch (IOException e) {
            System.out.println("Error saving documents.txt");
        }
    }

}


public class GoogleDocBadDesign {

    public static void main(String[] args) {
        DocumentEditor documentEditor = new DocumentEditor();
        documentEditor.addText("Hello World");
        documentEditor.addImage("ranjeet.png");
        System.out.println(documentEditor.renderDocument());
        documentEditor.saveToFile();
    }
}
