package com.network.inventory.locationManagement.api_management;

import com.network.inventory.locationManagement.pojo.City;
import com.network.inventory.rackManagement.dto.*;
import com.network.inventory.rackManagement.pojo.*;
import jakarta.websocket.server.PathParam;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import  java.lang.*;
import java.util.Optional;

@RestController
public class RackManagement {
    @Autowired
    RackRepo rackRepo;
    @Autowired
    ShelfRepo shelfRepo;
    @Autowired
    SlotRepo slotRepo;
    @Autowired
    CardRepo cardRepo;
    @Autowired
    CardSlotRepo cardSlotRepo;
//    Rack
    @PostMapping("/create/Rack")
    private JSONObject createRack(@RequestBody Rack rack, @PathParam("buildingName") String building_name) {
        JSONObject response = new JSONObject();
        String rack_id = formatName(rack.getRack_id());

        if (rackRepo.findByName(building_name,rack_id) == null) {
            rack.setRack_id(rack_id);
            rackRepo.createRack(building_name, rack_id);
            response.put("status", "success");
            response.put("response", "Successfully created");
        } else {
            response.put("status", "failure");
            response.put("response", "City already exists");
        }
        return response;
    }

    @GetMapping("/get/allRack")
    private JSONObject getAllRackByBuilding(@PathParam("building") String buildingName) {
        JSONObject response = new JSONObject();
        response.put("status", "success");
        response.put("response", rackRepo.findAllBuildingByRack(buildingName));
        return response;
    }

    @PutMapping("/edit/rack")
    private JSONObject editRack(@PathParam("id") Long id, @RequestBody Rack rack1) {
        JSONObject response = new JSONObject();
        Optional<Rack> optionalCity = rackRepo.findById(id);

        if (optionalCity.isPresent()) {
            Rack rack = optionalCity.get();
            rack.setRack_id(formatName(rack1.getRack_id()));
            rack.setDescription(rack1.getDescription());
            rackRepo.save(rack);
            response.put("status", "success");
            response.put("response", "Updated successfully");
        } else {
            response.put("status", "failure");
            response.put("response", "Cannot find the ID");
        }
        return response;
    }

    @DeleteMapping("/delete/rack")
    private JSONObject dropRack(@PathParam("id") Long id) {
        JSONObject response = new JSONObject();
        if (rackRepo.findById(id)!=null) {
            rackRepo.deleteById(id);
            response.put("status", "success");
            response.put("response", "Deleted successfully");
        } else {
            response.put("status", "failure");
            response.put("response", "Cannot find the ID");
        }
        return response;
    }

    // Shelf
    @PostMapping("/create/Shelf")
    private JSONObject createShelf(@RequestBody Shelf shelf, @PathParam("rackId") String rackId) {
        JSONObject response = new JSONObject();
        String shelfId = formatName(shelf.getShelf_id());

        if (shelfRepo.findByName(rackId, shelfId) == null) {
            shelf.setShelf_id(shelfId);
            shelfRepo.createShelf(rackId, shelfId);
            response.put("status", "success");
            response.put("response", "Successfully created");
        } else {
            response.put("status", "failure");
            response.put("response", "Shelf already exists");
        }
        return response;
    }

    @GetMapping("/get/allShelf")
    private JSONObject getAllShelfByRack(@PathParam("rackId") String rackId) {
        JSONObject response = new JSONObject();
        response.put("status", "success");
        response.put("response", shelfRepo.findAllRackByShelf(rackId));
        return response;
    }

    @PutMapping("/edit/shelf")
    private JSONObject editShelf(@PathParam("id") Long id, @RequestBody Shelf shelf1) {
        JSONObject response = new JSONObject();
        Optional<Shelf> optionalShelf = shelfRepo.findById(id);

        if (optionalShelf.isPresent()) {
            Shelf shelf = optionalShelf.get();
            shelf.setShelf_id(formatName(shelf1.getShelf_id()));
            shelf.setDescription(shelf1.getDescription());
            shelfRepo.save(shelf);
            response.put("status", "success");
            response.put("response", "Updated successfully");
        } else {
            response.put("status", "failure");
            response.put("response", "Cannot find the ID");
        }
        return response;
    }

    @DeleteMapping("/delete/shelf")
    private JSONObject dropShelf(@PathParam("id") Long id) {
        JSONObject response = new JSONObject();
        if (shelfRepo.findById(id).isPresent()) {
            shelfRepo.deleteById(id);
            response.put("status", "success");
            response.put("response", "Deleted successfully");
        } else {
            response.put("status", "failure");
            response.put("response", "Cannot find the ID");
        }
        return response;
    }

    // Slot
    @PostMapping("/create/Slot")
    private JSONObject createSlot(@RequestBody Slot slot, @PathParam("shelfId") String shelfId) {
        JSONObject response = new JSONObject();
        String slotId = formatName(slot.getSlot_id());

        if (slotRepo.findAllShelfBySlot(shelfId) == null) {
            slot.setSlot_id(slotId);
            slotRepo.createSlot(shelfId, slotId);
            response.put("status", "success");
            response.put("response", "Successfully created");
        } else {
            response.put("status", "failure");
            response.put("response", "Slot already exists");
        }
        return response;
    }

    @GetMapping("/get/allSlot")
    private JSONObject getAllSlotByShelf(@PathParam("shelfId") String shelfId) {
        JSONObject response = new JSONObject();
        response.put("status", "success");
        response.put("response", slotRepo.findAllShelfBySlot(shelfId));
        return response;
    }

