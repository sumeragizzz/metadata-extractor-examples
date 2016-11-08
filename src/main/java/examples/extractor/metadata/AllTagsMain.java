package examples.extractor.metadata;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

public class AllTagsMain {

    public static void main(String[] args) throws ImageProcessingException, IOException {
        // InputStream
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream input = classLoader.getResourceAsStream("images/black_iphone6.jpg")) {
            Metadata metadata = ImageMetadataReader.readMetadata(input);
            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {
                    System.out.println(tag);
                }
            }
        }

        System.out.println("---------------------------------------------------");

        // File
        File file = new File("src/main/resources/images/black_iphone6.jpg");
        System.out.println(file.getAbsolutePath());
        Metadata metadata = ImageMetadataReader.readMetadata(file);
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                System.out.println(tag);
            }
        }
    }

}
