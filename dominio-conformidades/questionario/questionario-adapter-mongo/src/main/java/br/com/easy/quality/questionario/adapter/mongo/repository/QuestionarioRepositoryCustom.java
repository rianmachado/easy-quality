package br.com.easy.quality.questionario.adapter.mongo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.easy.quality.questionario.adapter.mongo.entity.QuestionarioEntity;

@Repository
public interface QuestionarioRepositoryCustom extends MongoRepository<QuestionarioEntity, String> {

	default Iterable<QuestionarioEntity> getAll() {
		return findAll();
	}
	Optional<QuestionarioEntity> findById(String id);
}