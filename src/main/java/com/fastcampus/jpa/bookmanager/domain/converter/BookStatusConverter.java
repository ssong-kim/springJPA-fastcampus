package com.fastcampus.jpa.bookmanager.domain.converter;

import com.fastcampus.jpa.bookmanager.repository.dto.BookStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

// autoApply 속성은 개발자가 생성한 클래스타입에 한해 활용할 것
// autoApply 속성 사용시, String, Integer 같은 타입들을 Entity 타입으로 사용하면 예상치 못한 변환 일어날 수 있음
@Converter(autoApply = true)
public class BookStatusConverter implements AttributeConverter<BookStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(BookStatus attribute) {
        return attribute.getCode();
//        return null; // 사용안함
    }

    @Override
    public BookStatus convertToEntityAttribute(Integer dbData) {
        return dbData != null ? new BookStatus(dbData) : null;
    }
}
