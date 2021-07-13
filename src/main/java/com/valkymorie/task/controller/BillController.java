package com.valkymorie.task.controller;

import com.valkymorie.task.model.Account;
import com.valkymorie.task.model.Bill;
import com.valkymorie.task.model.Item;
import com.valkymorie.task.service.AccountService;
import com.valkymorie.task.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping("/bill")
@AllArgsConstructor
public class BillController {

    private final ItemService itemService;
    private final AccountService accountService;

    @GetMapping
    public String answer() {
        Scanner input = new Scanner(System.in);
        System.out.println("Account name: ");
        String name = input.nextLine();

        if (name.isEmpty())
            name = "emir";
        else if (name == null)
            name = "emir";

        List<Item> items = itemService.getItems(null);
        List<Account> account = accountService.geAccounts(name);

        Bill x = new Bill();
        x.setAmount(0);
        x.setPercentage(0);

        double amount = 0;
        double notPhoneAmount = 0;
        double phoneAmount = 0;

        if (account.get(0).getCardType().equals("gold"))
            x.setPercentage(30);
        else if (account.get(0).getCardType().equals("silver"))
            x.setPercentage(20);
        else
            x.setPercentage(10);

        if (x.getPercentage() < 10)
            if (account.get(0).getCustomerYear() >= 2)
                x.setPercentage(5);

        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).isPhone())
                phoneAmount += items.get(i).getValue();
            else
                notPhoneAmount += items.get(i).getValue();
        }

        int sum = (int) phoneAmount + (int) notPhoneAmount;
        int anotherDiscount = (sum / 200) * 5;

        amount = (notPhoneAmount-(notPhoneAmount*x.getPercentage()/100.0))+ phoneAmount - anotherDiscount;
        x.setAmount(amount);

        return String.valueOf(x.getAmount());
    }


}