    @PutMapping("/edit/slot")
    private JSONObject editSlot(@PathParam("id") Long id, @RequestBody Slot slot1) {
        JSONObject response = new JSONObject();
        Optional<Slot> optionalSlot = slotRepo.findById(id);

        if (optionalSlot.isPresent()) {
            Slot slot = optionalSlot.get();
            slot.setSlot_id(formatName(slot1.getSlot_id()));
            slot.setDescription(slot1.getDescription());
            slotRepo.save(slot);
            response.put("status", "success");
            response.put("response", "Updated successfully");
        } else {
            response.put("status", "failure");
            response.put("response", "Cannot find the ID");
        }
        return response;
    }

    @DeleteMapping("/delete/slot")
    private JSONObject dropSlot(@PathParam("id") Long id) {
        JSONObject response = new JSONObject();
        if (slotRepo.findById(id).isPresent()) {
            slotRepo.deleteById(id);
            response.put("status", "success");
            response.put("response", "Deleted successfully");
        } else {
            response.put("status", "failure");
            response.put("response", "Cannot find the ID");
        }
        return response;
    }

    // Card
    @PostMapping("/create/Card")
    private JSONObject createCard(@RequestBody Card card, @PathParam("slotId") Long slotId) {
        JSONObject response = new JSONObject();
        Long cardId = (card.getId());

        if (cardRepo.findById(cardId) == null) {
            cardRepo.createCard(slotId, cardId);
            response.put("status", "success");
            response.put("response", "Successfully created");
        } else {
            response.put("status", "failure");
            response.put("response", "Card already exists");
        }
        return response;
    }

    @GetMapping("/get/allCard")
    private JSONObject getAllCardBySlot(@PathParam("slotId") Long slotId) {
        JSONObject response = new JSONObject();
        response.put("status", "success");
        response.put("response", cardRepo.findAllSlotByCard(slotId));
        return response;
    }

    @PutMapping("/edit/card")
    private JSONObject editCard(@PathParam("id") Long id, @RequestBody Card card1) {
        JSONObject response = new JSONObject();
        Optional<Card> optionalCard = cardRepo.findById(id);

        if (optionalCard.isPresent()) {
//            Card card = optionalCard.get();
            card1.setId((card1.getId()));
            card1.setDescription(card1.getDescription());
            cardRepo.save(card1);
            response.put("status", "success");
            response.put("response", "Updated successfully");
        } else {
            response.put("status", "failure");
            response.put("response", "Cannot find the ID");
        }
        return response;
    }

    @DeleteMapping("/delete/card")
    private JSONObject dropCard(@PathParam("id") Long id) {
        JSONObject response = new JSONObject();
        if (cardRepo.findById(id).isPresent()) {
            cardRepo.deleteById(id);
            response.put("status", "success");
            response.put("response", "Deleted successfully");
        } else {
            response.put("status", "failure");
            response.put("response", "Cannot find the ID");
        }
        return response;
    }
    // CardSlot
    @PostMapping("/create/CardSlot")
    private JSONObject createCardSlot(@RequestBody CardSlot cardSlot, @PathParam("cardId") Long cardId) {
        JSONObject response = new JSONObject();
        String cardSlotId = formatName(cardSlot.getCard_slot_id());

        if (cardSlotRepo.findByName(cardId, cardSlotId) == null) {
            cardSlotRepo.createCardSlot(cardId, cardSlotId);
            response.put("status", "success");
            response.put("response", "Successfully created");
        } else {
            response.put("status", "failure");
            response.put("response", "CardSlot already exists");
        }
        return response;
    }

    @GetMapping("/get/allCardSlot")
    private JSONObject getAllCardSlotByCard(@PathParam("cardId") Long cardId) {
        JSONObject response = new JSONObject();
        response.put("status", "success");
        response.put("response", cardSlotRepo.findAllCardByCardSlot(cardId));
        return response;
    }

    @PutMapping("/edit/cardSlot")
    private JSONObject editCardSlot(@PathParam("id") Long id, @RequestBody CardSlot cardSlot1) {
        JSONObject response = new JSONObject();
        Optional<CardSlot> optionalCardSlot = cardSlotRepo.findById(id);

        if (optionalCardSlot.isPresent()) {
            cardSlot1.setId(optionalCardSlot.get().getId());

            cardSlotRepo.save(cardSlot1);
            response.put("status", "success");
            response.put("response", "Updated successfully");
        } else {
            response.put("status", "failure");
            response.put("response", "Cannot find the ID");
        }
        return response;
    }

    @DeleteMapping("/delete/cardSlot")
    private JSONObject dropCardSlot(@PathParam("id") Long id) {
        JSONObject response = new JSONObject();
        if (cardSlotRepo.findById(id).isPresent()) {
            cardSlotRepo.deleteById(id);
            response.put("status", "success");
            response.put("response", "Deleted successfully");
        } else {
            response.put("status", "failure");
            response.put("response", "Cannot find the ID");
        }
        return response;
    }



    /**
     * Utility method to format names to a unique, consistent format by removing spaces, special characters, and setting to camel case.
     * @param name Original name to format.
     * @return Formatted name.
     */
    private String formatName(String name) {
        if (name == null) return "";
        return name.replaceAll("[^a-zA-Z0-9]", "").toLowerCase().substring(0, 1).toUpperCase()
                + name.replaceAll("[^a-zA-Z0-9]", "").toLowerCase().substring(1);
    }
}
