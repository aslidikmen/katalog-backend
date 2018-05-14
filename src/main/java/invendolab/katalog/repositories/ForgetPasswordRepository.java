/*
 * Created by Batuhan Kök on 14/5/2018.
 * Copyright (c) D-Teknoloji 2018.
 */

package invendolab.katalog.repositories;

import invendolab.katalog.models.ForgetPassword;
import org.springframework.data.repository.CrudRepository;

public interface ForgetPasswordRepository extends CrudRepository<ForgetPassword, Long>{
    ForgetPassword findAllByKey(String key);
    boolean existsByKey(String Key);
}
