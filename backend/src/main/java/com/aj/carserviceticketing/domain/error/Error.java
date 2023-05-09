package com.aj.carserviceticketing.domain.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Error {

    String
            errorName,
            errorMessage;

}
