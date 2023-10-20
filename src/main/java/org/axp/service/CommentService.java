package org.axp.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.axp.dao.CommentDao;
import org.axp.domain.CommentDto;
import org.axp.transformer.CommentTransformer;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CommentService {

    @Inject
    CommentDao dao;

    @Inject
    CommentTransformer transformer;

    public void save(CommentDto dto) {
        dao.save(transformer.transform(dto));
    }

    public List<CommentDto> findAllByIds(String projectId, int ticketId) {
        return dao.findAllByIds(projectId, ticketId).all()
                .stream().map(transformer::transform).collect(Collectors.toList());
    }

    public boolean delete(CommentDto dto) {
        return dao.delete(transformer.transform(dto));
    }

}
