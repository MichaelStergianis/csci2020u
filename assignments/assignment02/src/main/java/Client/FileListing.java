package Client;

/**
 * Created by Irfaan on 28/03/2016.
 */
public class FileListing {
    private String fileName;

    public FileListing(String fName) {
        this.fileName = fName;
    }

    public String getFileName() {return fileName;}
    public void setFileName(String fName) {fileName = fName;}
}
