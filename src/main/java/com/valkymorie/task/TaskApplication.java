package com.valkymorie.task;

import com.valkymorie.task.controller.AccountController;
import com.valkymorie.task.controller.ItemController;
import com.valkymorie.task.model.Bill;
import com.valkymorie.task.model.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class TaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);
    }
}
