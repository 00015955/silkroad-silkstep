package uz.silkStep.project.utils;

import lombok.experimental.UtilityClass;


@UtilityClass
public class FileUtils {

    public static String getFileExtension(String originalFilename) {
        if (originalFilename == null || originalFilename.isBlank()) {
            return "";
        }

        int lastDotIndex = originalFilename.lastIndexOf('.');
        if (lastDotIndex == -1 || lastDotIndex == originalFilename.length() - 1) {
            return "";
        }

        return originalFilename.substring(lastDotIndex + 1).toLowerCase();
    }

}

// This utility class provides a method to extract the file extension from a given filename. 
//It checks for null or blank filenames and handles cases where there is no extension or the filename ends with a dot. 
//The extracted extension is returned in lowercase for consistency.