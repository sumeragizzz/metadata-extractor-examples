package examples.extractor.metadata;

import java.io.IOException;
import java.io.InputStream;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

public class JpegAllTagsMain {

    public static void main(String[] args) throws JpegProcessingException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream input = classLoader.getResourceAsStream("images/black_iphone6.jpg")) {
            Metadata metadata = JpegMetadataReader.readMetadata(input);
            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {
                    System.out.println(tag);
                }
            }
        }
    }

}
