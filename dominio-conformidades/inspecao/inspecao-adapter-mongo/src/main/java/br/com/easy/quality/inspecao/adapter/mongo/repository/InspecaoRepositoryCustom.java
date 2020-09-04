/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.inspecao.adapter.mongo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import br.com.easy.quality.inspecao.adapter.mongo.entity.InspecaoEntity;

@Repository
public interface InspecaoRepositoryCustom
		extends MongoRepository<InspecaoEntity, String>, QuerydslPredicateExecutor<InspecaoEntity> {

	default Iterable<InspecaoEntity> getAll() {
		return findAll();
	}

	Optional<InspecaoEntity> findById(String id);
}