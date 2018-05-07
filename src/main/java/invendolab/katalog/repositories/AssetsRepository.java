package invendolab.katalog.repositories;

import invendolab.katalog.models.Asset;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AssetsRepository extends CrudRepository<Asset, Long>{
    List<Asset> findAll();
    //List<Asset> findAllByProductId(long id);
}
