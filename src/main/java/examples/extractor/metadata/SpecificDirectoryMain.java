package examples.extractor.metadata;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.imaging.jpeg.JpegSegmentMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.icc.IccReader;
import com.drew.metadata.jpeg.JpegReader;

public class SpecificDirectoryMain {

    public static void main(String[] args) throws JpegProcessingException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream input = classLoader.getResourceAsStream("images/black_iphone6.jpg")) {
            Iterable<JpegSegmentMetadataReader> readers = Arrays.asList(new JpegReader(), new IccReader());
            Metadata metadata = JpegMetadataReader.readMetadata(input, readers);
            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {
                    System.out.println(tag);
                }
            }
        }
    }

}
