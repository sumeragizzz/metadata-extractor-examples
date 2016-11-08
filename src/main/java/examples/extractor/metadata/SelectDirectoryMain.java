package examples.extractor.metadata;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifDirectoryBase;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.jpeg.JpegDirectory;

public class SelectDirectoryMain {

    public static void main(String[] args) throws ImageProcessingException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream input = classLoader.getResourceAsStream("images/black_iphone6.jpg")) {
            Metadata metadata = ImageMetadataReader.readMetadata(input);

            Iterable<Directory> directories = metadata.getDirectories();

            Collection<ExifDirectoryBase> exifDirectories = metadata.getDirectoriesOfType(ExifDirectoryBase.class);

            JpegDirectory jpegDirectory = metadata.getFirstDirectoryOfType(JpegDirectory.class);

            System.out.println(directories);
            System.out.println(exifDirectories);
            System.out.println(jpegDirectory);

            boolean hasDirectory = metadata.containsDirectoryOfType(ExifIFD0Directory.class);
            int directoryCount = metadata.getDirectoryCount();
            boolean hasErrors = metadata.hasErrors();

            System.out.println(hasDirectory);
            System.out.println(directoryCount);
            System.out.println(hasErrors);
        }
    }

}
