package Lab_2.services.files.abstracts;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Objects;

public class FileAttributes {
    protected String fileName;
    protected String fileExtension;
    protected FileTime creationTime;
    protected FileTime lastModifiedTime;
    protected File file;

    public FileAttributes(File file) {
        this.file = file;
        this.fileName = file.getName();
        this.fileExtension = getFileExtension();
        this.creationTime = getFileCreationTime(file);
        this.lastModifiedTime = getFileLastModifiedTime(file);
    }

    private FileTime getFileCreationTime(File file) {
        try {
            BasicFileAttributes attrs = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
            return attrs.creationTime();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public FileTime getCreationTime() {
        try {
            Path path = file.toPath();
            BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
            return attr.creationTime();
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
            return null;
        }
    }

    private FileTime getFileLastModifiedTime(File file) {
        try {
            BasicFileAttributes attrs = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
            return attrs.lastModifiedTime();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public FileTime getLastModifiedTime()  {
        try {
            Path path = file.toPath();
            BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
            return attr.lastModifiedTime();
        } catch (IOException e) {
            System.out.println("Failed to retrieve last modified time due to an IO error: " + e.getMessage());
            return null;
        }

    }

    public String getFileExtension() {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        return name.substring(lastIndexOf);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileAttributes that = (FileAttributes) o;
        return fileName.equals(that.fileName) &&
                Objects.equals(fileExtension, that.fileExtension) &&
                creationTime.equals(that.creationTime) &&
                lastModifiedTime.equals(that.lastModifiedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, fileExtension, creationTime, lastModifiedTime);
    }
}
