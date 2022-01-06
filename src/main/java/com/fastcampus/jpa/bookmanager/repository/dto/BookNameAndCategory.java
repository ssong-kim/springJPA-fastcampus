package com.fastcampus.jpa.bookmanager.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookNameAndCategory {
    private String name;
    private String category;
}

// 인터페이스
//public interface BookNameAndCategory {
//    String getName();
//
//    String getCategory();
//}
