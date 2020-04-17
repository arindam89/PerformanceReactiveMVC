package com.geekpaul.reactivemvc.mongo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Sample Pojo Class holding out Data.
 */

@Document
@Data
@Builder
@AllArgsConstructor
public class Person {
    @Id
    public Integer id;
    public String firstName;
    public String lastName;
    public Integer age;
}
