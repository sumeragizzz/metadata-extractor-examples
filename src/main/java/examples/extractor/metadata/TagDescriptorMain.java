package examples.extractor.metadata;

import static com.drew.metadata.jpeg.JpegDirectory.*;

import java.io.IOException;
import java.io.InputStream;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.jpeg.JpegDescriptor;
import com.drew.metadata.jpeg.JpegDirectory;

public class TagDescriptorMain {

    public static void main(String[] args) throws ImageProcessingException, IOException, MetadataException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream input = classLoader.getResourceAsStream("images/black_iphone6.jpg")) {
            Metadata metadata = ImageMetadataReader.readMetadata(input);
            JpegDirectory jpegDirectory = metadata.getFirstDirectoryOfType(JpegDirectory.class);
            jpegDirectory.setDescriptor(new JpegDescriptor(jpegDirectory) {
                @Override
                public String getImageHeightDescription() {
                    return String.format("%spx", _directory.getString(TAG_IMAGE_HEIGHT));
                }
            });
            System.out.println(jpegDirectory.getDescription(TAG_IMAGE_HEIGHT));
        }
    }

}
