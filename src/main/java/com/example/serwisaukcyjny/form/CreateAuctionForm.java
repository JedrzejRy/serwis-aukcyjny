package com.example.serwisaukcyjny.form;

import com.example.serwisaukcyjny.form.validation.constrains.AuctionDuration;
import com.example.serwisaukcyjny.form.validation.constrains.AuctionPastDate;
import com.example.serwisaukcyjny.model.Category;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Transient;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.IntStream;

@Getter
@Setter
@ToString
@AuctionDuration(message = "Aukcja może trwać maksymalnie tydzień")
@AuctionPastDate(message = "Aukcja musi trwać minimum jeden dzień")
public class CreateAuctionForm {

    @NotBlank (message = "aukcja musi mieć tytuł")
    private String title;
    @Length(max = 500, message = "Opisz nie może przekraczać 500 znaków")
    private String description;
    @Positive(message = "Wartość musi być dodatnia")
    private int minimumPrice;
    @Positive(message = "Wartość musi być dodatnia")
    private int buyNowPrice;
    private boolean promotion;
    private LocalDateTime dateOfIssue;
    @Future
    private LocalDateTime endDate;
    private int views;
    @NonNull
    private Category category;
    @Length (max = 64)
    private String photos;

    @Transient
    public String getPhotosImagePath() {
        if (photos == null || title == null) return null;

        return "/photos/" + photos;
    }
    public void setPhotos(MultipartFile multipartFile) throws IOException {
        photos = multipartFile.getOriginalFilename();
        String uploadDir = "/src/main/resources/static/photos/";
        Path currentPath = Paths.get("."); //on Windows Paths.get(".")
        Path absolutePath = currentPath.toAbsolutePath();
        Path filePath = Paths.get(absolutePath + uploadDir + photos);
        Files.write(filePath, multipartFile.getBytes());
    }

    public boolean isImage(MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        return Arrays.stream(AllowedExtensions.values())
                .anyMatch(e -> e.toString().equalsIgnoreCase(extension));
    }

    public String wrongFileExtensionMessage() {
        StringBuilder messageSB = new StringBuilder("Plik musi być zdjęciem w formacie: ");
        AllowedExtensions[] extensions = AllowedExtensions.values();
        IntStream.range(0, extensions.length)
                .mapToObj(i -> extensions[i] + (i < extensions.length - 1 ? ", " : ""))
                .forEach(messageSB::append);
        return messageSB.toString();
    }

    private enum AllowedExtensions {
        JPG, JPEG, PNG
    }

}
