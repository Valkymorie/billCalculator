package com.valkymorie.task.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    private String id;

    private String name;
    private double value;
    private boolean isPhone;
}
