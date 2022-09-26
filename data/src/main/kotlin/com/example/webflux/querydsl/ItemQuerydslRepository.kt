package com.example.webflux.querydsl

import com.example.webflux.domain.Item
import com.example.webflux.domain.QItem.item
import com.infobip.spring.data.r2dbc.EnableQuerydslR2dbcRepositories
import com.infobip.spring.data.r2dbc.QuerydslR2dbcRepository
import com.querydsl.core.types.Projections
import reactor.core.publisher.Flux

@EnableQuerydslR2dbcRepositories
interface ItemQuerydslRepository : QuerydslR2dbcRepository<Item, Long> {

    fun <T> getAllBy(type: Class<T>): Flux<T> = this.query {
        it.select(
            Projections.fields(
                // Class<T> Type Add
                type,
                item.id,
                item.name,
                item.type,
                item.count,
                item.limitCount,
                item.createdAt
            )
        ).from(item)
    }
        .all()
}