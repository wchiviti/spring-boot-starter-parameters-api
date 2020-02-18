package io.github.hobbstech.parametersapi;

import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

public class AbstractParameterDomainRestController<T extends AbstractParameterDomainEntity,
        X extends CreateParameterCommand, Y extends UpdateParameterCommand> {

    private final AbstractParameterDomainService<T, X, Y> abstractParameterDomainService;

    public AbstractParameterDomainRestController(AbstractParameterDomainService<T, X, Y> abstractParameterDomainService) {
        this.abstractParameterDomainService = abstractParameterDomainService;
    }

    @GetMapping
    @ApiOperation("Get All paginated")
    public Page<T> findAll(@PageableDefault Pageable pageable,
                           @RequestParam(required = false, name = "search") String search) {
        return abstractParameterDomainService.findAll(pageable, search);
    }

    @GetMapping("/all")
    @ApiOperation("Get all un-paged")
    public Collection<T> findAll() {
        return abstractParameterDomainService.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation("Get One By Id")
    public T findById(@PathVariable long id) {
        return abstractParameterDomainService.findById(id);
    }

    @PostMapping
    @ApiOperation("Create")
    public T create(@RequestBody X x) {
        return abstractParameterDomainService.create(x);
    }

    @PutMapping("{id}")
    @ApiOperation("Update")
    public T update(@RequestBody Y y, @PathVariable long id) {
        y.setId(id);
        return abstractParameterDomainService.update(y);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Delete")
    public void delete(@PathVariable long id) {
        abstractParameterDomainService.pseudoDelete(id);
    }

    @DeleteMapping("{id}/permanently")
    @ApiOperation("Delete Permanently")
    public void deletePermanently(@PathVariable long id) {
        abstractParameterDomainService.delete(id);
    }

}
