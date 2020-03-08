package com.test.api.frontend.views;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.jetty.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseView {

    private String message;

    private int statusCode;
}
