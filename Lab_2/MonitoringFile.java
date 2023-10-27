package Lab_2;

import java.time.LocalDate;

public class MonitoringFile {
    private String fileName;
    private String extension;
    private LocalDate created;
    private LocalDate uploaded;

    public MonitoringFile(String fileName, String extension, LocalDate created, LocalDate uploaded){
        this.fileName = fileName;
        this.extension = extension;
        this.created = created;
        this.uploaded = uploaded;
    }

}

