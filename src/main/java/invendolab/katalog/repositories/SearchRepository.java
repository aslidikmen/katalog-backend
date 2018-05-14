/*
 * Created by Batuhan Kök on 14/5/2018.
 * Copyright (c) D-Teknoloji 2018.
 */

package invendolab.katalog.repositories;

import invendolab.katalog.models.Product;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface SearchRepository extends DataTablesRepository<Product, Long> {

}
