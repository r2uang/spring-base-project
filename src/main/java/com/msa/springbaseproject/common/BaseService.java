package com.msa.springbaseproject.common;

import org.hibernate.query.Page;
import org.springframework.data.jpa.domain.Specification;

import java.awt.print.Pageable;
import java.util.List;

public class BaseService <E extends Auditable, R extends BaseRepository<E>> {

    protected final R repository;
    private final String entityName;

    public BaseService(R repository) {
        this.repository = repository;

        entityName =  getClass().getSimpleName().replace("Service", "");
    }

    public void softDelete(Long id) {
        softDelete(findById(id));
    }

    public void softDelete(E auditable) {
        auditable.setDeleted(true);
        repository.save(auditable);
    }

    public void hardDelete(Long id) {
        repository.delete(findById(id));
        softDelete(findById(id));
    }

    public void hardDelete(E auditable) {
        repository.delete(auditable);
    }

    public E findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new BusinessException(400 ,"Not exists"));
    }

    public List<E> findAll() {
        return repository.findAll();
    }

//    public Page<E> paging(Pageable pageable) {
//        return repository.findAll(pageable);
//    }
//
//    public Page<E> paging(Specification<E> specification, Pageable pageable) {
//        return repository.findAll(specification, pageable);
//    }
}
