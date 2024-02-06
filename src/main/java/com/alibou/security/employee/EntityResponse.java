package com.alibou.security.employee;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EntityResponse<T> {
    private String message;
    private T entity;
    private Integer statusCode;
}
