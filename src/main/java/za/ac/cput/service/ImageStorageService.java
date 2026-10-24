package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Service
public class ImageStorageService {

    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024;

    private static final Set<String> ALLOWED_TYPES = Set.of("image/jpeg", "image/png", "image/webp");

    private static final Map<String, String> EXTENSIONS = Map.of("image/jpeg", ".jpg", "image/png", ".png", "image/webp", ".webp");

    private final Path uploadDirectory;

    public ImageStorageService(@Value("${app.upload.dir:uploads}") String uploadDir) throws IOException {
        this.uploadDirectory = Paths.get(uploadDir).toAbsolutePath().normalize();

        Files.createDirectories(uploadDirectory);
    }

    public String saveImage(MultipartFile file) throws IOException {

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Please select an image.");
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("Image must not exceed 5 MB.");
        }

        String contentType = file.getContentType();

        if (contentType == null || !ALLOWED_TYPES.contains(contentType)) {
            throw new IllegalArgumentException("Only JPG, PNG and WebP images are allowed.");
        }

        String filename = UUID.randomUUID() + EXTENSIONS.get(contentType);

        Path destination = uploadDirectory.resolve(filename);

        // Check that the uploaded content is a readable image.
        byte[] bytes = file.getBytes();

        if (!isValidImage(bytes, contentType)) {
            throw new IllegalArgumentException("The selected file is not a valid image.");
        }

        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

        return "/uploads/" + filename;
    }

    private boolean isValidImage(byte[] data, String contentType) {
        if (data.length < 12) {
            return false;
        }

        if ("image/jpeg".equals(contentType)) {
            return (data[0] & 0xFF) == 0xFF && (data[1] & 0xFF) == 0xD8 && (data[2] & 0xFF) == 0xFF;
        }

        if ("image/png".equals(contentType)) {
            return (data[0] & 0xFF) == 0x89 && data[1] == 'P' && data[2] == 'N' && data[3] == 'G';
        }

        if ("image/webp".equals(contentType)) {
            return new String(data, 0, 4, java.nio.charset.StandardCharsets.US_ASCII).equals("RIFF") && new String(data, 8, 4, java.nio.charset.StandardCharsets.US_ASCII).equals("WEBP");
        }

        return false;
    }
}
