package com.example.kotlinwebflux.web

import com.example.kotlinwebflux.domain.Item
import com.example.kotlinwebflux.service.ItemService
import com.example.kotlinwebflux.service.data.ItemDTO
import com.example.kotlinwebflux.service.data.ItemInfo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/items")
class ItemController
constructor(
    private val itemService: ItemService,
) {

    @PostMapping("")
    fun created(@RequestBody dto: ItemDTO) = itemService.created(dto)

    @GetMapping()
    fun getAll(): Flux<Item> = itemService.getAll()

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): Mono<Item> = itemService.get(id)

    @GetMapping("/name")
    fun get(name: String): Mono<ItemInfo> = itemService.get(name)
}