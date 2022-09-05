package com.example.kotlinwebflux.config.extend

import com.example.kotlinwebflux.config.MultiRoutingConnectionFactory
import io.r2dbc.spi.ConnectionFactory
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.stereotype.Component

enum class MultiRoutingType {
    READ,
    WRITE
}

annotation class MultiRouting constructor(
    val type: MultiRoutingType,
) {

}

@Aspect
@Component
class MultiRoutingAspect constructor(
    private val connectionFactory: ConnectionFactory,
) {

    @Around("@annotation(com.example.kotlinwebflux.config.extend.MultiRouting)")
    fun multiRouting(joinPoint: ProceedingJoinPoint): Any {

        val annotation = (joinPoint.signature as MethodSignature).method.getAnnotation(MultiRouting::class.java)

        // database type
        val databaseType = annotation.type.name

        val factory = (connectionFactory as MultiRoutingConnectionFactory)

        return joinPoint.proceed()
    }
}