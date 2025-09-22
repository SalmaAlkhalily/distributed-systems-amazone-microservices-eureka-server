package com.amazone.controllers;

import com.amazone.model.Search;
import com.amazone.service.SearchService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {
SearchService searchService=new SearchService();
    @PostMapping("/search")
    private String repaireSearchObject(@RequestBody Search search) {
      return   searchService.RepaireData(search);

    }

}
