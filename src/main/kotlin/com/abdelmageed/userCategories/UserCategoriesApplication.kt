package com.abdelmageed.userCategories

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import javax.naming.spi.DirectoryManager

@SpringBootApplication
class UserCategoriesApplication

fun main(args: Array<String>) {
    runApplication<UserCategoriesApplication>(*args)
}