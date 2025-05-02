package org.example.zentriotesting.model.entity.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardRequest {
    @NotBlank(message = "Title cannot be blank!")
    private String title;
    private String description;
    private String cover;
//    private Boolean isFavourite;
//    @NotBlank(message = "Title cannot be blank!")
    private Boolean isVerified;
//    @NotBlank(message = "Workspace Id cannot be blank!")
    private UUID workspaceId;
}
