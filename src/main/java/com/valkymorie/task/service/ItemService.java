package com.valkymorie.task.service;

import com.valkymorie.task.exception.ItemAlreadyExistsException;
import com.valkymorie.task.exception.ItemNotFoundException;
import com.valkymorie.task.model.Item;
import com.valkymorie.task.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemService {


    private final ItemRepository itemRepository;

    public List<Item> getItems(String name) {

        if(name == null)
            return itemRepository.findAll();
        else
            return itemRepository.findAllByName(name);


    }

    public Item addItem(Item newItem) {

        Optional<Item> itemByName =itemRepository.findByName(newItem.getName());
        /*if(itemByName.isPresent()){
            throw new ItemAlreadyExistsException("Item already exists with this name: "+ newItem.getName());
        }*/

        if (newItem.getName().equals("telefon"))
            newItem.setPhone(true);
        else
            newItem.setPhone(false);

        return itemRepository.save(newItem);
    }


    public void deleteItem(String id) {
        itemRepository.deleteById(id);
    }

    public Item findItem(String id) {

        return itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item not found with this id: "+ id));
    }


    public void replaceItem(String id, Item newItem) {

        Item oldItem = findItem(id);
        oldItem.setValue(newItem.getValue());
        oldItem.setName(newItem.getName());

        if(newItem.getName().equals("telefon"))
            oldItem.setPhone(true);
        else
            oldItem.setPhone(false);
        itemRepository.save(oldItem);
    }
}
