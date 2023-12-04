package com.aj.carserviceticketing.domain.nondb.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Error {

    String
            errorName,
            errorMessage;

}
