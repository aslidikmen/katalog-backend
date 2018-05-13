package invendolab.katalog.repositories;

import invendolab.katalog.models.Product;
import invendolab.katalog.specifications.SearchSpecifications;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SearchRepository extends DataTablesRepository<Product, Long> {

}
