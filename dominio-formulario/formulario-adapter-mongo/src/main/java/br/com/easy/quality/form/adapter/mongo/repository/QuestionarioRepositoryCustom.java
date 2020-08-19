/**
 * @author rianmachado@gmail.com
 */
package br.com.easy.quality.form.adapter.mongo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import br.com.easy.quality.form.adapter.mongo.entity.QuestionarioEntity;

@Repository
public interface QuestionarioRepositoryCustom
		extends MongoRepository<QuestionarioEntity, String>, QuerydslPredicateExecutor<QuestionarioEntity> {

	default Iterable<QuestionarioEntity> getAll() {
		return findAll();
	}

	Optional<QuestionarioEntity> findById(String id);
}