package com.valkymorie.task.controller;

import com.valkymorie.task.exception.ItemAlreadyExistsException;
import com.valkymorie.task.exception.ItemNotFoundException;
import com.valkymorie.task.model.Item;
import com.valkymorie.task.service.ItemService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/items")
@AllArgsConstructor
public class ItemController {


    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<List<Item>> getItems(@RequestParam(required = false) String name) {

        return new ResponseEntity<>(itemService.getItems(name), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable String id) {

        return new ResponseEntity<>(findItem(id), OK);
    }

    @PostMapping
    public ResponseEntity<Item> addItem(@RequestBody Item newItem) {

        return new ResponseEntity<>(itemService.addItem(newItem), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> replaceItem(@PathVariable String id, @RequestBody Item newItem){

        itemService.replaceItem(id, newItem);

        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable String id){

        itemService.deleteItem(id);

        return new ResponseEntity<>(OK);
    }

    private Item findItem(String id) {

        return itemService.findItem(id);
    }

   /* public List<Item> getAllofItemsbylist(){
        return itemService.getItems(null);
    }*/

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<String> itemNotFoundExceptionHandler (ItemNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), NOT_FOUND);
    }

    @ExceptionHandler(ItemAlreadyExistsException.class)
    public ResponseEntity<String> itemAlreadyExistsExceptionHandler (ItemAlreadyExistsException e){
        return new ResponseEntity<>(e.getMessage(), CONFLICT);
    }


}
