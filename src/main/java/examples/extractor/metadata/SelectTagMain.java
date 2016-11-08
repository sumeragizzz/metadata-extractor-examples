package examples.extractor.metadata;

import java.io.IOException;
import java.io.InputStream;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.jpeg.JpegDirectory;

public class SelectTagMain {

    public static void main(String[] args) throws ImageProcessingException, IOException, MetadataException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream input = classLoader.getResourceAsStream("images/black_iphone6.jpg")) {
            Metadata metadata = ImageMetadataReader.readMetadata(input);

            Directory directory = metadata.getFirstDirectoryOfType(Directory.class);
            if (directory.hasTagName(JpegDirectory.TAG_IMAGE_HEIGHT)) {
                System.out.println(directory.getInt(JpegDirectory.TAG_IMAGE_HEIGHT));
                System.out.println(directory.getInt(JpegDirectory.TAG_IMAGE_HEIGHT));
                System.out.println(directory.getDescription(JpegDirectory.TAG_IMAGE_HEIGHT));
            }
        }
    }

}
