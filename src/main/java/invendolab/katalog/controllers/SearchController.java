/*
 * Created by Batuhan Kök on 14/5/2018.
 * Copyright (c) D-Teknoloji 2018.
 */

package invendolab.katalog.controllers;

import invendolab.katalog.helpers.Response;
import invendolab.katalog.models.Product;
import invendolab.katalog.models.responses.SearchResponse;
import invendolab.katalog.repositories.ProductRepository;
import invendolab.katalog.repositories.SearchRepository;
import invendolab.katalog.specifications.SearchSpecifications;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/search")
@Api(value = "search", description = "Search for products")
public class SearchController {

    @Autowired
    SearchRepository repository;

    @Autowired
    ProductRepository productRepository;

    private Response response;

    @ApiOperation(value = "A list of all filtered products", response = SearchResponse.class)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "keyword", value = "Search keywords", paramType = "query"),
            @ApiImplicitParam(name = "price_start", paramType = "query", type = "int"),
            @ApiImplicitParam(name = "price_end", paramType = "query", type = "int"),
            @ApiImplicitParam(name = "difficulty", value = "Difficulty between 1-5", paramType = "query", type = "Integer", allowableValues = "1, 2, 3, 4, 5"),
            @ApiImplicitParam(name = "sort_by", value = "It can be title or price. For title: 1, for price: 2", type = "Integer", paramType = "query", allowableValues = "1, 2"),
            @ApiImplicitParam(name = "order", allowableValues = "DESC, ASC", paramType = "query"),
            @ApiImplicitParam(name = "start", value = "pagination end. ex: start = 0 offset = 1 returns one product ",type = "long", paramType = "query"),
            @ApiImplicitParam(name = "offset", value = "pagination end. ex: start = 0 offset = 1 returns one product ", type = "long", paramType = "query")
    })
    @RequestMapping(value = "/filtered", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getAllFilteredProduct(@RequestParam(required = false) Map<?,String> params, @RequestParam(value = "start", required = false) Integer start, @RequestParam(value = "offset", required = false) Integer offset) {

        try {

            List<Product> changeableList;

            String priceStart, priceEnd, difficulty, keyword, order, sortBy;

            keyword = params.get("keyword") != null ? params.get("keyword") : null;
            priceStart = params.get("price_start") != null ? params.get("price_start") : null;
            priceEnd = params.get("price_end") != null ? params.get("price_end") : null;
            difficulty = params.get("difficulty") != null ? params.get("difficulty") : null;
            sortBy = params.get("sort_by") != null ? params.get("sort_by") : null;
            order = params.get("order") != null ? params.get("order") : null;

            SearchSpecifications searchSpecification = new SearchSpecifications();
            Specification<Product> specification = searchSpecification.findAllSpecification(keyword, priceStart, priceEnd, difficulty, sortBy, order);

            if (start != null && offset != null) {
                changeableList = repository.findAll(specification, PageRequest.of(start, offset)).getContent();
            } else {
                changeableList = repository.findAll(specification);
            }

            if (changeableList.size() > 0) {

                SearchResponse resultList = new SearchResponse(repository.findAll(specification).size(), changeableList);

                return new ResponseEntity<>(resultList, HttpStatus.OK);

            } else {

                response.setErrorCode(404);
                response.setMessage("Filtrelere göre hiç ürün bulunamadı.");
                response.setSuccess(false);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

            }

        } catch (Exception e) {

            response.setErrorCode(503);
            response.setMessage("Beklenmeyen bir sorun oluştu.");
            response.setSuccess(false);
            response.setException(e);
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }

    }

}
