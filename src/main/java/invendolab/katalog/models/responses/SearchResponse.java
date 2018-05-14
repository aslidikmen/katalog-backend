/*
 * Created by Batuhan Kök on 14/5/2018.
 * Copyright (c) D-Teknoloji 2018.
 */

package invendolab.katalog.models.responses;

import invendolab.katalog.models.Product;

import java.util.List;

public class SearchResponse {

    private int totalProductCount;
    private List<Product> resultList;

    public SearchResponse(int totalProductCount, List<Product> resultList) {
        this.totalProductCount = totalProductCount;
        this.resultList = resultList;
    }

    public int getTotalProductCount() {
        return totalProductCount;
    }

    public void setTotalProductCount(int totalProductCount) {
        this.totalProductCount = totalProductCount;
    }

    public List<Product> getResultList() {
        return resultList;
    }

    public void setResultList(List<Product> resultList) {
        this.resultList = resultList;
    }
}

