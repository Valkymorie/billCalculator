package com.valkymorie.task.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    private String id;

    @NotBlank(message = "Pls provide a name.")
    private String name;

    @NotBlank(message = "Pls provide a Card Type.")
    private String cardType;

    private int customerYear;

}
