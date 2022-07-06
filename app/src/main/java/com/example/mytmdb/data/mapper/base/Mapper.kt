package com.example.mytmdb.data.mapper.base

interface Mapper<in T,out D> {
    fun toDomain(data:T):D
}