package invendolab.katalog.specifications;

import invendolab.katalog.models.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class SearchSpecifications {

    public Specification<Product> findAllSpecification(String keyword, String priceStart, String priceEnd, String difficulty){
        return (root, criteriaQuery, criteriaBuilder) -> {

            criteriaQuery.distinct(true);

            List<Predicate> conditions = new ArrayList();

            if (keyword != null && !keyword.isEmpty()) {

                List<Predicate> keywordConditions = new ArrayList();

                keywordConditions.add(criteriaBuilder.like(root.get("title"), "%" + keyword + "%"));
                keywordConditions.add(criteriaBuilder.like(root.get("description"), "%" + keyword + "%"));
                keywordConditions.add(criteriaBuilder.like(root.get("tags"), "%" + keyword + "%"));

                Predicate[] keywordPredicates = keywordConditions.toArray(new Predicate[keywordConditions.size()]);
                Predicate keywordPredicate = criteriaBuilder.or(keywordPredicates);
                conditions.add(keywordPredicate);
            }

            if (difficulty != null && !difficulty.isEmpty()) {

                conditions.add(criteriaBuilder.equal(root.get("difficulty"), difficulty));

            }

            if ((priceStart != null && priceEnd != null) && (!priceStart.isEmpty() && !priceEnd.isEmpty())) {
                conditions.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), Long.valueOf(priceStart)));
                conditions.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), Long.valueOf(priceEnd)));
            }

            // get only active products
            conditions.add(criteriaBuilder.isTrue(root.get("isActive")));

            Predicate[] predicates = conditions.toArray(new Predicate[conditions.size()]);

            return criteriaBuilder.and(predicates);
        };
    }

}
