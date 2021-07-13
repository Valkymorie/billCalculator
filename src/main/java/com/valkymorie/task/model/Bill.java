package com.valkymorie.task.model;


import com.valkymorie.task.controller.AccountController;
import com.valkymorie.task.controller.ItemController;
import com.valkymorie.task.service.ItemService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill {

    @Id
    private String id;

    private double amount;
    private double percentage;
    private double phoneAmount;



}
