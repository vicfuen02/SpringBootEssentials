package com.springbootessentials.springbootessentials.infrastructure.adapter.input.rest.address;

import com.springbootessentials.springbootessentials.common.annotations.LogExecutionSPE;
import com.springbootessentials.springbootessentials.domain.address.Address;
import com.springbootessentials.springbootessentials.infrastructure.adapter.input.rest.address.dto.AddressReqDTO;
import com.springbootessentials.springbootessentials.infrastructure.adapter.input.rest.address.dto.AddressResDTO;
import com.springbootessentials.springbootessentials.infrastructure.adapter.input.rest.mapper.AddressRestControllerMapper;
import com.springbootessentials.springbootessentials.application.ports.input.address.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/address")
@RestController
@LogExecutionSPE
public class AddressRestController extends AddressExceptionHandler  {


    private AddressService addressService;
    private AddressRestControllerMapper addressRestControllerMapper;

    @Autowired
    public AddressRestController(AddressService addressService, AddressRestControllerMapper addressRestControllerMapper) {
        this.addressService = addressService;
        this.addressRestControllerMapper = addressRestControllerMapper;
    }


    @PreAuthorize("hasAnyRole('ADMIN')")
    @Cacheable(value={"/address"}, condition="#root.target.isCacheEnabled(#root.caches, #root.target.class.name, #root.method.name, #root.args)", key="#root.target.getCacheKey(#root.caches, #root.target.class.name, #root.method.name, #root.args)")
    @GetMapping
    public List<AddressResDTO> getAll() {
        List<Address> addressList = addressService.getAll();
        return addressRestControllerMapper.toResDTOList(addressList);
    }


    @PreAuthorize("hasAnyRole('ADMIN')")
    @CacheEvict(value={"/address/create"}, condition="#root.target.isCacheEnabled(#root.caches, #root.target.class.name, #root.method.name, #root.args)", allEntries=true)
    @PostMapping
    public Long createAddress(@RequestBody AddressReqDTO addressReqDTO) {
        return addressService.createAddress(addressRestControllerMapper.toBDTO(addressReqDTO));
    }


    @PreAuthorize("hasAnyRole('ADMIN')")
    @CacheEvict(value={"/address/{id}/update"}, condition="#root.target.isCacheEnabled(#root.caches, #root.target.class.name, #root.method.name, #root.args)", allEntries = true)
    @PutMapping("/{id}")
    public Long updateAddress(@PathVariable Long id, @RequestBody AddressReqDTO address) {

        address.setId(id);
        Address addressBDTO = this.addressRestControllerMapper.toBDTO(address);
        return this.addressService.updateAddress(addressBDTO);
    }


    @PreAuthorize("hasAnyRole('ADMIN')")
    @CacheEvict(value={"/address/delete/{id}"}, condition="#root.target.isCacheEnabled(#root.caches, #root.target.class.name, #root.method.name, #root.args)", allEntries=true)
    @DeleteMapping("/{id}")
    public Long deleteAddress(@PathVariable Long id) {
        return this.addressService.deleteAddress(id);
    }

}
