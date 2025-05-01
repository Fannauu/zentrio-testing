package org.example.zentriotesting.model.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardRequest {
    private String title;
    private String description;
    private String cover;
    private Boolean isFavourite;
//    private UUID workspaceId;
}
