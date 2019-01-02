package com.example.entity

import javax.persistence.*

@Entity
@Table(name = "articles")
data class Article(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,
        var title: String? = null,
        @Lob
        var content: String? = null
) {
    @ManyToOne
    var account: Account? = null
}
