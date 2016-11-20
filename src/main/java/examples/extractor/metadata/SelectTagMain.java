package examples.extractor.metadata;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.Tag;
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

            for (Tag tag : directory.getTags()) {
                String directoryName = tag.getDirectoryName();
                String tagName = tag.getTagName();
                int tagType = tag.getTagType();
                String tagTypeHex = tag.getTagTypeHex();
                String description = tag.getDescription();
                System.out.println("----");
                System.out.println(tag.toString());
                System.out.println(directoryName);
                System.out.println(tagName);
                System.out.println(tagType);
                System.out.println(tagTypeHex);
                System.out.println(description);
            }

            if (directory.containsTag(JpegDirectory.TAG_IMAGE_HEIGHT)) {
                int imageHeight = directory.getInt(JpegDirectory.TAG_IMAGE_HEIGHT);
                String imageHeightDescription = directory.getDescription(JpegDirectory.TAG_IMAGE_HEIGHT);
                System.out.format("%d : %s%n", imageHeight, imageHeightDescription);
                Date s = directory.getDate(JpegDirectory.TAG_IMAGE_HEIGHT);
                System.out.format("---- %s%n", s);
                directory.getDescription(JpegDirectory.TAG_IMAGE_HEIGHT);
            }

            JpegDirectory jpegDirectory = metadata.getFirstDirectoryOfType(JpegDirectory.class);
            int imageHeight = jpegDirectory.getImageHeight();
            int imageWidth = jpegDirectory.getImageWidth();
            System.out.format("%d x %d%n", imageWidth, imageHeight);

        }
    }

}
